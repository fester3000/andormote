package pl.fester3k.antlr.semanticAnalysis.typeCheck;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import pl.fester3k.antlr.androCode.AndroCodeBaseListener;
import pl.fester3k.antlr.androCode.AndroCodeParser;
import pl.fester3k.antlr.androCode.AndroCodeParser.ArgumentsContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.AssignmentContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.BlockContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Condition_equalityContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Condition_relationalContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ExprContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_binopContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_decrContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_devContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_fcallContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_incrContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_parenthesisContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_uminusContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_unotContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_valueContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_varContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.FunctionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Main_functionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Return_statementContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ScriptContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.TypeContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Var_declarationContext;
import pl.fester3k.antlr.semanticAnalysis.Type;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.FunctionSymbol;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Symbol;

import com.google.common.base.Strings;

public class TypeCheckingPhase extends AndroCodeBaseListener {	
	private GlobalScope globals;
	private ParseTreeProperty<Scope> scopes;
	private ParseTreeProperty<Type> types = new ParseTreeProperty<Type>();
	private ParseTreeProperty<Type> promotedTypes = new ParseTreeProperty<Type>();
	private Scope currentScope;

	public TypeCheckingPhase(GlobalScope globals,
			ParseTreeProperty<Scope> scopes) {
		this.globals = globals;
		this.scopes = scopes;
	}
	
