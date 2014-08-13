package pl.fester3k.androcode.symbolManagement;

import pl.fester3k.androcode.antlr.enums.Type;
import pl.fester3k.androcode.scopeManagement.Scope;

/***
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
***/
public class Symbol { // A generic programming language symbol	
	protected final String name;      // All symbols at least have a name
    protected Type type;
    private Scope scope;      // All symbols know what scope contains them.

    public Symbol(String name) { 
    	this.name = name; 
    }
    
    public Symbol(String name, Type type) { 
    	this(name); this.type = type; 
    }
    
    public String toString() {
        if ( type!=null ) return '<'+name+":"+type+'>';
        return name;
    }

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}
}
