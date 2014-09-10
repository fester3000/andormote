package pl.fester3k.andromote.am2.functions.phone;

import pl.fester3k.andromote.am2.functions.ActionIntentHelper;
import pl.fester3k.andromote.am2.functions.CommonFunctionParams;
import pl.fester3k.andromote.functionalityFramework.datatypes.ActionResult;
import pl.fester3k.andromote.functionalityFramework.datatypes.FunctionParam;
import pl.fester3k.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class RecAudioFunction extends BaseDeviceFunction {
	public enum AUDIO_IN implements FunctionParam {
		/**
		 * Stan nagrywania
		 * ON, OFF 
		 */
		MODE;
	}
	public RecAudioFunction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent(ActionIntentHelper.ACTION);
		setIntentExtras(intent);	
		intent.putExtra(CommonFunctionParams.ACTIVITY_MODE.toString(), ActionIntentHelper.ACTION_AUDIO);
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
		if(params.containsKey(AUDIO_IN.MODE)) {
			boolean record = false;
			String mode = (String)params.get(AUDIO_IN.MODE);
			if(mode.equals("ON")) {
				record = true;
			}
			intent.putExtra(AUDIO_IN.MODE.toString(), record);
		}
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(AUDIO_IN.valueOf(propertyName), value);
	}
}
