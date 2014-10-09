package mobi.andromote.androcode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import mobi.andromote.androcode.antlr.AndroCodeLexer;
import mobi.andromote.androcode.antlr.AndroCodeParser;
import mobi.andromote.androcode.dataholder.TreeWithSymbolTable;
import mobi.andromote.androcode.exceptions.SemanticAnalysisException;
import mobi.andromote.androcode.logger.AndroLog;
import mobi.andromote.androcode.semanticAnalysis.SemanticAnalyser;
import mobi.andromote.androcode.symbolManagement.SymbolTable;
import mobi.andromote.androcode.utils.Utils;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * 
 * @author Sebastian Łuczak
 *
 */
public class AndroCodeCompilerFrontend {
	private static final AndroLog log = new AndroLog(AndroCodeCompilerFrontend.class.getSimpleName());
	SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
	ParseTree tree;

	/**
	 * Pozwala sprawdzić poprawność semantyczną programu dostarczonego pod postacią ciągu znaków
	 * @param script ciąg znaków zawierający kod programu
	 * @return true lub false, zaleznie od wyniku działania
	 */
	public boolean validateCode(String script) {
		InputStream inputStream = Utils.convertStringToStream(script);
		return tryToValidate(inputStream);
	}
	
	/**
	 * Pozwala sprawdzić poprawność semantyczną programu dostarczonego pod postacią ciągu znaków
	 * @param script strumień wejściowy zawierający kod programu
	 * @return true lub false, zaleznie od wyniku działania
	 */
	public boolean validateCode(InputStream inputStream) {
		return tryToValidate(inputStream);
	}

	/**
	 * pozwala sprawdzić poprawność semantyczną programu dostarczonego w pliku
	 * @param filename nazwa pliku z kodem programu
	 * @return true lub false, zależnie od wyniku działania
	 */
	public boolean validateFile(String filename) {
		InputStream inputStream = null;
		try {
			inputStream = getStreamFromFile(filename);
		} catch (IllegalArgumentException ex) {
			log.warn(ex.getMessage());
			log.info("Usage: androcode [filename]");
			log.info("where filename is the path to androcode script file to process");
			logProcessingFailed();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			logProcessingFailed();
		}
		return tryToValidate(inputStream);
	}

	/**
	 * Przeprowadza kolejno wszystkie bloki analizy 
	 * @param script obiekt typu String, zawierający kod źródłowy
	 * @return Obiekt agregujący drzewo wyprowadzeń i tablicę symboli
	 * @throws IOException
	 * @throws SemanticAnalysisException
	 */
	public TreeWithSymbolTable analyseCode(String script) throws IOException, SemanticAnalysisException {
		logProcessingStarted();
		InputStream inputStream = Utils.convertStringToStream(script);
		SymbolTable symbolTable = process(inputStream);
		return new TreeWithSymbolTable(tree, symbolTable);
	}
	
	/**
	 * Przeprowadza kolejno wszystkie bloki analizy 
	 * @param script strumień InputStream zawierający kod źródłowy
	 * @return Obiekt agregujący drzewo wyprowadzeń i tablicę symboli
	 * @throws IOException
	 * @throws SemanticAnalysisException
	 */
	public TreeWithSymbolTable analyseCode(InputStream inputStream) throws IOException, SemanticAnalysisException {
		logProcessingStarted();
		SymbolTable symbolTable = process(inputStream);
		return new TreeWithSymbolTable(tree, symbolTable);
	}

	/**
	 * Przeprowadza kolejno wszystkie bloki analizy 
	 * @param filename ścieżka do pliku z kodem źródłowym
	 * @return Obiekt agregujący drzewo wyprowadzeń i tablicę symboli
	 * @throws IOException
	 * @throws SemanticAnalysisException
	 */
	public TreeWithSymbolTable analyseCodeFromFile(String filename) throws SemanticAnalysisException {
		logProcessingStarted();
		TreeWithSymbolTable treeWithSymbolTable = null;
		try {
			InputStream inputStream = getStreamFromFile(filename);
			SymbolTable symbolTable = process(inputStream);
			treeWithSymbolTable = new TreeWithSymbolTable(tree, symbolTable);
		}  catch (SemanticAnalysisException e) {
			log.error(e.getMessage());
			log.debug(e.getStackTrace().toString());
			logProcessingFailed();
			throw new SemanticAnalysisException("Semantic analysis failed");
		} catch (IllegalArgumentException ex) {
			log.warn(ex.getMessage());
			log.info("Usage: androcode [filename]");
			log.info("where filename is the path to androcode script file to process");
			logProcessingFailed();
			throw new SemanticAnalysisException("Semantic analysis failed");
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			logProcessingFailed();
			throw new SemanticAnalysisException("Semantic analysis failed");
		}catch (IOException e) {
			log.error(e.getMessage());
			logProcessingFailed();
			throw new SemanticAnalysisException("Semantic analysis failed");
		}
		return treeWithSymbolTable;
	}

