package mobi.andromote.andro.androscript.processingTools;

import mobi.andromote.andro.androscript.datatypes.Script;
import mobi.andromote.andro.androscript.datatypes.ScriptProcessStatus;
import mobi.andromote.andro.runtimeEnv.RuntimeEnvironmentFacade;

public class Parser {
	public ScriptProcessStatus parse(Script scirpt) {
		//TODO parse
		RuntimeEnvironmentFacade.INSTANCE.run(scirpt);
		return null;
	}
}
