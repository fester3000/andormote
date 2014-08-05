package pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement;
/***
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
***/
import java.util.LinkedHashMap;
import java.util.Map;

import pl.fester3k.antlr.semanticAnalysis.Type;

import com.google.common.base.Strings;

public abstract class BaseScope implements Scope {
	Scope enclosingScope; // null if global (outermost) scope
	Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>();

    public BaseScope(Scope enclosingScope) { 
    	this.enclosingScope = enclosingScope;	
    }

    public Symbol resolve(String name) {
		Symbol s = symbols.get(name);
        if ( s!=null ) 
        	return s;
		// if not here, check any enclosing scope
		if ( enclosingScope != null ) 
			return enclosingScope.resolve(name);
		return null; // not found
	}
//    
//	public void resolveByIdIfNotNull(String name) {
//		if(!Strings.isNullOrEmpty(name)) {
//			Symbol s = resolve(name);
//			if(s != null) {
//				System.out.println("++ Symbol " + s.getType() + " " + s.getName() +" ok");
//			} else {
//				System.err.println("----- !Symbol " + name + " not declared!");
//			}
//		}
//	}
//    
//	public Type getTypeFromSymbol(String id) {
//		if(!Strings.isNullOrEmpty(id)) {
//			Symbol s = resolve(id);
//			if(s != null) {
//				System.out.println("@@ Symbol " + s.getType() + " " + s.getName());
//				return s.getType();
//			} else {
//				System.err.println("----- !Symbol " + id + " not declared!");
//			}
//		}
//		return Type.INVALID;
//	}

	public void define(Symbol sym) {
		symbols.put(sym.name, sym);
		sym.scope = this; // track the scope in each symbol
	}

    public Scope getEnclosingScope() { return enclosingScope; }

	public String toString() { 
		return getScopeName() + " " + symbols.keySet().toString(); 
	}
}
