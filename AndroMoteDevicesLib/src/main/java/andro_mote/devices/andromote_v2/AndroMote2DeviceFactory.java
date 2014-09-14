package andro_mote.devices.andromote_v2;

import java.util.ArrayList;
import java.util.List;

import andro_mote.devices.ElectronicDeviceFactory;
import andro_mote.devices.ElectronicDevice;

public class AndroMote2DeviceFactory implements ElectronicDeviceFactory {
	@Override
	public ElectronicDevice createRobotPlatform() {
		return new Rover5Platform();
	}

	@Override
	public List<ElectronicDevice> createDevices() {
		// TODO Auto-generated method stub
		return new ArrayList<ElectronicDevice>();
	}

}
