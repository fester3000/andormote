package mobi.andromote.am2.functions.phone;

import mobi.andromote.am2.functions.ActionIntentHelper;
import mobi.andromote.am2.functions.CommonFunctionParams;
import mobi.andromote.functionalityFramework.datatypes.ActionResult;
import mobi.andromote.functionalityFramework.datatypes.FunctionParam;
import mobi.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class FlashlightFunction extends BaseDeviceFunction {
	public enum FLASHLIGHT implements FunctionParam {
		/**
		 * Stan latarki
		 * ON, OFF
		 */
		MODE;
	}
	
	public FlashlightFunction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent(ActionIntentHelper.ACTION);
		setIntentExtras(intent);
		intent.putExtra(CommonFunctionParams.ACTIVITY_MODE.toString(), ActionIntentHelper.ACTION_FLASHLIGHT);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		return ActionResult.COMPLETED;
	}

	@Override
	public void cleanup() {
		Intent intent = new Intent(ActionIntentHelper.ACTION);
		intent.putExtra(FLASHLIGHT.MODE.toString(), false);
		intent.putExtra(CommonFunctionParams.ACTIVITY_MODE.toString(), ActionIntentHelper.ACTION_FLASHLIGHT);
		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		super.cleanup();
	}

	private void setIntentExtras(Intent intent) {
		if(params.containsKey(FLASHLIGHT.MODE)) {
			Boolean result = true;
			String flashMode = (String)params.get(FLASHLIGHT.MODE);
			if(flashMode.equals("ON")) {
				result = true;
			} else if (flashMode.equals("OFF")) {
				result = false;
			} 
			intent.putExtra(FLASHLIGHT.MODE.toString(), result);
		}
	}
	
	@Override
	public void putParam(String propertyName, String value) {
		params.put(FLASHLIGHT.valueOf(propertyName), value);
	}
}
