package pl.fester3k.antlr.semanticAnalysis;

import pl.fester3k.antlr.androCode.AndroCodeParser;

public enum Type {
	VOID, INT, FLOAT, CHAR, BOOLEAN, STRING, DEVICE, NULL, INVALID;
	
	public static Type getType(int tokenType) {
		Type result;
		switch(tokenType) {
		case AndroCodeParser.K_INT_TYPE: 
			result = INT;
			break;
		case AndroCodeParser.INT:
			result = INT;
			break;
		case AndroCodeParser.K_CHAR_TYPE: 
			result = CHAR;
			break;
		case AndroCodeParser.CHAR:
			result = CHAR;
			break;
		case AndroCodeParser.K_FLOAT_TYPE: 
			result = FLOAT;
			break;
		case AndroCodeParser.FLOAT:
			result = FLOAT;
			break;
		case AndroCodeParser.K_VOID_TYPE: 
			result = VOID;
			break;
		case AndroCodeParser.K_STRING_TYPE:
			result = STRING;
			break;
		case AndroCodeParser.STRING:
			result = STRING;
			break;
		case AndroCodeParser.K_DEV_TYPE:
			result = DEVICE;
			break;
		case AndroCodeParser.K_BOOLEAN_TYPE:
			result = BOOLEAN;
			break;
		case AndroCodeParser.BOOLEAN:
			result = BOOLEAN;
			break;
		default:
			result = INVALID;
		}
		return result;
	}
}
