package andro_mote.devices.generics;

import andro_mote.devices.Vehicle;
import ioio.lib.api.IOIO;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;

public abstract class ElectronicDeviceAbstract implements ElectronicDevice {
	private static final int LED_PIN = 0;
	private static final int LED_PWM_FREQ = 10000;
	
	protected double ledFreq = 0.01;

	protected PwmOutput led_;
	protected Vehicle parentDevice;
	
	public ElectronicDeviceAbstract(Vehicle parentDevice) {
		this.parentDevice = parentDevice;
	}

	@Override
	public void initIOIOPins(final IOIO ioio) throws ConnectionLostException {
		try {
			led_ = ioio.openPwmOutput(LED_PIN, LED_PWM_FREQ);
			led_.setDutyCycle((float) 0.001);
		} catch (ConnectionLostException e) {
			e.printStackTrace();
		}
	}
}
