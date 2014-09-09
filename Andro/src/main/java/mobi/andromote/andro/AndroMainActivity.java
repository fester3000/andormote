package mobi.andromote.andro;

import java.io.File;
import java.io.IOException;
import java.util.List;

import mobi.andromote.andro.AndroCodeService.LocalBinder;

import org.apache.log4j.Logger;

import pl.fester3k.andromote.functionalityFramework.functions.phone.helpers.CameraPreview;
import pl.fester3k.andromote.functionalityFramework.functions.phone.helpers.PhotoHandler;
import pl.fester3k.andromote.functionalityFramework.datatypes.ActionParams;
import pl.fester3k.andromote.functionalityFramework.datatypes.BroadcastIntentFilters;
import pl.fester3k.andromote.functionalityFramework.utils.FileUtils;
import pl.fester3k.andromote.functionalityFramework.utils.FileUtils.MediaType;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.FrameLayout;
import android.widget.Toast;

public class AndroMainActivity extends Activity {
	private final Logger logger = Logger.getLogger(AndroMainActivity.class);
	private static final boolean DEFAULT_IS_MAXIMUM_SIZE = true;
	private static final boolean DEFAULT_FLASHLIGHT_MODE = false;
	private static final String DEFAULT_FLASH_MODE = Camera.Parameters.FLASH_MODE_AUTO;
	private static final String DEFAULT_FOCUS_MODE = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE;
	private static final int DEFAULT_JPEG_QUALITY = 90;
	AndroCodeService androCodeService;
	boolean isBound = false;

