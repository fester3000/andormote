package pl.fester3k.androcode.interpreter.device.action.phone.sensors;

import java.util.HashMap;
import java.util.Map;

import pl.fester3k.androcode.interpreter.device.action.BaseSensorAction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class GyroscopeSensorAction extends BaseSensorAction {
	private Map<String, Float> values = new HashMap<String, Float>();
	
	public GyroscopeSensorAction(Context context) {
		super(context, Sensor.TYPE_GYROSCOPE);
	}

	@Override
	public Map<String, Float> getResult() {
		while(values.isEmpty()) {
			try {
				Thread.sleep(1000);
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
