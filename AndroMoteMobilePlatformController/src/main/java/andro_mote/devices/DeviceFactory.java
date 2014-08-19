package andro_mote.devices;

import ioio.lib.api.IOIO;
import andro_mote.commons.DeviceDefinitions;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.devices.motor_drivers.MotorDriver;
import andro_mote.devices.motor_drivers.MotorDriverFactory;
import andro_mote.devices.platforms.Platform;
import andro_mote.devices.platforms.PlatformFactory;
import andro_mote.logger.AndroMoteLogger;

/**
 * Klasa fabryki obiektów Device - nowe obiekty wytwarzane są po podaniu nazwy
 * platformy i sterownika
 * 
 * @author Sebastian Łuczak
 * 
 */
public class DeviceFactory {
	private static final String TAG = DeviceFactory.class.getName().toString();

	private static AndroMoteLogger logger = new AndroMoteLogger(DeviceFactory.class);

	public static Device getDevice(DeviceDefinitions.MobilePlatforms platformName, DeviceDefinitions.MotorDrivers driverName, IOIO ioio) throws UnknownDeviceException {
		logger.debug(TAG, "testing: platformName: " + platformName);
		logger.debug(TAG, "testing: driverName: " + driverName);
		MotorDriver driver = MotorDriverFactory.getMotorDriver(driverName, ioio);
		Platform platform = PlatformFactory.getModel(platformName, driver);
		return new Device(platform, driver); 
	}

}
