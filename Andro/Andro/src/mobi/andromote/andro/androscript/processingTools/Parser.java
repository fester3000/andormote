package mobi.andromote.andro.androscript.processingTools;

import android.util.Log;
import mobi.andromote.andro.androscript.datatypes.Script;
import mobi.andromote.andro.androscript.datatypes.ScriptProcessStatus;
import mobi.andromote.andro.runtimeEnv.RuntimeEnvironmentFacade;

public class Parser {
	private static final String TAG = Parser.class.getSimpleName();

	public ScriptProcessStatus parse(Script scirpt) {
		//TODO parse
		Log.d(TAG, "parsing");
		RuntimeEnvironmentFacade.INSTANCE.run(scirpt);
		return ScriptProcessStatus.OK;
	}
}
