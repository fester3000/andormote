package pl.fester3k.androcode.semanticAnalysis.typeCheck;

import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.androcode.antlr.AndroCodeParser.ArgumentsContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.AssignmentContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ConditionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_equalityContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_negatedContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_relationalContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Condition_var_negatedContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ExprContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_binopContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_castContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_fcallContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_incr_decrContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_uminusContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.FunctionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Main_functionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Return_statementContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ScriptContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Var_declarationContext;
import pl.fester3k.androcode.antlr.enums.Type;
import pl.fester3k.androcode.antlr.listeners.AndroCodeListenerWithScopes;
import pl.fester3k.androcode.scopeManagement.GlobalScope;
import pl.fester3k.androcode.scopeManagement.Scope;
import pl.fester3k.androcode.symbolManagement.FunctionSymbol;
import pl.fester3k.androcode.symbolManagement.Symbol;
import pl.fester3k.androcode.utils.Utils;

/**
 * 	3. type checking
 *	a) computing static expression types
 *	*b) automatic type promotion
 *	*c) enforcing static type safety
 * @author Sebastian
 *
 */
public class TypeCheckingPhase extends AndroCodeListenerWithScopes {
	private boolean error = false;
	private final ParseTreeProperty<Type> types;
	private ParseTreeProperty<Type> promotedTypes = new ParseTreeProperty<Type>();
	private TokenStreamRewriter rewriter;

	public TypeCheckingPhase(GlobalScope globals,
			ParseTreeProperty<Scope> scopes, ParseTreeProperty<Type> types, TokenStream tokens) {
		super(globals, scopes, TypeCheckingPhase.class.getSimpleName());
		log.info("Starting type checking phase");
		this.types = types;
		rewriter = new TokenStreamRewriter(tokens);
	}

	@Override
	public void exitScript(ScriptContext ctx) {
		log.debug(rewriter.getText());
	}

	@Override
	public void exitCondition_negated(Condition_negatedContext ctx) {
		ConditionContext condition = ctx.condition();
		Type type = types.get(condition);

		if(!(type.equals(Type.BOOLEAN))) {
			log.error(condition.getText() + " must have boolean type", ctx);
			error = true;
		}
		types.put(ctx, type);
	}

	@Override
	public void exitCondition_var_negated(
			Condition_var_negatedContext ctx) {
		String id = ctx.var_call().getText();
		Type type = Utils.getTypeFromSymbol(id, currentScope);
		if(!(type.equals(Type.BOOLEAN))) {
			log.error(id + " must have boolean type", ctx);
			error = true;
		}
		types.put(ctx, type);
	}


	@Override
	public void exitExpr_incr_decr(Expr_incr_decrContext ctx) {
		Type type = Utils.getTypeFromSymbol(ctx.ID().getText(), currentScope);
		if(!(type.equals(Type.INT))) {
			log.error(ctx.ID().getText() + " must have boolean type", ctx);
			error = true;
		}
		types.put(ctx, type);
	}

	@Override
	public void exitExpr_uminus(Expr_uminusContext ctx) {
		Type type = types.get(ctx);
		if(!(type.equals(Type.INT) || (type.equals(Type.FLOAT)))) {
			log.error(ctx.getText() + " must have int/float type", ctx);
			error = true;
		}
		types.put(ctx, type);
	}

	@Override
	public void exitExpr_cast(Expr_castContext ctx) {
		Type type = Type.getTypeByTokenType(ctx.type().start.getType());
		types.put(ctx, type);
		log.printTypeWithContext(type, ctx);

		processAssignmentTypePromotion(type, ctx.expr());
		//FIXME sprawdzać czy cast jest legalny
	}

	/**
	 * Catches all function calls "id(arguments)"
	 * Promotes and checks types of function arguments (if they exist)
	 */
	@Override
	public void exitExpr_fcall(Expr_fcallContext ctx) {
		String name = ctx.fcal.ID().getText();
		FunctionSymbol function = (FunctionSymbol)currentScope.resolve(name); 
		Map<String, Symbol> declaredParameters = function.getOrderedArgs();
		ArgumentsContext argumentsDefinedInFunctionCall = ctx.fcal.arguments();
		if(argumentsDefinedInFunctionCall == null) {
			return;
		}
		List<ExprContext> arguments = argumentsDefinedInFunctionCall.expr();
		int i = 0;
		if(declaredParameters.size() != arguments.size()) {
			log.error("number of parameters mismatch!", ctx);
			error = true;
			return;
//			throw new TypeCheckingException("number of parameters mismatch!", ctx);
		}
		for (Symbol parameter : declaredParameters.values()) {
			Type parameterType = parameter.getType();
			ExprContext expressionToPromote = arguments.get(i);
			processAssignmentTypePromotion(parameterType, expressionToPromote);
			i++;
		}
	}

	/**
	 * Catches all variable declarations
	 * Promotes and checks type of assignment if it exists
	 */
	@Override
	public void exitVar_declaration(Var_declarationContext ctx) {
		ExprContext expr = ctx.expr();
		boolean declarationWithAssignment = expr != null;
		if(declarationWithAssignment) {
			Type type = Type.getTypeByTokenType(ctx.type().start.getType());
			processAssignmentTypePromotion(type, expr);
		} 
	}

