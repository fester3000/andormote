package pl.fester3k.androcode.deviceManagement.action.phone.sensors;

import pl.fester3k.androcode.deviceManagement.action.BaseSensorAction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class TemperatureSensorAction extends BaseSensorAction {
	private float value = -1;
	
	public TemperatureSensorAction(Context context) {
		super(context, Sensor.TYPE_AMBIENT_TEMPERATURE);
	}

	@Override
	public Object run() {
		return value;
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		super.onSensorChanged(sensorEvent);
		value = sensorEvent.values[0];		
	}
}
