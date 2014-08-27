package pl.fester3k.androcode.deviceManagement.action.phone.sensors;

import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseSensorAction;
import pl.fester3k.androcode.deviceManagement.action.phone.helpers.AudioSensor;
import android.content.Context;
import android.hardware.SensorEvent;

public class AudioSensorAction extends BaseSensorAction {
	private float value = -1;
	private AudioSensor audioSensor;

	public AudioSensorAction(Context context) {
		super(context, 0);
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		super.cleanup();
	}

	@Override
	public ActionResult run() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		super.onSensorChanged(sensorEvent);

		// TODO Auto-generated method stub
		
	}

//
//	private Runnable mSleepTask = new Runnable() {
//		public void run() {
//			//Log.i("Noise", "runnable mSleepTask");
//
//			start();
//		}
//	};
//
//	// Create runnable thread to Monitor Voice
//	private Runnable mPollTask = new Runnable() {
//		public void run() {
//
//			double amp = mSensor.getAmplitude();
//			//Log.i("Noise", "runnable mPollTask");
//			updateDisplay("Monitoring Voice...", amp);
//
//			if ((amp > mThreshold)) {
//				callForHelp();
//				//Log.i("Noise", "==== onCreate ===");
//
//			}
//
//			// Runnable(mPollTask) will again execute after POLL_INTERVAL
//			mHandler.postDelayed(mPollTask, POLL_INTERVAL);
//
//		}
//	};


}
