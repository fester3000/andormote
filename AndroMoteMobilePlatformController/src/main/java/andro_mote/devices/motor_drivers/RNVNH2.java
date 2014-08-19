package andro_mote.devices.motor_drivers;

import ioio.lib.api.AnalogInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;

public class RNVNH2 extends MotorDriverAbstract implements MotorDriverRover5Compatible {
	private static final int M1_INA_PIN = 17;
	private static final int M1_INB_PIN = 19;
	private static final int M2_INA_PIN = 18;
	private static final int M2_INB_PIN = 16;
	
	private static final int M1_PWM_PIN = 3;
	private static final int M2_PWM_PIN = 4;
	
	private static final int M1_MEASURE_PIN = 33;
	private static final int M2_MEASURE_PIN = 34;
	private static final int TEMPERATURE_PIN = 46;
	
	//TODO encodery
	private static final int DEFAULT_PWM_FREQ = 1500;

	private DigitalOutput m1_InA_Output;
	private DigitalOutput m1_InB_Output;
	private DigitalOutput m2_InA_Output;
	private DigitalOutput m2_InB_Output;
	
	private PwmOutput m1_Pwm_Output;
	private PwmOutput m2_Pwm_Output;
	
	private AnalogInput m1_Measure_Input;
	private AnalogInput m2_Measure_Input;
	private AnalogInput rnvn2_temperature_Input;

	private boolean m1_inA_value = true;
	private boolean m1_inB_value = false;
	private boolean m2_inA_value = true;
	private boolean m2_inB_value = false;
	
	private double m1_PWM_DutyCycle_value = 0;
	private double m2_PWM_DutyCycle_value = 0;
	
	public RNVNH2(IOIO ioio) {
		super(ioio);
	}

	@Override
	public void initIOIOPins() {
		try {
			m1_InA_Output = ioio.openDigitalOutput(M1_INA_PIN);
			m1_InB_Output = ioio.openDigitalOutput(M1_INB_PIN);

			m2_InA_Output = ioio.openDigitalOutput(M2_INA_PIN);
			m2_InB_Output = ioio.openDigitalOutput(M2_INB_PIN);

			m1_Pwm_Output = ioio.openPwmOutput(M1_PWM_PIN, DEFAULT_PWM_FREQ);
			m2_Pwm_Output = ioio.openPwmOutput(M2_PWM_PIN, DEFAULT_PWM_FREQ);
			
			m1_Measure_Input = ioio.openAnalogInput(M1_MEASURE_PIN);
			m2_Measure_Input = ioio.openAnalogInput(M2_MEASURE_PIN);
			rnvn2_temperature_Input = ioio.openAnalogInput(TEMPERATURE_PIN);
//TODO		enkodery
			super.initIOIOPins();
		} catch (ConnectionLostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeNewIoioPinValues() throws ConnectionLostException {
		ioio.beginBatch();
		try {
			m1_Pwm_Output.setDutyCycle((float) m1_PWM_DutyCycle_value);
//			logger.debug(TAG, Boolean.toString(m1_inA));
			m1_InA_Output.write(m1_inA_value);
			m1_InB_Output.write(m1_inB_value);

			m2_Pwm_Output.setDutyCycle((float) m2_PWM_DutyCycle_value);
			m2_InA_Output.write(m2_inA_value);
			m2_InB_Output.write(m2_inB_value);
//			stby_.write(stby);
		} finally {
			ioio.endBatch();
		}
	}

	@Override
	public void hardStop() throws ConnectionLostException {
		// TODO Auto-generated method stub
		
	}

//	public boolean isM1_inA_value() {
//		return m1_inA_value;
//	}

	public void setM1_inA_value(boolean m1_inA_value) {
		this.m1_inA_value = m1_inA_value;
	}

//	public boolean isM1_inB_value() {
//		return m1_inB_value;
//	}

	public void setM1_inB_value(boolean m1_inB_value) {
		this.m1_inB_value = m1_inB_value;
	}

//	public boolean isM2_inA_value() {
//		return m2_inA_value;
//	}

	public void setM2_inA_value(boolean m2_inA_value) {
		this.m2_inA_value = m2_inA_value;
	}

//	public boolean isM2_inB_value() {
//		return m2_inB_value;
//	}

	public void setM2_inB_value(boolean m2_inB_value) {
		this.m2_inB_value = m2_inB_value;
	}
//
//	public double getM1_PWM_DutyCycle_value() {
//		return m1_PWM_DutyCycle_value;
//	}

	public void setM1_PWM_DutyCycle_value(double m1_PWM_DutyCycle_value) {
		this.m1_PWM_DutyCycle_value = m1_PWM_DutyCycle_value;
	}
//
//	public double getM2_PWM_DutyCycle_value() {
//		return m2_PWM_DutyCycle_value;
//	}

	public void setM2_PWM_DutyCycle_value(double m2_PWM_DutyCycle_value) {
		this.m2_PWM_DutyCycle_value = m2_PWM_DutyCycle_value;
	}
	
	
}
