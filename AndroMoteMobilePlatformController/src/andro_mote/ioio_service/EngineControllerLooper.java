package andro_mote.ioio_service;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import andro_mote.devices.IModel;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;

public class EngineControllerLooper extends BaseIOIOLooper {

	public static final int AIN1_PIN = 44;
	public static final int AIN2_PIN = 45;
	public static final int PWMA_PIN = 46;
	public static final int PWMB_PIN = 40;
	public static final int BIN2_PIN = 41;
	public static final int BIN1_PIN = 42;
	public static final int STBY_PIN = 43;

	private static final int LED_PIN = 0;

	private static final String TAG = EngineControllerLooper.class.getName();
	private AndroMoteLogger logger = null;

	private IModel model;

	private DigitalOutput ain1_;
	private DigitalOutput ain2_;
	public PwmOutput pwma_;
	private PwmOutput pwmb_;
	private DigitalOutput bin2_;
	private DigitalOutput bin1_;
	private DigitalOutput stby_;
	private PwmOutput led_;

	// steering values
	public boolean servoLeft = false;
	public boolean servoRight = false;
	public int servoVoltage = 0;
	public boolean engineGearForward = true;
	public boolean engineGearBackward = false;
	public double engineFreq = 0;
	public boolean stby = true;
	public DigitalOutput hardStop_;

	private Step currentStep = null;

	// obiekt serwisu kontrolera
	private EnginesControllerService parentControllerService;
	private double ledFreq = 0.01;

	public static final int PWM_FREQ = 100;

	public EngineControllerLooper(EnginesControllerService enginesControllerService) throws ConnectionLostException,
			InterruptedException {
		this.parentControllerService = enginesControllerService;

		logger = new AndroMoteLogger(EngineControllerLooper.class);
		AndroMoteLogger.ConfigureLogger("AndroMoteClient.log");
		logger.debug(TAG, "setup ioio engine controller");
	}

	public EngineControllerLooper() throws ConnectionLostException, InterruptedException {
		logger = new AndroMoteLogger(EngineControllerLooper.class);
		AndroMoteLogger.ConfigureLogger("AndroMoteClient.log");
		logger.debug(TAG, "EngineControllerLooper(): init");
	}

	@Override
	public void setup() throws ConnectionLostException, InterruptedException {
		logger = new AndroMoteLogger(EngineControllerLooper.class);
		AndroMoteLogger.ConfigureLogger("AndroMoteClient.log");
		logger.debug(TAG, "EngineControllerLooper: setup ioio engine controller");

		initIOIOPins();
	}

	private void initIOIOPins() {
		try {
			logger.debug(TAG, "EngineControllerLooper: initIOIOPins");
			ain1_ = ioio_.openDigitalOutput(AIN1_PIN);
			ain2_ = ioio_.openDigitalOutput(AIN2_PIN);

			bin2_ = ioio_.openDigitalOutput(BIN2_PIN);
			bin1_ = ioio_.openDigitalOutput(BIN1_PIN);

			pwma_ = ioio_.openPwmOutput(PWMA_PIN, PWM_FREQ);
			pwmb_ = ioio_.openPwmOutput(PWMB_PIN, PWM_FREQ);

			stby_ = ioio_.openDigitalOutput(STBY_PIN);
			stby_.write(true);

			led_ = ioio_.openPwmOutput(LED_PIN, PWM_FREQ);
			led_.setDutyCycle((float) 0.001);

		} catch (ConnectionLostException e) {
			logger.error(TAG, e);
		}
	}

	@Override
	public void loop() throws ConnectionLostException, InterruptedException {

		led_.setDutyCycle((float) 0.98);

		if (this.parentControllerService.getControllMode().equals(EnginesControllerService.MOTION_MODE_STEPPER)) {
			// logger.debug(TAG, "stepper mode loop start");

			// blokada przed pobieraniem kolejnych kroków w trakcie wykonywanej
			// akcji
			if (!this.parentControllerService.isOperationExecuted()) {
				// logger.debug(TAG,
				// "EngineControllerLooper; stepperMode: operationIsExecuted == false");
				currentStep = this.parentControllerService.getNextStep();
				if (currentStep != null) {
					logger.debug(TAG,
							"EngineControllerLooper; stepperMode: step pobrany z kolejki: " + currentStep.getStepType());
					this.model.takeStep(currentStep);
				}
			}

			Thread.sleep(50);

			ioio_.beginBatch();
			try {
				pwmb_.setDutyCycle((float) servoVoltage);
				bin1_.write(servoLeft);
				bin2_.write(servoRight);

				pwma_.setDutyCycle((float) engineFreq);
				ain1_.write(engineGearBackward);
				ain2_.write(engineGearForward);

				stby_.write(stby);
			} finally {
				ioio_.endBatch();
			}
		} else if (this.parentControllerService.getControllMode().equals(
				EnginesControllerService.MOTION_MODE_CONTINUOUS)) {
			// logger.debug(TAG, "continuous looper");
			ioio_.beginBatch();
			try {
				pwmb_.setDutyCycle((float) servoVoltage);
				bin1_.write(servoLeft);
				bin2_.write(servoRight);

				pwma_.setDutyCycle((float) engineFreq);
				ain1_.write(engineGearBackward);
				ain2_.write(engineGearForward);

				stby_.write(stby);
			} finally {
				ioio_.endBatch();
			}
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
		pwma_.setDutyCycle((float) 0);
		ain1_.write(false);
		ain2_.write(false);

		pwmb_.setDutyCycle((float) 0);
		bin1_.write(false);
		bin2_.write(false);
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

	public void setModel(IModel model) {
		this.model = model;
	}

	public IModel getModel() {
		return this.model;
	}

	public EnginesControllerService getParentControllerService() {
		return parentControllerService;
	}

	public void setParentControllerService(EnginesControllerService parentControllerService) {
		this.parentControllerService = parentControllerService;
	}

}
