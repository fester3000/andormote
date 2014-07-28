// Generated from AndroCode.g4 by ANTLR 4.2.2

    package pl.fester3k.antlr.androCode;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AndroCodeParser}.
 */
public interface AndroCodeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#stat_for_loop}.
	 * @param ctx the parse tree
	 */
	void enterStat_for_loop(@NotNull AndroCodeParser.Stat_for_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#stat_for_loop}.
	 * @param ctx the parse tree
	 */
	void exitStat_for_loop(@NotNull AndroCodeParser.Stat_for_loopContext ctx);

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
	 * Enter a parse tree produced by {@link AndroCodeParser#stat_block}.
	 * @param ctx the parse tree
	 */
	void enterStat_block(@NotNull AndroCodeParser.Stat_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#stat_block}.
	 * @param ctx the parse tree
	 */
	void exitStat_block(@NotNull AndroCodeParser.Stat_blockContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#dev_operation}.
	 * @param ctx the parse tree
	 */
	void enterDev_operation(@NotNull AndroCodeParser.Dev_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#dev_operation}.
	 * @param ctx the parse tree
	 */
	void exitDev_operation(@NotNull AndroCodeParser.Dev_operationContext ctx);

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
	 * Enter a parse tree produced by {@link AndroCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull AndroCodeParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull AndroCodeParser.ExprContext ctx);

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
	 * Enter a parse tree produced by {@link AndroCodeParser#stat_while_loop}.
	 * @param ctx the parse tree
	 */
	void enterStat_while_loop(@NotNull AndroCodeParser.Stat_while_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#stat_while_loop}.
	 * @param ctx the parse tree
	 */
	void exitStat_while_loop(@NotNull AndroCodeParser.Stat_while_loopContext ctx);

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
	 * Enter a parse tree produced by {@link AndroCodeParser#stat_return}.
	 * @param ctx the parse tree
	 */
	void enterStat_return(@NotNull AndroCodeParser.Stat_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#stat_return}.
	 * @param ctx the parse tree
	 */
	void exitStat_return(@NotNull AndroCodeParser.Stat_returnContext ctx);

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
	 * Enter a parse tree produced by {@link AndroCodeParser#main_function}.
	 * @param ctx the parse tree
	 */
	void enterMain_function(@NotNull AndroCodeParser.Main_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#main_function}.
	 * @param ctx the parse tree
	 */
	void exitMain_function(@NotNull AndroCodeParser.Main_functionContext ctx);

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
	 * Enter a parse tree produced by {@link AndroCodeParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull AndroCodeParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull AndroCodeParser.ConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link AndroCodeParser#stat_if_condition}.
	 * @param ctx the parse tree
	 */
	void enterStat_if_condition(@NotNull AndroCodeParser.Stat_if_conditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#stat_if_condition}.
	 * @param ctx the parse tree
	 */
	void exitStat_if_condition(@NotNull AndroCodeParser.Stat_if_conditionContext ctx);

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
	 * Enter a parse tree produced by {@link AndroCodeParser#stat_expr}.
	 * @param ctx the parse tree
	 */
	void enterStat_expr(@NotNull AndroCodeParser.Stat_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#stat_expr}.
	 * @param ctx the parse tree
	 */
	void exitStat_expr(@NotNull AndroCodeParser.Stat_exprContext ctx);

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
	 * Enter a parse tree produced by {@link AndroCodeParser#stat_var_declaration}.
	 * @param ctx the parse tree
	 */
	void enterStat_var_declaration(@NotNull AndroCodeParser.Stat_var_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#stat_var_declaration}.
	 * @param ctx the parse tree
	 */
	void exitStat_var_declaration(@NotNull AndroCodeParser.Stat_var_declarationContext ctx);

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
	 * Enter a parse tree produced by {@link AndroCodeParser#stat_assignment}.
	 * @param ctx the parse tree
	 */
	void enterStat_assignment(@NotNull AndroCodeParser.Stat_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link AndroCodeParser#stat_assignment}.
	 * @param ctx the parse tree
	 */
	void exitStat_assignment(@NotNull AndroCodeParser.Stat_assignmentContext ctx);
}