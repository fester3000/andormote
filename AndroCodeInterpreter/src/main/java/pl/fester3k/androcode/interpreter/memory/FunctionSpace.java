package pl.fester3k.androcode.interpreter.memory;

import pl.fester3k.androcode.symbolManagement.FunctionSymbol;

public class FunctionSpace extends MemorySpace {
	private FunctionSymbol functionDef;
	public FunctionSpace(FunctionSymbol function) {
		super(function.getName() + " invocation");
		this.functionDef = function; 
	}
	public FunctionSymbol getFunctionDef() {
		return functionDef;
	}
}
