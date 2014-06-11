package mobi.andromote.andro.androscript;

import org.apache.log4j.Logger;

import android.content.Context;
import android.util.Log;
import mobi.andromote.andro.androscript.datatypes.ScriptProcessStatus;
import mobi.andromote.andro.androscript.datatypes.UnverifiedScript;
import mobi.andromote.andro.androscript.datatypes.VerifiedScript;
import mobi.andromote.andro.androscript.processingTools.Parser;
import mobi.andromote.andro.androscript.processingTools.Validator;
import mobi.andromote.andro.webservice.Authenticator;

public enum AndroscriptProcessor {
	INSTANCE;
	
	private final Logger log = Logger.getLogger(AndroscriptProcessor.class);
			
	public ScriptProcessStatus process(UnverifiedScript unverifiedScript, Context context) {
		Validator validator = new Validator();
		Parser parser = new Parser();
		SDScriptRepository scriptRepository = new SDScriptRepository(context);

		log.debug("processing");
		
		ScriptProcessStatus result;
		ScriptProcessStatus validationResult = validator.validate(unverifiedScript);
		if(validationResult.equals(ScriptProcessStatus.OK)) {
			VerifiedScript verifiedScript = new VerifiedScript(unverifiedScript);
			scriptRepository.saveExternally(verifiedScript);
			result = parser.parse(verifiedScript);
		} else {
			result = ScriptProcessStatus.VALIDATION_FAILED;
		}
		return result;
	}
}
