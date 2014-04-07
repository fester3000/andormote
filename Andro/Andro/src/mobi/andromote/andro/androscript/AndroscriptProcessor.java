package mobi.andromote.andro.androscript;

import mobi.andromote.andro.androscript.datatypes.ScriptProcessStatus;
import mobi.andromote.andro.androscript.datatypes.UnverifiedScript;
import mobi.andromote.andro.androscript.datatypes.VerifiedScript;
import mobi.andromote.andro.androscript.processingTools.Parser;
import mobi.andromote.andro.androscript.processingTools.Validator;

public enum AndroscriptProcessor {
	INSTANCE;
	
	Validator validator;
	Parser parser;
	public ScriptProcessStatus process(UnverifiedScript unverifiedScript) {
		ScriptProcessStatus result;
		ScriptProcessStatus validationResult = validator.validate(unverifiedScript);
		if(validationResult.equals(ScriptProcessStatus.OK)) {
			VerifiedScript verifiedScript = new VerifiedScript(unverifiedScript);
			result = parser.parse(verifiedScript);
		} else {
			result = ScriptProcessStatus.VALIDATION_FAILED;
		}
		return result;
	}
}
