package mobi.andromote.am2.functions.ride;

import mobi.andromote.am2.functions.phone.helpers.Compass;
import mobi.andromote.functionalityFramework.datatypes.ActionResult;
import mobi.andromote.functionalityFramework.datatypes.FunctionParam;
import mobi.andromote.functionalityFramework.functions.BaseDeviceFunction;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Motion;
import andro_mote.hardware.ElectronicsController;
import android.content.Context;
import android.hardware.SensorManager;
import android.speech.SpeechRecognizer;


public class RideBearingFunction extends BaseDeviceFunction {
	private static final int _BEARING_TOLERANCE = 5;

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
	private static final double TURN_SPEED = 0.7;
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
		waitForSensor();
		int bearing = compass.getBearing();
		if(isOffset) {
			targetBearing = (bearing + targetBearing) % 360;
			if(targetBearing < 0) {
				targetBearing = 360 - Math.abs(targetBearing);
			}
		}
		goForward();
		sleepFor(300);
		bearing = compass.getBearing();
		while(!(bearing > targetBearing - _BEARING_TOLERANCE && bearing < targetBearing + _BEARING_TOLERANCE)) {
			logger.info("NOW: " + bearing + " " + targetBearing + " " + Math.abs(bearing-targetBearing));
			sleepFor(120);
			logger.info("NOW: " + bearing + " " + targetBearing + " " + Math.abs(bearing-targetBearing));
			if(bearing < targetBearing || bearing > targetBearing + 180 ) {
				turnRight();
			} else {
				turnLeft();
			}
			bearing = compass.getBearing();
			sleepFor(100);
			stop();
			sleepFor(120);
		}
		stop();
	}

	private void waitForSensor() {
		while(!compass.isInitialized()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void goForward() {
		Packet packet;
		packet = new Packet(Motion.MOVE_FORWARD);
		packet.setSpeed(TURN_SPEED);
		ElectronicsController.INSTANCE.execute(packet);
	}

	private void stop() {
		Packet packet;
		packet = new Packet(Motion.STOP);
		ElectronicsController.INSTANCE.execute(packet);
	}

	private void turnLeft() {
		Packet packet;
		logger.info("NOW: LEFT");
		packet = new Packet(Motion.MOVE_CATERPILLAR);
		packet.setSpeed(TURN_SPEED);
		packet.setSpeedB((-TURN_SPEED) + 0.3);
		ElectronicsController.INSTANCE.execute(packet);
	}

	private void turnRight() {
		Packet packet;
		logger.info("NOW: RIGHT");
		packet = new Packet(Motion.MOVE_CATERPILLAR);
		packet.setSpeed((-TURN_SPEED) + 0.3);
		packet.setSpeedB(TURN_SPEED);
		ElectronicsController.INSTANCE.execute(packet);
	}

	private void sleepFor(int sleepingTime) {
		try {
			Thread.sleep(sleepingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
