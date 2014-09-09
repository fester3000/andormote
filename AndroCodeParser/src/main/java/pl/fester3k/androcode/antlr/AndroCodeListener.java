// Generated from AndroCode.g4 by ANTLR 4.2.2

    package pl.fester3k.androcode.antlr;
    import pl.fester3k.androcode.antlr.enums.Type;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AndroCodeParser}.
 */
public interface AndroCodeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(@NotNull AndroCodeParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(@NotNull AndroCodeParser.BodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#dev_setParam}.
	 * @param ctx the parse tree
	 */
	void enterDev_setParam(@NotNull AndroCodeParser.Dev_setParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#dev_setParam}.
	 * @param ctx the parse tree
	 */
	void exitDev_setParam(@NotNull AndroCodeParser.Dev_setParamContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#expr_dev}.
	 * @param ctx the parse tree
	 */
	void enterExpr_dev(@NotNull AndroCodeParser.Expr_devContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#expr_dev}.
	 * @param ctx the parse tree
	 */
	void exitExpr_dev(@NotNull AndroCodeParser.Expr_devContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void enterFor_loop(@NotNull AndroCodeParser.For_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void exitFor_loop(@NotNull AndroCodeParser.For_loopContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#condition_negated}.
	 * @param ctx the parse tree
	 */
	void enterCondition_negated(@NotNull AndroCodeParser.Condition_negatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#condition_negated}.
	 * @param ctx the parse tree
	 */
	void exitCondition_negated(@NotNull AndroCodeParser.Condition_negatedContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#expr_binop}.
	 * @param ctx the parse tree
	 */
	void enterExpr_binop(@NotNull AndroCodeParser.Expr_binopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#expr_binop}.
	 * @param ctx the parse tree
	 */
	void exitExpr_binop(@NotNull AndroCodeParser.Expr_binopContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#condition_var_negated}.
	 * @param ctx the parse tree
	 */
	void enterCondition_var_negated(@NotNull AndroCodeParser.Condition_var_negatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#condition_var_negated}.
	 * @param ctx the parse tree
	 */
	void exitCondition_var_negated(@NotNull AndroCodeParser.Condition_var_negatedContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull AndroCodeParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull AndroCodeParser.BlockContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#condition_relational}.
	 * @param ctx the parse tree
	 */
	void enterCondition_relational(@NotNull AndroCodeParser.Condition_relationalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#condition_relational}.
	 * @param ctx the parse tree
	 */
	void exitCondition_relational(@NotNull AndroCodeParser.Condition_relationalContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#expr_value}.
	 * @param ctx the parse tree
	 */
	void enterExpr_value(@NotNull AndroCodeParser.Expr_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#expr_value}.
	 * @param ctx the parse tree
	 */
	void exitExpr_value(@NotNull AndroCodeParser.Expr_valueContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull AndroCodeParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull AndroCodeParser.TypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#condition_parenthesis}.
	 * @param ctx the parse tree
	 */
	void enterCondition_parenthesis(@NotNull AndroCodeParser.Condition_parenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#condition_parenthesis}.
	 * @param ctx the parse tree
	 */
	void exitCondition_parenthesis(@NotNull AndroCodeParser.Condition_parenthesisContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(@NotNull AndroCodeParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(@NotNull AndroCodeParser.FunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#expr_parenthesis}.
	 * @param ctx the parse tree
	 */
	void enterExpr_parenthesis(@NotNull AndroCodeParser.Expr_parenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#expr_parenthesis}.
	 * @param ctx the parse tree
	 */
	void exitExpr_parenthesis(@NotNull AndroCodeParser.Expr_parenthesisContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(@NotNull AndroCodeParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(@NotNull AndroCodeParser.ParameterContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#mixed_string}.
	 * @param ctx the parse tree
	 */
	void enterMixed_string(@NotNull AndroCodeParser.Mixed_stringContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#mixed_string}.
	 * @param ctx the parse tree
	 */
	void exitMixed_string(@NotNull AndroCodeParser.Mixed_stringContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#if_condition}.
	 * @param ctx the parse tree
	 */
	void enterIf_condition(@NotNull AndroCodeParser.If_conditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#if_condition}.
	 * @param ctx the parse tree
	 */
	void exitIf_condition(@NotNull AndroCodeParser.If_conditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull AndroCodeParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull AndroCodeParser.ValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#dev_exec}.
	 * @param ctx the parse tree
	 */
	void enterDev_exec(@NotNull AndroCodeParser.Dev_execContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#dev_exec}.
	 * @param ctx the parse tree
	 */
	void exitDev_exec(@NotNull AndroCodeParser.Dev_execContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(@NotNull AndroCodeParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(@NotNull AndroCodeParser.ParametersContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#while_loop}.
	 * @param ctx the parse tree
	 */
	void enterWhile_loop(@NotNull AndroCodeParser.While_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#while_loop}.
	 * @param ctx the parse tree
	 */
	void exitWhile_loop(@NotNull AndroCodeParser.While_loopContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#logical_op}.
	 * @param ctx the parse tree
	 */
	void enterLogical_op(@NotNull AndroCodeParser.Logical_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#logical_op}.
	 * @param ctx the parse tree
	 */
	void exitLogical_op(@NotNull AndroCodeParser.Logical_opContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#expr_incr_decr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_incr_decr(@NotNull AndroCodeParser.Expr_incr_decrContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#expr_incr_decr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_incr_decr(@NotNull AndroCodeParser.Expr_incr_decrContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVar_declaration(@NotNull AndroCodeParser.Var_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVar_declaration(@NotNull AndroCodeParser.Var_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statement(@NotNull AndroCodeParser.Return_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statement(@NotNull AndroCodeParser.Return_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#var_or_val}.
	 * @param ctx the parse tree
	 */
	void enterVar_or_val(@NotNull AndroCodeParser.Var_or_valContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#var_or_val}.
	 * @param ctx the parse tree
	 */
	void exitVar_or_val(@NotNull AndroCodeParser.Var_or_valContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#condition_equality}.
	 * @param ctx the parse tree
	 */
	void enterCondition_equality(@NotNull AndroCodeParser.Condition_equalityContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#condition_equality}.
	 * @param ctx the parse tree
	 */
	void exitCondition_equality(@NotNull AndroCodeParser.Condition_equalityContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#expr_var_or_fcall}.
	 * @param ctx the parse tree
	 */
	void enterExpr_var_or_fcall(@NotNull AndroCodeParser.Expr_var_or_fcallContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#expr_var_or_fcall}.
	 * @param ctx the parse tree
	 */
	void exitExpr_var_or_fcall(@NotNull AndroCodeParser.Expr_var_or_fcallContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#dev_release}.
	 * @param ctx the parse tree
	 */
	void enterDev_release(@NotNull AndroCodeParser.Dev_releaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#dev_release}.
	 * @param ctx the parse tree
	 */
	void exitDev_release(@NotNull AndroCodeParser.Dev_releaseContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#condition_combined}.
	 * @param ctx the parse tree
	 */
	void enterCondition_combined(@NotNull AndroCodeParser.Condition_combinedContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#condition_combined}.
	 * @param ctx the parse tree
	 */
	void exitCondition_combined(@NotNull AndroCodeParser.Condition_combinedContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(@NotNull AndroCodeParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(@NotNull AndroCodeParser.ScriptContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#sleep}.
	 * @param ctx the parse tree
	 */
	void enterSleep(@NotNull AndroCodeParser.SleepContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#sleep}.
	 * @param ctx the parse tree
	 */
	void exitSleep(@NotNull AndroCodeParser.SleepContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(@NotNull AndroCodeParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(@NotNull AndroCodeParser.PrintContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#var_call}.
	 * @param ctx the parse tree
	 */
	void enterVar_call(@NotNull AndroCodeParser.Var_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#var_call}.
	 * @param ctx the parse tree
	 */
	void exitVar_call(@NotNull AndroCodeParser.Var_callContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull AndroCodeParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull AndroCodeParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(@NotNull AndroCodeParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(@NotNull AndroCodeParser.AssignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(@NotNull AndroCodeParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(@NotNull AndroCodeParser.ArgumentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(@NotNull AndroCodeParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(@NotNull AndroCodeParser.Function_callContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#lib_includes}.
	 * @param ctx the parse tree
	 */
	void enterLib_includes(@NotNull AndroCodeParser.Lib_includesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#lib_includes}.
	 * @param ctx the parse tree
	 */
	void exitLib_includes(@NotNull AndroCodeParser.Lib_includesContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#dev_get}.
	 * @param ctx the parse tree
	 */
	void enterDev_get(@NotNull AndroCodeParser.Dev_getContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#dev_get}.
	 * @param ctx the parse tree
	 */
	void exitDev_get(@NotNull AndroCodeParser.Dev_getContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#expr_cast}.
	 * @param ctx the parse tree
	 */
	void enterExpr_cast(@NotNull AndroCodeParser.Expr_castContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#expr_cast}.
	 * @param ctx the parse tree
	 */
	void exitExpr_cast(@NotNull AndroCodeParser.Expr_castContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#expr_uminus}.
	 * @param ctx the parse tree
	 */
	void enterExpr_uminus(@NotNull AndroCodeParser.Expr_uminusContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#expr_uminus}.
	 * @param ctx the parse tree
	 */
	void exitExpr_uminus(@NotNull AndroCodeParser.Expr_uminusContext ctx);
}