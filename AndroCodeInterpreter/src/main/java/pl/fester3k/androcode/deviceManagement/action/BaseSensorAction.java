package pl.fester3k.androcode.deviceManagement.action;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public abstract class BaseSensorAction extends BaseAction implements Action, SensorEventListener {
	protected SensorManager sensorManager;
	protected Sensor sensor;

	public BaseSensorAction(Context context, int sensorType) {
		this.context = context;
		if(sensorType != 0) {
			sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
			sensor = sensorManager.getDefaultSensor(sensorType);
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	public void cleanup() {
		sensorManager.unregisterListener(this, sensor);
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
//		logger.debug("sensor changed");		
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {}
}
