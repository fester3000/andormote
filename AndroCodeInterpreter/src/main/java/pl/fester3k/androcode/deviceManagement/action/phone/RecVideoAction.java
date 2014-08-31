package pl.fester3k.androcode.deviceManagement.action.phone;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.datatypes.Feature;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v4.content.LocalBroadcastManager;

public class RecVideoAction extends BaseDeviceAction {
	public RecVideoAction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent(ActionParams.ACTION);
		setIntentExtras(intent);	
		intent.putExtra(ActionParams.Others.ACTIVITY_MODE.toString(), ActionParams.ACTION_VIDEO);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		return ActionResult.COMPLETED;
	}
	
	@Override
	public void cleanup() {
		// TODO zakończyć nagrywanie itp
		super.cleanup();
	}

	/**
	 * MODE: ON, OFF
	 * FLASH: ON, OFF
	 * QUALITY: HIGH, LOW
	 * 
	 * @param intent
	 */
	private void setIntentExtras(Intent intent) {
		if(params.containsKey(ActionParams.VIDEO.MODE)) {
			boolean record = false;
			String mode = (String)params.get(ActionParams.VIDEO.MODE);
			if(mode.equals("ON")) {
				record = true;
			}
			intent.putExtra(ActionParams.VIDEO.MODE.toString(), record);
		}
		if(params.containsKey(ActionParams.VIDEO.FLASH)) {
			String result = Camera.Parameters.FLASH_MODE_OFF;
			String flashMode = (String)params.get(ActionParams.VIDEO.FLASH);
			if(flashMode.equals("ON")) {
				result = Camera.Parameters.FLASH_MODE_TORCH;
			} else if (flashMode.equals("OFF")) {
				result = Camera.Parameters.FLASH_MODE_OFF;
			} 
			intent.putExtra(ActionParams.VIDEO.FLASH.toString(), result);
		}
		if(params.containsKey(ActionParams.VIDEO.QUALITY)) {
			String quality = (String)params.get(ActionParams.VIDEO.QUALITY);
			intent.putExtra(ActionParams.VIDEO.QUALITY.toString(), quality);
		}
	}
	

	@Override
	public void putParam(String propertyName, String value) {
		params.put(ActionParams.VIDEO.valueOf(propertyName), value);
	}

}
