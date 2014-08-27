package pl.fester3k.androcode.deviceManagement.action.phone.sensors;

import java.util.HashMap;
import java.util.Map;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.deviceManagement.action.BaseSensorAction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class GravitySensorAction extends BaseSensorAction {
	private Map<String, Float> values = new HashMap<String, Float>();
	
	public GravitySensorAction(Context context) {
		super(context, Sensor.TYPE_GRAVITY);
	}

	@Override
	public Object run() {
		float result = 0;
		if(params.containsKey(ActionParams.Others.GET.toString())) {
			String valueLabel = (String)params.get(ActionParams.Others.GET.toString());
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
}
