package pl.fester3k.andromote.functionalityFramework.functions.phone.sensors;

import pl.fester3k.andromote.functionalityFramework.functions.BaseSensorFunction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class HumidityFunction extends BaseSensorFunction {
	private float value = -1;
	
	public HumidityFunction(Context context) {
		super(context, Sensor.TYPE_RELATIVE_HUMIDITY);
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
