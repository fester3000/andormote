package andro_mote.devices;

import java.util.List;

public interface ElectronicDeviceFactory {
	public ElectronicDevice createRobotPlatform();
	public List<ElectronicDevice> createDevices();
}
