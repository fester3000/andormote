package pl.fester3k.antlr;

import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import pl.fester3k.antlr.expr.ExprLexer;
import pl.fester3k.antlr.expr.ExprParser;

public class ExprJoyRide {

		public static void main(String[] args) throws Exception {
			String inputFile = null;
			if(args.length > 0) inputFile = args[0];
			InputStream is = System.in;
			if( inputFile != null ) 
				is = new FileInputStream(inputFile);
			ANTLRInputStream input = new ANTLRInputStream(is);
			ExprLexer lexer = new ExprLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ExprParser parser = new ExprParser(tokens);
			ParseTree tree = parser.prog();
			
			EvalVisitor eval = new EvalVisitor();
			eval.visit(tree);
		}
}
