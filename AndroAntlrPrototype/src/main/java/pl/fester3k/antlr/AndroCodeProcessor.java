package pl.fester3k.antlr;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import pl.fester3k.antlr.androCode.AndroCodeLexer;
import pl.fester3k.antlr.androCode.AndroCodeParser;
import pl.fester3k.antlr.interpreter.Interpreter;
import pl.fester3k.antlr.semanticAnalysis.SemanticAnalyser;
import pl.fester3k.antlr.semanticAnalysis.SemanticAnalysisResult;

public class AndroCodeProcessor {
	SemanticAnalyser semanticAnalyser = new SemanticAnalyser();

	public void process(String[] args) throws IOException {
		InputStream streamAfterAutoPromotion = processProgramArguments(args);
		streamAfterAutoPromotion = firstPass(streamAfterAutoPromotion);
		secondPass(streamAfterAutoPromotion);
	}

	private InputStream firstPass(InputStream inputStream) throws IOException {
		CommonTokenStream tokens = createTokenStream(inputStream);
		ParseTree tree = buildParserTree(tokens); 												// I. parsing
		String androCodeAfterAutoPromotion = semanticAnalyser.processFirstPass(tree, tokens);	// II. Semantic analysis:
		return convertStringToStream(androCodeAfterAutoPromotion);
	}
	
	private void secondPass(InputStream streamAfterAutoPromotion) throws IOException {
		CommonTokenStream tokens = createTokenStream(streamAfterAutoPromotion);
		ParseTree tree = buildParserTree(tokens); 														// I. parsing
		SemanticAnalysisResult semanticAnalysisResult = semanticAnalyser.processLastPass(tree, tokens); // II. Semantic analysis:
		Interpreter interpreter = new Interpreter(semanticAnalysisResult);								// III. Interpreting
		interpreter.interpret(tree);

	}

	private InputStream processProgramArguments(String[] args)
			throws FileNotFoundException {
		String inputFile = null;
		if ( args.length>0 ) {
			inputFile = args[0];
		}
		InputStream inputStream = System.in;
		if ( inputFile!=null ) {
			inputStream = new FileInputStream(inputFile);
		}
		return inputStream;
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
