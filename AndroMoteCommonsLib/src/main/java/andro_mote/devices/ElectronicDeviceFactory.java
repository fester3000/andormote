package andro_mote.devices;

import java.util.List;

public interface ElectronicDeviceFactory {
	public RobotHardware createRobotPlatform();
	public List<RobotHardware> createDevices();
}
