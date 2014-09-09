package pl.fester3k.andromote.functionalityFramework.exceptions;

public class NoSuchActionException extends FunctionalityFrameworkException {

	public NoSuchActionException(String actionName) {
		super("No such action exception " + actionName); 
	}

}
