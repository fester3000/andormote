package pl.fester3k.antlr.semanticAnalysis.symbols;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.antlr.androCode.AndroCodeParser.BlockContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Dev_operationContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_incr_decrContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Expr_varContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.FunctionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Function_callContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Main_functionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ScriptContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Var_callContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Var_declarationContext;
import pl.fester3k.antlr.androCode.listeners.AndroCodeListenerWithScopes;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Symbol;
import pl.fester3k.prot.utils.Utils;


public class ResolvePhase extends AndroCodeListenerWithScopes {
	private static final String PREFIX = "<<";
	private static final String DELIMITER = "************\n";

	public ResolvePhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
		super(globals,scopes,PREFIX);
		System.out.println(DELIMITER + "Starting resolve phase");
	}

	@Override
	public void enterScript(ScriptContext ctx) {
		super.enterScript(ctx);
		printer.print(globals.toString(), ctx);
	}

	@Override
	public void enterBlock(BlockContext ctx) {
		super.enterBlock(ctx);
		printer.print(currentScope.toString(), ctx);
	}

	@Override
	public void enterMain_function(Main_functionContext ctx) {
		super.enterMain_function(ctx);
		printer.print(DELIMITER + currentScope, ctx);
	}

	@Override
	public void enterFunction(FunctionContext ctx) {
		super.enterFunction(ctx);
		printer.print(DELIMITER + currentScope, ctx);		
	}

	
	@Override
	public void enterFunction_call(Function_callContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);
	}

	@Override
	public void enterExpr_incr_decr(Expr_incr_decrContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);
	}

	@Override
	public void enterVar_call(Var_callContext ctx) {
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
