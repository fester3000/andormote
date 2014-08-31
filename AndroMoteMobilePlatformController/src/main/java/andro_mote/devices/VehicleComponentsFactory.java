package andro_mote.devices;

import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.DeviceDefinitions;
import andro_mote.commons.DeviceDefinitions.MotorDriverType;
import andro_mote.devices.andromote_v1.MotorDriverNewBrightCompatible;
import andro_mote.devices.andromote_v1.PlatformNewBright;
import andro_mote.devices.andromote_v1.PololuTwoEngines;
import andro_mote.devices.andromote_v2.MotorDriverRover5Compatible;
import andro_mote.devices.andromote_v2.RNVNH2;
import andro_mote.devices.andromote_v2.Rover5Platform;
import andro_mote.devices.generics.MotorDriver;
import andro_mote.devices.generics.Platform;
import andro_mote.logger.AndroMoteLogger;

/**
 * 
 * Klasa fabryki obiektów Device - nowe obiekty wytwarzane są po podaniu nazwy
 * platformy i sterownika
 * 
 * @author Sebastian Łuczak Łuczak
 * 
 */

public enum VehicleComponentsFactory {
	INSTANCE;
	
	private static final String TAG = VehicleComponentsFactory.class.getName().toString();
	private static AndroMoteLogger logger = new AndroMoteLogger(VehicleComponentsFactory.class);

	public MotorDriver getMotorDriver(MotorDriverType type, Vehicle parentDevice) throws UnknownDeviceException {
		MotorDriver result;
		logger.debug(TAG, "testing: driver name given: " + type);
		switch(type) {
		case PololuTwoEngines:
			logger.debug(TAG, "Model factory: creating PololuTwoEngines");
			result = new PololuTwoEngines(parentDevice);
			break;
		case RNVN2:
			logger.debug(TAG, "Model factory: creating MotorDriverRNVNH2");
			result = new RNVNH2(parentDevice);
			break;
		default:
			throw new UnknownDeviceException("Unknown motorDriver: " + type);
		}
		return result;
	}
	
	public Platform getModel(DeviceDefinitions.MobilePlatformType type, MotorDriver driver, Vehicle parentDevice) throws UnknownDeviceException {
		Platform result;
		logger.debug(TAG, "testing: platform name given: " + type);
		switch(type) {
		case NewBrightModel:
			logger.debug(TAG, "Model factory: creating NewBrightModel");
			if(driver instanceof MotorDriverNewBrightCompatible) {
				result = new PlatformNewBright((MotorDriverNewBrightCompatible)driver, parentDevice);
			} else {
				throw new UnknownDeviceException("Wrong driver for NewBrightModel");
			}
			break;
		case ROVER5TwoEngines:
			logger.debug(TAG, "Model factory: creating NewBrightModel");
			if(driver instanceof MotorDriverRover5Compatible) {
				result = new Rover5Platform((MotorDriverRover5Compatible)driver, parentDevice);
			} else {
				throw new UnknownDeviceException("Wrong driver for Rover5TwoEngines");
			}
			break;
		default:
			throw new UnknownDeviceException("Unknown model: " + type);
		}
		return result;
	}
	
	public VehicleSettings getVehicleSettings(DeviceDefinitions.MobilePlatformType platformType) throws UnknownDeviceException {
		VehicleSettings result;
		switch(platformType) {
		case NewBrightModel:
			result = new VehicleSettings.Builder(2000, 0.6).build();
			break;
		case ROVER5TwoEngines:
			result = new VehicleSettings.Builder(2000, -1.0).build();
			break;
			default:
				throw new UnknownDeviceException("Unknown model: " + platformType);	
		}
		return result;
	}

}
