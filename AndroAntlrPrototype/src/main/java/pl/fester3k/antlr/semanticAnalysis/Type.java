package pl.fester3k.antlr.semanticAnalysis;

import org.antlr.v4.runtime.tree.ParseTree;

import pl.fester3k.antlr.androCode.AndroCodeParser;

public enum Type {
	USER(0), BOOLEAN(1), CHAR(2), INT(3), FLOAT(4), VOID(5), STRING(0), DEVICE(0), NULL(-1), INVALID(0);
	
	public int priority;
	
	public static final Type[][] arithmeticResultType = new Type[][] {
		/*				otherTypes	boolean	char	int		float	void */
		/*otherTypes*/	{VOID,		VOID,	VOID,	VOID,	VOID,	VOID},
		/*boolean	*/	{VOID,		VOID,	VOID,	VOID,	VOID,	VOID},
		/*char		*/	{VOID,		VOID,	CHAR, 	INT,	FLOAT,	VOID},
		/*int		*/	{VOID,		VOID,	INT,	INT,	FLOAT,	VOID},
		/*float		*/	{VOID,		VOID,	FLOAT,	FLOAT,	FLOAT,  VOID},
		/*void		*/	{VOID,		VOID,	VOID,	VOID,	VOID, 	VOID}
	};
	
	public static final Type[][] equalityResultType = new Type[][] {
		/*				otherTypes	boolean	char	int		float	void */
		/*otherTypes*/	{VOID,		VOID,	VOID,	VOID,	VOID,	VOID},
		/*boolean	*/	{VOID,		BOOLEAN,VOID,	VOID,	VOID,	VOID},
		/*char		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID},
		/*int		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID},
		/*float		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID},
		/*void		*/	{VOID,		VOID,	VOID,	VOID,	VOID, 	VOID}
	};
	
	public static final Type[][] relationalResultType = new Type[][] {
		/*				otherTypes	boolean	char	int		float	void */
		/*otherTypes*/	{VOID,		VOID,	VOID,	VOID,	VOID,	VOID},
		/*boolean	*/	{VOID,		VOID,	VOID,	VOID,	VOID,	VOID},
		/*char		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID},
		/*int		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID},
		/*float		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID},
		/*void		*/	{VOID,		VOID,	VOID,	VOID,	VOID, 	VOID}
	};
	
	
	public static final Type[][] promoteFromTo = new Type[][] {
		/*				otherTypes	boolean	char	int		float	void */
		/*otherTypes*/	{NULL,		NULL,	NULL,	NULL,	NULL,	NULL},
		/*boolean	*/	{NULL,		NULL,	NULL,	NULL,	NULL,	NULL},
		/*char		*/	{NULL,		NULL,	NULL, 	INT,	FLOAT,	NULL},
		/*int		*/	{NULL,		NULL,	NULL,	NULL,	FLOAT,	NULL},
		/*float		*/	{NULL,		NULL,	NULL,	NULL,	NULL, 	NULL},
		/*void		*/	{NULL,		NULL,	NULL,	NULL,	NULL, 	NULL}
	};
	
	private Type(int priority) {
		this.priority = priority;
	}
	
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
