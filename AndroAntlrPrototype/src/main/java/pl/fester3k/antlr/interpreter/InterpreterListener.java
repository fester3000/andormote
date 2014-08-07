package pl.fester3k.antlr.interpreter;

import java.util.Stack;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.antlr.androCode.AndroCodeParser.AssignmentContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Condition_equalityContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Condition_relationalContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ExprContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_castContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_incr_decrContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_valueContext;
import pl.fester3k.antlr.androCode.listeners.AndroCodeListenerWithScopes;
import pl.fester3k.antlr.interpreter.memory.FunctionSpace;
import pl.fester3k.antlr.interpreter.memory.MemorySpace;
import pl.fester3k.antlr.interpreter.tokens.Operator;
import pl.fester3k.antlr.semanticAnalysis.SemanticAnalysisResult;
import pl.fester3k.antlr.semanticAnalysis.Type;

public class InterpreterListener extends AndroCodeListenerWithScopes {
	private static final String PREFIX = "**";
	private MemorySpace globalMemory = new MemorySpace("Global");
	private MemorySpace currentMemory = globalMemory;
	private Stack<FunctionSpace> stack = new Stack<FunctionSpace>();
	private ParseTreeProperty<Object> evaluationResults = new ParseTreeProperty<Object>();
	private final ParseTreeProperty<Type> types;
//	private final ParseTreeProperty<Type> promotedTypes;

	public InterpreterListener(SemanticAnalysisResult semanticAnalysisResult) {
		super(semanticAnalysisResult.getGlobals(), semanticAnalysisResult.getScopes(), PREFIX);
		this.types = semanticAnalysisResult.getTypes();
//		this.promotedTypes = semanticAnalysisResult.getPromotedTypes();
	}
	
	

	@Override
	public void exitExpr_cast(Expr_castContext ctx) {
		super.exitExpr_cast(ctx);
		Type type = Type.getTypeByTokenType(ctx.type().start.getType());
		ExprContext expr = ctx.expr();
		Object value = evaluationResults.get(expr);
		switch(type) {
		case BOOLEAN:
			//TODO illegal!
			break;
		case CHAR:
			//TODO illegal!
			break;
		case INT:
			if(value instanceof Character) {
				value = Integer.valueOf(((Character)value).toString());
			} else {
				//TODO illegal!
			}
			break;
		case FLOAT:
			if(value instanceof Character) {
				value = Float.valueOf(((Character)value).toString());
			} else if(value instanceof Integer) {
				value = Float.valueOf(((Integer)value));
			} else {
				//TODO illegal!
			}
			break;
		case STRING: 
			break;
		case DEVICE: 
			break;
		default: 
			break;
		}
		evaluationResults.put(ctx, value);		
	}



	@Override
	public void exitAssignment(AssignmentContext ctx) {
		super.exitAssignment(ctx);
		Token id = ctx.a;
		ExprContext expr = ctx.b;
		Object value = evaluationResults.get(expr);
		MemorySpace space = getSpaceWithSymbol(id.getText());
		if(value == null) {
			printer.printError("Value has not been yet computed", expr);
			//TODO Throw
		}
		if(space == null ) {
			space = currentMemory;
			//TODO Throw
		}
		space.put(id.getText(), value);
		printer.print(id.getText() + " = " + value, ctx);
	}

	@Override
	public void exitExpr_value(Expr_valueContext ctx) {
		super.exitExpr_value(ctx);
		String value = ctx.value().getText();
		Object evaluatedValue = null;
		Type type = Type.getTypeByTokenType(ctx.start.getType());
		switch(type) {
		case BOOLEAN: evaluatedValue = Boolean.valueOf(value); 
			break;
		case CHAR:	evaluatedValue = (value.toCharArray())[1];
			break;
		case DEVICE: evaluatedValue = value;  // TODO okreslic co dalej
			break;
		case FLOAT: evaluatedValue = Float.valueOf(value);
			break;
		case INT:	evaluatedValue = Integer.valueOf(value);
			break;
		case STRING: evaluatedValue = value;
			break;
		default: 
			break;
		}
		evaluationResults.put(ctx, evaluatedValue);
	}

	@Override
	public void exitExpr_incr_decr(Expr_incr_decrContext ctx) {
		super.exitExpr_incr_decr(ctx);
		Token id = ctx.id;
		int operatorTokenType = ctx.op.getType();
		Operator operator = Operator.getOperatorByTokenType(operatorTokenType);
		MemorySpace memorySpace = getSpaceWithSymbol(id.getText());
		if(memorySpace == null ) {
			memorySpace = currentMemory;
		}
		int value = (Integer)memorySpace.get(id.getText());
		value = calculateIncrDecrValue(ctx, operator, value);
		memorySpace.put(id.getText(), value);
		printer.print(id.getText() + " = " + value, ctx);
	}
	
	@Override
	public void exitCondition_relational(Condition_relationalContext ctx) {
		ExprContext exprA = ctx.a;
		ExprContext exprB = ctx.b;
		int operatorTokenType = ctx.op.getType();
		Type typeA = types.get(exprA);
		Type typeB = types.get(exprB);
		Object object = evaluationResults.get(exprA);
		Object object2 = evaluationResults.get(exprB);
		Operator operator = Operator.getOperatorByTokenType(operatorTokenType);
		
		super.exitCondition_relational(ctx);
	}

	@Override
	public void exitCondition_equality(Condition_equalityContext ctx) {
		// TODO Auto-generated method stub
		super.exitCondition_equality(ctx);
	}

	private int calculateIncrDecrValue(Expr_incr_decrContext ctx,
			Operator operator, int value) {
		switch(operator) {
			case INCR_OP: 
				value++;
			break;
			case DECR_OP: 
				value--;
			break;
			default:
				printer.printError("Something gone wrong", ctx);
				//TODO Throw
				break;
		}
		return value;
	}
	
    public MemorySpace getSpaceWithSymbol(String id) {
        if (stack.size() > 0 && stack.peek().get(id) != null) { // in top stack?
            return stack.peek();
        }
        if ( globalMemory.get(id)!=null ) {
        	return globalMemory;        // in globals?
        }
        return null;                                        // nowhere
    }
}
