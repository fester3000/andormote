package andro_mote.devices.andromote_v2;

import andro_mote.devices.generics.ElectronicDevice;

public interface MotorDriverRover5Compatible extends ElectronicDevice {

	public void setM1_inA_value(boolean m1_inA_value);

	public void setM1_inB_value(boolean m1_inB_value);

	public void setM2_inA_value(boolean m2_inA_value);

	public void setM2_inB_value(boolean m2_inB_value);

	public void setM1_PWM_DutyCycle_value(float m1_PWM_DutyCycle_value);

	public void setM2_PWM_DutyCycle_value(float m2_PWM_DutyCycle_value);	

}