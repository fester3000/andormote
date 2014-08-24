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
 * MIN_SPEED = 0.3
 * @author Sebastian
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

	public void steerLeft(double speed) {
		driver.setM1_inA_value(false);
		driver.setM1_inB_value(true);
		driver.setM2_inA_value(true);
		driver.setM2_inB_value(false);
		
		driver.setM1_PWM_DutyCycle_value(speed);
		driver.setM2_PWM_DutyCycle_value(speed);
	}

	public void steerRight(double speed) {
		driver.setM1_inA_value(true);
		driver.setM1_inB_value(false);
		driver.setM2_inA_value(false);
		driver.setM2_inB_value(true);
		
		driver.setM1_PWM_DutyCycle_value(speed);
		driver.setM2_PWM_DutyCycle_value(speed);

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
		
		driver.setM1_PWM_DutyCycle_value(speed);
		driver.setM2_PWM_DutyCycle_value(speed);
	}
	
	public void moveForwardDifferSpeed(double speed, double speedB) {
		logger.debug(TAG, "moveForward -> inA: true");
		driver.setM1_inA_value(true);
		driver.setM1_inB_value(false);
		driver.setM2_inA_value(true);
		driver.setM2_inB_value(false);
		
		driver.setM1_PWM_DutyCycle_value(speed);
		driver.setM2_PWM_DutyCycle_value(speedB);
	}

	public void moveBackward(double speed) {
		logger.debug(TAG, "moveBackward -> inA: false");
		driver.setM1_inA_value(false);
		driver.setM1_inB_value(true);
		driver.setM2_inA_value(false);
		driver.setM2_inB_value(true);
		driver.setM1_PWM_DutyCycle_value(speed);
		driver.setM2_PWM_DutyCycle_value(speed);
	}

	public void moveBackwardDifferSpeed(double speed, double speedB) {
		logger.debug(TAG, "moveBackward -> inA: false");
		driver.setM1_inA_value(false);
		driver.setM1_inB_value(true);
		driver.setM2_inA_value(false);
		driver.setM2_inB_value(true);
		driver.setM1_PWM_DutyCycle_value(speed);
		driver.setM2_PWM_DutyCycle_value(speedB);
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
		case MOVE_LEFT_FORWARD_REQUEST : 
			moveForwardDifferSpeed(speed * 0.3, speed);
			break;
		case MOVE_FORWARD_REQUEST : 
			moveForward(speed);
			break;
		case MOVE_FORWARD_DIFFER_SPEED_REQUEST : 
			moveForwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_FORWARD_REQUEST : 
			moveForwardDifferSpeed(speed, speed * 0.3);
			break;
		case MOVE_LEFT_REQUEST : 
			steerLeft(speed);
			break;
		case STOP_REQUEST : 
			stop();
			break;
		case MOVE_RIGHT_REQUEST : 
			steerRight(speed);
			break;
		case MOVE_LEFT_BACKWARD_REQUEST : 
			moveBackwardDifferSpeed(speed * 0.3, speed);
			break;
		case MOVE_BACKWARD_REQUEST : 
			moveBackward(speed);
			break;
		case MOVE_BACKWARD_DIFFER_SPEED_REQUEST : 
			moveBackwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_BACKWARD_REQUEST : 
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
		case MOVE_LEFT_FORWARD_REQUEST : 
			moveForwardDifferSpeed(speed * 0.3, speed);
			break;
		case MOVE_FORWARD_REQUEST : 
			moveForward(speed);
			break;
		case MOVE_FORWARD_DIFFER_SPEED_REQUEST : 
			moveForwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_FORWARD_REQUEST : 
			moveForwardDifferSpeed(speed, speed * 0.3);
			break;
		case MOVE_LEFT_REQUEST : 
			steerLeft(speed);
			break;
		case STOP_REQUEST : 
			stop();
			break;
		case MOVE_RIGHT_REQUEST : 
			steerRight(speed);
			break;
		case MOVE_LEFT_BACKWARD_REQUEST : 
			moveBackwardDifferSpeed(speed * 0.3, speed);
			break;
		case MOVE_BACKWARD_REQUEST : 
			moveBackward(speed);
			break;
		case MOVE_BACKWARD_DIFFER_SPEED_REQUEST : 
			moveBackwardDifferSpeed(speed, speedB);
			break;
		case MOVE_RIGHT_BACKWARD_REQUEST : 
			moveBackwardDifferSpeed(speed, speed * 0.3);
			break;
		default:
			break;
		}
	}
}
