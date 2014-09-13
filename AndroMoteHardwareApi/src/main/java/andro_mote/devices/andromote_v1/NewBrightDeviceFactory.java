package andro_mote.devices.andromote_v1;

import java.util.ArrayList;
import java.util.List;

import andro_mote.devices.ElectronicDeviceFactory;
import andro_mote.devices.RobotHardware;

public class NewBrightDeviceFactory implements ElectronicDeviceFactory {
	@Override
	public RobotHardware createRobotPlatform() {
		return new NewBrightPlatform();
	}

	@Override
	public List<RobotHardware> createDevices() {
		// TODO Auto-generated method stub
		return new ArrayList<RobotHardware>();
	}

}
