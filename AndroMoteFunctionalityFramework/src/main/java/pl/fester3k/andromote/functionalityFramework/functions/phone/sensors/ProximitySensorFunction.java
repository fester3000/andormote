package pl.fester3k.andromote.functionalityFramework.functions.phone.sensors;

import pl.fester3k.andromote.functionalityFramework.functions.BaseSensorFunction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class ProximitySensorFunction extends BaseSensorFunction {
	private float value = -1;
	
	public ProximitySensorFunction(Context context) {
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
