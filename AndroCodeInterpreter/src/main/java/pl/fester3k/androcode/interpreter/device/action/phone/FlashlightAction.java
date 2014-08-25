package pl.fester3k.androcode.interpreter.device.action.phone;

import java.util.Properties;

import pl.fester3k.androcode.interpreter.device.action.ActionParams;
import pl.fester3k.androcode.interpreter.device.action.ActionResult;
import pl.fester3k.androcode.interpreter.device.action.BaseDeviceAction;
import pl.fester3k.androcode.interpreter.device.action.phone.helpers.CameraActivity;
import android.content.Context;
import android.content.Intent;

public class FlashlightAction extends BaseDeviceAction {
	public FlashlightAction(Context context, Properties params) {
		super(context, params);

	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent(context, CameraActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);		
		setIntentExtras(intent);
		intent.putExtra(ActionParams.Others.ACTIVITY_MODE.toString(), ActionParams.CAMERA_FLASHLIGHT);
		context.startActivity(intent);
		return ActionResult.COMPLETED;
	}

	private void setIntentExtras(Intent intent) {
		if(params.containsKey(ActionParams.FLASHLIGHT.MODE.toString())) {
			Boolean result = true;
			String flashMode = (String)params.get(ActionParams.FLASHLIGHT.MODE.toString());
			if(flashMode.equals("ON")) {
				result = true;
			} else if (flashMode.equals("OFF")) {
				result = false;
			} 
			intent.putExtra(ActionParams.FLASHLIGHT.MODE.toString(), result);
		}
	}
}
