package andro_mote.devices.andromote_v2;

import java.util.ArrayList;
import java.util.List;

import andro_mote.devices.ElectronicDeviceFactory;
import andro_mote.devices.RobotHardware;

public class AndroMote2DeviceFactory implements ElectronicDeviceFactory {
	@Override
	public RobotHardware createRobotPlatform() {
		return new Rover5Platform();
	}

	@Override
	public List<RobotHardware> createDevices() {
		// TODO Auto-generated method stub
		return new ArrayList<RobotHardware>();
	}

}
