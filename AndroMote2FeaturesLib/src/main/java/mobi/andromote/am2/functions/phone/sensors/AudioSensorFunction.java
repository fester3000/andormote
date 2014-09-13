package mobi.andromote.am2.functions.phone.sensors;

import mobi.andromote.am2.functions.phone.helpers.AudioSensorHelper;
import mobi.andromote.functionalityFramework.functions.BaseSensorFunction;
import android.content.Context;
import android.hardware.SensorEvent;
import android.os.Handler;

public class AudioSensorFunction extends BaseSensorFunction {
	private static final int POLL_INTERVAL = 500;
	private AudioSensorHelper audioSensor;
	private double value;
	private final Handler handler;

	public AudioSensorFunction(Context context, Handler handler) {
		super(context, 0);
		audioSensor = new AudioSensorHelper();
		audioSensor.start();
		this.handler = handler;
		handler.postDelayed(mPollTask, POLL_INTERVAL);
	}

	@Override
	public void cleanup() {
		handler.removeCallbacks(mPollTask);
		audioSensor.stop();
	}

	@Override
	public Object run() {
		float result = (float) getValue();
		return result;
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		super.onSensorChanged(sensorEvent);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	// Create runnable thread to Monitor Voice
	private Runnable mPollTask = new Runnable() {
		public void run() {
			setValue(audioSensor.getAmplitudeEMA());

			handler.postDelayed(mPollTask, POLL_INTERVAL);
		}
	};

	@Override
	public void putParam(String propertyName, String value) {
		throw new UnsupportedOperationException();
	}
}
