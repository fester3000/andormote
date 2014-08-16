package pl.fester3k.androcode.runtimeEnvironment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.androcode.datatypes.Script;
import pl.fester3k.androcode.runtimeEnvironment.controllers.PhoneController;
import pl.fester3k.androcode.runtimeEnvironment.controllers.RideController;

public enum RuntimeEnvironmentFacade {
	INSTANCE;
	
	private final Logger log = LoggerFactory.getLogger(RuntimeEnvironmentFacade.class);
	PhoneController phoneController;
	RideController rideController;
	
	public void run() {}
	public void resume() {}
	public void pause() {}
	public void stop() {}
}
