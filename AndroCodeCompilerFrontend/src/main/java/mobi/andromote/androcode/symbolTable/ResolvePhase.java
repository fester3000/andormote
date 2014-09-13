package mobi.andromote.androcode.symbolTable;

import mobi.andromote.androcode.antlr.AndroCodeParser.BlockContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Dev_execContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Dev_getContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Dev_setParamContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Expr_incr_decrContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.FunctionContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Function_callContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.ScriptContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Var_callContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Var_declarationContext;
import mobi.andromote.androcode.antlr.listeners.AndroCodeListenerWithScopes;
import mobi.andromote.androcode.scopeManagement.GlobalScope;
import mobi.andromote.androcode.scopeManagement.Scope;
import mobi.andromote.androcode.symbolManagement.Symbol;
import mobi.andromote.androcode.utils.Utils;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;


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
