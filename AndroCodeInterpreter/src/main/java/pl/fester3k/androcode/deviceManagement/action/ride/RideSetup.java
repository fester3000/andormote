package pl.fester3k.androcode.deviceManagement.action.ride;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.RideController;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Engine;
import android.content.Context;


public class RideSetup extends BaseDeviceAction {	
	public RideSetup(Context context) {
		super(context);

	}

	@Override
	public ActionResult run() {
		ActionResult result;
		Packet packet;
		logger.debug("Ride action run");
		if(params.containsKey(ActionParams.RIDE_SETUP.MOTION_MODE.toString())) {
			String motionMode = (String)params.get(ActionParams.RIDE_SETUP.MOTION_MODE);
			if(motionMode.equals("STEP")) {
				packet = new Packet(Engine.SET_CONTINUOUS_MODE);
			} else {
				packet = new Packet(Engine.SET_STEPPER_MODE);
			}
			result = RideController.INSTANCE.execute(packet);
		} else if(params.containsKey(ActionParams.RIDE_SETUP.STEP_DURATION)) {
			// Zmiana czasu trwania kroku
			long stepDuration = Long.valueOf((String)params.get(ActionParams.RIDE_SETUP.STEP_DURATION));
			packet = new Packet(Engine.SET_STEP_DURATION);
			packet.setStepDuration(stepDuration);	
			result = RideController.INSTANCE.execute(packet);
		} else {
			result = ActionResult.FAILED;
		}
		return result;
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(ActionParams.RIDE_SETUP.valueOf(propertyName), value);
	}
}
