package andro_mote.devices;

import ioio.lib.api.exception.ConnectionLostException;
import andro_mote.commons.PacketType.IPacketType;
import andro_mote.devices.motorDrivers.MotorDriver;
import andro_mote.devices.platforms.Platform;
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
		// TODO Auto-generated method stub
		
	}



	@Override
	public void writeNewIoioPinValues() throws ConnectionLostException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void hardStop() throws ConnectionLostException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void steerLeft() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void steerRight() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void steerCenter() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void moveForward(double speed) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void moveBackward(double speed) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void turn90Right() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void turn90Left() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void turnRightDegrees(int degrees) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void turnLeftDegrees(int degrees) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public IPacketType getCurrentStepName() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void takeStep(Step step) {
		// TODO Auto-generated method stub
		
	}
}
