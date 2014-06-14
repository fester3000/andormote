package andro_mote.devices.motorDrivers;

import ioio.lib.api.exception.ConnectionLostException;

public interface MotorDriver {

	public void initIOIOPins() throws ConnectionLostException;

	public void writeNewIoioPinValues() throws ConnectionLostException;
	
	public void hardStop() throws ConnectionLostException;
}
