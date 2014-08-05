package pl.fester3k.antlr.semanticAnalysis.symbols;

import java.util.List;

import lombok.Getter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.antlr.androCode.AndroCodeBaseListener;
import pl.fester3k.antlr.androCode.AndroCodeParser;
import pl.fester3k.antlr.androCode.AndroCodeParser.BlockContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.FunctionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Main_functionContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ParameterContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ParametersContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.ScriptContext;
import pl.fester3k.antlr.androCode.AndroCodeParser.Var_declarationContext;
import pl.fester3k.antlr.semanticAnalysis.Type;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.FunctionSymbol;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.LocalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.VariableSymbol;

public class DefinePhase extends AndroCodeBaseListener {	
	private static final String DELIMITER = "++++++++++++\n";

	@Getter	
	private ParseTreeProperty<Scope> scopes;
	
	@Getter	
	private GlobalScope globals;

	@Getter	
	private Scope currentScope;	

	public DefinePhase() {
		super();
		System.out.println(DELIMITER + "Starting define phase");
		scopes = new ParseTreeProperty<Scope>();
	}

	@Override
	public void enterScript(ScriptContext ctx) {
		globals = new GlobalScope();
		currentScope = globals;
	}

	@Override
	public void exitScript(ScriptContext ctx) {
		System.out.println(DELIMITER + globals);
	}
	
	@Override
	public void enterBlock(BlockContext ctx) {
		currentScope = new LocalScope(currentScope);
		saveScope(ctx, currentScope);
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		System.out.println(DELIMITER + currentScope);
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterFunction(FunctionContext ctx) {
		String name = ctx.ID().getText();
		Type type = Type.getTypeByTokenID(ctx.type().start.getType());
		
		FunctionSymbol function = new FunctionSymbol(name, type, currentScope);
		currentScope.define(function);
		saveScope(ctx, function);
		currentScope = function;
		
//		List<ParameterContext> parameters = ctx.parameters().parameter();		
//		for (ParameterContext parameter : parameters) {
//			String paramName = parameter.stop.getText();
//			Type paramType = Type.getType(parameter.start.getType());
//			VariableSymbol paramSymbol = new VariableSymbol(paramName, paramType);
//			function.define(paramSymbol);
//		}		
	}
	

	@Override
	public void enterMain_function(Main_functionContext ctx) {
		String name = "main";		
		FunctionSymbol function = new FunctionSymbol(name, Type.INT, currentScope);
		currentScope.define(function);
		saveScope(ctx, function);
		currentScope = function;
	}

	@Override
	public void exitFunction(FunctionContext ctx) {
		System.out.println(DELIMITER + currentScope);
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void exitMain_function(Main_functionContext ctx) {
		System.out.println(DELIMITER + currentScope);
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
        Type type = Type.getTypeByTokenID(typeTokenType);
        VariableSymbol var = new VariableSymbol(nameToken.getText(), type);
        currentScope.define(var); // Define symbol in current scope
    }
	
	private void saveScope(ParserRuleContext ctx, Scope s) { 
		scopes.put(ctx, s); 
	}
}
