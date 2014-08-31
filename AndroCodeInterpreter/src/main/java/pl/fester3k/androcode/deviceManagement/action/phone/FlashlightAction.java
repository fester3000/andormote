package pl.fester3k.androcode.deviceManagement.action.phone;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class FlashlightAction extends BaseDeviceAction {
	public FlashlightAction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent(ActionParams.ACTION);
		setIntentExtras(intent);
		intent.putExtra(ActionParams.Others.ACTIVITY_MODE.toString(), ActionParams.ACTION_FLASHLIGHT);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		return ActionResult.COMPLETED;
	}

	@Override
	public void cleanup() {
		Intent intent = new Intent(ActionParams.ACTION);
		intent.putExtra(ActionParams.FLASHLIGHT.MODE.toString(), false);
		intent.putExtra(ActionParams.Others.ACTIVITY_MODE.toString(), ActionParams.ACTION_FLASHLIGHT);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		super.cleanup();
	}

	private void setIntentExtras(Intent intent) {
		if(params.containsKey(ActionParams.FLASHLIGHT.MODE)) {
			Boolean result = true;
			String flashMode = (String)params.get(ActionParams.FLASHLIGHT.MODE);
			if(flashMode.equals("ON")) {
				result = true;
			} else if (flashMode.equals("OFF")) {
				result = false;
			} 
			intent.putExtra(ActionParams.FLASHLIGHT.MODE.toString(), result);
		}
	}
	
	@Override
	public void putParam(String propertyName, String value) {
		params.put(ActionParams.FLASHLIGHT.valueOf(propertyName), value);
	}
}
