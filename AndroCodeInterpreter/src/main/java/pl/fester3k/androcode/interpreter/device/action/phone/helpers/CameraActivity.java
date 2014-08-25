package pl.fester3k.androcode.interpreter.device.action.phone.helpers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.androcode.R;
import pl.fester3k.androcode.interpreter.device.action.ActionParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {
	private static final Logger log = LoggerFactory.getLogger(CameraActivity.class.getSimpleName());
	private static final boolean DEFAULT_IS_MAXIMUM_SIZE = true;
	private static final boolean DEFAULT_FLASHLIGHT_MODE = false;
	private static final String DEFAULT_FLASH_MODE = Camera.Parameters.FLASH_MODE_AUTO;
	private static final String DEFAULT_FOCUS_MODE = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE;
	private static final int DEFAULT_JPEG_QUALITY = 90;
	private Camera camera;
	private CameraPreview preview;
	FrameLayout previewFrame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
	} 

	@Override
	protected void onResume() {
		super.onResume();
		camera = getCameraInstance();
		Intent intent = getIntent();
		if(camera == null) {
			return;
		}

		if(intent != null) {
			Bundle extras = intent.getExtras();
			previewFrame = (FrameLayout) findViewById(R.id.camera_preview);
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
		log.debug("Flashlight: " + flashlightTurnOn);
		Camera.Parameters params = camera.getParameters();
		if(flashlightTurnOn) {
			params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
			camera.setParameters(params);
			preview = new CameraPreview(this, camera);
			previewFrame.addView(preview);
		} else {
			camera.stopPreview();
			camera.release();
			finish();
		}
	}

	private void takePhoto(Bundle extras) {
		int jpegQuality = extras.getInt(ActionParams.Camera.JPEG_QUALITY.toString(), DEFAULT_JPEG_QUALITY);
		String flashMode = extras.getString(ActionParams.Camera.FLASH.toString(), DEFAULT_FLASH_MODE);
		boolean isMaximumSize = extras.getBoolean(ActionParams.Camera.SIZE.toString(), DEFAULT_IS_MAXIMUM_SIZE);

		log.debug("Quality: " + jpegQuality + " flash: " + flashMode + " sizeMode: " + isMaximumSize);
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
		preview = new CameraPreview(this, camera);
		previewFrame.addView(preview);	

		TakePhotoTask task = new TakePhotoTask(this);
		task.execute();
	}

	@Override
	protected void onPause() {
		camera.release();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance(){
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		}
		catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return c; // returns null if camera is unavailable
	}

	private class TakePhotoTask extends AsyncTask<Void, Void, Void> {

		private static final long SLEEP_TIME_BEFORE_PHOTO = 1500;
		private static final long SLEEP_INTERVAL_TIME_AFTER_PHOTO = 200;
		private Activity context;
		private PhotoHandler jpegHandler;

		public TakePhotoTask(Activity context) {
			super();
			this.context = context;
			jpegHandler = new PhotoHandler(context);
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(SLEEP_TIME_BEFORE_PHOTO);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			camera.takePicture(null, null, jpegHandler);
			while(!jpegHandler.isDone()) {
				try {
					Thread.sleep(SLEEP_INTERVAL_TIME_AFTER_PHOTO);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			context.finish();
			return null;
		}

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
