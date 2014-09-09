package andro_mote.devices.andromote_v2;

import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.Motion;
import andro_mote.devices.Vehicle;
import andro_mote.devices.generics.Platform;
import andro_mote.devices.generics.PlatformAbstract;
import andro_mote.logger.AndroMoteLogger;

/**
 * Dwukanałowy sterownik max 4A
 * MAX_STEP_DURATION = 2000
 * MIN_SPEED = -1.0
 * @author Sebastian Łuczak
 *
 */
public class Rover5Platform extends PlatformAbstract implements Platform {
	private static final String TAG = Rover5Platform.class.toString();
	private AndroMoteLogger logger = new AndroMoteLogger(Rover5Platform.class);
	

	private final MotorDriverRover5Compatible driver;

	public Rover5Platform(MotorDriverRover5Compatible driver, Vehicle parentDevice) {
		super(parentDevice);
		this.driver = driver;
	}
	
	public void moveCaterpillar(double speed, double speedB) {
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

	public void steerLeft(double speed) {
		driver.setM1_inA_value(false);
		driver.setM1_inB_value(true);
		driver.setM2_inA_value(true);
		driver.setM2_inB_value(false);
		
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speed));
	}

	public void steerRight(double speed) {
		driver.setM1_inA_value(true);
		driver.setM1_inB_value(false);
		driver.setM2_inA_value(false);
		driver.setM2_inB_value(true);
		
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speed));

	}

	public void steerCenter() {
		logger.debug(TAG, "steerCenter -> inA: true");
		driver.setM1_inA_value(true);
		driver.setM1_inB_value(false);
		driver.setM2_inA_value(true);
		driver.setM2_inB_value(false);
		
		driver.setM1_PWM_DutyCycle_value(0);
		driver.setM2_PWM_DutyCycle_value(0);
	}

	public void moveForward(double speed) {
		logger.debug(TAG, "moveForward -> inA: true");
		driver.setM1_inA_value(true);
		driver.setM1_inB_value(false);
		driver.setM2_inA_value(true);
		driver.setM2_inB_value(false);
		
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speed));
	}
	
	public void moveForwardDifferSpeed(double speed, double speedB) {
		logger.debug(TAG, "moveForward -> inA: true");
		driver.setM1_inA_value(true);
		driver.setM1_inB_value(false);
		driver.setM2_inA_value(true);
		driver.setM2_inB_value(false);
		
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speedB));
	}

	public void moveBackward(double speed) {
		logger.debug(TAG, "moveBackward -> inA: false");
		driver.setM1_inA_value(false);
		driver.setM1_inB_value(true);
		driver.setM2_inA_value(false);
		driver.setM2_inB_value(true);
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speed));
	}

	public void moveBackwardDifferSpeed(double speed, double speedB) {
		logger.debug(TAG, "moveBackward -> inA: false");
		driver.setM1_inA_value(false);
		driver.setM1_inB_value(true);
		driver.setM2_inA_value(false);
		driver.setM2_inB_value(true);
		driver.setM1_PWM_DutyCycle_value(Math.abs(speed));
		driver.setM2_PWM_DutyCycle_value(Math.abs(speedB));
	}

	
	public void stop() {
		logger.debug(TAG, "STOP!");
		driver.setM1_PWM_DutyCycle_value(0.0);
		driver.setM2_PWM_DutyCycle_value(0.0);
	}
	
	public void interpretMotionPacket(Packet inputPacket) {
		double speed = inputPacket.getSpeed();
		double speedB = inputPacket.getSpeedB();
		PacketType.Motion packetType = (Motion) inputPacket.getPacketType();
		switch(packetType) {
		case MOVE_CATERPILLAR : 
			moveCaterpillar(speed, speedB);
			break;
		case MOVE_LEFT_FORWARD : 
			moveForwardDifferSpeed(speed * 0.3, speed);
			break;
		case MOVE_FORWARD : 
			moveForward(speed);
			break;
		case MOVE_FORWARD_DIFFER_SPEED : 
			moveForwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_FORWARD : 
			moveForwardDifferSpeed(speed, speed * 0.3);
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
			moveBackwardDifferSpeed(speed * 0.3, speed);
			break;
		case MOVE_BACKWARD : 
			moveBackward(speed);
			break;
		case MOVE_BACKWARD_DIFFER_SPEED : 
			moveBackwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_BACKWARD : 
			moveBackwardDifferSpeed(speed, speed * 0.3);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void setValuesForSimpleStep(Motion packetType) {
		double speed = parentDevice.getSettings().getSpeed();
		double speedB = parentDevice.getSettings().getSpeed_B();
		switch(packetType) {
		case MOVE_LEFT_FORWARD : 
			moveForwardDifferSpeed(speed * 0.3, speed);
			break;
		case MOVE_FORWARD : 
			moveForward(speed);
			break;
		case MOVE_FORWARD_DIFFER_SPEED : 
			moveForwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_FORWARD : 
			moveForwardDifferSpeed(speed, speed * 0.3);
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
			moveBackwardDifferSpeed(speed * 0.3, speed);
			break;
		case MOVE_BACKWARD : 
			moveBackward(speed);
			break;
		case MOVE_BACKWARD_DIFFER_SPEED : 
			moveBackwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_BACKWARD : 
			moveBackwardDifferSpeed(speed, speed * 0.3);
			break;
		default:
			break;
		}
	}
}
