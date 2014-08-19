package andro_mote.devices.motor_drivers;

import ioio.lib.api.IOIO;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;

public abstract class MotorDriverAbstract implements MotorDriver {
	private static final int LED_PIN = 0;
	private static final int LED_PWM_FREQ = 10000;
	
	protected IOIO ioio;
	protected double ledFreq = 0.01;

	protected PwmOutput led_;
	
	public MotorDriverAbstract(IOIO ioio) {
		this.ioio = ioio;
	}

	@Override
	public void initIOIOPins() throws ConnectionLostException {
		try {
			led_ = ioio.openPwmOutput(LED_PIN, LED_PWM_FREQ);
			led_.setDutyCycle((float) 0.001);
		} catch (ConnectionLostException e) {
			e.printStackTrace();
		}
	}
}
