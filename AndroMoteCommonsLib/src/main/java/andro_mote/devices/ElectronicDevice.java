package andro_mote.devices;

import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Motion;
import andro_mote.devices.generics.ElectronicDeviceHardware;
import andro_mote.stepper.Step;

public interface ElectronicDevice extends ElectronicDeviceHardware {
	public abstract void takeStep(Step step);
	public abstract void interpretPacket(Packet inputPacket);
	public abstract void setValuesForSimpleStep(Motion stepType);
	public abstract void stop();
	public abstract DeviceSettings getSettings();
}