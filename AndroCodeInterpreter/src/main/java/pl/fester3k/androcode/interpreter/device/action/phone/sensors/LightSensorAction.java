package pl.fester3k.androcode.interpreter.device.action.phone.sensors;

import pl.fester3k.androcode.interpreter.device.action.BaseSensorAction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class LightSensorAction extends BaseSensorAction {
	private float value = -1;
	
	public LightSensorAction(Context context) {
		super(context, Sensor.TYPE_LIGHT);
	}

	@Override
	public Float getResult() {
		while(value == -1) {
			try {
				Thread.sleep(1000);
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
