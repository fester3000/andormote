package andro_mote.devices.andromote_v2;

import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.Motion;
import andro_mote.devices.Vehicle;
import andro_mote.devices.generics.ElectronicDeviceInterfaceAbstract;
import andro_mote.logger.AndroMoteLogger;

/**
 * Dwukanałowy sterownik max 4A
 * MAX_STEP_DURATION = 2000
 * MIN_SPEED = -1.0
 * @author Sebastian Łuczak
 *
 */
public class Rover5Platform extends ElectronicDeviceInterfaceAbstract {
	private static final String TAG = Rover5Platform.class.toString();
	private AndroMoteLogger logger = new AndroMoteLogger(Rover5Platform.class);

	private final MotorDriverRover5Compatible driver;

	public Rover5Platform(MotorDriverRover5Compatible driver, Vehicle parentDevice) {
		super(parentDevice);
		this.driver = driver;
	}
	
	private void moveCaterpillar(float speed, float speedB) {
		if(speed >= 0) {
			driver.setM1_inA_value(true);
			driver.setM1_inB_value(false);
		} else {
			driver.setM1_inA_value(false);
			driver.setM1_inB_value(true);
		}
		if(speedB >= 0) {
			driver.setM2_inA_value(true);
			driver.setM2_inB_value(false);
		} else {
			driver.setM2_inA_value(false);
			driver.setM2_inB_value(true);
		}
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speedB));
	}

	private void steerLeft(float speed) {
		driver.setM1_inA_value(false);
		driver.setM1_inB_value(true);
		driver.setM2_inA_value(true);
		driver.setM2_inB_value(false);
		
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speed));
	}

	public void steerRight(float speed) {
		driver.setM1_inA_value(true);
		driver.setM1_inB_value(false);
		driver.setM2_inA_value(false);
		driver.setM2_inB_value(true);
		
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speed));

	}

	private void moveForward(float speed) {
		logger.debug(TAG, "moveForward -> inA: true");
		driver.setM1_inA_value(true);
		driver.setM1_inB_value(false);
		driver.setM2_inA_value(true);
		driver.setM2_inB_value(false);
		
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speed));
	}
	
	private void moveForwardDifferSpeed(float speed, float speedB) {
		logger.debug(TAG, "moveForward -> inA: true");
		driver.setM1_inA_value(true);
		driver.setM1_inB_value(false);
		driver.setM2_inA_value(true);
		driver.setM2_inB_value(false);
		
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speedB));
	}

	private void moveBackward(float speed) {
		logger.debug(TAG, "moveBackward -> inA: false");
		driver.setM1_inA_value(false);
		driver.setM1_inB_value(true);
		driver.setM2_inA_value(false);
		driver.setM2_inB_value(true);
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speed));
	}

	private void moveBackwardDifferSpeed(float speed, float speedB) {
		logger.debug(TAG, "moveBackward -> inA: false");
		driver.setM1_inA_value(false);
		driver.setM1_inB_value(true);
		driver.setM2_inA_value(false);
		driver.setM2_inB_value(true);
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speedB));
	}

	@Override
	public void stop() {
		logger.debug(TAG, "STOP!");
		driver.setM1_PWM_DutyCycle_value(0.0f);
		driver.setM2_PWM_DutyCycle_value(0.0f);
	}
	
	@Override
	public void interpretPacket(Packet inputPacket) {
		float speed = (float) inputPacket.getSpeed();
		float speedB = (float) inputPacket.getSpeedB();
		PacketType.Motion packetType = (Motion) inputPacket.getPacketType();
		switch(packetType) {
		case MOVE_CATERPILLAR : 
			moveCaterpillar(speed, speedB);
			break;
		case MOVE_LEFT_FORWARD : 
			moveForwardDifferSpeed(speed * 0.3f, speed);
			break;
		case MOVE_FORWARD : 
			moveForward(speed);
			break;
		case MOVE_FORWARD_DIFFER_SPEED : 
			moveForwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_FORWARD : 
			moveForwardDifferSpeed(speed, speed * 0.3f);
			break;
		case MOVE_LEFT : 
			steerLeft(speed);
			break;
		case STOP : 
			stop();
			break;
		case MOVE_RIGHT : 
			steerRight(speed);
			break;
		case MOVE_LEFT_BACKWARD : 
			moveBackwardDifferSpeed(speed * 0.3f, speed);
			break;
		case MOVE_BACKWARD : 
			moveBackward(speed);
			break;
		case MOVE_BACKWARD_DIFFER_SPEED : 
			moveBackwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_BACKWARD : 
			moveBackwardDifferSpeed(speed, speed * 0.3f);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void setValuesForSimpleStep(Motion packetType) {
		float speed = (float)parentDevice.getSettings().getSpeed();
		float speedB = (float)parentDevice.getSettings().getSpeed_B();
		switch(packetType) {
		case MOVE_LEFT_FORWARD : 
			moveForwardDifferSpeed(speed * 0.3f, speed);
			break;
		case MOVE_FORWARD : 
			moveForward(speed);
			break;
		case MOVE_FORWARD_DIFFER_SPEED : 
			moveForwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_FORWARD : 
			moveForwardDifferSpeed(speed, speed * 0.3f);
			break;
		case MOVE_LEFT : 
			steerLeft(speed);
			break;
		case STOP : 
			stop();
			break;
		case MOVE_RIGHT : 
			steerRight(speed);
			break;
		case MOVE_LEFT_BACKWARD : 
			moveBackwardDifferSpeed(speed * 0.3f, speed);
			break;
		case MOVE_BACKWARD : 
			moveBackward(speed);
			break;
		case MOVE_BACKWARD_DIFFER_SPEED : 
			moveBackwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_BACKWARD : 
			moveBackwardDifferSpeed(speed, speed * 0.3f);
			break;
		default:
			break;
		}
	}
}
