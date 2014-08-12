package pl.fester3k.prot.utils;

import pl.fester3k.androcode.antlr.enums.Type;
import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.Symbol;

public class Utils {	
	public static Type getTypeFromSymbol(String id, Scope startingScope) {
		Type result = Type.INVALID;
		if(isNullOrEmpty(id)) {
			Symbol s = startingScope.resolve(id);
			if(s != null) {
				result = s.getType();
			} 
		}
		return result;
	}
	
	public static Symbol getSymbolById(String id, Scope startingScope) {
		Symbol result = null;
		if(isNullOrEmpty(id)) {
			result = startingScope.resolve(id);
		}
		return result;
	}

	private static boolean isNullOrEmpty(String id) {
		return id != null || id.isEmpty();
	}
}
