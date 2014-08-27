package pl.fester3k.androcode.deviceManagement.action.phone.helpers;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG = CameraPreview.class.getSimpleName();
	private SurfaceHolder holder;
	private Camera camera;
	
	public CameraPreview(Context context, Camera camera) {
		super(context);
		this.camera = camera;
		
		holder = getHolder();
		holder.addCallback(this);
		// deprecated setting, but required on Android versions prior to 3.0
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		 if (holder.getSurface() == null){
	          // preview surface does not exist
	          return;
	        }

	        // stop preview before making changes
	        try {
	            camera.stopPreview();
	        } catch (Exception e){
	        	Log.d(TAG, "Error while stopingPreview()");
	          // ignore: tried to stop a non-existent preview
	        }

	        // set preview size and make any resize, rotate or
	        // reformatting changes here

	        // start preview with new settings
	        try {
	            camera.setPreviewDisplay(holder);
	            camera.startPreview();
	        } catch (Exception e){
	            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
	        }
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

}
