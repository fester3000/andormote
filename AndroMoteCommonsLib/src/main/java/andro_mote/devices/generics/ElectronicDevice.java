package andro_mote.devices.generics;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;

public interface ElectronicDevice {
	public void initIOIOPins(final IOIO ioio) throws ConnectionLostException;
	public void writeNewIOIOPinValues(final IOIO ioio) throws ConnectionLostException;
	public void readNewPinValues() throws InterruptedException, ConnectionLostException;
}
