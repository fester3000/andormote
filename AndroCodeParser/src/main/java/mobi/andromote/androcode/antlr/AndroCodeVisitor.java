// Generated from AndroCode.g4 by ANTLR 4.2.2

    package mobi.andromote.androcode.antlr;
    import mobi.andromote.androcode.antlr.enums.Type;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AndroCodeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AndroCodeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(@NotNull AndroCodeParser.BodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#dev_setParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDev_setParam(@NotNull AndroCodeParser.Dev_setParamContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#expr_dev}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_dev(@NotNull AndroCodeParser.Expr_devContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_loop(@NotNull AndroCodeParser.For_loopContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#condition_negated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_negated(@NotNull AndroCodeParser.Condition_negatedContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#expr_binop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_binop(@NotNull AndroCodeParser.Expr_binopContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#condition_var_negated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_var_negated(@NotNull AndroCodeParser.Condition_var_negatedContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull AndroCodeParser.BlockContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#condition_relational}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_relational(@NotNull AndroCodeParser.Condition_relationalContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#expr_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_value(@NotNull AndroCodeParser.Expr_valueContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull AndroCodeParser.TypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#condition_parenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_parenthesis(@NotNull AndroCodeParser.Condition_parenthesisContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(@NotNull AndroCodeParser.FunctionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#expr_parenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_parenthesis(@NotNull AndroCodeParser.Expr_parenthesisContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(@NotNull AndroCodeParser.ParameterContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#mixed_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMixed_string(@NotNull AndroCodeParser.Mixed_stringContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#if_condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_condition(@NotNull AndroCodeParser.If_conditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull AndroCodeParser.ValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#dev_exec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDev_exec(@NotNull AndroCodeParser.Dev_execContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(@NotNull AndroCodeParser.ParametersContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#while_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_loop(@NotNull AndroCodeParser.While_loopContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#logical_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_op(@NotNull AndroCodeParser.Logical_opContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#expr_incr_decr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_incr_decr(@NotNull AndroCodeParser.Expr_incr_decrContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#var_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_declaration(@NotNull AndroCodeParser.Var_declarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#return_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statement(@NotNull AndroCodeParser.Return_statementContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#var_or_val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_or_val(@NotNull AndroCodeParser.Var_or_valContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#condition_equality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_equality(@NotNull AndroCodeParser.Condition_equalityContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#expr_var_or_fcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_var_or_fcall(@NotNull AndroCodeParser.Expr_var_or_fcallContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#dev_release}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDev_release(@NotNull AndroCodeParser.Dev_releaseContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#condition_combined}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_combined(@NotNull AndroCodeParser.Condition_combinedContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScript(@NotNull AndroCodeParser.ScriptContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#sleep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSleep(@NotNull AndroCodeParser.SleepContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(@NotNull AndroCodeParser.PrintContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#var_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_call(@NotNull AndroCodeParser.Var_callContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull AndroCodeParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull AndroCodeParser.AssignmentContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(@NotNull AndroCodeParser.ArgumentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(@NotNull AndroCodeParser.Function_callContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#lib_includes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLib_includes(@NotNull AndroCodeParser.Lib_includesContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#dev_get}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDev_get(@NotNull AndroCodeParser.Dev_getContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#expr_cast}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_cast(@NotNull AndroCodeParser.Expr_castContext ctx);

	/**
	 * Visit a parse tree produced by {@link AndroCodeParser#expr_uminus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_uminus(@NotNull AndroCodeParser.Expr_uminusContext ctx);
}