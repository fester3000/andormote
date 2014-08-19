package pl.fester3k.androcode.interpreter.device.action;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public abstract class BaseSensorAction implements Action, SensorAction, SensorEventListener {
	protected Context context;
	protected SensorManager sensorManager;
	protected Sensor sensor;

	public BaseSensorAction(Context context, int sensorType) {
		this.context = context;
		sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(sensorType);
	}
	
	@Override
	public ActionResult run() {
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
		return null;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {}


	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		sensorManager.unregisterListener(this);	
	}
	
	
}
