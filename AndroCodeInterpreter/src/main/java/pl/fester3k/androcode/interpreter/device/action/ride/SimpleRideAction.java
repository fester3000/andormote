package pl.fester3k.androcode.interpreter.device.action.ride;

import java.util.Properties;

import pl.fester3k.androcode.interpreter.device.RideController;
import pl.fester3k.androcode.interpreter.device.action.ActionResult;
import pl.fester3k.androcode.interpreter.device.action.BaseDeviceAction;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.Motion;
import android.app.Activity;
import android.content.Context;


public class SimpleRideAction extends BaseDeviceAction {
	
	public SimpleRideAction(Context context, Properties params) {
		super(context, params);

	}

	@Override
	public ActionResult run() {
		ActionResult result;
		Packet packet;
		logger.debug("Ride action run");
		if(params.containsKey("MOTION_TYPE")) {
			String motionType = params.getProperty("MOTION_TYPE");
			packet = new Packet(Motion.valueOf(motionType));
			if(params.containsKey("SPEED")) {
				String speedParam = params.getProperty("SPEED", "0.5");
				double speed = Double.valueOf(speedParam);
				packet.setSpeed(speed);	
			}
			if(params.containsKey("SPEED_B")) {
				String speedParam = params.getProperty("SPEED_B", "0.5");
				double speed = Double.valueOf(speedParam);
				packet.setSpeedB(speed);	
			}
			result = RideController.INSTANCE.execute(packet);
		} else if(params.containsKey("SPEED")) {
			String speedParam = params.getProperty("SPEED", "0.5");
			double speed = Double.valueOf(speedParam);
			packet = new Packet(Engine.SET_SPEED);
			packet.setSpeed(speed);
			result = RideController.INSTANCE.execute(packet);
		}
		if(params.containsKey("SPEED_B")) {
			String speedParam = params.getProperty("SPEED_B", "0.5");
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
