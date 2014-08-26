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
		Intent intent = new Intent(ActionParams.ACTION);
		setIntentExtras(intent);	
		intent.putExtra(ActionParams.Others.ACTIVITY_MODE.toString(), ActionParams.ACTION_PICTURE);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		return ActionResult.COMPLETED;
	}

	/**
	 * FLASH:
	 * ON, AUTO, OF
	 * QUALITY:
	 * integer from 0 to 100
	 * SIZE:
	 * SMALL, MAX
	 * @param intent
	 */
	private void setIntentExtras(Intent intent) {
		if(params.containsKey(ActionParams.CAMERA.FLASH.toString())) {
			String result = Camera.Parameters.FLASH_MODE_AUTO;
			String flashMode = (String)params.get(ActionParams.CAMERA.FLASH.toString());
			if(flashMode.equals("ON")) {
				result = Camera.Parameters.FLASH_MODE_ON;
			} else if (flashMode.equals("OFF")) {
				result = Camera.Parameters.FLASH_MODE_OFF;
			} else if (flashMode.equals("AUTO")) {
				result = Camera.Parameters.FLASH_MODE_AUTO;
			}
			intent.putExtra(ActionParams.CAMERA.FLASH.toString(), result);
		}
		if(params.containsKey(ActionParams.CAMERA.QUALITY.toString())) {
			int quality;
			try {
			quality = Integer.valueOf((String)params.get(ActionParams.CAMERA.QUALITY.toString()));
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				quality = 90;
			}
			intent.putExtra(ActionParams.CAMERA.QUALITY.toString(), quality);
		}
		if(params.containsKey(ActionParams.CAMERA.SIZE.toString())) {
			boolean isMaximumSize = true;
			String size = (String)params.get(ActionParams.CAMERA.SIZE.toString());
			if(size.equals("SMALL")) {
				isMaximumSize = false;
			} else if(size.equals("MAX")){
				isMaximumSize = true;
			}
			intent.putExtra(ActionParams.CAMERA.SIZE.toString(), isMaximumSize);
		}
	}

}
