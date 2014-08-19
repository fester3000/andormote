package pl.fester3k.androcode.semanticAnalysis;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.androcode.antlr.AndroCodeParser.BlockContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Dev_execContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Dev_getContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Dev_operationContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Dev_setParamContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Expr_incr_decrContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.FunctionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Function_callContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Main_functionContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.ScriptContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Var_callContext;
import pl.fester3k.androcode.antlr.AndroCodeParser.Var_declarationContext;
import pl.fester3k.androcode.antlr.listeners.AndroCodeListenerWithScopes;
import pl.fester3k.androcode.scopeManagement.GlobalScope;
import pl.fester3k.androcode.scopeManagement.Scope;
import pl.fester3k.androcode.symbolManagement.Symbol;
import pl.fester3k.androcode.utils.Utils;


public class ResolvePhase extends AndroCodeListenerWithScopes {
	private boolean error = false;

	public ResolvePhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
		super(globals, scopes, ResolvePhase.class.getSimpleName());
		log.info("Starting resolve phase");
	}

	@Override
	public void enterScript(ScriptContext ctx) {
		super.enterScript(ctx);
		log.debug(globals.toString(), ctx);
	}

	@Override
	public void enterBlock(BlockContext ctx) {
		super.enterBlock(ctx);
		log.debug("exitBlock " + currentScope, ctx);
	}

	@Override
	public void enterMain_function(Main_functionContext ctx) {
		super.enterMain_function(ctx);
		log.debug("exitMainFunction " + currentScope, ctx);
	}

	@Override
	public void enterFunction(FunctionContext ctx) {
		super.enterFunction(ctx);
		log.debug("exitFunction " + currentScope, ctx);		
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
	public void enterDev_setParam(Dev_setParamContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);	
		super.enterDev_setParam(ctx);
	}

	@Override
	public void enterDev_exec(Dev_execContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);	
		super.enterDev_exec(ctx);
	}

	@Override
	public void enterDev_get(Dev_getContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);	
		super.enterDev_get(ctx);
	}

	@Override
	public void enterVar_declaration(Var_declarationContext ctx) {
		String id = ctx.ID().getText();
		resolveByIdIfNotNull(id, ctx);
	}

	private <T extends ParserRuleContext> void resolveByIdIfNotNull(String id, T ctx) {
		Symbol symbol = Utils.getSymbolById(id, currentScope);
		if(symbol != null) {
			log.debug("Symbol " + symbol.getType() + " " + symbol.getName() +" ok", ctx);
		} else {
			log.error("!Symbol " + id + " not declared!", ctx);
			error = true;
		}
	}

	public boolean isError() {
		return error;
	}
}
