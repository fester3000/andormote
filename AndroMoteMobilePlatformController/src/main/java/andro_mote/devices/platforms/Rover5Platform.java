package andro_mote.devices.platforms;

import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.IPacketType;
import andro_mote.commons.PacketType.Motion;
import andro_mote.devices.motor_drivers.MotorDriverRover5Compatible;
import andro_mote.ioio_service.EnginesService;
import andro_mote.logger.AndroMoteLogger;

public class Rover5Platform extends PlatformAbstract implements Platform {
	private static final String TAG = Rover5Platform.class.toString();
	private AndroMoteLogger logger = new AndroMoteLogger(Rover5Platform.class);

	private MotorDriverRover5Compatible driver;

	public Rover5Platform(MotorDriverRover5Compatible driver) {
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

	public void moveBackward(double speed) {
		logger.debug(TAG, "moveBackward -> inA: false");
		driver.setM1_inA_value(false);
		driver.setM1_inB_value(true);
		driver.setM2_inA_value(false);
		driver.setM2_inB_value(true);
		driver.setM1_PWM_DutyCycle_value(speed);
		driver.setM2_PWM_DutyCycle_value(speed);
	}

	public void stop() {
		logger.debug(TAG, "STOP!");
		driver.setM1_PWM_DutyCycle_value(0.0);
		driver.setM2_PWM_DutyCycle_value(0.00);
	}
	
	public void interpretMotionPacket(Packet inputPacket) {
		double speed = inputPacket.getSpeed();
		PacketType.Motion packetType = (Motion) inputPacket.getPacketType();
		switch(packetType) {
		case MOVE_LEFT_FORWARD_REQUEST : 
			moveForward(speed);
			steerLeft(speed);
			break;
		case MOVE_FORWARD_REQUEST : 
			moveForward(speed);
//			steerCenter();
			break;
		case MOVE_RIGHT_FORWARD_REQUEST : 
			moveForward(speed);
			steerRight(speed);
			break;
		case MOVE_LEFT_REQUEST : 
//			moveForward(0.0);
			steerLeft(speed);
			break;
		case STOP_REQUEST : 
			stop();
			break;
		case MOVE_RIGHT_REQUEST : 
//			stop();
			steerRight(speed);
			break;
		case MOVE_LEFT_BACKWARD_REQUEST : 
			moveBackward(speed);
			steerLeft(speed);
			break;
		case MOVE_BACKWARD_REQUEST : 
			moveBackward(speed);
//			steerCenter();
			break;
		case MOVE_RIGHT_BACKWARD_REQUEST : 
			moveBackward(speed);
			steerRight(speed);
			break;
		default:
			break;
		}
	}
	
	//TODO Do przepisania
	@Override
	protected void setValuesForSimpleStep(IPacketType packetType) {
		if (packetType == Motion.MOVE_LEFT_FORWARD_REQUEST) {
			steerLeft(EnginesService.getSpeed());
			moveForward(EnginesService.getSpeed());
		} else if (packetType == PacketType.Motion.MOVE_FORWARD_REQUEST) {
//			steerCenter();
			moveForward(EnginesService.getSpeed());
		} else if (packetType == PacketType.Motion.MOVE_RIGHT_FORWARD_REQUEST) {
			steerRight(EnginesService.getSpeed());
			moveForward(EnginesService.getSpeed());
		} else if (packetType == PacketType.Motion.MOVE_LEFT_REQUEST) {
			steerLeft(EnginesService.getSpeed());
			moveForward(0.0);
		} else if (packetType == PacketType.Motion.MOVE_RIGHT_REQUEST) {
			steerRight(EnginesService.getSpeed());
			moveForward(0.0);
		} else if (packetType == PacketType.Motion.STOP_REQUEST) {
			stop();
		} else if (packetType == PacketType.Motion.MOVE_LEFT_BACKWARD_REQUEST) {
			steerLeft(EnginesService.getSpeed());
			moveBackward(EnginesService.getSpeed());
		} else if (packetType == PacketType.Motion.MOVE_BACKWARD_REQUEST) {
//			steerCenter();
			moveBackward(EnginesService.getSpeed());
		} else if (packetType == PacketType.Motion.MOVE_RIGHT_BACKWARD_REQUEST) {
			steerRight(EnginesService.getSpeed());
			moveBackward(EnginesService.getSpeed());
		}
	}
}
