package andro_mote.devices.andromote_v1;

import andro_mote.devices.generics.ElectronicDevice;

public interface MotorDriverNewBrightCompatible extends ElectronicDevice {

	public void setStby(boolean stby);

	public void setEngineFreq(double engineFreq);

	public void setEngineGearBackward(boolean engineGearBackward);

	public void setEngineGearForward(boolean engineGearForward);

	public void setServoVoltage(int servoVoltage);

	public void setServoRight(boolean servoRight);

	public void setServoLeft(boolean servoLeft);
	
}
