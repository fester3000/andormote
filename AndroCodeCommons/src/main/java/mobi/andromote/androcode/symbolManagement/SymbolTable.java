package mobi.andromote.androcode.symbolManagement;

import mobi.andromote.androcode.scopeManagement.GlobalScope;
import mobi.andromote.androcode.scopeManagement.Scope;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

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
