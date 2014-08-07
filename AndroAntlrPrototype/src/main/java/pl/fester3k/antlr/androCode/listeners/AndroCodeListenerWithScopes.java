package pl.fester3k.antlr.androCode.listeners;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import pl.fester3k.antlr.androCode.AndroCodeBaseListener;
import pl.fester3k.antlr.androCode.AndroCodeParser.ArgumentsContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.AssignmentContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.BlockContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.BodyContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Condition_equalityContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Condition_relationalContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Dev_operationContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_binopContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_castContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_devContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_fcallContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_incr_decrContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_parenthesisContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_uminusContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_unotContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_valueContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_varContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.For_loopContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.FunctionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Function_callContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.If_conditionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Lib_includesContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Main_functionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ParameterContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ParametersContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Return_statementContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ScriptContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.StatementContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.TypeContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ValueContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Var_declarationContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.While_loopContext;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.prot.utils.PrintUtils;

public class AndroCodeListenerWithScopes extends AndroCodeBaseListener {
	protected final PrintUtils printer;
	protected GlobalScope globals;
	protected ParseTreeProperty<Scope> scopes;
	protected Scope currentScope;
	
	public AndroCodeListenerWithScopes(GlobalScope globals,	ParseTreeProperty<Scope> scopes, String prefix) {
		super();
		this.printer = new PrintUtils(prefix);
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
	public void enterMain_function(Main_functionContext ctx) {
		currentScope = scopes.get(ctx);
	}

	@Override
	public void exitMain_function(Main_functionContext ctx) {
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
	public void enterDev_operation(Dev_operationContext ctx) {
		
		super.enterDev_operation(ctx);
	}


	@Override
	public void exitDev_operation(Dev_operationContext ctx) {
		
		super.exitDev_operation(ctx);
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
	public void enterExpr_unot(Expr_unotContext ctx) {
		
		super.enterExpr_unot(ctx);
	}


	@Override
	public void exitExpr_unot(Expr_unotContext ctx) {
		
		super.exitExpr_unot(ctx);
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
		// TODO Auto-generated method stub
		super.enterExpr_incr_decr(ctx);
	}


	@Override
	public void exitExpr_incr_decr(Expr_incr_decrContext ctx) {
		// TODO Auto-generated method stub
		super.exitExpr_incr_decr(ctx);
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
