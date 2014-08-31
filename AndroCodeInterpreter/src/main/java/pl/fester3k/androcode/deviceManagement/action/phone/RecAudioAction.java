package pl.fester3k.androcode.deviceManagement.action.phone;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.datatypes.Feature;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class RecAudioAction extends BaseDeviceAction {
	public RecAudioAction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent(ActionParams.ACTION);
		setIntentExtras(intent);	
		intent.putExtra(ActionParams.Others.ACTIVITY_MODE.toString(), ActionParams.ACTION_AUDIO);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		return ActionResult.COMPLETED;
	}

	
	@Override
	public void cleanup() {
		// TODO zakończyć nagrywanie itp
		super.cleanup();
	}

	/**
	 * MODE:
	 * ON, OFF
	 * @param intent
	 */
	private void setIntentExtras(Intent intent) {
		if(params.containsKey(ActionParams.AUDIO_IN.MODE)) {
			boolean record = false;
			String mode = (String)params.get(ActionParams.AUDIO_IN.MODE);
			if(mode.equals("ON")) {
				record = true;
			}
			intent.putExtra(ActionParams.AUDIO_IN.MODE.toString(), record);
		}
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(ActionParams.AUDIO_IN.valueOf(propertyName), value);
	}
}