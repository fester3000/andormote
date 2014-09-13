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

public class PictureFunction extends BaseDeviceFunction {
	/**
	 * 
	 * @author Sebastian Luczak
	 *
	 */
	public enum PICTURE implements FunctionParam {
		/**
		 * Tryb flasha w aparacie
		 * ON, OFF, AUTO, STILL
		 */ 
		FLASH,
		
		/**
		 * Wyjściowa rozdzielczość zdjęcia
		 * LOW, HIGH
		 */ 
		RESOLUTION,
		
		/**
		 * Jakość wyjściowego pliku JPEG
		 * wartość całkowita od 0 do 100
		 */ 
		QUALITY;
	}
	
	public PictureFunction(Context context) {
		super(context);		
	}
	
	@Override
	public ActionResult run() {
		Intent intent = new Intent(ActionIntentHelper.ACTION);
		setIntentExtras(intent);	
		intent.putExtra(CommonFunctionParams.ACTIVITY_MODE.toString(), ActionIntentHelper.ACTION_PICTURE);
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
		if(params.containsKey(PICTURE.FLASH)) {
			String result = Camera.Parameters.FLASH_MODE_AUTO;
			String flashMode = (String)params.get(PICTURE.FLASH);
			if(flashMode.equals("ON")) {
				result = Camera.Parameters.FLASH_MODE_ON;
			} else if (flashMode.equals("OFF")) {
				result = Camera.Parameters.FLASH_MODE_OFF;
			} else if (flashMode.equals("AUTO")) {
				result = Camera.Parameters.FLASH_MODE_AUTO;
			} else if (flashMode.equals("STILL")) {
				result = Camera.Parameters.FLASH_MODE_TORCH;
			}
			intent.putExtra(PICTURE.FLASH.toString(), result);
		}
		if(params.containsKey(PICTURE.QUALITY)) {
			int quality;
			try {
			quality = Integer.valueOf((String)params.get(PICTURE.QUALITY));
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				quality = 90;
			}
			intent.putExtra(PICTURE.QUALITY.toString(), quality);
		}
		if(params.containsKey(PICTURE.RESOLUTION)) {
			boolean isMaximumSize = true;
			String size = (String)params.get(PICTURE.RESOLUTION);
			if(size.equals("LOW")) {
				isMaximumSize = false;
			} else if(size.equals("HIGH")){
				isMaximumSize = true;
			}
			intent.putExtra(PICTURE.RESOLUTION.toString(), isMaximumSize);
		}
	}

	@Override
	public void putParam(String propertyName, String value) {
		params.put(PICTURE.valueOf(propertyName), value);
	}
}
