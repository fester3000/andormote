package andro_mote.devices.factories;

import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.DeviceDefinitions;
import andro_mote.devices.andromote_v1.MotorDriverNewBrightCompatible;
import andro_mote.devices.andromote_v1.PlatformNewBright;
import andro_mote.devices.andromote_v2.MotorDriverRover5Compatible;
import andro_mote.devices.andromote_v2.Rover5Platform;
import andro_mote.devices.generics.MotorDriver;
import andro_mote.devices.generics.Platform;
import andro_mote.logger.AndroMoteLogger;

/**
 * Klasa fabryki obiektów Model - nowe obiekty wytwarzane są po podaniu nazwy
 * klasy.
 * 
 * @author Maciej Gzik
 * 
 */
public class PlatformFactory {
	private static final String TAG = PlatformFactory.class.getName().toString();

	private static AndroMoteLogger logger = new AndroMoteLogger(PlatformFactory.class);

	public static Platform getModel(DeviceDefinitions.MobilePlatformType name, MotorDriver driver) throws UnknownDeviceException {
		Platform result;
		logger.debug(TAG, "testing: platform name given: " + name);
		switch(name) {
		case NewBrightModel:
			logger.debug(TAG, "Model factory: creating NewBrightModel");
			if(driver instanceof MotorDriverNewBrightCompatible) {
				result = new PlatformNewBright((MotorDriverNewBrightCompatible)driver);
			} else {
				throw new UnknownDeviceException("Wrong driver for NewBrightModel");
			}
			break;
		case ROVER5TwoEngines:
			logger.debug(TAG, "Model factory: creating NewBrightModel");
			if(driver instanceof MotorDriverRover5Compatible) {
				result = new Rover5Platform((MotorDriverRover5Compatible)driver);
			} else {
				throw new UnknownDeviceException("Wrong driver for Rover5TwoEngines");
			}
			break;
		default:
			throw new UnknownDeviceException("Unknown model: " + name);
		}
		return result;
	}

}
