package pl.fester3k.androcode.symbolManagement;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.androcode.scopeManagement.GlobalScope;
import pl.fester3k.androcode.scopeManagement.Scope;

public class SymbolTable {
	private final GlobalScope globals;
	private final ParseTreeProperty<Scope> scopes;
	
	public SymbolTable(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
		super();
		this.globals = globals;
		this.scopes = scopes;
	}

	public GlobalScope getGlobals() {
		return globals;
	}

	public ParseTreeProperty<Scope> getScopes() {
		return scopes;
	}
}
