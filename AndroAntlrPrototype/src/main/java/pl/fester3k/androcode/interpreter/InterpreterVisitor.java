package pl.fester3k.androcode.interpreter;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import pl.fester3k.androcode.antlr.AndroCodeBaseVisitor;
import pl.fester3k.androcode.antlr.AndroCodeParser.ArgumentsContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.AssignmentContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.BlockContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.BodyContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ConditionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_equalityContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_negatedContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_relationalContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_var_negatedContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ExprContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_binopContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_castContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_incr_decrContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_parenthesisContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_uminusContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_valueContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_varContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.For_loopContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.FunctionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Function_callContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.If_conditionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Main_functionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Return_statementContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ScriptContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.StatementContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Var_callContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Var_declarationContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.While_loopContext;
import pl.fester3k.androcode.antlr.enums.Type;
import pl.fester3k.androcode.interpreter.memory.FunctionSpace;
import pl.fester3k.androcode.interpreter.memory.MemorySpace;
import pl.fester3k.androcode.interpreter.tokens.Operator;
import pl.fester3k.androcode.logger.AndroLog;
import pl.fester3k.androcode.semanticAnalysis.SymbolTable;
import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.FunctionSymbol;
import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.Symbol;
import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.VariableSymbol;
import pl.fester3k.prot.utils.Utils;

public class InterpreterVisitor extends AndroCodeBaseVisitor<Object> {
	private MemorySpace globalMemory = new MemorySpace("Global");
	private MemorySpace currentMemory = globalMemory;
	private Stack<FunctionSpace> stack = new Stack<FunctionSpace>();

	private final GlobalScope globals;
	private Scope currentScope;
	private final ParseTreeProperty<Scope> scopes;
	private static final AndroLog log = new AndroLog(InterpreterVisitor.class.getSimpleName());

	public InterpreterVisitor(SymbolTable semanticAnalysisResult) {
		log.info("Starting interpreter");
		this.globals = semanticAnalysisResult.getGlobals();
		this.scopes = semanticAnalysisResult.getScopes();
	}

	

	@Override
	public Object visitStatement(StatementContext ctx) {
		return super.visitStatement(ctx);
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
		Object result = null;
		List<StatementContext> statement = ctx.statement();
		for (StatementContext statementContext : statement) {
			result = visit(statementContext);
			//TODO Naiwne - zaklada, ze return jest ostatni, ale nie wymaga tego
			//TODO Powinno być tak, że blok przerywa/konczy swoje wykonanie jesli napotka na return -> musi go identyfikować -> musze otagowac te regule i podreguly 
		}

		currentScope = currentScope.getEnclosingScope();
		return result;
	}

	@Override
	public Object visitExpr_parenthesis(Expr_parenthesisContext ctx) {
		return visit(ctx.expr());
	}

	@Override
	public Object visitFunction(FunctionContext ctx) {
		currentScope = scopes.get(ctx);

		Object result = visit(ctx.block());

		currentScope = currentScope.getEnclosingScope();
		return result;
	}
	
	@Override
	public Object visitReturn_statement(Return_statementContext ctx) {
		Object result = null;
		ExprContext expr = ctx.expr();
		if(expr != null) {
			result = visit(expr);
		}
		return result;
	}

	@Override
	public Object visitMain_function(Main_functionContext ctx) {
		currentScope = scopes.get(ctx);
		
		visit(ctx.block());
		
		currentScope = globals;
		return null;
	}

	@Override
	public Object visitExpr_uminus(Expr_uminusContext ctx) {
		Number value = (Number)visit(ctx.expr());
		value = -value.floatValue();
		return value;
	}


	@Override
	public Object visitCondition_negated(Condition_negatedContext ctx) {
		Boolean result = (Boolean)visit(ctx.condition());
		return !result;
	}
	
	@Override
	public Object visitCondition_var_negated(
			Condition_var_negatedContext ctx) {		
		Boolean result = (Boolean)visit(ctx.var_call());
		return !result;
	}

	@Override
	public Object visitExpr_cast(Expr_castContext ctx) {
		Type type = Type.getTypeByTokenType(ctx.type().start.getType());
		Object value = visit(ctx.expr());
		value = castValueToType(value, type);
		return value;
	}
	
	@Override
	public Object visitVar_declaration(Var_declarationContext ctx) {
		String id = ctx.ID().getText();
		ExprContext expr = ctx.expr();
		MemorySpace space = getSpaceWithSymbol(id);
		Object result = null;
		if(expr != null) {
//			printer.print(id + " = " , ctx);
			result = assignValueToVar(id, expr, space);
		}
		return result;
	}

