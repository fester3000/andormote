package pl.fester3k.antlr.semanticAnalysis.typeCheck;

import java.util.List;
import java.util.Map;

import lombok.Getter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.antlr.androCode.AndroCodeBaseListener;
import pl.fester3k.antlr.androCode.AndroCodeParser.ArgumentsContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.AssignmentContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.BlockContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Condition_equalityContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Condition_relationalContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ExprContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_binopContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_castContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_fcallContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.FunctionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Main_functionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Return_statementContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ScriptContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Var_declarationContext;
import pl.fester3k.antlr.semanticAnalysis.Type;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.FunctionSymbol;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Symbol;
import pl.fester3k.prot.utils.Utils;

import com.google.common.base.Strings;

public class TypeCheckingPhase extends AndroCodeBaseListener {	
	private GlobalScope globals;
	private ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	private ParseTreeProperty<Type> types = new ParseTreeProperty<Type>();
	
	@Getter private ParseTreeProperty<Type> promotedTypes = new ParseTreeProperty<Type>();
	@Getter private TokenStreamRewriter rewriter;

	//3. type checking
	////a) computing static expression types
	////b) automatic type promotion
	////c) enforcing static type safety


	public TypeCheckingPhase(GlobalScope globals,
			ParseTreeProperty<Scope> scopes, TokenStream tokens) {
		this.globals = globals;
		this.scopes = scopes;
		rewriter = new TokenStreamRewriter(tokens);
	}
	
	@Override
	public void enterScript(ScriptContext ctx) {
		currentScope = globals;
	}
	
	@Override
	public void exitScript(ScriptContext ctx) {
		System.out.println(rewriter.getText());
	}

	@Override
	public void enterBlock(BlockContext ctx) {
		currentScope = scopes.get(ctx);
	}
	
	@Override
	public void exitBlock(BlockContext ctx) {
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
	public void enterFunction(FunctionContext ctx) {
		currentScope = scopes.get(ctx);
	}

	@Override
	public void exitFunction(FunctionContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}
	
	@Override
	public void exitExpr_cast(Expr_castContext ctx) {
		Type type = Type.getTypeByTokenID(ctx.type().start.getType());
		types.put(ctx, type);
		Utils.printTypeWithContext(type, ctx);
		
		processAssignmentTypePromotion(type, ctx.expr());
		//FIXME sprawdzać czy cast jest legalny
	}

	
	/**
	 * Catches all function calls "id(arguments)"
	 */
	@Override
	public void exitExpr_fcall(Expr_fcallContext ctx) {
		String name = ctx.fcal.ID().getText();
		//FIXME trzeba sprawdzić typ, bo co jeśli ktoś źle zadeklarował?!
		FunctionSymbol function = (FunctionSymbol)currentScope.resolve(name); 
		Map<String, Symbol> declaredParameters = function.getOrderedArgs();
		
		ArgumentsContext argumentsDefinedInFunctionCall = ctx.fcal.arguments();
		
		if(argumentsDefinedInFunctionCall == null) {
			return;
		}
		List<ExprContext> arguments = argumentsDefinedInFunctionCall.expr();
		int i = 0;
		for (Symbol parameter : declaredParameters.values()) {
			Type parameterType = parameter.getType();
			//FIXME Sprawdzać sekwencję
			ExprContext expressionToPromote = arguments.get(i);
			processAssignmentTypePromotion(parameterType, expressionToPromote);
			i++;
		}
	}

	/**
	 * Catches all variable declarations
	 */
	@Override
	public void exitVar_declaration(Var_declarationContext ctx) {
		Type type = Type.getTypeByTokenID(ctx.type().start.getType());
		ExprContext expr = ctx.expr();
		boolean declarationWithAssignment = expr != null;
		if(declarationWithAssignment) {
			processAssignmentTypePromotion(type, expr);
		} 
	}

	/**
	 * Catches all variable assignments
	 */
	@Override
	public void exitAssignment(AssignmentContext ctx) {
		Type type = types.get(ctx.a);
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
		tryToPromote(ctx, Type.BOOLEAN);
	}
		
	/**
	 * Catches all equality conditions
	 */
	@Override
	public void exitCondition_equality(Condition_equalityContext ctx) {
		processAutomaticTypePromotion(Type.equalityResultType, ctx.a, ctx.b, ctx);
		tryToPromote(ctx, Type.BOOLEAN);
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
	
	private void processAssignmentTypePromotion(Type variableType, ExprContext expressionToPromote) {
		Type assignmentExpressionType = types.get(expressionToPromote);
		if(assignmentExpressionType == null) return; //FIXME błąd!!
		
		Type assignmentPromotionType = Type.promoteFromTo[assignmentExpressionType.priority][variableType.priority];
		Utils.printPromotedTypeWithContext(assignmentPromotionType, expressionToPromote);
		
		tryToPromote(expressionToPromote, assignmentPromotionType);
	}

	private <T extends ParserRuleContext> void processAutomaticTypePromotion(Type[][] typeTable, T argA, T argB, ParserRuleContext ctx) {
		Token startToken = ctx.start;
		String messagePrefix = "@@ [" + startToken.getLine() +","+ startToken.getCharPositionInLine() + "] ";
		Type typeA = types.get(argA);
		Type typeB = types.get(argB);
		if(typeA == null  || typeB == null) {
			System.err.println(messagePrefix + "Unable to process automatic type promotion for " + argA.getText() + " and " + argB.getText() + ". Expressions has incompatible types in " + ctx.getText());
			return;
		}
		
		int typePriorityA = typeA.priority;
		int typePriorityB = typeB.priority;
		Type resultType = typeTable[typePriorityA][typePriorityB];
		if (resultType == Type.VOID) {
			System.err.println(messagePrefix + "Arguments " + argA.getText() + " and " + argB.getText() + " has incompatible types in " + ctx.getText());
		} else {
			performPromotion(argA, argB, ctx, typePriorityA, typePriorityB,	resultType);
		}
	}

	private <T extends ParserRuleContext> void performPromotion(T argA, T argB, ParserRuleContext ctx,
			int typePriorityA, int typePriorityB, Type resultType) {
		Type promoteTypeA = Type.promoteFromTo[typePriorityA][resultType.priority];
		Type promoteTypeB = Type.promoteFromTo[typePriorityB][resultType.priority];
		
		Utils.printTypeWithContext(promoteTypeA, argA);
		Utils.printTypeWithContext(promoteTypeB, argB);
		Utils.printPromotedTypeWithContext(resultType, ctx);
		
		tryToPromote(argA, promoteTypeA);
		tryToPromote(argB, promoteTypeB);
		tryToPromote(ctx, resultType);
	}

	private void tryToPromote(ParserRuleContext ctx, Type typeToCast) {
		promotedTypes.put(ctx, typeToCast);
		insertCast(ctx, typeToCast);
	}

	private void insertCast(ParserRuleContext ctx, Type typeToCast) {
		if(typeToCast != null) {
			String textToInsert = "(" + typeToCast + ")";
			rewriter.insertBefore(ctx.start, textToInsert);
		} 
	}
}
