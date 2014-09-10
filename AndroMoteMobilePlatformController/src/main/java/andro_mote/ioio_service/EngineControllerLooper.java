package andro_mote.ioio_service;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.DeviceDefinitions.MobilePlatformType;
import andro_mote.commons.DeviceDefinitions.MotorDriverType;
import andro_mote.commons.MotionMode;
import andro_mote.commons.Packet;
import andro_mote.devices.Vehicle;
import andro_mote.devices.VehicleSettings;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;

//Stworzyć interfejs controllerlooper'a i klasę abstrakcyjną -  stworzyć współzależność modelu i loopera
public class EngineControllerLooper extends BaseIOIOLooper {
	private static final String TAG = EngineControllerLooper.class.getName();
	private AndroMoteLogger logger = new AndroMoteLogger(EngineControllerLooper.class);
	private final EnginesService parentControllerService;

	private final Vehicle vehicle;
	private Step currentStep = null;	
	private boolean isDeviceConnected = false;

	public EngineControllerLooper(EnginesService enginesControllerService, Vehicle vehicle) throws ConnectionLostException,
	InterruptedException, UnknownDeviceException {
		super();
		AndroMoteLogger.ConfigureLogger("AndroMoteClient.log");
		this.parentControllerService = enginesControllerService;
		this.vehicle = vehicle;
		logger.debug(TAG, "setup ioio engine controller");
	}

	@Override
	public void setup() throws ConnectionLostException, InterruptedException {
		logger.debug(TAG, "EngineControllerLooper: initIOIOPins");
		try {
			vehicle.initIOIOPins(ioio_);
			isDeviceConnected = true;
		} catch (ConnectionLostException e) {
			logger.error(TAG, e);
		} 
	}

	@Override
	public void loop() throws ConnectionLostException, InterruptedException {
		if (vehicle.getSettings().getMotionMode().equals(MotionMode.MOTION_MODE_STEPPER)) {
			logger.debug(TAG, "stepper mode loop start");
			// blokada przed pobieraniem kolejnych kroków w trakcie wykonywanej akcji
			if (!EnginesService.isOperationExecuted) {
				currentStep = parentControllerService.getNextStep();
				if (currentStep != null) {
					logger.debug(TAG,
							"EngineControllerLooper; stepperMode: step pobrany z kolejki: " + currentStep.getStepType());
					vehicle.takeStep(currentStep);
				}
			}

			Thread.sleep(50);
			writeNewPinValuesToIoio();
			vehicle.readCurrentValues();

		} else if (vehicle.getSettings().getMotionMode().equals(
				MotionMode.MOTION_MODE_CONTINUOUS)) {
			writeNewPinValuesToIoio();
			vehicle.readCurrentValues();
			Thread.sleep(50);
		}

	}

	private void writeNewPinValuesToIoio() throws ConnectionLostException {
		ioio_.beginBatch();
		try {
			vehicle.writeNewIoioPinValues(ioio_);
		} finally {
			ioio_.endBatch();
		}
	}

	@Override
	public void disconnected() {
		logger.debug(TAG, "ioio disconnected");
		isDeviceConnected = false;
	}

	@Override
	public void incompatible() {
		logger.debug(TAG, "ioio incompatibile");
	}

	public void executePacket(Packet inputPacket) {
		vehicle.interpretPacket(inputPacket);
	}

	public boolean isDeviceConnected() {
		return isDeviceConnected;
	}
}
