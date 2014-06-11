package mobi.andromote.andro.runtimeEnv;

import org.apache.log4j.Logger;

import mobi.andromote.andro.androscript.datatypes.Script;
import mobi.andromote.andro.runtimeEnv.controllers.PhoneController;
import mobi.andromote.andro.runtimeEnv.controllers.RideController;
import mobi.andromote.andro.webservice.Authenticator;

public enum RuntimeEnvironmentFacade {
	INSTANCE;
	
	private final Logger log = Logger.getLogger(RuntimeEnvironmentFacade.class);
	CapabilitiesAnalyzer capabilitiesAnalyzer = CapabilitiesAnalyzer.INSTANCE;
	PhoneController phoneController;
	RideController rideController;
	
	public void run(Script script) {}
	public void resume() {}
	public void pause() {}
	public void stop() {}
}