	private Camera camera;
	private CameraPreview preview;
	FrameLayout previewFrame;
	private MediaRecorder mediaRecorder;
	private TakePhotoTask task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, AndroCodeService.class);
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
		LocalBroadcastManager.getInstance(this).registerReceiver(localMessageReceiver, new IntentFilter(BroadcastIntentFilters.TOAST));
		LocalBroadcastManager.getInstance(this).registerReceiver(cameraActionsReceiver, new IntentFilter(ActionParams.ACTION));
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		safeCameraOpenInView();
	}

	@Override
	protected void onPause() {
		if(mediaRecorder != null) {
			mediaRecorder.release();
		}
		releaseCameraAndPreview();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(localMessageReceiver);
		if(isBound) {
			unbindService(connection);
			isBound = false;
		}
	}	

	private BroadcastReceiver localMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String message = intent.getStringExtra("message");
			Toast.makeText(AndroMainActivity.this, message, Toast.LENGTH_SHORT).show();
		}
	};

	private BroadcastReceiver cameraActionsReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(AndroMainActivity.this, "Activity action received ", Toast.LENGTH_SHORT).show();
			logger.debug("Activity action received");
			WorkerThread thread = new WorkerThread(intent);
			thread.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
		}
	};

	private ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName className,
				IBinder service) {
			// We've bound to LocalService, cast the IBinder and get LocalService instance
			LocalBinder binder = (LocalBinder) service;
			androCodeService = binder.getService();
			isBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			isBound = false;
		}
	};

	private synchronized void interpretIntent(Intent intent) {
		if(camera == null) {
			return;
		}
		if(intent != null) {
			Bundle extras = intent.getExtras();
			int activityMode = extras.getInt(ActionParams.Others.ACTIVITY_MODE.toString());
			switch(activityMode) {
			case ActionParams.ACTION_PICTURE: 
				takePhoto(extras); 
				break;
			case ActionParams.ACTION_FLASHLIGHT: 
				useFlashlight(extras);
				break;
			case ActionParams.ACTION_VIDEO: 
				recordVideo(extras);
				break;
			case ActionParams.ACTION_AUDIO:
				recordAudio(extras);
				break;
			}
		}
	}

	private void useFlashlight(Bundle extras) {
		boolean flashlightTurnOn = extras.getBoolean(ActionParams.FLASHLIGHT.MODE.toString(), DEFAULT_FLASHLIGHT_MODE);
		logger.debug("Flashlight: " + flashlightTurnOn);
		Camera.Parameters params = camera.getParameters();
		if(flashlightTurnOn) {
			params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
			camera.setParameters(params);
		} else {
			params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			camera.setParameters(params);
		}
	}

	private void takePhoto(Bundle extras) {
		int jpegQuality = extras.getInt(ActionParams.PICTURE.QUALITY.toString(), DEFAULT_JPEG_QUALITY);
		String flashMode = extras.getString(ActionParams.PICTURE.FLASH.toString(), DEFAULT_FLASH_MODE);
		boolean isMaximumSize = extras.getBoolean(ActionParams.PICTURE.RESOLUTION.toString(), DEFAULT_IS_MAXIMUM_SIZE);

		logger.debug("Quality: " + jpegQuality + " flash: " + flashMode + " sizeMode: " + isMaximumSize);
		Camera.Parameters params = camera.getParameters();  
		params.setJpegQuality(jpegQuality);
		params.setFocusMode(DEFAULT_FOCUS_MODE);  
		params.setFlashMode(flashMode);
		if(isMaximumSize) {
			List<Size> imageSizes = camera.getParameters().getSupportedPictureSizes();
			Size size = getMaximumPictureSize(imageSizes);
			params.setPictureSize(size.width, size.height);
		}
		camera.setParameters(params);  
		task = new TakePhotoTask(this);
		task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
	}

	private void recordVideo(Bundle extras) {
		boolean record = extras.getBoolean(ActionParams.VIDEO.MODE.toString(), false);
		if(record) {
			logger.info("starting video recording");
			startVideoRecording(extras);
		} else {
			logger.info("stoping video recording");
			stopVideoRecording();
		}
	}

	private void stopVideoRecording() {
		stopMediaRecording();
		try {
			camera.reconnect();
			Camera.Parameters params = camera.getParameters();  
			params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			camera.setParameters(params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void stopMediaRecording() {
		// stop media recorder
		mediaRecorder.stop();
		// reset media recorder
		mediaRecorder.reset();
	}

	private void startVideoRecording(Bundle extras) {
		String quality = extras.getString(ActionParams.VIDEO.QUALITY.toString(), "HIGH");
		String flash = extras.getString(ActionParams.VIDEO.FLASH.toString(), Camera.Parameters.FLASH_MODE_OFF);
		try {
			File videoFile = FileUtils.createFileName(MediaType.VIDEO);
			File mediafile = videoFile;
			if(mediafile.exists()) {
				mediafile.delete();
			}
			mediafile = null;
			// set up media recorder
			if(mediaRecorder == null) {
				mediaRecorder = new MediaRecorder();
			}
			Camera.Parameters params = camera.getParameters();  
			params.setFlashMode(flash);
			camera.setParameters(params);
			if(camera != null) {
				camera.unlock();
			}
			mediaRecorder.setCamera(camera);

			mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);			
			mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);


			CamcorderProfile profile = null;
			if(quality.equals("HIGH")) {
				profile = getBestPossibleCamcoderProfile();
			} else {
				profile = getLowCamcoderProfile();
			}
			if (profile != null) {
				mediaRecorder.setProfile(profile);
			}
			mediaRecorder.setOutputFile(videoFile.toString());

			// prepare media recorder
			mediaRecorder.prepare();
			// start media recorder
			mediaRecorder.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private CamcorderProfile getLowCamcoderProfile() {
		CamcorderProfile profile = null;
		if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_480P))
			profile = CamcorderProfile.get(CamcorderProfile.QUALITY_480P);
		else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_HIGH))
			profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
		return profile;
	}

	private CamcorderProfile getBestPossibleCamcoderProfile() {
		CamcorderProfile profile = null;
		if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_1080P))
			profile = CamcorderProfile.get(CamcorderProfile.QUALITY_1080P);
		else if (CamcorderProfile.hasProfile(CamcorderProfile.QUALITY_720P))
			profile = CamcorderProfile.get(CamcorderProfile.QUALITY_720P);
		else {
			profile = getLowCamcoderProfile();
		}
		return profile;
	}

	private void recordAudio(Bundle extras) {
		boolean record = extras.getBoolean(ActionParams.AUDIO_IN.MODE.toString(), false);
		if(record) {
			logger.info("starting audio recording");
			startAudioRecording();
		} else {
			logger.info("stoping audio recording");
			stopMediaRecording();
		}
	}

	private void startAudioRecording() {
		try {
			File audioFile = FileUtils.createFileName(MediaType.AUDIO);
			File mediafile = audioFile;
			if(mediafile.exists()) {
				mediafile.delete();
			}
			mediafile = null;
			// set up media recorder
			if(mediaRecorder == null) {
				mediaRecorder = new MediaRecorder();
			}
			mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
			mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC);
			mediaRecorder.setOutputFile(audioFile.toString());

			// prepare media recorder
			mediaRecorder.prepare();
			// start media recorder
			mediaRecorder.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean safeCameraOpenInView() { 
		boolean qOpened = false;
//		releaseCameraAndPreview(); 
		camera = getCameraInstance(); 
		qOpened = (camera != null); 
		preview = new CameraPreview(this, camera); 
		previewFrame = (FrameLayout) findViewById(R.id.camera_preview); 
		previewFrame.addView(preview); 
		return qOpened; 
	}

	/**
	 * Clear any existing preview / camera.
	 */
	private void releaseCameraAndPreview() {
		if (camera != null) {
			camera.stopPreview();
			camera.release();
			camera = null;
		}
		if(preview != null){
			preview.destroyDrawingCache();
			preview.setCamera(null);
			previewFrame.removeAllViews();
		}
	}

	private class TakePhotoTask extends AsyncTask<Void, Void, Void> {
		private static final long SLEEP_TIME_BEFORE_PHOTO = 1500;
		private static final long SLEEP_INTERVAL_TIME_AFTER_PHOTO = 200;
		private PhotoHandler jpegHandler;

		public TakePhotoTask(Activity context) {
			super();
			jpegHandler = new PhotoHandler(context);
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(SLEEP_TIME_BEFORE_PHOTO);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!isCancelled()) {
				camera.takePicture(null, null, jpegHandler);
				while(!jpegHandler.isDone()) {
					try {
						Thread.sleep(SLEEP_INTERVAL_TIME_AFTER_PHOTO);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				logger.debug("after picture");
			}
			return null;
		}
	}

	private class WorkerThread extends AsyncTask<Void, Void, Void> {
		private final Intent intent;

		public WorkerThread(Intent intent) {
			super();
			this.intent = intent;
		}

		@Override
		protected Void doInBackground(Void... params) {
			interpretIntent(intent);
			return null;
		}
	}

	/** A safe way to get an instance of the Camera object. */
	public Camera getCameraInstance(){
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
			logger.debug("Getting camera!!");
		}
		catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return c; // returns null if camera is unavailable
	}

	private Size getMaximumPictureSize(List<Size> imageSizes) {
		Size currentMaxSize = null;
		long sizeField = 0;
		for (Size size : imageSizes) {
			long tempSizeField = size.width*size.height;
			if(tempSizeField > sizeField) {
				currentMaxSize = size;
				sizeField = tempSizeField;
			}
		}
		return currentMaxSize;
	}
}
