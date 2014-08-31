package pl.fester3k.androcode.deviceManagement.action.phone;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v4.content.LocalBroadcastManager;

public class PictureAction extends BaseDeviceAction {
	public PictureAction(Context context) {
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
		if(params.containsKey(ActionParams.PICTURE.FLASH)) {
			String result = Camera.Parameters.FLASH_MODE_AUTO;
			String flashMode = (String)params.get(ActionParams.PICTURE.FLASH);
			if(flashMode.equals("ON")) {
				result = Camera.Parameters.FLASH_MODE_ON;
			} else if (flashMode.equals("OFF")) {
				result = Camera.Parameters.FLASH_MODE_OFF;
			} else if (flashMode.equals("AUTO")) {
				result = Camera.Parameters.FLASH_MODE_AUTO;
			} else if (flashMode.equals("STILL")) {
				result = Camera.Parameters.FLASH_MODE_TORCH;
			}
			intent.putExtra(ActionParams.PICTURE.FLASH.toString(), result);
		}
		if(params.containsKey(ActionParams.PICTURE.QUALITY)) {
			int quality;
			try {
			quality = Integer.valueOf((String)params.get(ActionParams.PICTURE.QUALITY));
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				quality = 90;
			}
			intent.putExtra(ActionParams.PICTURE.QUALITY.toString(), quality);
		}
		if(params.containsKey(ActionParams.PICTURE.RESOLUTION)) {
			boolean isMaximumSize = true;
			String size = (String)params.get(ActionParams.PICTURE.RESOLUTION);
			if(size.equals("LOW")) {
				isMaximumSize = false;
			} else if(size.equals("HIGH")){
				isMaximumSize = true;
			}
			intent.putExtra(ActionParams.PICTURE.RESOLUTION.toString(), isMaximumSize);
		}
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(ActionParams.PICTURE.valueOf(propertyName), value);
	}
}
