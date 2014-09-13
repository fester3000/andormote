package mobi.andromote.androcode.semanticAnalysis.typeCheck;

import mobi.andromote.androcode.antlr.AndroCodeParser.AssignmentContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Condition_combinedContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Condition_equalityContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Condition_negatedContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Condition_relationalContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Condition_var_negatedContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Dev_execContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Dev_getContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_binopContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_castContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_devContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_incr_decrContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_parenthesisContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_uminusContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_valueContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_var_or_fcallContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Function_callContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Var_callContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Var_declarationContext;
import mobi.andromote.androcode.antlr.enums.Type;
import mobi.andromote.androcode.antlr.listeners.AndroCodeListenerWithScopes;
import mobi.andromote.androcode.scopeManagement.GlobalScope;
import mobi.andromote.androcode.scopeManagement.Scope;
import mobi.andromote.androcode.utils.Utils;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

/**
 * 	3. type checking
 *	*a) computing static expression types
 *	b) automatic type promotion
 *	c) enforcing static type safety
 *
 *
 * @author Sebastian Łuczak
 *
 */

public class StaticTypeComputingPhase extends AndroCodeListenerWithScopes {	
	private ParseTreeProperty<Type> types = new ParseTreeProperty<Type>();

	public StaticTypeComputingPhase(GlobalScope globals,
			ParseTreeProperty<Scope> scopes, TokenStream tokens) {
		super(globals, scopes, StaticTypeComputingPhase.class.getSimpleName());
		log.info("Starting static type computing phase");
	}

	/**
	 * Catches all casts 
	 */
	@Override
	public void exitExpr_cast(Expr_castContext ctx) {
		Type type = Type.getTypeByTokenType(ctx.type().start.getType());
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all requests to device 
	 */
	@Override
	public void exitExpr_dev(Expr_devContext ctx) {
		Type type = types.get(ctx.dev_operation());
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all basic type values int, void, char etc.
	 */
	@Override
	public void exitExpr_value(Expr_valueContext ctx) {
		Type type = Type.getTypeByTokenType(ctx.start.getType());
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all expressions enclosed in parenthesis '(' , ')'
	 */
	@Override
	public void exitExpr_parenthesis(Expr_parenthesisContext ctx) {
		Type type = types.get(ctx.expr());
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all minus unary calls "-expr"
	 */
	@Override
	public void exitExpr_uminus(Expr_uminusContext ctx) {
		Type type = types.get(ctx.subExpr);
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all increment/decrement expressions "expr++" "expr--" 
	 */
	@Override
	public void exitExpr_incr_decr(Expr_incr_decrContext ctx) {
		Type type = Type.INT;
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}



	@Override
	public void exitDev_get(Dev_getContext ctx) {
		Type type = Type.DEVICE;
		types.put(ctx,  type);
		log.printTypeWithContext(type, ctx);
	}

	//	/**
	//	 * Catches all variable calls "id"
	//	 */
	//	@Override
	//	public void exitExpr_var(Expr_varContext ctx) {
	//		Type type = Utils.getTypeFromSymbol(ctx.getChild(0).getText(), currentScope);
	//		types.put(ctx, type);
	//		log.printTypeWithContext(type, ctx);
	//	}
	//
	//	/**
	//	 * Catches all function calls "id(arguments)"
	//	 */
	//	@Override
	//	public void exitExpr_fcall(Expr_fcallContext ctx) {
	//		String name = ctx.fcal.ID().getText();
	//		Type type = Utils.getTypeFromSymbol(name, currentScope);
	//		types.put(ctx, type);
	//		log.printTypeWithContext(type, ctx);
	//	}

	@Override
	public void exitVar_call(Var_callContext ctx) {
		Type type = Utils.getTypeFromSymbol(ctx.getChild(0).getText(), currentScope);
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_var_or_fcall(Expr_var_or_fcallContext ctx) {
		Type type = types.get(ctx.var_or_function_call());
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	@Override
	public void exitFunction_call(Function_callContext ctx) {
		String name = ctx.ID().getText();
		Type type = Utils.getTypeFromSymbol(name, currentScope);
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all device execs
	 */
	@Override
	public void exitDev_exec(Dev_execContext ctx) {
		Type type = Type.OBJECT;
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all variable declarations
	 */
	@Override
	public void exitVar_declaration(Var_declarationContext ctx) {
		int typeTokenType = ctx.type().start.getType();
		Type type = Type.getTypeByTokenType(typeTokenType);
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all variable assignments
	 */
	@Override
	public void exitAssignment(AssignmentContext ctx) {
		Type type = Utils.getTypeFromSymbol(ctx.ID().getText(), currentScope);
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all binary arithmetic operations (+,-,*,/)
	 */
	@Override
	public void exitExpr_binop(Expr_binopContext ctx) {
		Type type = types.get(ctx.a);
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all relational conditions
	 */
	@Override
	public void exitCondition_relational(Condition_relationalContext ctx) {
		Type type = Type.BOOLEAN;
		types.put(ctx,type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all equality conditions
	 */
	@Override
	public void exitCondition_equality(Condition_equalityContext ctx) {
		Type type = Type.BOOLEAN;
		types.put(ctx,type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all negated boolean variables or functions 
	 */
	@Override
	public void exitCondition_var_negated(Condition_var_negatedContext ctx) {
		Type type = Type.BOOLEAN;
		types.put(ctx,type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all negated conditions
	 */
	@Override
	public void exitCondition_negated(Condition_negatedContext ctx) {
		Type type = Type.BOOLEAN;
		types.put(ctx,type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all combined conditions
	 */
	@Override
	public void exitCondition_combined(Condition_combinedContext ctx) {
		Type type = Type.BOOLEAN;
		types.put(ctx,type);
		log.printTypeWithContext(type, ctx);
	}

	public ParseTreeProperty<Type> getTypes() {
		return types;
	}


}
