package pl.fester3k.androcode.deviceManagement.action.ride;

import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.RideController;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.Motion;
import android.content.Context;


public class RideGPS extends BaseDeviceAction {
	
	public RideGPS(Context context) {
		super(context);

	}

	@Override
	public ActionResult run() {
		ActionResult result;
		Packet packet;
		logger.debug("Ride action run");
		if(getParams().containsKey("MOTION_TYPE")) {
			String motionType = getParams().getProperty("MOTION_TYPE");
			packet = new Packet(Motion.valueOf(motionType));
			if(getParams().containsKey("SPEED")) {
				String speedParam = getParams().getProperty("SPEED", "0.5");
				double speed = Double.valueOf(speedParam);
				packet.setSpeed(speed);	
			}
			if(getParams().containsKey("SPEED_B")) {
				String speedParam = getParams().getProperty("SPEED_B", "0.5");
				double speed = Double.valueOf(speedParam);
				packet.setSpeedB(speed);	
			}
			result = RideController.INSTANCE.execute(packet);
		} else if(getParams().containsKey("SPEED")) {
			String speedParam = getParams().getProperty("SPEED", "0.5");
			double speed = Double.valueOf(speedParam);
			packet = new Packet(Engine.SET_SPEED);
			packet.setSpeed(speed);
			result = RideController.INSTANCE.execute(packet);
		}
		if(getParams().containsKey("SPEED_B")) {
			String speedParam = getParams().getProperty("SPEED_B", "0.5");
			double speed = Double.valueOf(speedParam);
			packet = new Packet(Engine.SET_SPEED_B);
			packet.setSpeedB(speed);	
			result = RideController.INSTANCE.execute(packet);
		} else {
			result = ActionResult.FAILED;
		}
		return result;
	}

}
