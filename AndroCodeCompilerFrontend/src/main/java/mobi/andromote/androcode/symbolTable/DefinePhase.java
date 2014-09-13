package mobi.andromote.androcode.symbolTable;

import mobi.andromote.androcode.antlr.AndroCodeBaseListener;
import mobi.andromote.androcode.antlr.AndroCodeParser;
import mobi.andromote.androcode.antlr.AndroCodeParser.BlockContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.FunctionContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.ParameterContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.ScriptContext;
import mobi.andromote.androcode.antlr.AndroCodeParser.Var_declarationContext;
import mobi.andromote.androcode.antlr.enums.Type;
import mobi.andromote.androcode.logger.AndroLog;
import mobi.andromote.androcode.scopeManagement.GlobalScope;
import mobi.andromote.androcode.scopeManagement.LocalScope;
import mobi.andromote.androcode.scopeManagement.Scope;
import mobi.andromote.androcode.symbolManagement.FunctionSymbol;
import mobi.andromote.androcode.symbolManagement.VariableSymbol;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

/**
 * TODO Kontrola unikalności identyfikatorów w ramach scope'ów (i w górę hierarchii)
 * @author Sebastian Łuczak
 *
 */
public class DefinePhase extends AndroCodeBaseListener {	
	private final AndroLog log;

	private ParseTreeProperty<Scope> scopes;
	private GlobalScope globals;
	private Scope currentScope;	

	public DefinePhase() {
		super();
		this.log = new AndroLog(DefinePhase.class.getSimpleName()); 
		log.info("Starting define phase");
		scopes = new ParseTreeProperty<Scope>();
	}

	@Override
	public void enterScript(ScriptContext ctx) {
		globals = new GlobalScope();
		currentScope = globals;
	}

	@Override
	public void exitScript(ScriptContext ctx) {
		log.debug("exitScript " + globals, ctx);
		
	}
	
	@Override
	public void enterBlock(BlockContext ctx) {
		currentScope = new LocalScope(currentScope);
		saveScope(ctx, currentScope);
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		log.debug("exitBlock " + currentScope, ctx);
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterFunction(FunctionContext ctx) {
		String name = ctx.ID().getText();
		Type type = Type.getTypeByTokenType(ctx.type().start.getType());
		
		FunctionSymbol function = new FunctionSymbol(name, type, currentScope);
		currentScope.define(function);
		saveScope(ctx, function);
		currentScope = function;
	}

	@Override
	public void exitFunction(FunctionContext ctx) {
		log.debug("exitFunction " + currentScope, ctx);
		currentScope = currentScope.getEnclosingScope();
	}	
	
	@Override
	public void exitParameter(ParameterContext ctx) {
		defineVar(ctx.type(), ctx.ID().getSymbol());
	}

	@Override
	public void exitVar_declaration(Var_declarationContext ctx) {
		defineVar(ctx.type(), ctx.ID().getSymbol());
	}

	private void defineVar(AndroCodeParser.TypeContext typeCtx, Token nameToken) {
        int typeTokenType = typeCtx.start.getType();
        Type type = Type.getTypeByTokenType(typeTokenType);
        VariableSymbol var = new VariableSymbol(nameToken.getText(), type);
        currentScope.define(var); // Define symbol in current scope
    }
	
	private void saveScope(ParserRuleContext ctx, Scope s) { 
		scopes.put(ctx, s); 
	}

	public ParseTreeProperty<Scope> getScopes() {
		return scopes;
	}

	public GlobalScope getGlobals() {
		return globals;
	}

	public Scope getCurrentScope() {
		return currentScope;
	}
}
