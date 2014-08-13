package pl.fester3k.androcode.semanticAnalysis.typeCheck;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.androcode.antlr.AndroCodeParser.AssignmentContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_equalityContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_relationalContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_binopContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_castContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_devContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_fcallContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_incr_decrContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_parenthesisContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_uminusContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_valueContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_varContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Var_declarationContext;
import pl.fester3k.androcode.antlr.enums.Type;
import pl.fester3k.androcode.antlr.listeners.AndroCodeListenerWithScopes;
import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.androcode.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.androcode.utils.Utils;

/**
 * 	3. type checking
 *	*a) computing static expression types
 *	b) automatic type promotion
 *	c) enforcing static type safety
 *
 *
 * @author Sebastian
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
		Type type = Type.BOOLEAN;
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
		
	/**
	 * Catches all variable calls "id"
	 */
	@Override
	public void exitExpr_var(Expr_varContext ctx) {
		Type type = Utils.getTypeFromSymbol(ctx.getChild(0).getText(), currentScope);
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all function calls "id(arguments)"
	 */
	@Override
	public void exitExpr_fcall(Expr_fcallContext ctx) {
		String name = ctx.fcal.ID().getText();
		Type type = Utils.getTypeFromSymbol(name, currentScope);
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

	public ParseTreeProperty<Type> getTypes() {
		return types;
	}
	
	
}
