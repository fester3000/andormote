package pl.fester3k.antlr.semanticAnalysis.symbols;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import pl.fester3k.antlr.androCode.AndroCodeBaseListener;
import pl.fester3k.antlr.androCode.AndroCodeParser.BlockContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Dev_operationContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ExprContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_decrContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_incrContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_varContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.FunctionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Function_callContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Main_functionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ScriptContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Var_declarationContext;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Symbol;

import com.google.common.base.Strings;


public class ResolvePhase extends AndroCodeBaseListener {
	private static final String DELIMITER = "************\n";
	private GlobalScope globals;
	private ParseTreeProperty<Scope> scopes;
	private Scope currentScope;

	public ResolvePhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
		System.out.println(DELIMITER + "Starting resolve phase");
		this.globals = globals;
		this.scopes = scopes;
	}

	@Override
	public void enterScript(ScriptContext ctx) {
		currentScope = globals;
		System.out.println(globals);
	}
	
	@Override
	public void enterBlock(BlockContext ctx) {
		System.out.println(currentScope);
		currentScope = scopes.get(ctx);
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}
	
	@Override
	public void enterMain_function(Main_functionContext ctx) {
		System.out.println(DELIMITER + currentScope);
		currentScope = scopes.get(ctx);
	}

	@Override
	public void exitMain_function(Main_functionContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}
	
	@Override
	public void enterFunction(FunctionContext ctx) {
		System.out.println(DELIMITER + currentScope);
		currentScope = scopes.get(ctx);
	}

	@Override
	public void exitFunction(FunctionContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}
	
	@Override
	public void enterFunction_call(Function_callContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id);
	}
	
	
	
	@Override
	public void enterExpr_incr(Expr_incrContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id);
	}

	@Override
	public void enterExpr_var(Expr_varContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id);
	}

	@Override
	public void enterExpr_decr(Expr_decrContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id);
	}

	@Override
	public void enterDev_operation(Dev_operationContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id);	
	}

	@Override
	public void enterVar_declaration(Var_declarationContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id);
	}

	private void resolveByIdIfNotNull(String id) {
		if(!Strings.isNullOrEmpty(id)) {
			Symbol s = currentScope.resolve(id);
			if(s != null) {
				System.out.println("++ Symbol " + s.getType() + " " + s.getName() +" ok");
			} else {
				System.out.println("----- !Symbol " + id + " not declared!");
			}
		}
	}
}
