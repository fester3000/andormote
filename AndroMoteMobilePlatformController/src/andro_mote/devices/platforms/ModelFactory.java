package andro_mote.devices.platforms;

import andro_mote.api.DevicesDefinitions.MobilePlatforms;
import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.devices.motorDrivers.MotorDriver;
import andro_mote.ioio_service.EngineControllerLooper;
import andro_mote.logger.AndroMoteLogger;

/**
 * Klasa fabryki obiektów Model - nowe obiekty wytwarzane są po podaniu nazwy
 * klasy.
 * 
 * @author Maciej Gzik
 * 
 */
public class ModelFactory {
	private static final String TAG = ModelFactory.class.getName().toString();

	private static AndroMoteLogger logger = new AndroMoteLogger(ModelFactory.class);

	public static Platform getModel(String name, MotorDriver driver) throws UnknownDeviceException {
		logger.debug(TAG, "testing: name otrzymane:" + name);
		logger.debug(TAG, "testing: name z enuma:" + MobilePlatforms.NewBrightModel.toString());
		if (name.equals(MobilePlatforms.NewBrightModel.toString())) {
			logger.debug(TAG, "Model factory: creating NewBrightModel");
			return new PlatformNewBright(driver);
		} else if (name.equals(MobilePlatforms.PololuTwoEngines.toString())) {
			logger.debug(TAG, "Model factory: creating PololuTwoEnginesModel");
			return new PololuTwoEnginesModel(driver);
		} else {
			throw new UnknownDeviceException("Unknown model: " + name);
		}
	}

}
