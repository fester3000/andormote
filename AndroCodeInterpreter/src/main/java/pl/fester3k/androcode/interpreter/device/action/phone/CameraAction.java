package pl.fester3k.androcode.interpreter.device.action.phone;

import java.util.Properties;

import pl.fester3k.androcode.interpreter.device.action.ActionParams;
import pl.fester3k.androcode.interpreter.device.action.ActionResult;
import pl.fester3k.androcode.interpreter.device.action.BaseDeviceAction;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v4.content.LocalBroadcastManager;

public class CameraAction extends BaseDeviceAction {
	public CameraAction(Context context, Properties params) {
		super(context, params);

	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent("CAMERA_ACTION");
		setIntentExtras(intent);	
		intent.putExtra(ActionParams.Others.ACTIVITY_MODE.toString(), ActionParams.CAMERA_PICTURE);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		return ActionResult.COMPLETED;
	}

	private void setIntentExtras(Intent intent) {
		if(params.containsKey(ActionParams.Camera.FLASH.toString())) {
			String result = Camera.Parameters.FLASH_MODE_AUTO;
			String flashMode = (String)params.get(ActionParams.Camera.FLASH.toString());
			if(flashMode.equals("ON")) {
				result = Camera.Parameters.FLASH_MODE_ON;
			} else if (flashMode.equals("OFF")) {
				result = Camera.Parameters.FLASH_MODE_OFF;
			} else if (flashMode.equals("AUTO")) {
				result = Camera.Parameters.FLASH_MODE_AUTO;
			}
			intent.putExtra(ActionParams.Camera.FLASH.toString(), result);
		}
		if(params.containsKey(ActionParams.Camera.JPEG_QUALITY.toString())) {
			int quality;
			try {
			quality = Integer.valueOf((String)params.get(ActionParams.Camera.JPEG_QUALITY.toString()));
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				quality = 90;
			}
			intent.putExtra(ActionParams.Camera.JPEG_QUALITY.toString(), quality);
		}
		if(params.containsKey(ActionParams.Camera.SIZE.toString())) {
			boolean isMaximumSize = true;
			String size = (String)params.get(ActionParams.Camera.SIZE.toString());
			if(size.equals("SMALL")) {
				isMaximumSize = false;
			} else if(size.equals("MAX")){
				isMaximumSize = true;
			}
			intent.putExtra(ActionParams.Camera.SIZE.toString(), isMaximumSize);
		}
	}

}
