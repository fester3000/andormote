package mobi.andromote.andro;

import java.util.List;

import mobi.andromote.andro.AndroCodeService.LocalBinder;

import org.apache.log4j.Logger;

import pl.fester3k.androcode.interpreter.device.action.ActionParams;
import pl.fester3k.androcode.interpreter.device.action.phone.helpers.CameraPreview;
import pl.fester3k.androcode.interpreter.device.action.phone.helpers.PhotoHandler;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.Camera;
import android.hardware.Camera.Size;
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
	private TakePhotoTask task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, AndroCodeService.class);
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
		LocalBroadcastManager.getInstance(this).registerReceiver(localMessageReceiver, new IntentFilter("message-event"));
		LocalBroadcastManager.getInstance(this).registerReceiver(cameraActionsReceiver, new IntentFilter("CAMERA_ACTION"));
		setContentView(R.layout.activity_main);
		safeCameraOpenInView();
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
			Toast.makeText(AndroMainActivity.this, "camera action received ", Toast.LENGTH_SHORT).show();
			logger.debug("camera action received");
			interpretIntent(intent);
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


	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		releaseCameraAndPreview();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(localMessageReceiver);
		if(isBound) {
			unbindService(connection);
			isBound = false;
		}
	}	

	private synchronized void interpretIntent(Intent intent) {
		if(camera == null) {
			return;
		}
		if(intent != null) {
			Bundle extras = intent.getExtras();
			int activityMode = extras.getInt(ActionParams.Others.ACTIVITY_MODE.toString());
			switch(activityMode) {
			case ActionParams.CAMERA_PICTURE: 
				takePhoto(extras); 
				break;
			case ActionParams.CAMERA_FLASHLIGHT: 
				useFlashlight(extras);
				break;
			case ActionParams.CAMERA_VIDEO: 
				recordVideo(extras);
				break;
			}
		}
	}

	private void recordVideo(Bundle extras) {
		// TODO Auto-generated method stub

	}

	private void useFlashlight(Bundle extras) {
		boolean flashlightTurnOn = extras.getBoolean(ActionParams.FLASHLIGHT.MODE.toString(), DEFAULT_FLASHLIGHT_MODE);
		logger.debug("Flashlight: " + flashlightTurnOn);
		Camera.Parameters params = camera.getParameters();
		if(flashlightTurnOn) {
			params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
			camera.setParameters(params);
			camera.stopPreview();
			preview = new CameraPreview(this, camera); 
			FrameLayout previewFrame = (FrameLayout) findViewById(R.id.camera_preview);
			previewFrame.addView(preview); 
		} else {
			params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			camera.setParameters(params);
		}
	}

	private void takePhoto(Bundle extras) {
		int jpegQuality = extras.getInt(ActionParams.Camera.JPEG_QUALITY.toString(), DEFAULT_JPEG_QUALITY);
		String flashMode = extras.getString(ActionParams.Camera.FLASH.toString(), DEFAULT_FLASH_MODE);
		boolean isMaximumSize = extras.getBoolean(ActionParams.Camera.SIZE.toString(), DEFAULT_IS_MAXIMUM_SIZE);

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

	private boolean safeCameraOpenInView() { 
		boolean qOpened = false;
		releaseCameraAndPreview(); 
		camera = getCameraInstance(); 
		qOpened = (camera != null); 
		preview = new CameraPreview(this, camera); 
		FrameLayout previewFrame = (FrameLayout) findViewById(R.id.camera_preview); 
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
