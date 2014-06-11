package andro_mote.devices;

import andro_mote.commons.PacketType;
import andro_mote.ioio_service.EngineControllerLooper;

public class PololuTwoEnginesModel extends AbstractModel {

	public PololuTwoEnginesModel(EngineControllerLooper looper) {
		super(looper);
	}

	@Override
	public void steerLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void steerRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void steerCenter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveForward(double speed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveBackward(double speed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turn90Right() {
		// TODO Auto-generated method stub
	}

	@Override
	public void turn90Left() {
		// TODO Auto-generated method stub
	}

	@Override
	public PacketType.IPacketType getCurrentStepName() {
		return null;
	}

}
