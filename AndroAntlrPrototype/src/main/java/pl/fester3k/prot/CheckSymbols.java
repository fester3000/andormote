package pl.fester3k.prot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pl.fester3k.antlr.androCode.AndroCodeLexer;
import pl.fester3k.antlr.androCode.AndroCodeParser;
import pl.fester3k.antlr.scopeManagement.Symbol;
import pl.fester3k.antlr.scopeManagement.Symbol.Type;

public class CheckSymbols {
	public static Symbol.Type getType(int tokenType) {
		Symbol.Type result;
		switch(tokenType) {
		case AndroCodeParser.K_INT_TYPE: 
			result = Type.tINT;
			break;
		case AndroCodeParser.K_VOID_TYPE: 
			result = Type.tVOID;
			break;
		case AndroCodeParser.K_STRING_TYPE:
			result = Type.tSTRING;
			break;
		case AndroCodeParser.K_DEV_TYPE:
			result = Type.tDEVICE;
			break;
		case AndroCodeParser.K_BOOLEAN_TYPE:
			result = Type.tBOOLEAN;
			break;
		default:
			result = Type.tINVALID;
		}
		return result;
	}

	public void process(String[] args) throws IOException {
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

		ParseTreeWalker walker = new ParseTreeWalker();
		DefinePhase definePhase = new DefinePhase();
		walker.walk(definePhase, tree);
		
		ResolvePhase resolvePhase = new ResolvePhase(definePhase.getGlobals(), definePhase.getScopes());
		walker.walk(resolvePhase, tree);
	}
}
