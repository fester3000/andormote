package andro_mote.devices.andromote_v2;

import andro_mote.commons.PacketType.IPacketType;

public class AdditionalPacketTypes {
	public enum RNVN2Alerts implements IPacketType { 
	CHIP_TEMPERATURE_ALERT,
	
	ENGINE_CURRENT_ALERT
	}
}