	/**
	 * Catches all variable assignments
	 */
	@Override
	public void exitAssignment(AssignmentContext ctx) {
		Type type = types.get(ctx);
		processAssignmentTypePromotion(type, ctx.b);
	}

	/**
	 * Catches all binary arithmetic operations (+,-,*,/)
	 */
	@Override
	public void exitExpr_binop(Expr_binopContext ctx) {
		processAutomaticTypePromotion(Type.arithmeticResultType, ctx.a, ctx.b, ctx);
	}

	/**
	 * Catches all relational conditions
	 */
	@Override
	public void exitCondition_relational(Condition_relationalContext ctx) {	
		processAutomaticTypePromotion(Type.relationalResultType, ctx.a, ctx.b, ctx);
		//tryToPromote(ctx, Type.BOOLEAN);
	}

	/**
	 * Catches all equality conditions
	 */
	@Override
	public void exitCondition_equality(Condition_equalityContext ctx) {
		processAutomaticTypePromotion(Type.equalityResultType, ctx.a, ctx.b, ctx);
		//tryToPromote(ctx, Type.BOOLEAN);
	}

	/**
	 * Catches all return statements
	 */	
	@Override
	public void exitReturn_statement(Return_statementContext ctx) {
		ParserRuleContext currentCtx = ctx;
		while(isNotFunction(currentCtx) && isNotMainFunction(currentCtx)) {
			currentCtx = currentCtx.getParent();
		}

		String functionName = currentCtx.getChild(1).getText();
		Type type = Utils.getTypeFromSymbol(functionName, currentScope);
		processAssignmentTypePromotion(type, ctx.expr());
	}

	private boolean isNotMainFunction(ParserRuleContext currentCtx) {
		return !(currentCtx instanceof Main_functionContext);
	}

	private boolean isNotFunction(ParserRuleContext currentCtx) {
		return !(currentCtx instanceof FunctionContext);
	}	

	private void processAssignmentTypePromotion(Type destinationType, ExprContext expressionToPromote) {
		Type expressionType = types.get(expressionToPromote);
		if(expressionType == null) {
			log.error("Unable to process automatic type promotion for " + destinationType + " and " + expressionToPromote.getText() + ". Assigned value has unknown type", expressionToPromote);
			error = true;
			return; //FIXME throw!!
		}
		Type expressionPromotionType = Type.promoteFromTo[expressionType.priority][destinationType.priority];
		log.printPromotedTypeWithContext(expressionPromotionType, expressionToPromote);

		if(canAssignTo(expressionType, expressionPromotionType, destinationType)) {
			//tryToPromote(expressionToPromote, expressionPromotionType);
		} else {
			log.error("Expression's type is incompatible with its destination", expressionToPromote);
			error = true;
		}
	}

	private boolean canAssignTo(Type valueType, Type promotionType, Type destinationType) {
		return valueType == destinationType || promotionType == destinationType;
	}

	private <T extends ParserRuleContext> void processAutomaticTypePromotion(Type[][] typeTable, T argA, T argB, ParserRuleContext ctx) {
		Type typeA = types.get(argA);
		Type typeB = types.get(argB);
		if(typeA == null  || typeB == null) {
			log.error("Unable to process automatic type promotion for " + argA.getText() + " and " + argB.getText() + ". expression types was unrecognized", ctx);
			error = true;
			return; //FIXME throw!!
		}

		int typePriorityA = typeA.priority;
		int typePriorityB = typeB.priority;
		Type resultType = typeTable[typePriorityA][typePriorityB];
		if (resultType == Type.VOID) {
			log.error("Arguments " + argA.getText() + " and " + argB.getText() + " has incompatible types in " + ctx.getText(), ctx);
			error = true;
			return; //FIXME throw!!
		} else {
			//performArgumentPromotion(argA, argB, ctx, typePriorityA, typePriorityB,	resultType);
			log.printTypeWithContext(resultType, ctx);

			//tryToPromote(ctx, resultType);
		}
	}

	private <T extends ParserRuleContext> void performArgumentPromotion(T argA, T argB, ParserRuleContext ctx,
			int typePriorityA, int typePriorityB, Type resultType) {
		Type promoteTypeA = Type.promoteFromTo[typePriorityA][resultType.priority];
		Type promoteTypeB = Type.promoteFromTo[typePriorityB][resultType.priority];

		log.printPromotedTypeWithContext(promoteTypeA, argA);
		log.printPromotedTypeWithContext(promoteTypeB, argB);

		tryToPromote(argA, promoteTypeA);
		tryToPromote(argB, promoteTypeB);
	}

	private void tryToPromote(ParserRuleContext ctx, Type typeToCast) {
		Type type = types.get(ctx);
		if(typeToCast != type) {
			promotedTypes.put(ctx, typeToCast);
			insertCast(ctx, typeToCast);
		}
	}

	private void insertCast(ParserRuleContext ctx, Type typeToCast) {
		if(typeToCast != null) {
			String textToInsert = "(" + typeToCast + ")";
			log.debug("Inserting cast to " + textToInsert, ctx);
			rewriter.insertBefore(ctx.start, textToInsert);
			types.put(ctx, typeToCast);
		} 
	}

	public boolean isError() {
		return error;
	}

	public ParseTreeProperty<Type> getPromotedTypes() {
		return promotedTypes;
	}

	public TokenStreamRewriter getRewriter() {
		return rewriter;
	}
}
