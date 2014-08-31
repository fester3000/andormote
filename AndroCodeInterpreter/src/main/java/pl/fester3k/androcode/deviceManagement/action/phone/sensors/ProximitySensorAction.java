package pl.fester3k.androcode.deviceManagement.action.phone.sensors;

import pl.fester3k.androcode.deviceManagement.action.BaseSensorAction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class ProximitySensorAction extends BaseSensorAction {
	private float value = -1;
	
	public ProximitySensorAction(Context context) {
		super(context, Sensor.TYPE_PROXIMITY);
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
	
	@Override
	public void putParam(String propertyName, String value) {
		throw new UnsupportedOperationException();
	}
}
