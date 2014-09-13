package andro_mote.devices;

import andro_mote.commons.MotionMode;

public interface DeviceSettings {

	public abstract MotionMode getMotionMode();

	public abstract long getStepDuration();

	public abstract double getSpeed();

	public abstract double getSpeed_B();

	public abstract void setSpeed(double speed);

	public abstract void setSpeedB(double speed);

	public abstract void setStepDuration(long stepDuration);

	public abstract void setMotionMode(MotionMode motionMode);

	public abstract double getMIN_SPEED();

	public abstract long getPauseTimeBetweenSteps();

}