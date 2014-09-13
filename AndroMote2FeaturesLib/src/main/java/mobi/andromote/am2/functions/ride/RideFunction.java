package mobi.andromote.am2.functions.ride;

import mobi.andromote.functionalityFramework.datatypes.FunctionParam;
import mobi.andromote.functionalityFramework.functions.BaseDeviceFunction;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.Motion;
import andro_mote.platform_controller.ElectronicsController;
import android.content.Context;


public class RideFunction extends BaseDeviceFunction {
	
	/**
	 * Parametry akcji - Sterowanie ręczne
	 * @author Sebastian Luczak
	 *
	 */
	public enum RIDE_MANUAL implements FunctionParam {
		/**
		 * Prędkość/prędkość lewej gąsienicy (jeśli dotyczy)
		 */
		SPEED,
		/**
		 * Prędkość prawej gąsienicy (jeśli dotyczy)
		 */
		SPEED_B,
		/**
		 * Tryb ruchu zgodny z {@link Motion}
		 */
		MOTION;
	}

	public RideFunction(Context context) {
		super(context);
	}

	@Override
	public Boolean run() {
		boolean result;
		Packet packet;
		logger.debug("Ride action run");
		if(params.containsKey(RideFunction.RIDE_MANUAL.MOTION)) {
			// Jazda ze zdefiniowaną prędkością
			String motionType = params.get(RideFunction.RIDE_MANUAL.MOTION);
			packet = new Packet(Motion.valueOf(motionType));
			if(params.containsKey(RideFunction.RIDE_MANUAL.SPEED)) {
				String speedParam = params.get(RideFunction.RIDE_MANUAL.SPEED);
				double speed = Double.valueOf(speedParam);
				packet.setSpeed(speed);	
			}
			if(params.containsKey(RideFunction.RIDE_MANUAL.SPEED_B)) {
				String speedParam = params.get(RideFunction.RIDE_MANUAL.SPEED_B);
				double speed = Double.valueOf(speedParam);
				packet.setSpeedB(speed);	
			}
			result = ElectronicsController.INSTANCE.execute(packet);
		} else if(params.containsKey("SPEED")) {
			// Zmiana samej prędkości, bez zmiany trybu jazdy
			String speedParam = params.get(RideFunction.RIDE_MANUAL.SPEED);
			double speed = Double.valueOf(speedParam);
			packet = new Packet(Engine.SET_SPEED);
			packet.setSpeed(speed);
			result = ElectronicsController.INSTANCE.execute(packet);
		} else if(params.containsKey(RideFunction.RIDE_MANUAL.SPEED_B)) {
			// Zmiana samej prędkości prawej gąsienicy (jeśli dotyczy), bez zmiany trybu jazdy
			String speedParam = params.get(RideFunction.RIDE_MANUAL.SPEED_B);
			double speed = Double.valueOf(speedParam);
			packet = new Packet(Engine.SET_SPEED_B);
			packet.setSpeedB(speed);	
			result = ElectronicsController.INSTANCE.execute(packet);
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(RideFunction.RIDE_MANUAL.valueOf(propertyName), value);
	}

	@Override
	public void cleanup() {
		ElectronicsController.INSTANCE.stopRide();
		super.cleanup();
	}	
}
