package andro_mote.devices.motorDrivers;

import ioio.lib.api.IOIO;
import andro_mote.api.DevicesDefinitions.MotorDrivers;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.ioio_service.EngineControllerLooper;
import andro_mote.logger.AndroMoteLogger;

/**
 * Klasa fabryki obiektów Model - nowe obiekty wytwarzane są po podaniu nazwy
 * klasy.
 * 
 * @author Maciej Gzik
 * 
 */
public class MotorDriverFactory {
	private static final String TAG = MotorDriverFactory.class.getName().toString();

	private static AndroMoteLogger logger = new AndroMoteLogger(MotorDriverFactory.class);

	public static MotorDriver getMotorDriver(String name, IOIO ioio) throws UnknownDeviceException {
		logger.debug(TAG, "testing: name otrzymane:" + name);
		logger.debug(TAG, "testing: name z enuma:" + MotorDrivers.PololuTwoEngines.toString());
		if (name.equals(MotorDrivers.PololuTwoEngines.toString())) {
			logger.debug(TAG, "Model factory: creating PololuTwoEngines");
			return new MotorDriverPololuTwoEngines(ioio);
		} else if (name.equals(MotorDrivers.PololuTwoEngines.toString())) {
			logger.debug(TAG, "Model factory: creating MotorDriverRNVNH2");
			return new MotorDriverRNVNH2(ioio);
		} else {
			throw new UnknownDeviceException("Unknown motorDriver: " + name);
		}
	}

}
