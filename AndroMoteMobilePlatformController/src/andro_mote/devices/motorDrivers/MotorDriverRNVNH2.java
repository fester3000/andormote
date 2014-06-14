package andro_mote.devices.motorDrivers;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;

public class MotorDriverRNVNH2 extends MotorDriverAbstract {
	
	public MotorDriverRNVNH2(IOIO ioio) {
		super(ioio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initIOIOPins() {
		try {
			super.initIOIOPins();
		} catch (ConnectionLostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void writeNewIoioPinValues() throws ConnectionLostException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hardStop() throws ConnectionLostException {
		// TODO Auto-generated method stub
		
	}
	
	
}
