package andro_mote.devices;

import andro_mote.api.DevicesDefinitions.Devices;
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
public class ModelFactory {
	private static final String TAG = ModelFactory.class.getName().toString();

	private static AndroMoteLogger logger = new AndroMoteLogger(ModelFactory.class);

	public static IModel getModel(String name, EngineControllerLooper looper) throws UnknownDeviceException {
		logger.debug(TAG, "testing: name otrzymane:" + name);
		logger.debug(TAG, "testing: name z enuma:" + Devices.NewBrightModel.toString());
		if (name.equals(Devices.NewBrightModel.toString())) {
			logger.debug(TAG, "Model factory: creating NewBrightModel");
			return new NewBrightModel(looper);
		} else if (name.equals(Devices.PololuTwoEngines.toString())) {
			logger.debug(TAG, "Model factory: creating PololuTwoEnginesModel");
			return new PololuTwoEnginesModel(looper);
		} else {
			throw new UnknownDeviceException("Unknown model: " + name);
		}
	}

}
