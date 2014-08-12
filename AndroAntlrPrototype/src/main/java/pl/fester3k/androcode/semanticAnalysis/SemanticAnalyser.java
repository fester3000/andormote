package pl.fester3k.androcode.semanticAnalysis;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pl.fester3k.androcode.semanticAnalysis.symbols.DefinePhase;
import pl.fester3k.androcode.semanticAnalysis.symbols.ResolvePhase;
import pl.fester3k.androcode.semanticAnalysis.typeCheck.StaticTypeComputingPhase;
import pl.fester3k.androcode.semanticAnalysis.typeCheck.TypeCheckingPhase;
import pl.fester3k.prot.exceptions.SemanticAnalysisException;

public class SemanticAnalyser {
	private ParseTreeWalker walker;
	private DefinePhase definePhase;
	private ResolvePhase resolvePhase;
	private StaticTypeComputingPhase typeComputingPhase;
	private TypeCheckingPhase typeCheckingPhase;

	public SemanticAnalyser() {
		walker = new ParseTreeWalker();
	}

	public String processFirstPass(ParseTree tree, TokenStream tokens) throws SemanticAnalysisException {
		commonAnalysisSteps(tree, tokens);
		typeCheckingPhase(tree, tokens);
		return typeCheckingPhase.getRewriter().getText();
	}

	public SymbolTable processLastPass(ParseTree tree, TokenStream tokens) throws SemanticAnalysisException {
		commonAnalysisSteps(tree, tokens);
		return new SymbolTable(definePhase.getGlobals(), definePhase.getScopes());
	}

	private void commonAnalysisSteps(ParseTree tree, TokenStream tokens) throws SemanticAnalysisException {
		definePhase = new DefinePhase();
		walker.walk(definePhase, tree);//1. building symbol table

		resolvePhase = new ResolvePhase(definePhase.getGlobals(), definePhase.getScopes());
		walker.walk(resolvePhase, tree);// 2. checking references
		if(resolvePhase.isError()) {
			throw new SemanticAnalysisException("Errors found during resolve phase");
		}

		typeComputingPhase = new StaticTypeComputingPhase(definePhase.getGlobals(), definePhase.getScopes(), tokens);
		walker.walk(typeComputingPhase, tree);
	}
	
	private void typeCheckingPhase(ParseTree tree, TokenStream tokens) throws SemanticAnalysisException {
		typeCheckingPhase = new TypeCheckingPhase(definePhase.getGlobals(), definePhase.getScopes(), typeComputingPhase.getTypes(), tokens);
		walker.walk(typeCheckingPhase, tree);
		if(typeCheckingPhase.isError()) {
			throw new SemanticAnalysisException("Errors found during resolve phase");
		}

	}
}
