package andro_mote.devices.motor_drivers;

public interface MotorDriverRover5Compatible extends MotorDriver {

	void setM1_inA_value(boolean m1_inA_value);

	void setM1_inB_value(boolean m1_inB_value);

	void setM2_inA_value(boolean m2_inA_value);

	void setM2_inB_value(boolean m2_inB_value);

	void setM1_PWM_DutyCycle_value(double m1_PWM_DutyCycle_value);

	void setM2_PWM_DutyCycle_value(double m2_PWM_DutyCycle_value);

}
