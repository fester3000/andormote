package andro_mote.devices.factories;

import ioio.lib.api.IOIO;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.DeviceDefinitions.MotorDriverType;
import andro_mote.devices.andromote_v1.PololuTwoEngines;
import andro_mote.devices.andromote_v2.RNVNH2;
import andro_mote.devices.generics.MotorDriver;
import andro_mote.logger.AndroMoteLogger;

/**
 * Klasa fabryki obiektów MotorDriver - nowe obiekty wytwarzane są po podaniu enuma
 * klasy.
 * 
 * @author Sebastian Łuczak
 * 
 */
public class MotorDriverFactory {
	private static final String TAG = MotorDriverFactory.class.getName().toString();

	private static AndroMoteLogger logger = new AndroMoteLogger(MotorDriverFactory.class);

	public static MotorDriver getMotorDriver(MotorDriverType name, IOIO ioio) throws UnknownDeviceException {
		MotorDriver result;
		logger.debug(TAG, "testing: driver name given: " + name);
		switch(name) {
		case PololuTwoEngines:
			logger.debug(TAG, "Model factory: creating PololuTwoEngines");
			result = new PololuTwoEngines(ioio);
			break;
		case RNVN2:
			logger.debug(TAG, "Model factory: creating MotorDriverRNVNH2");
			result = new RNVNH2(ioio);
			break;
		default:
			throw new UnknownDeviceException("Unknown motorDriver: " + name);
		}
		return result;
	}

}
