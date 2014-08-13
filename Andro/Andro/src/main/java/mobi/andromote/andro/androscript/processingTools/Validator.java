package mobi.andromote.andro.androscript.processingTools;

import mobi.andromote.andro.androscript.datatypes.ScriptProcessStatus;
import mobi.andromote.andro.androscript.datatypes.UnverifiedScript;

import org.apache.log4j.Logger;

public class Validator {
	private final Logger log = Logger.getLogger(Validator.class);

	public ScriptProcessStatus validate(UnverifiedScript script) {
		//TODO validate
		log.debug("validating");
		return ScriptProcessStatus.OK;
		}
}
