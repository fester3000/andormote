package andro_mote.stepper;

import andro_mote.commons.PacketType.IPacketType;
import andro_mote.commons.PacketType.Motion;

public class Step implements IStep {

	/**
	 * Czas trwania kroku
	 */
	private long stepDuration;

	private int degrees;

	private IPacketType stepType;

	public Step() {
	}

	public Step(IPacketType stepType) {
		this.stepType = stepType;
	}

	public IPacketType getReverseDirection() {
		if (this.stepType == Motion.MOVE_FORWARD_REQUEST) {
			return Motion.MOVE_BACKWARD_REQUEST;
		} else if (this.stepType == Motion.MOVE_LEFT_FORWARD_REQUEST) {
			return Motion.MOVE_LEFT_BACKWARD_REQUEST;
		} else if (this.stepType == Motion.MOVE_RIGHT_FORWARD_REQUEST) {
			return Motion.MOVE_RIGHT_BACKWARD_REQUEST;
		} else if (this.stepType == Motion.MOVE_BACKWARD_REQUEST) {
			return Motion.MOVE_FORWARD_REQUEST;
		} else if (this.stepType == Motion.MOVE_LEFT_BACKWARD_REQUEST) {
			return Motion.MOVE_LEFT_FORWARD_REQUEST;
		} else if (this.stepType == Motion.MOVE_RIGHT_BACKWARD_REQUEST) {
			return Motion.MOVE_RIGHT_FORWARD_REQUEST;
		} else if (this.stepType == Motion.MOVE_RIGHT_90_DEGREES_REQUEST) {
			return Motion.MOVE_LEFT_90_DEGREES_REQUEST;
		} else if (this.stepType == Motion.MOVE_LEFT_90_DEGREES_REQUEST) {
			return Motion.MOVE_RIGHT_90_DEGREES_REQUEST;
		} else if (this.stepType == Motion.MOVE_RIGHT_DEGREES_REQUEST) {
			return Motion.MOVE_LEFT_DEGREES_REQUEST;
		} else if (this.stepType == Motion.MOVE_LEFT_DEGREES_REQUEST) {
			return Motion.MOVE_RIGHT_DEGREES_REQUEST;
		} else {
			return this.stepType;
		}
	}

	/**
	 * Funkcja zwraca nazwę kroku, który został wykonany (bez sufiksu _REQUEST)
	 * 
	 * @param stepRequest
	 *            Nazwa zlecenia wykonania kroku.
	 * @return Nazwa wykonanego kroku
	 */
	public static Motion getTakenStep(Motion stepRequest) {
		Motion returnStepType = null;
		switch (stepRequest) {
		case MOVE_LEFT_FORWARD_REQUEST:
			returnStepType = Motion.MOVE_LEFT_FORWARD;
			break;
		case MOVE_FORWARD_REQUEST:
			returnStepType = Motion.MOVE_FORWARD;
			break;
		case MOVE_RIGHT_FORWARD_REQUEST:
			returnStepType = Motion.MOVE_RIGHT_FORWARD;
			break;
		case MOVE_LEFT_REQUEST:
			returnStepType = Motion.MOVE_LEFT;
			break;
		case STOP_REQUEST:
			returnStepType = Motion.STOP;
			break;
		case MOVE_RIGHT_REQUEST:
			returnStepType = Motion.MOVE_RIGHT;
			break;
		case MOVE_LEFT_BACKWARD_REQUEST:
			returnStepType = Motion.MOVE_LEFT_BACKWARD;
			break;
		case MOVE_BACKWARD_REQUEST:
			returnStepType = Motion.MOVE_BACKWARD;
			break;
		case MOVE_RIGHT_BACKWARD_REQUEST:
			returnStepType = Motion.MOVE_RIGHT_BACKWARD;
			break;
		case MOVE_LEFT_90_DEGREES_REQUEST:
			returnStepType = Motion.MOVE_LEFT_90_DEGREES;
			break;
		case MOVE_RIGHT_90_DEGREES_REQUEST:
			returnStepType = Motion.MOVE_RIGHT_90_DEGREES;
			break;
		case MOVE_LEFT_DEGREES_REQUEST:
			returnStepType = Motion.MOVE_LEFT_DEGREES;
			break;
		case MOVE_RIGHT_DEGREES_REQUEST:
			returnStepType = Motion.MOVE_RIGHT_DEGREES;
			break;
		default:
			break;
		}
		return returnStepType;
	}

	public long getStepDuration() {
		return stepDuration;
	}

	public void setStepDuration(long stepDuration) {
		this.stepDuration = stepDuration;
	}

	public int getDegrees() {
		return degrees;
	}

	public void setDegrees(int degrees) {
		this.degrees = degrees;
	}

	public IPacketType getStepType() {
		return stepType;
	}

	public void setStepType(IPacketType stepType) {
		this.stepType = stepType;
	}
}
