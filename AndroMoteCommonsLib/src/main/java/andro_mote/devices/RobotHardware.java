package andro_mote.devices;

import andro_mote.devices.generics.DeviceBehaviour;
import andro_mote.devices.generics.ElectronicDevice;

public interface RobotHardware extends ElectronicDevice, DeviceBehaviour {
	public abstract DeviceSettings getSettings();
}