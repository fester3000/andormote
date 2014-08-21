package mobi.andromote.andro.androscript.processingTools;

import org.apache.log4j.Logger;

import pl.fester3k.androcode.AndroCodePreprocessor;
import pl.fester3k.androcode.datatypes.ScriptProcessStatus;
import pl.fester3k.androcode.datatypes.UnverifiedScript;

public class Validator {
	private final Logger log = Logger.getLogger(Validator.class);
	AndroCodePreprocessor processor = new AndroCodePreprocessor();

	public ScriptProcessStatus validate(UnverifiedScript script) {
		ScriptProcessStatus result;
		log.debug("validating");
		boolean validateCode = processor.validateCode(script.getContent());
		if(validateCode) {
			result = ScriptProcessStatus.OK;
		} else {
			result = ScriptProcessStatus.VALIDATION_FAILED;
		}
		return result;
	}
}
