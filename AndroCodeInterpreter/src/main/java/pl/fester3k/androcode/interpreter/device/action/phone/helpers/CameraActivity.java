package pl.fester3k.androcode.interpreter.device.action.phone.helpers;

import pl.fester3k.androcode.R;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {
	private Camera camera;
	private CameraPreview preview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.activity_camera);
		camera = getCameraInstance();
		Camera.Parameters params = camera.getParameters();  
        params.setJpegQuality(100);  
        camera.setParameters(params);  
		preview = new CameraPreview(this, camera);
		FrameLayout previewFrame = (FrameLayout) findViewById(R.id.camera_preview);
		previewFrame.addView(preview);	
	}   

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			camera.takePicture(null, null, new PhotoHandler(this));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
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
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}
	
	private class TakePhotoTask extends AsyncTask<Void, Void, Void> {

		private static final long SLEEP_BEFORE_TAKING_PHOTO = 1500;
		private Activity context;

		public TakePhotoTask(Activity context) {
			super();
			this.context = context;
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(SLEEP_BEFORE_TAKING_PHOTO);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			camera.takePicture(null, null, new PhotoHandler(context));
			try {
				Thread.sleep(SLEEP_BEFORE_TAKING_PHOTO);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			context.finish();
			return null;
		}
		
	}
}
