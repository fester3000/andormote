package pl.fester3k.androcode.interpreter.exceptions;

public class NoSuchFeatureException extends InterpreterException {

	public NoSuchFeatureException(String actionName) {
		super("No such action exception " + actionName); 
	}

}
