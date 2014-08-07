package pl.fester3k.prot.utils;

import java.util.Stack;

import pl.fester3k.antlr.interpreter.memory.MemorySpace;
import pl.fester3k.antlr.semanticAnalysis.Type;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Symbol;

import com.google.common.base.Strings;

public class Utils {	
	public static Type getTypeFromSymbol(String id, Scope startingScope) {
		Type result = Type.INVALID;
		if(!Strings.isNullOrEmpty(id)) {
			Symbol s = startingScope.resolve(id);
			if(s != null) {
				result = s.getType();
			} 
		}
		return result;
	}
	
	public static Symbol getSymbolById(String id, Scope startingScope) {
		Symbol result = null;
		if(!Strings.isNullOrEmpty(id)) {
			result = startingScope.resolve(id);
		}
		return result;
	}
}
