package pl.fester3k.antlr.semanticAnalysis.symbols;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pl.fester3k.antlr.semanticAnalysis.typeCheck.TypeCheckingPhase;

public class SemanticAnalyser {
	public void process(ParseTree tree) {
		ParseTreeWalker walker = new ParseTreeWalker();
		DefinePhase definePhase = new DefinePhase();
		walker.walk(definePhase, tree);//1. building symbol table
		
		ResolvePhase resolvePhase = new ResolvePhase(definePhase.getGlobals(), definePhase.getScopes());
		walker.walk(resolvePhase, tree);// 2. checking references
		
		TypeCheckingPhase typeChecking = new TypeCheckingPhase(definePhase.getGlobals(), definePhase.getScopes());
		walker.walk(typeChecking, tree);
		//3. type checking
		////a) computing static expression types
		////b) automatic type promotion
		////c) enforcing static type safety
	}
}
