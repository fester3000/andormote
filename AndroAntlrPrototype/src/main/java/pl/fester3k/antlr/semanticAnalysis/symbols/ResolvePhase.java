package pl.fester3k.antlr.semanticAnalysis.symbols;

import org.antlr.v4.runtime.ParserRuleContext;
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
import pl.fester3k.prot.utils.PrintUtils;
import pl.fester3k.prot.utils.Utils;

import com.google.common.base.Strings;


public class ResolvePhase extends AndroCodeBaseListener {
	private static final String PREFIX = "<<";
	private static final String DELIMITER = "************\n";
	private final PrintUtils printer;
	private GlobalScope globals;
	private ParseTreeProperty<Scope> scopes;
	private Scope currentScope;

	public ResolvePhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
		System.out.println(DELIMITER + "Starting resolve phase");
		printer = new PrintUtils(PREFIX);
		this.globals = globals;
		this.scopes = scopes;
	}

	@Override
	public void enterScript(ScriptContext ctx) {
		currentScope = globals;
		printer.print(globals.toString(), ctx);
		System.out.println(globals);
	}

	@Override
	public void enterBlock(BlockContext ctx) {
		printer.print(currentScope.toString(), ctx);
		currentScope = scopes.get(ctx);
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterMain_function(Main_functionContext ctx) {
		printer.print(DELIMITER + currentScope, ctx);
		currentScope = scopes.get(ctx);
	}

	@Override
	public void exitMain_function(Main_functionContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterFunction(FunctionContext ctx) {
		printer.print(DELIMITER + currentScope, ctx);
		currentScope = scopes.get(ctx);
	}

	@Override
	public void exitFunction(FunctionContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterFunction_call(Function_callContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);
	}



	@Override
	public void enterExpr_incr(Expr_incrContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);
	}

	@Override
	public void enterExpr_var(Expr_varContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);
	}

	@Override
	public void enterExpr_decr(Expr_decrContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);
	}

	@Override
	public void enterDev_operation(Dev_operationContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);	
	}

	@Override
	public void enterVar_declaration(Var_declarationContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);
	}

	private <T extends ParserRuleContext> void resolveByIdIfNotNull(String id, T ctx) {
		Symbol symbol = Utils.getSymbolById(id, currentScope);
		if(symbol != null) {
			printer.print("Symbol " + symbol.getType() + " " + symbol.getName() +" ok", ctx);
		} else {
			printer.printError("!Symbol " + id + " not declared!", ctx);
		}
	}
}
