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
		if(params.containsKey(ActionParams.RIDE_MANUAL.MOTION)) {
			// Jazda ze zdefiniowaną prędkością
			String motionType = params.get(ActionParams.RIDE_MANUAL.MOTION);
			packet = new Packet(Motion.valueOf(motionType));
			if(params.containsKey(ActionParams.RIDE_MANUAL.SPEED)) {
				String speedParam = params.get(ActionParams.RIDE_MANUAL.SPEED);
				double speed = Double.valueOf(speedParam);
				packet.setSpeed(speed);	
			}
			if(params.containsKey(ActionParams.RIDE_MANUAL.SPEED_B)) {
				String speedParam = params.get(ActionParams.RIDE_MANUAL.SPEED_B);
				double speed = Double.valueOf(speedParam);
				packet.setSpeedB(speed);	
			}
			result = RideController.INSTANCE.execute(packet);
		} else if(params.containsKey("SPEED")) {
			// Zmiana samej prędkości, bez zmiany trybu jazdy
			String speedParam = params.get(ActionParams.RIDE_MANUAL.SPEED);
			double speed = Double.valueOf(speedParam);
			packet = new Packet(Engine.SET_SPEED);
			packet.setSpeed(speed);
			result = RideController.INSTANCE.execute(packet);
		} else if(params.containsKey(ActionParams.RIDE_MANUAL.SPEED_B)) {
			// Zmiana samej prędkości prawej gąsienicy (jeśli dotyczy), bez zmiany trybu jazdy
			String speedParam = params.get(ActionParams.RIDE_MANUAL.SPEED_B);
			double speed = Double.valueOf(speedParam);
			packet = new Packet(Engine.SET_SPEED_B);
			packet.setSpeedB(speed);	
			result = RideController.INSTANCE.execute(packet);
		} else {
			result = ActionResult.FAILED;
		}
		return result;
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(ActionParams.RIDE_MANUAL.valueOf(propertyName), value);
	}
}
