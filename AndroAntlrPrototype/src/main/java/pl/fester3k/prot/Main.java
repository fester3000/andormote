package pl.fester3k.prot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import pl.fester3k.antlr.androCode.AndroCodeLexer;
import pl.fester3k.antlr.androCode.AndroCodeParser;
import pl.fester3k.antlr.semanticAnalysis.symbols.SemanticAnalyser;

public class Main {
	
	public static void main(String[] args) throws Exception {
		ParseTree tree = initParser(args);	// I. parsing
		new SemanticAnalyser().process(tree);	// II. Semantic analysis:
											// III. Interpreting
											//1. tree-based interpreter
	}
	
	private static ParseTree initParser(String[] args) throws IOException {
		String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) {
            is = new FileInputStream(inputFile);
        }
        ANTLRInputStream input = new ANTLRInputStream(is);
		AndroCodeLexer lexer = new AndroCodeLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		AndroCodeParser parser = new AndroCodeParser(tokens);
		parser.setBuildParseTree(true);
		ParseTree tree = parser.script();
		
		return tree;
	}
}
