package pl.fester3k.andromote.am2.functions.phone;

import pl.fester3k.andromote.am2.functions.CommonFunctionParams;
import pl.fester3k.andromote.am2.functions.phone.helpers.Compass;
import pl.fester3k.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;
import android.hardware.SensorManager;

public class CompassFunction extends BaseDeviceFunction {
	private final Compass compass;
	public CompassFunction(Context context) {
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
		if(params.containsKey(CommonFunctionParams.GET)) {
			String valueLabel = (String)params.get(CommonFunctionParams.GET);
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
		params.put(CommonFunctionParams.valueOf(propertyName), value);
	}
}
