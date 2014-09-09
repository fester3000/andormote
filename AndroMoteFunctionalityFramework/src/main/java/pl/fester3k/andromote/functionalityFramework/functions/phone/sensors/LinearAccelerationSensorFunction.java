package pl.fester3k.andromote.functionalityFramework.functions.phone.sensors;

import java.util.HashMap;
import java.util.Map;

import pl.fester3k.andromote.functionalityFramework.datatypes.ActionParams;
import pl.fester3k.andromote.functionalityFramework.datatypes.ActionParams.Others;
import pl.fester3k.andromote.functionalityFramework.functions.BaseSensorFunction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class LinearAccelerationSensorFunction extends BaseSensorFunction  {
	private Map<String, Float> values = new HashMap<String, Float>();
	
	public LinearAccelerationSensorFunction(Context context) {
		super(context, Sensor.TYPE_LINEAR_ACCELERATION);
	}

	@Override
	public Object run() {
		float result = 0;
		if(params.containsKey(ActionParams.Others.GET)) {
			String valueLabel = (String)params.get(ActionParams.Others.GET);
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
		params.put(Others.valueOf(propertyName), value);
	}
}