	private InputStream getStreamFromFile(String filename)
			throws FileNotFoundException, IllegalArgumentException {
		if ( filename != null) {
			log.info("Processing file " + filename);
		} else {
			throw new IllegalArgumentException("No file was specified to process!");
		}
		return new FileInputStream(filename);
	}

	private boolean tryToValidate(InputStream inputStream) {
		boolean result = false;
		try {
			logValidatingStarted();
			performAnalyserPass(inputStream);
			result = true;
			logValidatingDone();
		} catch (SemanticAnalysisException e) {
			log.error(e.getMessage());
			log.debug(e.getStackTrace().toString());
			logValidatingFailed();
		} catch (Exception e) {
			log.error(e.getMessage());
			log.debug(e.getStackTrace().toString());
			logValidatingFailed();
		}
		return result;
	}

	private SymbolTable process(InputStream inputStream) throws IOException, SemanticAnalysisException {
		SymbolTable symbolTable = performAnalyserPass(inputStream);
		logProcessingDone();
		return symbolTable;
	}

//	private SymbolTable validate(InputStream inputStream) throws IOException, SemanticAnalysisException {
//		InputStream streamAfterAutoPromotion = firstPass(inputStream);
//		SymbolTable secondPassResult = secondPass(streamAfterAutoPromotion);
//		SymbolTable symbolTable = performAnalyserPass(inputStream);
//		return symbolTable;
//	}
	
	private SymbolTable performAnalyserPass(InputStream inputStream) throws IOException, SemanticAnalysisException {
//		log.info("Processing pass...");
		CommonTokenStream tokens = createTokenStream(inputStream);
		tree = buildParserTree(tokens); 														// I. parsing
		SymbolTable symbolTable = semanticAnalyser.processAnalyserPass(tree, tokens); // II. Semantic analysis:
//		log.info("Pass done.");
		return symbolTable;
	}

//	private InputStream firstPass(InputStream inputStream) throws IOException, SemanticAnalysisException {
//		log.info("Processing first pass...");
//		CommonTokenStream tokens = createTokenStream(inputStream);
//		tree = buildParserTree(tokens); 														// I. parsing
//		String androCodeAfterAutoPromotion = semanticAnalyser.processFirstPass(tree, tokens);	// II. Semantic analysis:
//		log.info("First pass done.");
//		return Utils.convertStringToStream(androCodeAfterAutoPromotion);
//	}
//
//	private SymbolTable secondPass(InputStream streamAfterAutoPromotion) throws IOException, SemanticAnalysisException {
//		log.info("Processing second pass...");
//		CommonTokenStream tokens = createTokenStream(streamAfterAutoPromotion);
//		tree = buildParserTree(tokens); 														// I. parsing
//		SymbolTable symbolTable = semanticAnalyser.processLastPass(tree, tokens); // II. Semantic analysis:
//		log.info("Second pass done.");
//		return symbolTable;
//	}

	private ParseTree buildParserTree(CommonTokenStream tokens) {
		ParseTree tree;
		AndroCodeParser parser = new AndroCodeParser(tokens);
		parser.setBuildParseTree(true);
		tree = parser.script();
		return tree;
	}

	private CommonTokenStream createTokenStream(InputStream inputStream)
			throws IOException {
		ANTLRInputStream input = new ANTLRInputStream(inputStream);
		AndroCodeLexer lexer = new AndroCodeLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		return tokens;
	}

	private void logValidatingDone() {
		log.info("####################################");
		log.info("##  AndroCode validating is done  ##");
		log.info("####################################");
	}

	private void logValidatingStarted() {
		log.info("####################################");
		log.info("##  AndroCode validating started  ##");
		log.info("####################################");
	}

	private void logValidatingFailed() {
		log.info("##############!!!!!!!!!!#############");
		log.info("!!!  AndroCode validating failed  !!!");
		log.info("##############!!!!!!!!!!#############");
	}

	private void logProcessingDone() {
		log.info("####################################");
		log.info("##  AndroCode processing is done  ##");
		log.info("####################################");
	}

	private void logProcessingStarted() {
		log.info("####################################");
		log.info("##  AndroCode processing started  ##");
		log.info("####################################");
	}

	private void logProcessingFailed() {
		log.info("##############!!!!!!!!!!#############");
		log.info("!!!  AndroCode processing failed  !!!");
		log.info("##############!!!!!!!!!!#############");
	}
}
