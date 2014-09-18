package andro_mote.hardware.ioio_service;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.MotionMode;
import andro_mote.commons.Packet;
import andro_mote.hardware.devices.ElectronicDevice;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;

//Stworzyć interfejs controllerlooper'a i klasę abstrakcyjną -  stworzyć współzależność modelu i loopera
public class AndroMoteIOIOLooper extends BaseIOIOLooper {
	private static final String TAG = AndroMoteIOIOLooper.class.getName();
	private AndroMoteLogger logger = new AndroMoteLogger(AndroMoteIOIOLooper.class);
	private final IOIOLooperManagerService parentControllerService;

	private final ElectronicDevice hardware;
	private Step currentStep = null;	
	private boolean isDeviceConnected = false;

	public AndroMoteIOIOLooper(IOIOLooperManagerService enginesControllerService, ElectronicDevice hardware) throws ConnectionLostException,
	InterruptedException, UnknownDeviceException {
		super();
		AndroMoteLogger.ConfigureLogger("AndroMoteClient.log");
		this.parentControllerService = enginesControllerService;
		this.hardware = hardware;
		logger.debug(TAG, "setup ioio engine controller");
	}

	@Override
	public void setup() throws ConnectionLostException, InterruptedException {
		logger.debug(TAG, "EngineControllerLooper: initIOIOPins");
		try {
			hardware.initIOIOPins(ioio_);
			isDeviceConnected = true;
		} catch (ConnectionLostException e) {
			logger.error(TAG, e);
		} 
	}

	@Override
	public void loop() throws ConnectionLostException, InterruptedException {
		if (hardware.getSettings().getMotionMode().equals(MotionMode.MOTION_MODE_STEPPER)) {
//			logger.debug(TAG, "stepper mode loop start");
			// blokada przed pobieraniem kolejnych kroków w trakcie wykonywanej akcji
			if (!IOIOLooperManagerService.isOperationExecuted) {
				currentStep = parentControllerService.getNextStep();
				if (currentStep != null) {
					logger.debug(TAG,
							"EngineControllerLooper; stepperMode: step pobrany z kolejki: " + currentStep.getStepType());
					hardware.takeStep(currentStep);
				}
			}

			Thread.sleep(50);
			writeNewIOIOPinValues();
			hardware.readNewPinValues();

		} else if (hardware.getSettings().getMotionMode().equals(
				MotionMode.MOTION_MODE_CONTINUOUS)) {
			writeNewIOIOPinValues();
			hardware.readNewPinValues();
			Thread.sleep(50);
		}

	}

	private void writeNewIOIOPinValues() throws ConnectionLostException {
		ioio_.beginBatch();
		try {
			hardware.writeNewIOIOPinValues(ioio_);
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
		hardware.interpretPacket(inputPacket);
	}

	public boolean isDeviceConnected() {
		return isDeviceConnected;
	}
}
