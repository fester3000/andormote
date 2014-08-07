package pl.fester3k.antlr.semanticAnalysis;

import lombok.Getter;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;

public class SemanticAnalysisResult {
	@Getter private final GlobalScope globals;
	@Getter private final ParseTreeProperty<Scope> scopes;
	@Getter private final ParseTreeProperty<Type> types;
	
	public SemanticAnalysisResult(GlobalScope globals,
			ParseTreeProperty<Scope> scopes, ParseTreeProperty<Type> types) {
		super();
		this.globals = globals;
		this.scopes = scopes;
		this.types = types;
	}
}
