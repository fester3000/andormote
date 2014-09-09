package pl.fester3k.andromote.functionalityFramework.functions.phone.helpers;

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
			//FIXME
			/*
			 * 08-28 13:29:12.630: E/AndroidRuntime(1520): FATAL EXCEPTION: main
08-28 13:29:12.630: E/AndroidRuntime(1520): Process: mobi.andromote.andro, PID: 1520
08-28 13:29:12.630: E/AndroidRuntime(1520): java.lang.NullPointerException
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at pl.fester3k.andromote.functionalityFrameworkaction.phone.helpers.CameraPreview.surfaceCreated(CameraPreview.java:29)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.SurfaceView.updateWindow(SurfaceView.java:572)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.SurfaceView.onWindowVisibilityChanged(SurfaceView.java:232)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.View.dispatchWindowVisibilityChanged(View.java:8004)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.ViewGroup.dispatchWindowVisibilityChanged(ViewGroup.java:1077)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.ViewGroup.dispatchWindowVisibilityChanged(ViewGroup.java:1077)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.ViewGroup.dispatchWindowVisibilityChanged(ViewGroup.java:1077)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.ViewGroup.dispatchWindowVisibilityChanged(ViewGroup.java:1077)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.ViewGroup.dispatchWindowVisibilityChanged(ViewGroup.java:1077)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:1233)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:996)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:5600)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.Choreographer$CallbackRecord.run(Choreographer.java:761)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.Choreographer.doCallbacks(Choreographer.java:574)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.Choreographer.doFrame(Choreographer.java:544)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:747)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.os.Handler.handleCallback(Handler.java:733)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.os.Handler.dispatchMessage(Handler.java:95)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.os.Looper.loop(Looper.java:136)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at android.app.ActivityThread.main(ActivityThread.java:5001)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at java.lang.reflect.Method.invokeNative(Native Method)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at java.lang.reflect.Method.invoke(Method.java:515)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:785)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:601)
08-28 13:29:12.630: E/AndroidRuntime(1520): 	at dalvik.system.NativeStart.main(Native Method)
*/
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
