package pl.fester3k.antlr.semanticAnalysis.typeCheck;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import com.google.common.base.Strings;

import pl.fester3k.antlr.androCode.AndroCodeBaseListener;
import pl.fester3k.antlr.androCode.AndroCodeParser.AssignmentContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.BlockContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ConditionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_binopContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_decrContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_devContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_fcallContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_incrContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_parenthesisContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_powContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_uminusContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_unotContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_valueContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_varContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.FunctionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Main_functionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ScriptContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Var_declarationContext;
import pl.fester3k.antlr.semanticAnalysis.Type;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Symbol;

public class TypeCheckingPhase extends AndroCodeBaseListener {

	private GlobalScope globals;
	private ParseTreeProperty<Scope> scopes;
	private ParseTreeProperty<Type> types = new ParseTreeProperty<Type>();
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
	
	

	@Override
	public void exitExpr_dev(Expr_devContext ctx) {
		Type type = Type.BOOLEAN;
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_binop(Expr_binopContext ctx) {
		Type type = ctx.a.evalType;
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_value(Expr_valueContext ctx) {
		int typeTokenType = ctx.value().start.getType();
		Type type = Type.getType(typeTokenType);
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_parenthesis(Expr_parenthesisContext ctx) {
		Type type = ctx.expr().evalType;
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_pow(Expr_powContext ctx) {
		Type type = ctx.subExpr.evalType;
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_unot(Expr_unotContext ctx) {
		Type type = Type.BOOLEAN;
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_incr(Expr_incrContext ctx) {
		Type type = Type.INT;
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_fcall(Expr_fcallContext ctx) {
		Type type = getTypeFrom(ctx.fcal.ID().getText());
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_var(Expr_varContext ctx) {
		String id = ctx.getChild(0).getText();
		Type type = getTypeFrom(id);
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_decr(Expr_decrContext ctx) {
		Type type = Type.INT;
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitExpr_uminus(Expr_uminusContext ctx) {
		Type type = ctx.subExpr.evalType;
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitVar_declaration(Var_declarationContext ctx) {
		Type type = Type.getType(ctx.type().start.getType());
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitAssignment(AssignmentContext ctx) {
		Type type = ctx.a.evalType;
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}

	@Override
	public void exitCondition(ConditionContext ctx) {
		Type type = Type.BOOLEAN;
		ctx.evalType = type;
		printTypeWithContext(type, ctx);
	}
	
	private <T extends ParseTree> void printTypeWithContext(Type type, T ctx) {
		types.put(ctx, type);
		System.out.println("@@ Type " + type + " from " + ctx.getText());
	} 
//	
//	private void printTypeWithContext(Type type, String text) {
//		System.out.println("@@ Type " + type + " from " + text);
//	}

	private Type getTypeFrom(String id) {
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
	
	//3. type checking
			////a) computing static expression types
			////b) automatic type promotion
			////c) enforcing static type safety
	
}
