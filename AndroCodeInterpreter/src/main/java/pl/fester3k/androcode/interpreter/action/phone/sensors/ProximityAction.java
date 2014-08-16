package pl.fester3k.androcode.interpreter.action.phone.sensors;

import pl.fester3k.androcode.interpreter.action.BaseSensorAction;
import pl.fester3k.androcode.interpreter.action.FloatGetter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class ProximityAction extends BaseSensorAction implements FloatGetter, SensorEventListener {
	private float value = -1;
	
	public ProximityAction(Context context) {
		super(context, Sensor.TYPE_PROXIMITY);
	}

	@Override
	public float getFloat() {
		while(value == -1) {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		value = sensorEvent.values[0];		
		super.onSensorChanged(sensorEvent);
	}
}
