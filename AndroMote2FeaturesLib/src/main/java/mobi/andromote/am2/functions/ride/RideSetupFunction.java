package mobi.andromote.am2.functions.ride;

import mobi.andromote.functionalityFramework.datatypes.FunctionParam;
import mobi.andromote.functionalityFramework.functions.BaseDeviceFunction;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Engine;
import andro_mote.platform_controller.ElectronicsController;
import android.content.Context;


public class RideSetupFunction extends BaseDeviceFunction {	
	/**
	 * parametry akcji - Konfiguracja platformy jezdnej
	 * @author Sebastian Luczak
	 *
	 */
	public enum RIDE_SETUP implements FunctionParam {
		/**
		 * Ruch ciągły lub krokowy
		 * CONT, STEP
		 */
		MOTION_MODE,
		
		/**
		 * Czas trwania kroku w przypadku ruchu ciągłego
		 */
		STEP_DURATION;
	}
	public RideSetupFunction(Context context) {
		super(context);

	}

	@Override
	public Boolean run() {
		boolean result;
		Packet packet;
		logger.debug("Ride action run");
		if(params.containsKey(RIDE_SETUP.MOTION_MODE)) {
			String motionMode = (String)params.get(RIDE_SETUP.MOTION_MODE);
			if(motionMode.equals("STEP")) {
				packet = new Packet(Engine.SET_STEPPER_MODE);
			} else {
				packet = new Packet(Engine.SET_CONTINUOUS_MODE);
			}
			result = ElectronicsController.INSTANCE.execute(packet);
		} else if(params.containsKey(RIDE_SETUP.STEP_DURATION)) {
			// Zmiana czasu trwania kroku
			long stepDuration = Long.valueOf((String)params.get(RIDE_SETUP.STEP_DURATION));
			packet = new Packet(Engine.SET_STEP_DURATION);
			packet.setStepDuration(stepDuration);	
			result = ElectronicsController.INSTANCE.execute(packet);
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(RIDE_SETUP.valueOf(propertyName), value);
	}

	@Override
	public void cleanup() {
		Packet packet = new Packet(Engine.SET_CONTINUOUS_MODE);
		ElectronicsController.INSTANCE.execute(packet);
		super.cleanup();
	}
	
	
}
