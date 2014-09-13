package mobi.andromote.am2.functions.phone;

import mobi.andromote.am2.functions.ActionIntentHelper;
import mobi.andromote.am2.functions.CommonFunctionParams;
import mobi.andromote.functionalityFramework.datatypes.ActionResult;
import mobi.andromote.functionalityFramework.datatypes.FunctionParam;
import mobi.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v4.content.LocalBroadcastManager;

public class RecVideoFunction extends BaseDeviceFunction {
	public enum VIDEO implements FunctionParam {
		/**
		 * Stan nagrywania
		 * ON, OFF
		 */
		MODE,
		/**
		 * Tryb lampy doświetlającej przy nagrywaniu
		 * ON, OFF
		 */ 
		FLASH,
		/**
		 * Jakość nagrywanego obrazu
		 * LOW, HIGH
		 */ 
		QUALITY;
	}
	
	public RecVideoFunction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent(ActionIntentHelper.ACTION);
		setIntentExtras(intent);	
		intent.putExtra(CommonFunctionParams.ACTIVITY_MODE.toString(), ActionIntentHelper.ACTION_VIDEO);
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
		if(params.containsKey(VIDEO.MODE)) {
			boolean record = false;
			String mode = (String)params.get(VIDEO.MODE);
			if(mode.equals("ON")) {
				record = true;
			}
			intent.putExtra(VIDEO.MODE.toString(), record);
		}
		if(params.containsKey(VIDEO.FLASH)) {
			String result = Camera.Parameters.FLASH_MODE_OFF;
			String flashMode = (String)params.get(VIDEO.FLASH);
			if(flashMode.equals("ON")) {
				result = Camera.Parameters.FLASH_MODE_TORCH;
			} else if (flashMode.equals("OFF")) {
				result = Camera.Parameters.FLASH_MODE_OFF;
			} 
			intent.putExtra(VIDEO.FLASH.toString(), result);
		}
		if(params.containsKey(VIDEO.QUALITY)) {
			String quality = (String)params.get(VIDEO.QUALITY);
			intent.putExtra(VIDEO.QUALITY.toString(), quality);
		}
	}
	

	@Override
	public void putParam(String propertyName, String value) {
		params.put(VIDEO.valueOf(propertyName), value);
	}

}
