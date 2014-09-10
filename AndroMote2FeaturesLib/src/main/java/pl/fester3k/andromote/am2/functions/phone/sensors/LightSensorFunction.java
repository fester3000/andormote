package pl.fester3k.andromote.am2.functions.phone.sensors;

import pl.fester3k.andromote.functionalityFramework.functions.BaseSensorFunction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class LightSensorFunction extends BaseSensorFunction {
	private float value = -1;
	
	public LightSensorFunction(Context context) {
		super(context, Sensor.TYPE_LIGHT);
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
