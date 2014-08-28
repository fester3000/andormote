package pl.fester3k.androcode.antlr.listeners;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import pl.fester3k.androcode.antlr.AndroCodeBaseListener;
import pl.fester3k.androcode.antlr.AndroCodeParser.ArgumentsContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.AssignmentContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.BlockContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.BodyContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_equalityContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_negatedContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_relationalContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_var_negatedContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Dev_execContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Dev_getContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Dev_setParamContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_binopContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_castContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_devContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_fcallContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_incr_decrContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_parenthesisContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_uminusContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_valueContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_varContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.For_loopContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.FunctionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Function_callContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.If_conditionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Lib_includesContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Mixed_stringContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ParameterContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ParametersContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.PrintContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Return_statementContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ScriptContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.SleepContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.StatementContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.TypeContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ValueContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Var_callContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Var_declarationContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.While_loopContext;
import pl.fester3k.androcode.logger.AndroLog;
import pl.fester3k.androcode.scopeManagement.GlobalScope;
import pl.fester3k.androcode.scopeManagement.Scope;


public class AndroCodeListenerWithScopes extends AndroCodeBaseListener {
	protected final AndroLog log;
	protected GlobalScope globals;
	protected ParseTreeProperty<Scope> scopes;
	protected Scope currentScope;
	
	public AndroCodeListenerWithScopes(
			GlobalScope globals, ParseTreeProperty<Scope> scopes, String className) {
		super();
		this.log = new AndroLog(className);   
		this.globals = globals;
		this.scopes = scopes;
	}


	@Override
	public void enterScript(ScriptContext ctx) {
		currentScope = globals;
		super.enterScript(ctx);
	}
	