	@Override
	public void enterScript(ScriptContext ctx) {
		currentScope = globals;
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
	
	/**
	 * Catches all requests to device 
	 */
	@Override
	public void exitExpr_dev(Expr_devContext ctx) {
		Type type = Type.BOOLEAN;
		types.put(ctx, type);
		printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all basic type values int, void, char etc.
	 */
	@Override
	public void exitExpr_value(Expr_valueContext ctx) {
		Type type = Type.getType(ctx.start.getType());
		types.put(ctx, type);
		printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all expressions enclosed in parenthesis '(' , ')'
	 */
	@Override
	public void exitExpr_parenthesis(Expr_parenthesisContext ctx) {
		Type type = types.get(ctx.expr());
		types.put(ctx, type);
		printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all negated logical expressions "!expr" 
	 */
	@Override
	public void exitExpr_unot(Expr_unotContext ctx) {
		Type type = Type.BOOLEAN;
		types.put(ctx, type);
		printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all increment expressions "++expr"
	 */
	@Override
	public void exitExpr_incr(Expr_incrContext ctx) {
		Type type = Type.INT;
		types.put(ctx, type);
		printTypeWithContext(type, ctx);
	}
	
	/**
	 * Catches all decrement expressions "--expr"
	 */
	@Override
	public void exitExpr_decr(Expr_decrContext ctx) {
		Type type = Type.INT;
		types.put(ctx, type);
		printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all function calls "id(arguments)"
	 */
	@Override
	public void exitExpr_fcall(Expr_fcallContext ctx) {
		String name = ctx.fcal.ID().getText();
		Type type = getTypeFromSymbol(name);
		types.put(ctx, type);
		printTypeWithContext(type, ctx);
		
		FunctionSymbol function = (FunctionSymbol)currentScope.resolve(name);
		Map<String, Symbol> declaredParameters = function.getOrderedArgs();
		List<ExprContext> definedArguments = ctx.fcal.arguments().expr();
		
		int i = 0;
		for (Entry<String, Symbol> parameter : declaredParameters.entrySet()) {
			Type parameterType = parameter.getValue().getType();
			processAssignmentTypePromotion(parameterType, definedArguments.get(i));
			i++;
		}
	}

	/**
	 * Catches all variable calls "id"
	 */
	@Override
	public void exitExpr_var(Expr_varContext ctx) {
		Type type = getTypeFromSymbol(ctx.getChild(0).getText());
		types.put(ctx, type);
		printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all minus unary calls "-expr"
	 */
	@Override
	public void exitExpr_uminus(Expr_uminusContext ctx) {
		Type type = types.get(ctx.subExpr);
		types.put(ctx, type);
		printTypeWithContext(type, ctx);
	}

	/**
	 * Catches all variable declarations
	 */
	@Override
	public void exitVar_declaration(Var_declarationContext ctx) {
		int typeTokenType = ctx.type().start.getType();
		Type evalType = Type.getType(typeTokenType);
		types.put(ctx, evalType);
		printTypeWithContext(evalType, ctx);
		
		ExprContext expr = ctx.expr();
		boolean declarationWithAssignment = expr != null;
		if(declarationWithAssignment) {
			processAssignmentTypePromotion(evalType, expr);
		} 
	}

	/**
	 * Catches all variable assignments
	 */
	@Override
	public void exitAssignment(AssignmentContext ctx) {
		Type evalType = types.get(ctx.a);
		types.put(ctx, evalType);
		printTypeWithContext(evalType, ctx);
		
		processAssignmentTypePromotion(evalType, ctx.b);
	}
	
	/**
	 * Catches all binary arithmetic operations (+,-,*,/)
	 */
	@Override
	public void exitExpr_binop(Expr_binopContext ctx) {
		Type evalType = ctx.a.evalType;
		types.put(ctx, evalType);
		printTypeWithContext(evalType, ctx);
		
		processAutomaticTypePromotion(Type.arithmeticResultType, ctx.a, ctx.b, ctx);
	}
	
	/**
	 * Catches all relational conditions
	 */
	@Override
	public void exitCondition_relational(Condition_relationalContext ctx) {
		Type evalType = ctx.a.evalType;
		types.put(ctx,evalType);
		printTypeWithContext(evalType, ctx);
		
		processAutomaticTypePromotion(Type.relationalResultType, ctx.a, ctx.b, ctx);
	}
		
	/**
	 * Catches all equality conditions
	 */
	@Override
	public void exitCondition_equality(Condition_equalityContext ctx) {
		Type evalType = ctx.a.evalType;
		types.put(ctx,evalType);
		printTypeWithContext(evalType, ctx);
		
		processAutomaticTypePromotion(Type.equalityResultType, ctx.a, ctx.b, ctx);
	}
	
	/**
	 * Catches all return statements
	 */	
	@Override
	public void exitReturn_statement(Return_statementContext ctx) {
		ParserRuleContext currentCtx = ctx;
		boolean isNotFunction = !(currentCtx instanceof FunctionContext);
		boolean isNotMainFunction = !(currentCtx instanceof Main_functionContext);
		while(isNotFunction && isNotMainFunction) {
			currentCtx = currentCtx.getParent();
			isNotFunction = !(currentCtx instanceof FunctionContext);
			isNotMainFunction = !(currentCtx instanceof Main_functionContext);
		}
		
		String functionName = currentCtx.getChild(1).getText();
		Type type = getTypeFromSymbol(functionName);
		
		System.out.println("Funkcja " + type + " " + functionName);
		processAssignmentTypePromotion(type, ctx.expr());
	}	
	
	private void processAssignmentTypePromotion(Type variableType, ExprContext expressionToPromote) {
		Type assignmentExpressionType = types.get(expressionToPromote);
		Type assignmentPromotionType = Type.promoteFromTo[assignmentExpressionType.priority][variableType.priority];
		promotedTypes.put(expressionToPromote, assignmentPromotionType);
		printPromotedTypeWithContext(assignmentPromotionType, expressionToPromote);
	}
	
	/**
	 * Automatic type promotion
	 */
	private <T extends ParseTree> void processAutomaticTypePromotion(Type[][] typeTable, T argA, T argB, ParseTree ctx) {
		int typePriorityA = types.get(argA).priority;
		int typePriorityB = types.get(argB).priority;
		
		Type resultType = typeTable[typePriorityA][typePriorityB];
		Type promoteTypeA = Type.promoteFromTo[typePriorityA][resultType.priority];
		Type promoteTypeB = Type.promoteFromTo[typePriorityB][resultType.priority];
		
		promotedTypes.put(argA, promoteTypeA);
		promotedTypes.put(argB, promoteTypeB);
		promotedTypes.put(ctx, resultType);
		printTypeWithContext(promoteTypeA, argA);
		printTypeWithContext(promoteTypeB, argB);
		printPromotedTypeWithContext(resultType, ctx);
	}
	//3. type checking
			////a) computing static expression types
			////b) automatic type promotion
			////c) enforcing static type safety
	

	private <T extends ParseTree> void printTypeWithContext(Type type, T ctx) {
		System.out.println("@@ Type " + type + " from " + ctx.getText());
	} 
	
	private <T extends ParseTree> void printPromotedTypeWithContext(Type type, T ctx) {
		System.out.println("@@-> PromotedType (" + type + ") from " + ctx.getText());
	} 

	private Type getTypeFromSymbol(String id) {
		if(!Strings.isNullOrEmpty(id)) {
			Symbol s = currentScope.resolve(id);
			if(s != null) {
				System.out.println("@@ Symbol " + s.getType() + " " + s.getName());
				return s.getType();
			} else {
				System.out.println("----- !Symbol " + id + " not declared!");
			}
		}
		return Type.INVALID;
	}
}
