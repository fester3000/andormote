package pl.fester3k.androcode.interpreter.device.action.phone;

import java.util.Properties;

import pl.fester3k.androcode.interpreter.device.action.ActionParams;
import pl.fester3k.androcode.interpreter.device.action.ActionResult;
import pl.fester3k.androcode.interpreter.device.action.BaseDeviceAction;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v4.content.LocalBroadcastManager;

public class RecVideoAction extends BaseDeviceAction {
	public RecVideoAction(Context context, Properties params) {
		super(context, params);

	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent(ActionParams.ACTION);
		setIntentExtras(intent);	
		intent.putExtra(ActionParams.Others.ACTIVITY_MODE.toString(), ActionParams.ACTION_VIDEO);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		return ActionResult.COMPLETED;
	}

	/**
	 * MODE: ON, OFF
	 * FLASH: ON, OFF
	 * QUALITY: HIGH, LOW
	 * 
	 * @param intent
	 */
	private void setIntentExtras(Intent intent) {
		if(params.containsKey(ActionParams.VIDEO.MODE.toString())) {
			boolean record = false;
			String mode = (String)params.get(ActionParams.VIDEO.MODE.toString());
			if(mode.equals("ON")) {
				record = true;
			}
			intent.putExtra(ActionParams.VIDEO.MODE.toString(), record);
		}
	}

}
