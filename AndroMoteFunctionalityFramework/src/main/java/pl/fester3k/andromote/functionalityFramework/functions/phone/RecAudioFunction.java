package pl.fester3k.andromote.functionalityFramework.functions.phone;

import pl.fester3k.andromote.functionalityFramework.datatypes.ActionParams;
import pl.fester3k.andromote.functionalityFramework.datatypes.ActionResult;
import pl.fester3k.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class RecAudioFunction extends BaseDeviceFunction {
	public RecAudioFunction(Context context) {
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
