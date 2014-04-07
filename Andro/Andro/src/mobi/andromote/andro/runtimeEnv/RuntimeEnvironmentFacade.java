package mobi.andromote.andro.runtimeEnv;

import mobi.andromote.andro.androscript.datatypes.Script;
import mobi.andromote.andro.runtimeEnv.controllers.PhoneController;
import mobi.andromote.andro.runtimeEnv.controllers.RideController;

public enum RuntimeEnvironmentFacade {
	INSTANCE;
	
	CapabilitiesAnalyzer capabilitiesAnalyzer = CapabilitiesAnalyzer.INSTANCE;
	PhoneController phoneController;
	RideController rideController;
	
	public void run(Script script) {}
	public void resume() {}
	public void pause() {}
	public void stop() {}
}
