package pl.fester3k.antlr.semanticAnalysis;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pl.fester3k.antlr.semanticAnalysis.symbols.DefinePhase;
import pl.fester3k.antlr.semanticAnalysis.symbols.ResolvePhase;
import pl.fester3k.antlr.semanticAnalysis.typeCheck.StaticTypeComputingPhase;
import pl.fester3k.antlr.semanticAnalysis.typeCheck.TypeCheckingPhase;

public class SemanticAnalyser {
	private ParseTreeWalker walker;
	private DefinePhase definePhase;
	private ResolvePhase resolvePhase;
	private StaticTypeComputingPhase typeComputingPhase;
	private TypeCheckingPhase typeCheckingPhase;

	public SemanticAnalyser() {
		walker = new ParseTreeWalker();
	}

	public String processFirstPass(ParseTree tree, TokenStream tokens) {
		commonAnalysisSteps(tree, tokens);
		typeCheckingPhase(tree, tokens);
		return typeCheckingPhase.getRewriter().getText();
	}

	public SemanticAnalysisResult processLastPass(ParseTree tree, TokenStream tokens) {
		commonAnalysisSteps(tree, tokens);
		return new SemanticAnalysisResult(definePhase.getGlobals(), definePhase.getScopes(), typeComputingPhase.getTypes());
	}

	private void typeCheckingPhase(ParseTree tree, TokenStream tokens) {
		typeCheckingPhase = new TypeCheckingPhase(definePhase.getGlobals(), definePhase.getScopes(), typeComputingPhase.getTypes(), tokens);
		walker.walk(typeCheckingPhase, tree);
	}

	private void commonAnalysisSteps(ParseTree tree, TokenStream tokens) {
		definePhase = new DefinePhase();
		walker.walk(definePhase, tree);//1. building symbol table

		resolvePhase = new ResolvePhase(definePhase.getGlobals(), definePhase.getScopes());
		walker.walk(resolvePhase, tree);// 2. checking references

		typeComputingPhase = new StaticTypeComputingPhase(definePhase.getGlobals(), definePhase.getScopes(), tokens);
		walker.walk(typeComputingPhase, tree);
	}
}
