package mobi.andromote.am2.functions.phone.helpers;

import java.io.IOException;

import mobi.andromote.functionalityFramework.FunctionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.media.MediaRecorder;

public class AudioSensorHelper {
	private final Logger logger = LoggerFactory.getLogger(AudioSensorHelper.class);
	
	static final private double EMA_FILTER = 0.6;

	private MediaRecorder mRecorder = null;
	private double mEMA = 0.0;

	public void start() {
		logger.debug("AudioSensor started");
		if (mRecorder == null) {
			mRecorder = new MediaRecorder();
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			mRecorder.setOutputFile("/dev/null"); 

			try {
				mRecorder.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mRecorder.start();
			mEMA = 0.0;
		}
	}

	public void stop() {
		logger.debug("AudioSensor stopped");
		if (mRecorder != null) {
			mRecorder.stop();       
			mRecorder.release();
			mRecorder = null;
		}
	}

	public synchronized double getAmplitude() {
		if (mRecorder != null)
			return  (mRecorder.getMaxAmplitude()/2700.0);
		else
			return 0;

	}

	public synchronized double getAmplitudeEMA() {
		double amp = getAmplitude();
		mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
		return mEMA * 10;
	}
}

