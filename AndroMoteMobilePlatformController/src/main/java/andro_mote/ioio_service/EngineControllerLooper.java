package andro_mote.ioio_service;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.DeviceDefinitions.MobilePlatformType;
import andro_mote.commons.DeviceDefinitions.MotorDriverType;
import andro_mote.commons.MotionMode;
import andro_mote.commons.Packet;
import andro_mote.devices.andromote_v2.AndroV2Settings;
import andro_mote.devices.factories.DeviceFactory;
import andro_mote.devices.generics.Device;
import andro_mote.devices.generics.DeviceSettings;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;

//Stworzyć interfejs controllerlooper'a i klasę abstrakcyjną -  stworzyć współzależność modelu i loopera
public class EngineControllerLooper extends BaseIOIOLooper {
	private static final String TAG = EngineControllerLooper.class.getName();
	private DeviceSettings deviceSettings = AndroV2Settings.INSTANCE;
	private final EnginesService parentControllerService;
	
	private AndroMoteLogger logger = null;
	private Device device;
	private Step currentStep = null;	
	private MobilePlatformType platformName;
	private MotorDriverType driverName;
	private boolean isDeviceConnected = false;

	public EngineControllerLooper(EnginesService enginesControllerService, MobilePlatformType platformName, MotorDriverType driverName) throws ConnectionLostException,
	InterruptedException, UnknownDeviceException {
		super();
		logger = new AndroMoteLogger(EngineControllerLooper.class);
		AndroMoteLogger.ConfigureLogger("AndroMoteClient.log");
		this.parentControllerService = enginesControllerService;
		this.platformName = platformName;
		this.driverName = driverName;
		logger.debug(TAG, "setting platform... " + platformName );
		logger.debug(TAG, "setting engine driver... " + driverName);
		logger.debug(TAG, "setup ioio engine controller");
	}

	@Override
	public void setup() throws ConnectionLostException, InterruptedException {
		logger.debug(TAG, "EngineControllerLooper: initIOIOPins");
		try {
			device = DeviceFactory.getDevice(platformName, driverName, ioio_);
			device.initIOIOPins();
			isDeviceConnected = true;
		} catch (ConnectionLostException e) {
			logger.error(TAG, e);
		} catch (UnknownDeviceException e) {
			logger.error(TAG, e);
			e.printStackTrace();
		}
	}

	@Override
	public void loop() throws ConnectionLostException, InterruptedException {
		if (deviceSettings.getMotionMode().equals(MotionMode.MOTION_MODE_STEPPER)) {
			 logger.debug(TAG, "stepper mode loop start");
			// blokada przed pobieraniem kolejnych kroków w trakcie wykonywanej akcji
			if (!EnginesService.isOperationExecuted) {
				currentStep = parentControllerService.getNextStep();
				if (currentStep != null) {
					logger.debug(TAG,
							"EngineControllerLooper; stepperMode: step pobrany z kolejki: " + currentStep.getStepType());
					device.takeStep(currentStep);
				}
			}

			Thread.sleep(50);
			device.writeNewIoioPinValues();
			device.readCurrentValues();

		} else if (deviceSettings.getMotionMode().equals(
				MotionMode.MOTION_MODE_CONTINUOUS)) {
			device.writeNewIoioPinValues();
			device.readCurrentValues();
			Thread.sleep(50);
		}

	}

	/**
	 * Funkcja zatrzymuje silniki i samochód bezpośrednio wypisując odpowiednie
	 * wartości sterownikowi. Dane ze zmiennych nie są wykorzystywane.
	 * 
	 * @throws ConnectionLostException
	 */
	public void hardStop() throws ConnectionLostException {
		device.hardStop();
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
		device.interpretMotionPacket(inputPacket);
	}
	
	public boolean isDeviceConnected() {
		return isDeviceConnected;
	}
}
