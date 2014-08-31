package pl.fester3k.androcode.deviceManagement.action.phone;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionParams.Others;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import pl.fester3k.androcode.deviceManagement.action.phone.helpers.Compass;
import android.content.Context;
import android.hardware.SensorManager;

public class CompassAction extends BaseDeviceAction {
	private final Compass compass;
	public CompassAction(Context context) {
		super(context);		
		compass = new Compass(context);
		compass.registerListeners(SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	public Object run() {
		int result = 0;
		while(!compass.isInitialized()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(params.containsKey(ActionParams.Others.GET)) {
			String valueLabel = (String)params.get(ActionParams.Others.GET);
			if(valueLabel.equals("BEARING")) {
				result = (int)compass.getBearing();
			} else if(valueLabel.equals("AZIMUT")) {
				result = (int)compass.getAzimut();
			} else if(valueLabel.equals("PITCH")) {
				result = (int)compass.getPitch();
			} else if(valueLabel.equals("ROLL")) {
				result = (int)compass.getRoll();
			}
		}
		return result;
	}

	@Override
	public void cleanup() {
		compass.unregisterListeners();
		super.cleanup();
	}
	
	@Override
	public void putParam(String propertyName, String value) {
		params.put(Others.valueOf(propertyName), value);
	}
}
