package andro_mote.devices.generics;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;

public interface MotorDriver {
	public void initIOIOPins(final IOIO ioio) throws ConnectionLostException;
	public void writeNewIoioPinValues(final IOIO ioio) throws ConnectionLostException;
	public void readCurrentValues() throws InterruptedException, ConnectionLostException;
}
