package andro_mote.devices.motor_drivers;

public interface MotorDriverNewBrightCompatible extends MotorDriver {

	void setStby(boolean stby);

	void setEngineFreq(double engineFreq);

	void setEngineGearBackward(boolean engineGearBackward);

	void setEngineGearForward(boolean engineGearForward);

	void setServoVoltage(int servoVoltage);

	void setServoRight(boolean servoRight);

	void setServoLeft(boolean servoLeft);
	
}
