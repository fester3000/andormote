package mobi.andromote.androcode.interpreter.memory;

import mobi.andromote.androcode.symbolManagement.FunctionSymbol;

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
