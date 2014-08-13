package pl.fester3k.androcode;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import pl.fester3k.androcode.antlr.AndroCodeLexer;
import pl.fester3k.androcode.antlr.AndroCodeParser;
import pl.fester3k.androcode.exceptions.SemanticAnalysisException;
import pl.fester3k.androcode.interpreter.Interpreter;
import pl.fester3k.androcode.logger.AndroLog;
import pl.fester3k.androcode.semanticAnalysis.SemanticAnalyser;
import pl.fester3k.androcode.semanticAnalysis.SymbolTable;
import pl.fester3k.androcode.utils.Utils;

public class AndroCodeProcessor {
	private final AndroLog log;
	SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
	Interpreter interpreter = new Interpreter();
	ParseTree tree;

	public AndroCodeProcessor() {
		this.log = new AndroLog(AndroCodeProcessor.class.getSimpleName()); 
	}

	public void processCode(String script) {
		logProcessingStarted();
		InputStream inputStream = Utils.convertStringToStream(script);
		tryToProcess(inputStream);
	}

	public void processFile(String filename) throws IOException {
		logProcessingStarted();
		try {
			InputStream inputStream = getStreamFromFile(filename);
			tryToProcess(inputStream);
		} catch (IllegalArgumentException ex) {
			log.warn(ex.getMessage());
			log.info("Usage: androcode [filename]");
			log.info("where filename is the path to androcode script file to process");
			logProcessingFailed();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			logProcessingFailed();
		}
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
	
	private void tryToProcess(InputStream inputStream) {
		try {
			InputStream streamAfterAutoPromotion = firstPass(inputStream);
			SymbolTable secondPassResult = secondPass(streamAfterAutoPromotion);
			interpret(secondPassResult);
			logProcessingDone();
		} catch (IllegalArgumentException ex) {
			log.warn(ex.getMessage());
			log.info("Usage: androcode [filename]");
			log.info("where filename is the path to androcode script file to process");
			logProcessingFailed();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			logProcessingFailed();
		} catch (SemanticAnalysisException e) {
			log.error(e.getMessage());
			log.debug(e.getStackTrace().toString());
			logProcessingFailed();
		} catch (Exception e) {
			log.error(e.getMessage());
			log.debug(e.getStackTrace().toString());
			logProcessingFailed();
		}
	}

	private InputStream firstPass(InputStream inputStream) throws IOException, SemanticAnalysisException {
		log.info("Processing first pass...");
		CommonTokenStream tokens = createTokenStream(inputStream);
		tree = buildParserTree(tokens); 														// I. parsing
		String androCodeAfterAutoPromotion = semanticAnalyser.processFirstPass(tree, tokens);	// II. Semantic analysis:
		log.info("First pass done.");
		return Utils.convertStringToStream(androCodeAfterAutoPromotion);
	}

	private SymbolTable secondPass(InputStream streamAfterAutoPromotion) throws IOException, SemanticAnalysisException {
		log.info("Processing second pass...");
		CommonTokenStream tokens = createTokenStream(streamAfterAutoPromotion);
		tree = buildParserTree(tokens); 														// I. parsing
		SymbolTable symbolTable = semanticAnalyser.processLastPass(tree, tokens); // II. Semantic analysis:
		log.info("Second pass done.");
		return symbolTable;
	}

	private void interpret(SymbolTable symbolTable) {
		log.info("Interpreting...");
		interpreter.interpret(tree, symbolTable);								// III. Interpreting
		log.info("Interpreting done.");
	}



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
