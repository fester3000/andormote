package pl.fester3k.androcode.interpreter.exceptions;

public class NoSuchActionException extends InterpreterException {

	public NoSuchActionException(String actionName) {
		super("No such action exception " + actionName); 
	}

}
