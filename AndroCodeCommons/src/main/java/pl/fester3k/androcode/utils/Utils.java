package pl.fester3k.androcode.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import pl.fester3k.androcode.antlr.enums.Type;
import pl.fester3k.androcode.scopeManagement.Scope;
import pl.fester3k.androcode.symbolManagement.Symbol;

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
	
	public static InputStream convertStringToStream(String androCode) {
		InputStream inputStream;
		byte[] bArray = androCode.getBytes();
		inputStream = new ByteArrayInputStream(bArray);
		return inputStream;
	}

	private static boolean isNullOrEmpty(String id) {
		return id != null || id.isEmpty();
	}

	public static String getStringOutOfQuotes(String text) {
		if(text.endsWith("\"") && text.startsWith("\"")) {
			text = text.substring(1, text.length() - 1);
		}
		return text;
	}	
}
