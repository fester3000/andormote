package pl.fester3k.androcode.interpreter.action.phone.sensors;

import java.util.HashMap;
import java.util.Map;

import pl.fester3k.androcode.interpreter.action.BaseSensorAction;
import pl.fester3k.androcode.interpreter.action.MapGetter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class LinearAccelerationAction extends BaseSensorAction implements MapGetter {
	private Map<String, Float> values = new HashMap<String, Float>();
	
	public LinearAccelerationAction(Context context) {
		super(context, Sensor.TYPE_LINEAR_ACCELERATION);
	}

	@Override
	public Map<String, Float> getMap() {
		while(values.isEmpty()) {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return values;
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		float[] sensorValues = sensorEvent.values;
		values.put("x", sensorValues[0]);
		values.put("y", sensorValues[0]);
		values.put("z", sensorValues[0]);
		super.onSensorChanged(sensorEvent);
	}
}
