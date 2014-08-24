package andro_mote.devices.generics;

import andro_mote.commons.MotionMode;

public interface DeviceSettings {

	public abstract MotionMode getMotionMode();

	public abstract long getStepDuration();

	public abstract long getPauseTimeBetweenSteps();

	public abstract double getSpeed();

	public abstract double getSpeed_B();

	public abstract void setStepDuration(long stepDuration);

	public abstract double getMIN_SPEED();

	public abstract int getMAX_STEP_DURATION();
	
	public abstract void setMotionMode(MotionMode motionMode);
	
	public abstract void setSpeed(double speed);
	
	public abstract void setSpeedB(double speed);
}