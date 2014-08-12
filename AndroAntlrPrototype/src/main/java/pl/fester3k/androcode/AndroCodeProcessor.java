package pl.fester3k.androcode;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.print.attribute.standard.PrinterResolution;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import pl.fester3k.androcode.antlr.AndroCodeLexer;
import pl.fester3k.androcode.antlr.AndroCodeParser;
import pl.fester3k.androcode.interpreter.Interpreter;
import pl.fester3k.androcode.semanticAnalysis.SemanticAnalyser;
import pl.fester3k.androcode.semanticAnalysis.SymbolTable;
import pl.fester3k.prot.exceptions.SemanticAnalysisException;
import pl.fester3k.prot.utils.AndroLog;

public class AndroCodeProcessor {
	private static final AndroLog log = new AndroLog(AndroCodeProcessor.class.getSimpleName());
	SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
	Interpreter interpreter = new Interpreter();
	ParseTree tree;

	public void process(String[] args) throws IOException {
		log.info("####################################");
		log.info("##  AndroCode processing started  ##");
		log.info("####################################");
		try {
		InputStream streamAfterAutoPromotion = processProgramArguments(args); 
		streamAfterAutoPromotion = firstPass(streamAfterAutoPromotion);
		SymbolTable secondPassResult = secondPass(streamAfterAutoPromotion);
		interpret(secondPassResult);
		log.info("####################################");
		log.info("##  AndroCode processing is done  ##");
		log.info("####################################");
		} catch (IllegalArgumentException ex) {
			log.warn(ex.getMessage());
			log.info("Usage: androcode [filename]");
			log.info("where filename is the path to androcode script file to process");
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (SemanticAnalysisException e) {
			log.error(e.getMessage());
			log.debug(e.getStackTrace().toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			log.debug(e.getStackTrace().toString());
		} finally {
			log.info("##############!!!!!!!!!!#############");
			log.info("!!!  AndroCode processing failed  !!!");
			log.info("##############!!!!!!!!!!#############");
		}
	}
	
	private InputStream processProgramArguments(String[] args)
			throws FileNotFoundException, IllegalArgumentException {
		String filename = null;
		if ( args.length>0 ) {
			filename = args[0];
			log.info("Processing file " + args[0]);
		} else {
			throw new IllegalArgumentException("No file was specified to process!");
		}
		InputStream inputStream;
		if ( filename!=null ) {
			inputStream = new FileInputStream(filename);
		} else {
			throw new FileNotFoundException("File " + filename + " not found");
		}
		return inputStream;
	}

	private InputStream firstPass(InputStream inputStream) throws IOException, SemanticAnalysisException {
		log.info("Processing first pass...");
		CommonTokenStream tokens = createTokenStream(inputStream);
		tree = buildParserTree(tokens); 														// I. parsing
		String androCodeAfterAutoPromotion = semanticAnalyser.processFirstPass(tree, tokens);	// II. Semantic analysis:
		log.info("First pass done.");
		return convertStringToStream(androCodeAfterAutoPromotion);
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

	private InputStream convertStringToStream(String androCode) {
		InputStream inputStream;
		byte[] bArray = androCode.getBytes();
		inputStream = new ByteArrayInputStream(bArray);
		return inputStream;
	}
}
