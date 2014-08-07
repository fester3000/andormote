package pl.fester3k.antlr.interpreter.memory;

import lombok.Getter;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.FunctionSymbol;

public class FunctionSpace extends MemorySpace {
	@Getter private FunctionSymbol functionDef;
	public FunctionSpace(FunctionSymbol function) {
		super(function.getName() + " invocation");
		this.functionDef = function; 
	}
}