	@Override
	public void enterBlock(BlockContext ctx) {
		currentScope = scopes.get(ctx);
		super.enterBlock(ctx);
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterFunction(FunctionContext ctx) {
		currentScope = scopes.get(ctx);
	}

	@Override
	public void exitFunction(FunctionContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterBody(BodyContext ctx) {
		
		super.enterBody(ctx);
	}


	@Override
	public void exitBody(BodyContext ctx) {
		
		super.exitBody(ctx);
	}


	@Override
	public void enterExpr_dev(Expr_devContext ctx) {
		
		super.enterExpr_dev(ctx);
	}


	@Override
	public void exitExpr_dev(Expr_devContext ctx) {
		
		super.exitExpr_dev(ctx);
	}

	@Override
	public void enterDev_setParam(Dev_setParamContext ctx) {
		super.enterDev_setParam(ctx);
	}


	@Override
	public void exitDev_setParam(Dev_setParamContext ctx) {
		super.exitDev_setParam(ctx);
	}


	@Override
	public void enterDev_exec(Dev_execContext ctx) {
		super.enterDev_exec(ctx);
	}


	@Override
	public void exitDev_exec(Dev_execContext ctx) {
		super.exitDev_exec(ctx);
	}


	@Override
	public void enterDev_get(Dev_getContext ctx) {
		super.enterDev_get(ctx);
	}


	@Override
	public void exitDev_get(Dev_getContext ctx) {
		super.exitDev_get(ctx);
	}


	@Override
	public void enterFor_loop(For_loopContext ctx) {
		
		super.enterFor_loop(ctx);
	}


	@Override
	public void exitFor_loop(For_loopContext ctx) {
		
		super.exitFor_loop(ctx);
	}


	@Override
	public void enterExpr_binop(Expr_binopContext ctx) {
		
		super.enterExpr_binop(ctx);
	}


	@Override
	public void exitExpr_binop(Expr_binopContext ctx) {
		
		super.exitExpr_binop(ctx);
	}


	@Override
	public void enterCondition_relational(Condition_relationalContext ctx) {
		
		super.enterCondition_relational(ctx);
	}


	@Override
	public void exitCondition_relational(Condition_relationalContext ctx) {
		
		super.exitCondition_relational(ctx);
	}


	@Override
	public void enterExpr_value(Expr_valueContext ctx) {
		
		super.enterExpr_value(ctx);
	}


	@Override
	public void exitExpr_value(Expr_valueContext ctx) {
		
		super.exitExpr_value(ctx);
	}


	@Override
	public void enterType(TypeContext ctx) {
		
		super.enterType(ctx);
	}


	@Override
	public void exitType(TypeContext ctx) {
		
		super.exitType(ctx);
	}


	@Override
	public void enterExpr_parenthesis(Expr_parenthesisContext ctx) {
		
		super.enterExpr_parenthesis(ctx);
	}


	@Override
	public void exitExpr_parenthesis(Expr_parenthesisContext ctx) {
		
		super.exitExpr_parenthesis(ctx);
	}


	@Override
	public void enterParameter(ParameterContext ctx) {
		
		super.enterParameter(ctx);
	}


	@Override
	public void exitParameter(ParameterContext ctx) {
		
		super.exitParameter(ctx);
	}


	@Override
	public void enterIf_condition(If_conditionContext ctx) {
		
		super.enterIf_condition(ctx);
	}


	@Override
	public void exitIf_condition(If_conditionContext ctx) {
		
		super.exitIf_condition(ctx);
	}

	@Override
	public void enterCondition_negated(Condition_negatedContext ctx) {
		super.enterCondition_negated(ctx);
	}

	@Override
	public void enterCondition_var_negated(
			Condition_var_negatedContext ctx) {
		super.enterCondition_var_negated(ctx);
	}


	@Override
	public void exitCondition_var_negated(
			Condition_var_negatedContext ctx) {
		super.exitCondition_var_negated(ctx);
	}


	@Override
	public void exitCondition_negated(Condition_negatedContext ctx) {
		super.exitCondition_negated(ctx);
	}


	@Override
	public void enterValue(ValueContext ctx) {
		
		super.enterValue(ctx);
	}


	@Override
	public void exitValue(ValueContext ctx) {
		
		super.exitValue(ctx);
	}


	@Override
	public void enterParameters(ParametersContext ctx) {
		
		super.enterParameters(ctx);
	}


	@Override
	public void exitParameters(ParametersContext ctx) {
		
		super.exitParameters(ctx);
	}


	@Override
	public void enterWhile_loop(While_loopContext ctx) {
		
		super.enterWhile_loop(ctx);
	}


	@Override
	public void exitWhile_loop(While_loopContext ctx) {
		
		super.exitWhile_loop(ctx);
	}

	

	@Override
	public void enterMixed_string(Mixed_stringContext ctx) {
		super.enterMixed_string(ctx);
	}


	@Override
	public void exitMixed_string(Mixed_stringContext ctx) {
		super.exitMixed_string(ctx);
	}


	@Override
	public void enterSleep(SleepContext ctx) {
		super.enterSleep(ctx);
	}


	@Override
	public void exitSleep(SleepContext ctx) {
		super.exitSleep(ctx);
	}


	@Override
	public void enterPrint(PrintContext ctx) {
		super.enterPrint(ctx);
	}


	@Override
	public void exitPrint(PrintContext ctx) {
		super.exitPrint(ctx);
	}


	@Override
	public void enterExpr_fcall(Expr_fcallContext ctx) {
		super.enterExpr_fcall(ctx);
	}


	@Override
	public void exitExpr_fcall(Expr_fcallContext ctx) {
		
		super.exitExpr_fcall(ctx);
	}


	@Override
	public void enterVar_declaration(Var_declarationContext ctx) {
		
		super.enterVar_declaration(ctx);
	}


	@Override
	public void exitVar_declaration(Var_declarationContext ctx) {
		
		super.exitVar_declaration(ctx);
	}


	@Override
	public void enterReturn_statement(Return_statementContext ctx) {
		
		super.enterReturn_statement(ctx);
	}


	@Override
	public void exitReturn_statement(Return_statementContext ctx) {
		
		super.exitReturn_statement(ctx);
	}


	@Override
	public void enterCondition_equality(Condition_equalityContext ctx) {
		
		super.enterCondition_equality(ctx);
	}


	@Override
	public void exitCondition_equality(Condition_equalityContext ctx) {
		
		super.exitCondition_equality(ctx);
	}


	@Override
	public void enterExpr_var(Expr_varContext ctx) {
		
		super.enterExpr_var(ctx);
	}


	@Override
	public void exitExpr_var(Expr_varContext ctx) {
		
		super.exitExpr_var(ctx);
	}


	@Override
	public void exitScript(ScriptContext ctx) {
		
		super.exitScript(ctx);
	}


	@Override
	public void enterStatement(StatementContext ctx) {
		
		super.enterStatement(ctx);
	}


	@Override
	public void exitStatement(StatementContext ctx) {
		
		super.exitStatement(ctx);
	}


	@Override
	public void enterAssignment(AssignmentContext ctx) {
		
		super.enterAssignment(ctx);
	}


	@Override
	public void exitAssignment(AssignmentContext ctx) {
		
		super.exitAssignment(ctx);
	}


	@Override
	public void enterArguments(ArgumentsContext ctx) {
		
		super.enterArguments(ctx);
	}


	@Override
	public void exitArguments(ArgumentsContext ctx) {
		
		super.exitArguments(ctx);
	}

	@Override
	public void enterExpr_incr_decr(Expr_incr_decrContext ctx) {
		super.enterExpr_incr_decr(ctx);
	}


	@Override
	public void exitExpr_incr_decr(Expr_incr_decrContext ctx) {
		super.exitExpr_incr_decr(ctx);
	}
	
	@Override
	public void enterVar_call(Var_callContext ctx) {
		super.enterVar_call(ctx);
	}


	@Override
	public void exitVar_call(Var_callContext ctx) {
		super.exitVar_call(ctx);
	}


	@Override
	public void enterFunction_call(Function_callContext ctx) {
		
		super.enterFunction_call(ctx);
	}


	@Override
	public void exitFunction_call(Function_callContext ctx) {
		
		super.exitFunction_call(ctx);
	}


	@Override
	public void enterLib_includes(Lib_includesContext ctx) {
		super.enterLib_includes(ctx);
	}


	@Override
	public void exitLib_includes(Lib_includesContext ctx) {
		super.exitLib_includes(ctx);
	}


	@Override
	public void enterExpr_cast(Expr_castContext ctx) {
		super.enterExpr_cast(ctx);
	}


	@Override
	public void exitExpr_cast(Expr_castContext ctx) {
		super.exitExpr_cast(ctx);
	}


	@Override
	public void enterExpr_uminus(Expr_uminusContext ctx) {
		super.enterExpr_uminus(ctx);
	}


	@Override
	public void exitExpr_uminus(Expr_uminusContext ctx) {
		super.exitExpr_uminus(ctx);
	}
	
	
	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		super.enterEveryRule(ctx);
	}


	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		super.exitEveryRule(ctx);
	}


	@Override
	public void visitTerminal(TerminalNode node) {
		super.visitTerminal(node);
	}


	@Override
	public void visitErrorNode(ErrorNode node) {
		super.visitErrorNode(node);
	}	
}