	@Override
	public Object visitAssignment(AssignmentContext ctx) {
		String id = ctx.a.getText();
		ExprContext expr = ctx.b;
		MemorySpace space = getSpaceWithSymbol(id);
		log.debug(id + " = " , ctx);
		assignValueToVar(id, expr, space);
		return null;
	}
	
	private Object assignValueToVar(String id,
			ExprContext expr, MemorySpace space) {
		Object value = visit(expr);
		if(value == null) {
			log.error("Value has not been yet computed", expr);
			//TODO Throw
		}
		Type destinationType = Utils.getTypeFromSymbol(id, currentScope);
		value = castValueToType(value, destinationType);
		space.put(id, value);
		log.debug(id + " = " + value.toString(), expr);
		return value;
	}


	@Override
	public Object visitExpr_value(Expr_valueContext ctx) {
		String value = ctx.value().getText();
		Object evaluatedValue = null;
		Type type = Type.getTypeByTokenType(ctx.start.getType());
		evaluatedValue = convertStringToProperValue(value, evaluatedValue, type);
		return evaluatedValue;
	}



	@Override
	public Object visitExpr_binop(Expr_binopContext ctx) {
		log.debug("visit binop", ctx);
		//Utrzymac zwracany typ
		Number result = 0;
		Number resultExprA = (Number)visit(ctx.a);
		Number resultExprB = (Number)visit(ctx.b);
		Operator operator = Operator.getOperatorByTokenType((ctx.op.getType()));
		result = computeBinaryOperation(ctx, result, resultExprA, resultExprB,
				operator);

		return result;
	}	
	
	@Override
	public Object visitVar_call(Var_callContext ctx) {
		String id = ctx.ID().getText();
		Object value = null;
		MemorySpace memorySpace = getSpaceWithSymbol(id);
		value = memorySpace.get(id);
		log.debug("Found " + id + " = " + value, ctx);
		return value;
	}

	@Override
	public Object visitFunction_call(Function_callContext ctx) {
		String id = ctx.ID().getText();
		ArgumentsContext argumentsContext = ctx.arguments();
		
		FunctionSymbol function = (FunctionSymbol)currentScope.resolve(id);
		if(function == null) {
			log.error("Function not found!", ctx);
			return null;
			//TODO throw
		}
		FunctionSpace fspace = new FunctionSpace(function);
		MemorySpace savedMemory = currentMemory;
		currentMemory = fspace;
		
		Map<String, Symbol> declaredParameters = function.getOrderedArgs();
		if(argumentsContext == null) {
			//TODO jesli nie ma arguemntow np. funkcja bezargumentowa
		} else {
			List<ExprContext> arguments = argumentsContext.expr();
			int i = 0;
			for (Symbol parameter : declaredParameters.values()) {
				VariableSymbol declaredParameterSymbol = (VariableSymbol)parameter;
				ExprContext argExpression = arguments.get(i);
				String paramName = declaredParameterSymbol.getName();
				Object paramValue = visit(argExpression);
				fspace.put(paramName, paramValue);
				i++;
			}
		}
		Object result = null;
		stack.push(fspace);
		ParserRuleContext functionBody = getFunctionById(ctx, id);
		result = visit(functionBody);
		stack.pop();
		currentMemory = savedMemory;
		return result;
	}


	@Override
	public Object visitExpr_var(Expr_varContext ctx) {
		return super.visitExpr_var(ctx);
	}

