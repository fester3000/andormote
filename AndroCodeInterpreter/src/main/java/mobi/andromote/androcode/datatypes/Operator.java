package mobi.andromote.androcode.datatypes;

import mobi.andromote.androcode.antlr.AndroCodeLexer;

public enum Operator {
	INVALID_OP, ADD_OP, MINUS_OP, MULT_OP, DEV_OP, INCR_OP, DECR_OP, EQ_OP, NOT_EQ_OP, GT_OP, LT_OP, GTEQ_OP, LTEQ_OP, AND_OP, OR_OP;
	
	public static Operator getOperatorByTokenType(int tokenType) {
		Operator result = INVALID_OP;
		switch(tokenType) {
			case AndroCodeLexer.ADD_OP: 
				result = ADD_OP;
				break;
			case AndroCodeLexer.MINUS_OP: 
				result = MINUS_OP;
				break;
			case AndroCodeLexer.MULT_OP: 
				result = MULT_OP;
				break;
			case AndroCodeLexer.DEV_OP: 
				result = DEV_OP;
				break;
			case AndroCodeLexer.INCR_OP: 
				result = INCR_OP;
				break;
			case AndroCodeLexer.DECR_OP: 
				result = DECR_OP;
				break;
			case AndroCodeLexer.EQ_OP: 
				result = EQ_OP;
				break;
			case AndroCodeLexer.NOT_EQ_OP: 
				result = NOT_EQ_OP;
				break;
			case AndroCodeLexer.GT_OP: 
				result = GT_OP;
				break;
			case AndroCodeLexer.LT_OP: 
				result = LT_OP;
				break;
			case AndroCodeLexer.GTEQ_OP: 
				result = GTEQ_OP;
				break;
			case AndroCodeLexer.LTEQ_OP: 
				result = LTEQ_OP;
				break;
			case AndroCodeLexer.AND_OP:
				result = AND_OP;
				break;
			case AndroCodeLexer.OR_OP:
				result = OR_OP;
				break;
		}
		return result;
	}
	
}
