package pl.fester3k.prot;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import pl.fester3k.antlr.androCode.AndroCodeBaseListener;
import pl.fester3k.antlr.androCode.AndroCodeParser.ArgumentsContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.AssignmentContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.BlockContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Dev_operationContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ExprContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.For_loopContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.FunctionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Function_callContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.If_conditionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Main_functionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ParameterContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ScriptContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Var_declarationContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.While_loopContext;
import pl.fester3k.antlr.scopeManagement.GlobalScope;
import pl.fester3k.antlr.scopeManagement.Scope;
import pl.fester3k.antlr.scopeManagement.Symbol;


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
		if(id != null ) {
			resolveById(id);
		}
	}
	
	@Override
	public void enterExpr(ExprContext ctx) {
		TerminalNode _ID = ctx.ID();
		if(_ID != null) {
			String id = ctx.ID().getText();
			if(id != null ) {
				resolveById(id);
			}
		}
	}

	@Override
	public void enterDev_operation(Dev_operationContext ctx) {
		String id = ctx.ID().getText();
		if(id != null ) {
			resolveById(id);
		}	
	}

	@Override
	public void enterVar_declaration(Var_declarationContext ctx) {
		String id = ctx.ID().getText();
		if(id != null ) {
			resolveById(id);
		}
	}

	private void resolveById(String id) {
		Symbol s = currentScope.resolve(id);
		if(s != null) {
			System.out.println("++ Symbol " + s.getType() + " " + s.getName() +" ok");
		} else {
			System.out.println("----- !Symbol " + id + " not declared!");
		}
	}
}
