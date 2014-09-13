package mobi.andromote.androcode.antlr.enums;

import mobi.andromote.androcode.antlr.AndroCodeParser;

public enum Type {
	USER(0, "userDef"), BOOLEAN(1, "bool"), CHAR(2, "char"), INT(3, "int"), 
	FLOAT(4, "float"), VOID(5, "void"), STRING(0, "String"), DEVICE(0, "device"), 
	NULL(-1, "null"), INVALID(0, "INVALID"), OBJECT(6, "object");
	
	public int index;

	private String typename;
	
	public static final Type[][] arithmeticResultType = new Type[][] {
		/*				otherTypes	boolean	char	int		float	void    object*/
		/*otherTypes*/	{VOID,		VOID,	VOID,	VOID,	VOID,	VOID,	VOID},
		/*boolean	*/	{VOID,		VOID,	VOID,	VOID,	VOID,	VOID,	VOID},
		/*char		*/	{VOID,		VOID,	CHAR, 	INT,	FLOAT,	VOID, 	VOID},
		/*int		*/	{VOID,		VOID,	INT,	INT,	FLOAT,	VOID,	VOID},
		/*float		*/	{VOID,		VOID,	FLOAT,	FLOAT,	FLOAT,  VOID,	VOID},
		/*void		*/	{VOID,		VOID,	VOID,	VOID,	VOID, 	VOID,	VOID},
		/*object	*/	{VOID,		VOID,	VOID,	VOID,	VOID, 	VOID,	VOID},
	};
	
	public static final Type[][] equalityResultType = new Type[][] {
		/*				otherTypes	boolean	char	int		float	void 	object*/
		/*otherTypes*/	{VOID,		VOID,	VOID,	VOID,	VOID,	VOID,	VOID},
		/*boolean	*/	{VOID,		BOOLEAN,VOID,	VOID,	VOID,	VOID,	VOID},
		/*char		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID,	VOID},
		/*int		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID,	VOID},
		/*float		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID,	VOID},
		/*void		*/	{VOID,		VOID,	VOID,	VOID,	VOID, 	VOID,	VOID},
		/*object	*/	{VOID,		VOID,	VOID,	VOID,	VOID, 	VOID,	VOID},
	};
	
	public static final Type[][] relationalResultType = new Type[][] {
		/*				otherTypes	boolean	char	int		float	void 	object*/
		/*otherTypes*/	{VOID,		VOID,	VOID,	VOID,	VOID,	VOID,	VOID},
		/*boolean	*/	{VOID,		VOID,	VOID,	VOID,	VOID,	VOID,	VOID},
		/*char		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID,	VOID},
		/*int		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID,	VOID},
		/*float		*/	{VOID,		VOID,	BOOLEAN,BOOLEAN,BOOLEAN,VOID,	VOID},
		/*void		*/	{VOID,		VOID,	VOID,	VOID,	VOID, 	VOID,	VOID},
		/*object	*/	{VOID,		VOID,	VOID,	VOID,	VOID, 	VOID,	VOID},
	};
	
	
	public static final Type[][] promoteFromTo = new Type[][] {
		/*				otherTypes	boolean	char	int		float	void 	object*/
		/*otherTypes*/	{null,		null,	null,	null,	null,	null,	null},
		/*boolean	*/	{null,		null,	null,	null,	null,	null,	null},
		/*char		*/	{null,		null,	null, 	INT,	FLOAT,	null,	null},
		/*int		*/	{null,		null,	null,	null,	FLOAT,	null,	null},
		/*float		*/	{null,		null,	null,	null,	null, 	null,	null},
		/*void		*/	{null,		null,	null,	null,	null, 	null,	null},
		/*object	*/	{STRING,	BOOLEAN,CHAR,	INT,	FLOAT, 	null,	null}
	};
	
	private Type(int priority, String typeName) {
		this.index = priority;
		this.typename = typeName;
	}
	
	public static Type getTypeByTokenType(int tokenType) {
		Type result;
		switch(tokenType) {
		case AndroCodeParser.K_INT_TYPE: 
			result = INT;
			break;
		case AndroCodeParser.INT:
			result = INT;
			break;
		case AndroCodeParser.NEGATED_INT:
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
		case AndroCodeParser.NEGATED_FLOAT:
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

	@Override
	public String toString() {
		return typename;
	}
}
