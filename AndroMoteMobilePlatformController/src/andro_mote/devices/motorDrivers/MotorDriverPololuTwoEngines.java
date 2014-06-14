package andro_mote.devices.motorDrivers;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;

public class MotorDriverPololuTwoEngines extends MotorDriverAbstract {
	private static final int AIN1_PIN = 44;
	private static final int AIN2_PIN = 45;
	private static final int PWMA_PIN = 46;
	private static final int PWMB_PIN = 40;
	private static final int BIN2_PIN = 41;
	private static final int BIN1_PIN = 42;
	private static final int STBY_PIN = 43;

	private static final int PWM_FREQ = 100;

	private DigitalOutput ain1_;
	private DigitalOutput ain2_;
	private PwmOutput pwma_;
	private PwmOutput pwmb_;
	private DigitalOutput bin2_;
	private DigitalOutput bin1_;
	private DigitalOutput stby_;

	// steering values
	private boolean servoLeft = false;
	private boolean servoRight = false;
	private int servoVoltage = 0;
	private boolean engineGearForward = true;
	private boolean engineGearBackward = false;
	private double engineFreq = 0;
	private boolean stby = true;
	private DigitalOutput hardStop_;

	public MotorDriverPololuTwoEngines(IOIO ioio) {
		super(ioio);
	}

	@Override
	public void initIOIOPins() throws ConnectionLostException {
		ain1_ = ioio.openDigitalOutput(AIN1_PIN);
		ain2_ = ioio.openDigitalOutput(AIN2_PIN);

		bin2_ = ioio.openDigitalOutput(BIN2_PIN);
		bin1_ = ioio.openDigitalOutput(BIN1_PIN);

		pwma_ = ioio.openPwmOutput(PWMA_PIN, PWM_FREQ);
		pwmb_ = ioio.openPwmOutput(PWMB_PIN, PWM_FREQ);

		stby_ = ioio.openDigitalOutput(STBY_PIN);
		stby_.write(true);
		super.initIOIOPins();
	}

	@Override
	public void writeNewIoioPinValues() throws ConnectionLostException {
		ioio.beginBatch();
		try {
			pwmb_.setDutyCycle((float) servoVoltage);
			bin1_.write(servoLeft);
			bin2_.write(servoRight);

			pwma_.setDutyCycle((float) engineFreq);
			ain1_.write(engineGearBackward);
			ain2_.write(engineGearForward);

			stby_.write(stby);
		} finally {
			ioio.endBatch();
		}
	}

	@Override
	public void hardStop() throws ConnectionLostException {
		pwma_.setDutyCycle((float) 0);
		ain1_.write(false);
		ain2_.write(false);

		pwmb_.setDutyCycle((float) 0);
		bin1_.write(false);
		bin2_.write(false);
	}
}