	@Override
	public Object visitExpr_incr_decr(Expr_incr_decrContext ctx) {
		String id = ctx.id.getText();
		int operatorTokenType = ctx.op.getType();
		Operator operator = Operator.getOperatorByTokenType(operatorTokenType);
		MemorySpace memorySpace = getSpaceWithSymbol(id);
		if(memorySpace == null ) {
			memorySpace = currentMemory;
		}
		int value = (Integer)memorySpace.get(id);
		value = calculateIncrDecrValue(ctx, operator, value);
		memorySpace.put(id, value);
		log.debug(id + " = " + value, ctx);
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
				log.debug("if No. " + i + " condition met", ctx);
				BlockContext ifBlock = blocks.get(i);
				visit(ifBlock);
				isConditionMet = true;
				break;
			}
			i++;
		}
		BlockContext elseBlock = ctx.elseBlock;
		if( (!isConditionMet) && (elseBlock != null)) { 
			log.debug("else", ctx);
			visit(elseBlock);
		}
		return null;
	}

	@Override
	public Object visitFor_loop(For_loopContext ctx) {
		AssignmentContext startValAssignment = ctx.assignment().get(0);
		ConditionContext condition = ctx.condition();
		BlockContext block = ctx.block();
		ExprContext newValExpr = ctx.newValExpr;
		AssignmentContext newValAssignment = ctx.newValAssign;
		ParserRuleContext newVal;
		if(newValExpr!= null) {
			newVal = newValExpr;
		} else {
			newVal = newValAssignment;
		}
		
		visit(startValAssignment); 
		while((Boolean)visit(condition)) { 
			visit(block); 
			visit(newVal);
		}
		return null;
	}
	
	@Override
	public Object visitWhile_loop(While_loopContext ctx) {
		ConditionContext condition = ctx.condition();
		BlockContext block = ctx.block();
		while((Boolean)visit(condition)) {
			visit(block);
		}
		return null;
	}

	@Override
	public Object visitCondition_relational(Condition_relationalContext ctx) {
		Boolean result = false;
		ExprContext exprA = ctx.a;
		ExprContext exprB = ctx.b;
		int operatorTokenType = ctx.op.getType();
		result = computeRelationalCondition(ctx, result, exprA, exprB, operatorTokenType);

		return result;
	}

	@Override
	public Object visitCondition_equality(Condition_equalityContext ctx) {
		Boolean result = false;
		ExprContext exprA = ctx.a;
		ExprContext exprB = ctx.b;
		int operatorTokenType = ctx.op.getType();
		result = computeEqualityCondition(ctx, result, exprA, exprB,
				operatorTokenType);
		return result;
	}

	private Object castValueToType(Object value, Type type) {
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
			} else if(value instanceof Number) {
				value = ((Number)value).intValue();
			} else {
				//TODO illegal!
			}
			break;
		case FLOAT:
			if(value instanceof Character) {
				value = Float.valueOf(((Character)value).toString());
			} else if(value instanceof Number) {
				value = ((Number)value).floatValue();
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

	private Object convertStringToProperValue(String value,
			Object evaluatedValue, Type type) {
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
			log.error("Something gone wrong", ctx);
			//TODO Throw
			break;
		}
		return value;
	}

	private Boolean computeRelationalCondition(Condition_relationalContext ctx,
			Boolean result, ExprContext exprA, ExprContext exprB,
			int operatorTokenType) {
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
			log.error("Something gone wrong", ctx);
			//TODO Throw
			break;
		}
		return result;
	}

	private Boolean computeEqualityCondition(Condition_equalityContext ctx,
			Boolean result, ExprContext exprA, ExprContext exprB,
			int operatorTokenType) {
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
			log.error("Something gone wrong", ctx);
			//TODO Throw
			break;
		}
		return result;
	}

	private Number computeBinaryOperation(Expr_binopContext ctx, Number result,
			Number resultExprA, Number resultExprB, Operator operator) {
		switch(operator) {
		case ADD_OP:
			result = resultExprA.floatValue() + resultExprB.floatValue();
			break;
		case SUBST_OP:
			result = resultExprA.floatValue() - resultExprB.floatValue();
			break;
		case MULT_OP:
			result = resultExprA.floatValue() * resultExprB.floatValue();
			break;
		case DEV_OP:
			if(resultExprB.floatValue() != 0) {
				result = resultExprA.floatValue() / resultExprB.floatValue();
			} else {
				log.error("Division by zero!!", ctx);
				//TODO Throw
			}
			break;
		default:
			break;
		}
		return result;
	}
	
	private ParserRuleContext getFunctionById(ParserRuleContext baseCtx, String id) {
		ParserRuleContext result = null;
		ParserRuleContext ctx = baseCtx;
		while(!(ctx instanceof BodyContext)) {
			ctx = ctx.getParent();
		}
		BodyContext bodyCtx = (BodyContext)ctx;
		List<FunctionContext> function = bodyCtx.function();
		for (FunctionContext functionContext : function) {
			String tempFunctionName = functionContext.ID().getText();
			if(tempFunctionName.equals(id)) {
				result = functionContext;
			}
		}
		return result;
	}

	private MemorySpace getSpaceWithSymbol(String id) {
		MemorySpace result;
		if (stack.size() > 0 && stack.peek().get(id) != null) { // in top stack?
			result = stack.peek();
		} else if ( globalMemory.get(id)!=null ) {
			result = globalMemory;        // in globals?
		} else {
			result = currentMemory;
			//TODO Throw
		}
		return result;                                        // nowhere
	}
}
