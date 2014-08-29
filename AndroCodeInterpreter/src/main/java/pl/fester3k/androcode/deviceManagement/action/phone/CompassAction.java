package pl.fester3k.androcode.deviceManagement.action.phone;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import pl.fester3k.androcode.deviceManagement.action.phone.helpers.Compass;
import android.content.Context;
import android.hardware.SensorManager;

public class CompassAction extends BaseDeviceAction {
	private final Compass compass;
	public CompassAction(Context context) {
		super(context);		
		compass = new Compass(context);
		compass.registerListeners(SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public Object run() {
		float result = 0;
		if(params.containsKey(ActionParams.Others.GET.toString())) {
			String valueLabel = (String)params.get(ActionParams.Others.GET.toString());
			if(valueLabel.equals("BEARING")) {
				result = (float)compass.getBearing();
			} else if(valueLabel.equals("AZIMUT")) {
				result = (float)compass.getAzimut();
			} else if(valueLabel.equals("PITCH")) {
				result = (float)compass.getPitch();
			} else if(valueLabel.equals("ROLL")) {
				result = (float)compass.getRoll();
			}
		}
		return result;
	}

	@Override
	public void cleanup() {
		compass.unregisterListeners();
		super.cleanup();
	}
}
