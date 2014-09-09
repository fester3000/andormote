package pl.fester3k.andromote.functionalityFramework.functions.ride;

import pl.fester3k.andromote.functionalityFramework.datatypes.ActionParams;
import pl.fester3k.andromote.functionalityFramework.functions.BaseDeviceFunction;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Engine;
import andro_mote.platform_controller.VehicleController;
import android.content.Context;


public class RideSetupFunction extends BaseDeviceFunction {	
	public RideSetupFunction(Context context) {
		super(context);

	}

	@Override
	public Boolean run() {
		boolean result;
		Packet packet;
		logger.debug("Ride action run");
		if(params.containsKey(ActionParams.RIDE_SETUP.MOTION_MODE.toString())) {
			String motionMode = (String)params.get(ActionParams.RIDE_SETUP.MOTION_MODE);
			if(motionMode.equals("STEP")) {
				packet = new Packet(Engine.SET_CONTINUOUS_MODE);
			} else {
				packet = new Packet(Engine.SET_STEPPER_MODE);
			}
			result = VehicleController.INSTANCE.execute(packet);
		} else if(params.containsKey(ActionParams.RIDE_SETUP.STEP_DURATION)) {
			// Zmiana czasu trwania kroku
			long stepDuration = Long.valueOf((String)params.get(ActionParams.RIDE_SETUP.STEP_DURATION));
			packet = new Packet(Engine.SET_STEP_DURATION);
			packet.setStepDuration(stepDuration);	
			result = VehicleController.INSTANCE.execute(packet);
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(ActionParams.RIDE_SETUP.valueOf(propertyName), value);
	}
}
