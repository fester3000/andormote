package mobi.andromote.andro.androscript.processingTools;

import org.apache.log4j.Logger;

import android.util.Log;
import mobi.andromote.andro.androscript.datatypes.Script;
import mobi.andromote.andro.androscript.datatypes.ScriptProcessStatus;
import mobi.andromote.andro.runtimeEnv.RuntimeEnvironmentFacade;
import mobi.andromote.andro.webservice.Authenticator;

public class Parser {
	private final Logger log = Logger.getLogger(Parser.class);

	public ScriptProcessStatus parse(Script scirpt) {
		//TODO parse
		log.debug("parsing");
		RuntimeEnvironmentFacade.INSTANCE.run(scirpt);
		return ScriptProcessStatus.OK;
	}
}
