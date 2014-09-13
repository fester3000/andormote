package mobi.andromote.am2.functions.ride;

import mobi.andromote.am2.functions.phone.helpers.Compass;
import mobi.andromote.functionalityFramework.datatypes.ActionResult;
import mobi.andromote.functionalityFramework.datatypes.FunctionParam;
import mobi.andromote.functionalityFramework.functions.BaseDeviceFunction;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Motion;
import andro_mote.platform_controller.ElectronicsController;
import android.content.Context;
import android.hardware.SensorManager;


public class RideBearingFunction extends BaseDeviceFunction {
	private static final int _BEARING_TOLERANCE = 15;

	/**
	 * parametry akcji - Obieranie kursu
	 * @author Sebastian Luczak
	 *
	 */
	public enum RIDE_BEARING implements FunctionParam {
		/**
		 * Kierunek geograficzny do obrania przez platformę
		 * Podawany w stopniach 0-359 
		 */
		BEARING,
		
		/**
		 * Określa czy podana wartość BEARING to azymut, czy przesunięcie względem obecnego kursu 
		 */
		IS_OFFSET;
	}
	private static final double TURN_SPEED = 0.4;
	Compass compass;
	public RideBearingFunction(Context context) {
		super(context);
		compass = new Compass(context);
		compass.registerListeners(SensorManager.SENSOR_DELAY_FASTEST);
	}

	@Override
	public ActionResult run() {
		ActionResult result;
		logger.debug("Ride action run");
		if(params.containsKey(RIDE_BEARING.BEARING)) {
			int targetBearing = Integer.valueOf(params.get(RIDE_BEARING.BEARING));
			boolean isOffset = false;
			if(params.containsKey(RIDE_BEARING.IS_OFFSET)) {
				isOffset = Boolean.valueOf(params.get(RIDE_BEARING.IS_OFFSET));
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
		while(!(bearing > targetBearing - _BEARING_TOLERANCE && bearing < targetBearing + _BEARING_TOLERANCE)) {
			bearing = compass.getBearing();
			logger.info("NOW: " + bearing + " " + targetBearing + " " + Math.abs(bearing-targetBearing));
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bearing = compass.getBearing();
			logger.info("NOW: " + bearing + " " + targetBearing + " " + Math.abs(bearing-targetBearing));
			if(bearing < targetBearing || bearing > targetBearing + 180 ) {
				logger.info("NOW: RIGHT");
				packet = new Packet(Motion.MOVE_RIGHT_FORWARD);
				packet.setSpeed(TURN_SPEED);
			} else {
				logger.info("NOW: LEFT");
				packet = new Packet(Motion.MOVE_LEFT_FORWARD);
				packet.setSpeed(TURN_SPEED);
			}
			ElectronicsController.INSTANCE.execute(packet);
			try {
				Thread.sleep(300);
				packet = new Packet(Motion.STOP);
				ElectronicsController.INSTANCE.execute(packet);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		packet = new Packet(Motion.STOP);
		ElectronicsController.INSTANCE.execute(packet);
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(RIDE_BEARING.valueOf(propertyName), value);
	}
	
	@Override
	public void cleanup() {
		ElectronicsController.INSTANCE.stopRide();
		super.cleanup();
	}
}
