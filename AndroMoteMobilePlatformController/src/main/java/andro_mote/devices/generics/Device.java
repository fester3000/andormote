package andro_mote.devices.generics;

import ioio.lib.api.exception.ConnectionLostException;
import andro_mote.commons.Packet;
import andro_mote.stepper.Step;

public final class Device implements Platform, MotorDriver {
	private final MotorDriver driver;
	private final Platform platform;
	
	public Device (Platform platform, MotorDriver driver) {
		this.platform = platform;
		this.driver = driver;
	}
	
	public Platform getPlatform() {
		return platform;
	}
	public MotorDriver getDriver() {
		return driver;
	}

	@Override
	public void initIOIOPins() throws ConnectionLostException {
		driver.initIOIOPins();
	}


	@Override
	public void writeNewIoioPinValues() throws ConnectionLostException {
		driver.writeNewIoioPinValues();
	}

	@Override
	public void hardStop() throws ConnectionLostException {
		driver.hardStop();		
	}

	@Override
	public void takeStep(Step step) {
		platform.takeStep(step);
	}

	@Override
	public void interpretMotionPacket(Packet inputPacket) {
		platform.interpretMotionPacket(inputPacket);		
	}

	@Override
	public void stop() {
		platform.stop();
	}

	public void readCurrentValues() throws InterruptedException, ConnectionLostException {
		driver.readCurrentValues();
		
	}
}
