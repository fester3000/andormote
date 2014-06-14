package andro_mote.ioio_service;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.MotionModes;
import andro_mote.devices.Device;
import andro_mote.devices.DeviceFactory;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;

//Stworzyć interfejs controllerlooper'a i klasę abstrakcyjną -  stworzyć współzależność modelu i loopera
//wystawić parametry o wysokim poziomie abstrakcji - prędkość itp wyżej 
public class EngineControllerLooper extends BaseIOIOLooper {
	private static final String TAG = EngineControllerLooper.class.getName();

	// obiekt serwisu kontrolera
	private EnginesControllerService parentControllerService;
	
	private AndroMoteLogger logger = null;
	private Device device;
	private Step currentStep = null;	

	public EngineControllerLooper(EnginesControllerService enginesControllerService, String platformName, String driverName) throws ConnectionLostException,
	InterruptedException, UnknownDeviceException {
		this.parentControllerService = enginesControllerService;
		device = DeviceFactory.getDevice(platformName, driverName, ioio_);
		logger = new AndroMoteLogger(EngineControllerLooper.class);
		AndroMoteLogger.ConfigureLogger("AndroMoteClient.log");
		logger.debug(TAG, "setup ioio engine controller");
	}

	@Override
	public void setup() throws ConnectionLostException, InterruptedException {
		logger.debug(TAG, "EngineControllerLooper: initIOIOPins");
		try {
			device.initIOIOPins();
		} catch (ConnectionLostException e) {
			logger.error(TAG, e);
		}
	}

	@Override
	public void loop() throws ConnectionLostException, InterruptedException {
		if (this.parentControllerService.getControlMode().equals(MotionModes.MOTION_MODE_STEPPER)) {
			// logger.debug(TAG, "stepper mode loop start");

			// blokada przed pobieraniem kolejnych kroków w trakcie wykonywanej akcji
			if (!this.parentControllerService.isOperationExecuted()) {
				// logger.debug(TAG,
				// "EngineControllerLooper; stepperMode: operationIsExecuted == false");
				currentStep = this.parentControllerService.getNextStep();
				if (currentStep != null) {
					logger.debug(TAG,
							"EngineControllerLooper; stepperMode: step pobrany z kolejki: " + currentStep.getStepType());
					device.takeStep(currentStep);
				}
			}

			Thread.sleep(50);
			device.writeNewIoioPinValues();

		} else if (this.parentControllerService.getControlMode().equals(
				MotionModes.MOTION_MODE_CONTINUOUS)) {
			// logger.debug(TAG, "continuous looper");
			device.writeNewIoioPinValues();
			Thread.sleep(50);
		}

	}

	/**
	 * Funkcja zatrzymuje silniki i samochód bezpośrednio wypisując odpowiednie
	 * wartości sterownikowi. Dane ze zmiennych nie są wykorzystywane.
	 * 
	 * @throws ConnectionLostException
	 */
	private void hardStop() throws ConnectionLostException {
		device.hardStop();
	}

	@Override
	public void disconnected() {
		if (logger != null)
			logger.debug(TAG, "ioio disconnected");
	}

	@Override
	public void incompatible() {
		logger.debug(TAG, "ioio incompatibile");
	}
	
	
	
	public EnginesControllerService getParentControllerService() {
		return parentControllerService;
	}

	public void setParentControllerService(EnginesControllerService parentControllerService) {
		this.parentControllerService = parentControllerService;
	}
}
