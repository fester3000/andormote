package andro_mote.devices.andromote_v1;

import java.util.ArrayList;
import java.util.List;

import andro_mote.devices.ElectronicDeviceFactory;
import andro_mote.devices.ElectronicDevice;

public class AndroMote1DeviceFactory implements ElectronicDeviceFactory {
	@Override
	public ElectronicDevice createRobotPlatform() {
		return new NewBrightPlatform();
	}

	@Override
	public List<ElectronicDevice> createDevices() {
		return new ArrayList<ElectronicDevice>();
	}

}
