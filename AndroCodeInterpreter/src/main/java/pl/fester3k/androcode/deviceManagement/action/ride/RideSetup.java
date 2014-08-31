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
		if(getParams().containsKey(ActionParams.RIDE_SETUP.MOTION_MODE.toString())) {
			String motionMode = (String)getParams().get(ActionParams.RIDE_SETUP.MOTION_MODE.toString());
			if(motionMode.equals("STEP")) {
				packet = new Packet(Engine.SET_CONTINUOUS_MODE);
			} else {
				packet = new Packet(Engine.SET_STEPPER_MODE);
			}
			result = RideController.INSTANCE.execute(packet);
		} else if(getParams().containsKey(ActionParams.RIDE_SETUP.STEP_DURATION.toString())) {
			// Zmiana czasu trwania kroku
			long stepDuration = Long.valueOf(getParams().getProperty(ActionParams.RIDE_SETUP.STEP_DURATION.toString()));
			packet = new Packet(Engine.SET_STEP_DURATION);
			packet.setStepDuration(stepDuration);	
			result = RideController.INSTANCE.execute(packet);
		} else {
			result = ActionResult.FAILED;
		}
		return result;
	}

}
