package pl.fester3k.antlr.interpreter;

import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.antlr.androCode.AndroCodeBaseVisitor;
import pl.fester3k.antlr.androCode.AndroCodeParser.AssignmentContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.BlockContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.BodyContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ConditionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Condition_equalityContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Condition_relationalContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ExprContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_castContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_incr_decrContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_valueContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.FunctionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.If_conditionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Main_functionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ScriptContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.StatementContext;
import pl.fester3k.antlr.interpreter.memory.FunctionSpace;
import pl.fester3k.antlr.interpreter.memory.MemorySpace;
import pl.fester3k.antlr.interpreter.tokens.Operator;
import pl.fester3k.antlr.semanticAnalysis.SemanticAnalysisResult;
import pl.fester3k.antlr.semanticAnalysis.Type;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.prot.utils.PrintUtils;

public class InterpreterVisitor extends AndroCodeBaseVisitor<Object> {
	private static final String PREFIX = "*>";
	private MemorySpace globalMemory = new MemorySpace("Global");
	private MemorySpace currentMemory = globalMemory;
	private Stack<FunctionSpace> stack = new Stack<FunctionSpace>();
	
//	private final ParseTreeProperty<Type> types;
	private final GlobalScope globals;
	private Scope currentScope;
	private final ParseTreeProperty<Scope> scopes;
	private final PrintUtils printer;
//	private final ParseTreeProperty<Type> promotedTypes;

	public InterpreterVisitor(SemanticAnalysisResult semanticAnalysisResult) {
		this.globals = semanticAnalysisResult.getGlobals();
		this.scopes = semanticAnalysisResult.getScopes();
//		this.types = semanticAnalysisResult.getTypes();
		this.printer = new PrintUtils(PREFIX);
//		this.promotedTypes = semanticAnalysisResult.getPromotedTypes();
	}


	@Override
	public Object visitScript(ScriptContext ctx) {
		currentScope = globals;
		return super.visitScript(ctx);
	}
	
	@Override
	public Object visitBody(BodyContext ctx) {
		visit(ctx.main_function());
		return null;
	}


	@Override
	public Object visitBlock(BlockContext ctx) {
		currentScope = scopes.get(ctx);
		
		List<StatementContext> statement = ctx.statement();
		for (StatementContext statementContext : statement) {
			visit(statementContext);
		}
		
		currentScope = currentScope.getEnclosingScope();
		return null;
	}



	@Override
	public Object visitFunction(FunctionContext ctx) {
		currentScope = scopes.get(ctx);
		
		visit(ctx.block());
		
		currentScope = currentScope.getEnclosingScope();
		return null;
	}


	@Override
	public Object visitMain_function(Main_functionContext ctx) {
		currentScope = scopes.get(ctx);
		visit(ctx.block());
		//
		currentScope = currentScope.getEnclosingScope();
		return null;
	}

	@Override
	public Object visitExpr_cast(Expr_castContext ctx) {
		Type type = Type.getTypeByTokenType(ctx.type().start.getType());
		ExprContext expr = ctx.expr();
		Object value = visit(expr);
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
		return value;
	}

	@Override
	public Object visitAssignment(AssignmentContext ctx) {
		Token id = ctx.a;
		ExprContext expr = ctx.b;
		Object value = visit(expr);
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
		return value;
	}

	
	
	@Override
	public Object visitExpr_value(Expr_valueContext ctx) {
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
		return evaluatedValue;
	}


	@Override
	public Object visitExpr_incr_decr(Expr_incr_decrContext ctx) {
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
		return value;
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
	
	@Override
	public Object visitIf_condition(If_conditionContext ctx) {
		List<ConditionContext> conditions = ctx.condition();
		List<BlockContext> blocks = ctx.block();
		int i = 0;
		boolean isConditionMet = false;
		for (ConditionContext condition : conditions) {
			if((Boolean)visit(condition)) {
				printer.print("if No" + i + " condition met", ctx);
				BlockContext ifBlock = blocks.get(i);
				visit(ifBlock);
				isConditionMet = true;
				break;
			}
			i++;
		}
		if( (!isConditionMet) && (blocks.size() == conditions.size() + 1)) { //jesli blokow jest o jeden wiecej niz warunkow -> else
			printer.print("else", ctx);
			BlockContext elseBlock = blocks.get(blocks.size() - 1);
			visit(elseBlock);
		}
		return null;
	}


	@Override
	public Object visitCondition_relational(Condition_relationalContext ctx) {
		Boolean result = false;
		ExprContext exprA = ctx.a;
		ExprContext exprB = ctx.b;
		int operatorTokenType = ctx.op.getType();
		Number valueExprA = (Number)visit(exprA);
		Number valueExprB = (Number)visit(exprB);
		Operator operator = Operator.getOperatorByTokenType(operatorTokenType);
		switch(operator) {
		case GT_OP: 
			if(valueExprA.floatValue() > valueExprB.floatValue()) {
				result = true;
			}
			break;
		case GTEQ_OP: 
			if(valueExprA.floatValue() >= valueExprB.floatValue()) {
				result = true;
			}
			break;
		case LT_OP: 
			if(valueExprA.floatValue() < valueExprB.floatValue()) {
				result = true;
			}
			break;
		case LTEQ_OP: 
			if(valueExprA.floatValue() <= valueExprB.floatValue()) {
				result = true;
			}
			break;
		default:
			printer.printError("Something gone wrong", ctx);
			//TODO Throw
			break;
		}
		
		return result;
	}

	@Override
	public Object visitCondition_equality(Condition_equalityContext ctx) {
		Boolean result = false;
		ExprContext exprA = ctx.a;
		ExprContext exprB = ctx.b;
		int operatorTokenType = ctx.op.getType();
		//TODO -> wartosci moga byc liczbowe!!
		Object valueExprA = visit(exprA);
		Object valueExprB = visit(exprB);
		Operator operator = Operator.getOperatorByTokenType(operatorTokenType);
		switch(operator) {
		case EQ_OP:
			if(valueExprA == valueExprB) {
				result = true;
			}
			break;
		case NOT_EQ_OP:
			if(valueExprA != valueExprB) {
				result = true;
			}
			break;
		default:
			printer.printError("Something gone wrong", ctx);
			//TODO Throw
			break;
		}
		return result;
	}

	
	
	
	
    private MemorySpace getSpaceWithSymbol(String id) {
        if (stack.size() > 0 && stack.peek().get(id) != null) { // in top stack?
            return stack.peek();
        }
        if ( globalMemory.get(id)!=null ) {
        	return globalMemory;        // in globals?
        }
        return null;                                        // nowhere
    }
}
