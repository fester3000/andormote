package pl.fester3k.androcode.deviceManagement.action.phone;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v4.content.LocalBroadcastManager;

public class CameraAction extends BaseDeviceAction {
	public CameraAction(Context context) {
		super(context);		
	}
	
	@Override
	public ActionResult run() {
		Intent intent = new Intent(ActionParams.ACTION);
		setIntentExtras(intent);	
		intent.putExtra(ActionParams.Others.ACTIVITY_MODE.toString(), ActionParams.ACTION_PICTURE);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		return ActionResult.COMPLETED;
	}
	
	/**
	 * FLASH: ON, AUTO, STILL, OFF
	 * QUALITY: integer from 0 to 100
	 * SIZE:  SMALL, MAX
	 * @param intent
	 */
	private void setIntentExtras(Intent intent) {
		if(getParams().containsKey(ActionParams.CAMERA.FLASH.toString())) {
			String result = Camera.Parameters.FLASH_MODE_AUTO;
			String flashMode = (String)getParams().get(ActionParams.CAMERA.FLASH.toString());
			if(flashMode.equals("ON")) {
				result = Camera.Parameters.FLASH_MODE_ON;
			} else if (flashMode.equals("OFF")) {
				result = Camera.Parameters.FLASH_MODE_OFF;
			} else if (flashMode.equals("AUTO")) {
				result = Camera.Parameters.FLASH_MODE_AUTO;
			} else if (flashMode.equals("STILL")) {
				result = Camera.Parameters.FLASH_MODE_TORCH;
			}
			intent.putExtra(ActionParams.CAMERA.FLASH.toString(), result);
		}
		if(getParams().containsKey(ActionParams.CAMERA.QUALITY.toString())) {
			int quality;
			try {
			quality = Integer.valueOf((String)getParams().get(ActionParams.CAMERA.QUALITY.toString()));
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				quality = 90;
			}
			intent.putExtra(ActionParams.CAMERA.QUALITY.toString(), quality);
		}
		if(getParams().containsKey(ActionParams.CAMERA.RESOLUTION.toString())) {
			boolean isMaximumSize = true;
			String size = (String)getParams().get(ActionParams.CAMERA.RESOLUTION.toString());
			if(size.equals("LOW")) {
				isMaximumSize = false;
			} else if(size.equals("HIGH")){
				isMaximumSize = true;
			}
			intent.putExtra(ActionParams.CAMERA.RESOLUTION.toString(), isMaximumSize);
		}
	}

}
