package andro_mote.devices;

import ioio.lib.api.IOIO;
import andro_mote.api.DevicesDefinitions.MotorDrivers;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.devices.motorDrivers.MotorDriver;
import andro_mote.devices.motorDrivers.MotorDriverFactory;
import andro_mote.devices.platforms.Platform;
import andro_mote.devices.platforms.ModelFactory;
import andro_mote.ioio_service.EngineControllerLooper;
import andro_mote.logger.AndroMoteLogger;

/**
 * Klasa fabryki obiektów Model - nowe obiekty wytwarzane są po podaniu nazwy
 * klasy.
 * 
 * @author Maciej Gzik
 * 
 */
public class DeviceFactory {
	private static final String TAG = DeviceFactory.class.getName().toString();

	private static AndroMoteLogger logger = new AndroMoteLogger(DeviceFactory.class);

	public static Device getDevice(String platformName, String driverName, IOIO ioio) throws UnknownDeviceException {
		logger.debug(TAG, "testing: platformName otrzymane:" + platformName);
		logger.debug(TAG, "testing: driverName otrzymane:" + driverName);
		MotorDriver driver = MotorDriverFactory.getMotorDriver(driverName, ioio);
		Platform platform = ModelFactory.getModel(platformName, null);
		return new Device(platform, driver); 
	}

}
