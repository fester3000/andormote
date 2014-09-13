package mobi.andromote.am2.functions.phone.sensors;

import java.util.HashMap;
import java.util.Map;

import mobi.andromote.am2.functions.CommonFunctionParams;
import mobi.andromote.functionalityFramework.functions.BaseSensorFunction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class GravitySensorFunction extends BaseSensorFunction {
	private Map<String, Float> values = new HashMap<String, Float>();
	
	public GravitySensorFunction(Context context) {
		super(context, Sensor.TYPE_GRAVITY);
	}

	@Override
	public Object run() {
		float result = 0;
		if(params.containsKey(CommonFunctionParams.GET)) {
			String valueLabel = (String)params.get(CommonFunctionParams.GET);
			if(values.containsKey(valueLabel)) {
				result = values.get(valueLabel);
			}
		}
		return result;
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		super.onSensorChanged(sensorEvent);

		float[] sensorValues = sensorEvent.values;
		values.put("x", sensorValues[0]);
		values.put("y", sensorValues[1]);
		values.put("z", sensorValues[2]);
	}
	
	@Override
	public void putParam(String propertyName, String value) {
		params.put(CommonFunctionParams.valueOf(propertyName), value);
	}
}
