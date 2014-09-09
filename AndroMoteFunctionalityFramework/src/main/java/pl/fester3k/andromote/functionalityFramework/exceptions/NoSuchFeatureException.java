package pl.fester3k.andromote.functionalityFramework.exceptions;

public class NoSuchFeatureException extends FunctionalityFrameworkException {

	public NoSuchFeatureException(String actionName) {
		super("No such action exception " + actionName); 
	}

}
