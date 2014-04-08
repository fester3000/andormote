package mobi.andromote.andro.androscript;

import android.util.Log;
import mobi.andromote.andro.androscript.datatypes.ScriptProcessStatus;
import mobi.andromote.andro.androscript.datatypes.UnverifiedScript;
import mobi.andromote.andro.androscript.datatypes.VerifiedScript;
import mobi.andromote.andro.androscript.processingTools.Parser;
import mobi.andromote.andro.androscript.processingTools.Validator;

public enum AndroscriptProcessor {
	INSTANCE;
	
	private static final String TAG = AndroscriptProcessor.class.getSimpleName();
			
	public ScriptProcessStatus process(UnverifiedScript unverifiedScript) {
		Validator validator = new Validator();
		Parser parser = new Parser();

		Log.d(TAG, "processing");
		
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
