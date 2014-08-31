package pl.fester3k.androcode.deviceManagement.action.ride;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionParams.RIDE_BEARING;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.RideController;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import pl.fester3k.androcode.deviceManagement.action.phone.helpers.Compass;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Motion;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.AsyncTask;


public class RideBearing extends BaseDeviceAction {
	private static final double TURN_SPEED = 0.4;
	Compass compass;
	public RideBearing(Context context) {
		super(context);
		compass = new Compass(context);
		compass.registerListeners(SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	public ActionResult run() {
		ActionResult result;
		logger.debug("Ride action run");
		if(params.containsKey(ActionParams.RIDE_BEARING.BEARING)) {
			int targetBearing = Integer.valueOf(params.get(RIDE_BEARING.BEARING));
			boolean isOffset = false;
			if(params.containsKey(ActionParams.RIDE_BEARING.IS_OFFSET)) {
				isOffset = Boolean.valueOf(params.get(ActionParams.RIDE_BEARING.IS_OFFSET));
			}
			performManeuver(targetBearing, isOffset);				 
			result = ActionResult.COMPLETED;
		} else {
			result = ActionResult.FAILED;
		}
		return result;
	}

	private void performManeuver(float targetBearing, boolean isOffset) {
		while(!compass.isInitialized()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int bearing = compass.getBearing();
		if(isOffset) {
			targetBearing = (bearing + targetBearing) % 360;
		}
		Packet packet;
		while(!(bearing > targetBearing - 10 && bearing < targetBearing + 10)) {
			bearing = compass.getBearing();
			logger.info("NOW: " + bearing + " " + targetBearing);
			if(bearing < targetBearing || bearing > targetBearing + 180 ) {
				logger.info("NOW: RIGHT");
				packet = new Packet(Motion.MOVE_RIGHT);
				packet.setSpeed(TURN_SPEED);
			} else {
				logger.info("NOW: LEFT");
				packet = new Packet(Motion.MOVE_LEFT);
				packet.setSpeed(TURN_SPEED);
			}
			RideController.INSTANCE.execute(packet);
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(ActionParams.RIDE_BEARING.valueOf(propertyName), value);
	}

}
