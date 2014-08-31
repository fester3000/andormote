package pl.fester3k.androcode.deviceManagement.action.ride;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.RideController;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.Motion;
import android.content.Context;


public class Ride extends BaseDeviceAction {
	
	private static final String DEFAULT_SPEED = "0.5";

	public Ride(Context context) {
		super(context);

	}

	@Override
	public ActionResult run() {
		ActionResult result;
		Packet packet;
		logger.debug("Ride action run");
		if(getParams().containsKey(ActionParams.RIDE_MANUAL.MOTION.toString())) {
			// Jazda ze zdefiniowaną prędkością
			String motionType = getParams().getProperty(ActionParams.RIDE_MANUAL.MOTION.toString());
			packet = new Packet(Motion.valueOf(motionType));
			if(getParams().containsKey(ActionParams.RIDE_MANUAL.SPEED.toString())) {
				String speedParam = getParams().getProperty(ActionParams.RIDE_MANUAL.SPEED.toString(), DEFAULT_SPEED);
				double speed = Double.valueOf(speedParam);
				packet.setSpeed(speed);	
			}
			if(getParams().containsKey(ActionParams.RIDE_MANUAL.SPEED_B.toString())) {
				String speedParam = getParams().getProperty(ActionParams.RIDE_MANUAL.SPEED_B.toString(), DEFAULT_SPEED);
				double speed = Double.valueOf(speedParam);
				packet.setSpeedB(speed);	
			}
			result = RideController.INSTANCE.execute(packet);
		} else if(getParams().containsKey("SPEED")) {
			// Zmiana samej prędkości, bez zmiany trybu jazdy
			String speedParam = getParams().getProperty("SPEED", DEFAULT_SPEED);
			double speed = Double.valueOf(speedParam);
			packet = new Packet(Engine.SET_SPEED);
			packet.setSpeed(speed);
			result = RideController.INSTANCE.execute(packet);
		} else if(getParams().containsKey("SPEED_B")) {
			// Zmiana samej prędkości prawej gąsienicy (jeśli dotyczy), bez zmiany trybu jazdy
			String speedParam = getParams().getProperty("SPEED_B", DEFAULT_SPEED);
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
