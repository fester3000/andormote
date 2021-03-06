package mobi.andromote.androcode.interpreter;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import mobi.andromote.androcode.antlr.AndroCodeBaseVisitor;
import mobi.andromote.androcode.antlr.AndroCodeParser.ArgumentsContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.AssignmentContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.BlockContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.BodyContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.ConditionContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Condition_combinedContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Condition_equalityContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Condition_negatedContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Condition_relationalContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Condition_var_negatedContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Dev_execContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Dev_getContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Dev_releaseContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Dev_setParamContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.ExprContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_binopContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_castContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_devContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_incr_decrContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_parenthesisContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_uminusContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_valueContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.For_loopContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.FunctionContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Function_callContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.If_conditionContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Logical_opContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Mixed_stringContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.PrintContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Return_statementContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.ScriptContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.SleepContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.StatementContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.ValueContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Var_callContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Var_declarationContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Var_or_function_callContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Var_or_valContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.While_loopContext;
import mobi.andromote.androcode.antlr.enums.Type;
import mobi.andromote.androcode.datatypes.Operator;
import mobi.andromote.androcode.interpreter.memory.FunctionSpace;
import mobi.andromote.androcode.interpreter.memory.MemorySpace;
import mobi.andromote.androcode.logger.AndroLog;
import mobi.andromote.androcode.scopeManagement.GlobalScope;
import mobi.andromote.androcode.scopeManagement.Scope;
import mobi.andromote.androcode.symbolManagement.FunctionSymbol;
import mobi.andromote.androcode.symbolManagement.Symbol;
import mobi.andromote.androcode.symbolManagement.SymbolTable;
import mobi.andromote.androcode.symbolManagement.VariableSymbol;
import mobi.andromote.androcode.utils.Utils;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import mobi.andromote.functionalityFramework.FunctionManager;
import mobi.andromote.functionalityFramework.exceptions.NoSuchFunctionException;
import mobi.andromote.functionalityFramework.exceptions.NoSuchFeatureException;

public class InterpreterVisitor extends AndroCodeBaseVisitor<Object> {
	private MemorySpace globalMemory = new MemorySpace("Global");
	private MemorySpace currentMemory = globalMemory;
	private Stack<FunctionSpace> stack = new Stack<FunctionSpace>();

	private final GlobalScope globals;
	private Scope currentScope;
	private final ParseTreeProperty<Scope> scopes;
	private final AndroLog log;

	public InterpreterVisitor(SymbolTable symbolTable) {
		this.log = new AndroLog(InterpreterVisitor.class.getSimpleName()); 
		log.info("Starting interpreter");
		this.globals = symbolTable.getGlobals();
		this.scopes = symbolTable.getScopes();
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
		List<StatementContext> statements = ctx.statement();
		for (StatementContext statement : statements) {
			visit(statement);
		}
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
	public Object visitCondition_var_negated(Condition_var_negatedContext ctx) {		
		Boolean result = (Boolean)visit(ctx.var_or_function_call());
		return !result;
	}

	@Override
	public Object visitCondition_combined(Condition_combinedContext ctx) {
		boolean result = false;
		List<ConditionContext> conditions = ctx.condition();
		List<Logical_opContext> logical_operators = ctx.logical_op();

		result = computeCombinedCondition(conditions, logical_operators);
		log.debug("Combined result " + result);
		return result;
	}

	private boolean computeCombinedCondition(List<ConditionContext> conditions,
			List<Logical_opContext> logical_operators) {
		boolean result = false;
		boolean resultA = (Boolean) visit(conditions.get(0));
		conditions.remove(0);
		Operator operator = Operator.getOperatorByTokenType(logical_operators.get(0).start.getType());
		logical_operators.remove(0);
		boolean resultB;
		if(!logical_operators.isEmpty()) {
			resultB = computeCombinedCondition(conditions, logical_operators);
		} else {
			resultB = (Boolean)visit(conditions.get(0));
		}
		log.debug("resultA " + resultA);
		log.debug("resultB " + resultB);
		switch(operator) {
		case AND_OP:
			result = resultA && resultB;
			log.debug("AND result " + result);
			break;
		case OR_OP:
			result = resultA || resultB;
			log.debug("OR result " + result);
			break;
		default:
			log.warn("Incorrect arguments and/or operators to compute combined condition result");
			break;
		}
		return result;
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
			return value;
		}
		Type destinationType = Utils.getTypeFromSymbol(id, currentScope);
		value = castValueToType(value, destinationType);
		space.put(id, value);
		log.debug(id + " = " + value.toString(), expr);
		return value;
	}


	@Override
	public Object visitExpr_value(Expr_valueContext ctx) {
		return visit(ctx.value());
	}

