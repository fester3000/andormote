package mobi.andromote.functionalityFramework.exceptions;

public class NoSuchFunctionException extends FunctionalityFrameworkException {

	public NoSuchFunctionException(String actionName) {
		super("No such function exception " + actionName); 
	}

}
