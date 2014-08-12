package pl.fester3k.androcode.semanticAnalysis;

import lombok.Getter;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.Scope;

public class SymbolTable {
	@Getter private final GlobalScope globals;
	@Getter private final ParseTreeProperty<Scope> scopes;
	
	public SymbolTable(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
		super();
		this.globals = globals;
		this.scopes = scopes;
	}
}