	@Override
	public Object visitValue(ValueContext ctx) {
		String value = ctx.getText();
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
		if(condition != null) {
			BlockContext block = ctx.block();
			while((Boolean)visit(condition)) {
				visit(block);
			}
		} else {
			log.error("Bad while condition!");
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


	@Override
	public Object visitSleep(SleepContext ctx) {
		int sleepTime = Integer.valueOf(ctx.INT().getText());
		tryToSleepInAndrocode(sleepTime);
		return null;
	}

	@Override
	public Object visitPrint(PrintContext ctx) {
		String message = (String)visit(ctx.mixed_string());
		print(message);
		return null;
	}

	//TODO przepisać na binop 
	@Override
	public String visitMixed_string(Mixed_stringContext ctx) {
		String result;
		List<Var_or_valContext> var_or_vals = ctx.var_or_val();
		result = tryToConcat(var_or_vals);
		return result;
	}

	@Override
	public Object visitVar_or_val(Var_or_valContext ctx) {
		String result = "";
		Var_or_function_callContext varOrFunction = ctx.var_or_function_call();
		ValueContext value = ctx.value();
		if(varOrFunction != null) {
			Object var_callString = visit(varOrFunction);
			result = String.valueOf(var_callString);
		} else if(value != null) {
			Object valueString = visit(value);
			result = String.valueOf(valueString);
		}
		return result;
	}

	@Override
	public Object visitExpr_dev(Expr_devContext ctx) {
		return visit(ctx.dev_operation());
	}


	@Override
	public Object visitDev_release(Dev_releaseContext ctx) {
		boolean result = false; 
		String varId = ctx.ID().getText();
		result = releaseDevice(ctx, varId);
		return result;
	}


	@Override
	public Object visitDev_setParam(Dev_setParamContext ctx) {
		boolean result = false; 
		String varId = ctx.ID().getText();
		String propertyName = Utils.getStringOutOfQuotes(ctx.STRING().getText());
		String value = String.valueOf(visit(ctx.mixed_string()));
		value = Utils.getStringOutOfQuotes(value);
		result = setDeviceParam(ctx, varId, propertyName, value);
		return result;
	}

	@Override
	public Object visitDev_exec(Dev_execContext ctx) {
		String varId = ctx.ID().getText();
		Object result = null;
		if(ctx.STRING() != null && ctx.mixed_string() != null) {
			String propertyName = Utils.getStringOutOfQuotes(ctx.STRING().getText());
			String value = String.valueOf(visit(ctx.mixed_string()));
			value = Utils.getStringOutOfQuotes(value);
			setDeviceParam(ctx, varId, propertyName, value);
		}
		try {
			result = FunctionManager.INSTANCE.execute(varId);
			if(ctx.INT() != null) {
				int sleepTime = Integer.valueOf(ctx.INT().getText());
				tryToSleepInAndrocode(sleepTime);
			}
		} catch(NoSuchFunctionException e) {
			log.warn(e.getMessage(), ctx);
		}
		return result;
	}

	@Override
	public Object visitDev_get(Dev_getContext ctx) {
		String varId = ctx.ID().getText();
		String featureName = Utils.getStringOutOfQuotes(ctx.STRING().getText());
		try {
			FunctionManager.INSTANCE.get(featureName, varId);
		} catch (NoSuchFeatureException e) {
			log.warn(e.getMessage(), ctx);
		}
		return featureName;
	}

	private void print(final String message) {
		log.info(message);
	}

	private String tryToConcat(List<Var_or_valContext> var_or_vals) {
		String result = "";
		for (Var_or_valContext var_or_val : var_or_vals) {
			String tempValue = (String)visit(var_or_val);
			result = result + tempValue;
		}
		return result;
	}

	private boolean setDeviceParam(ParserRuleContext ctx, String varId,
			String propertyName, String value) {
		boolean result = false;
		try {
			FunctionManager.INSTANCE.setParam(varId, propertyName, value);
			result = true;
		} catch (NoSuchFunctionException e) {
			log.warn("setParam " + varId + " - No such device! Have you forgot to call device.getDevice(\"deviceName\")?", ctx);
		}
		return result;
	}


	private boolean releaseDevice(Dev_releaseContext ctx, String varId) {
		boolean result = false;
		try {
			FunctionManager.INSTANCE.release(varId);
			result = true;
		} catch (NoSuchFunctionException e) {
			log.warn("Release  " + varId + " - No such device! Have you forgot to call device.getDevice(\"deviceName\")?", ctx);
		}
		return result;
	}


	private void tryToSleepInAndrocode(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		case STRING: evaluatedValue = Utils.getStringOutOfQuotes(value);
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
		case MINUS_OP:
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
