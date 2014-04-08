package mobi.andromote.andro.androscript.processingTools;

import android.util.Log;
import mobi.andromote.andro.androscript.AndroscriptProcessor;
import mobi.andromote.andro.androscript.datatypes.ScriptProcessStatus;
import mobi.andromote.andro.androscript.datatypes.UnverifiedScript;

public class Validator {
	private static final String TAG = Validator.class.getSimpleName();

	public ScriptProcessStatus validate(UnverifiedScript script) {
		//TODO validate
		Log.d(TAG, "validating");
		return ScriptProcessStatus.OK;
		}
}
