package pl.fester3k.androcode.interpreter.device.action.phone;

import java.util.Properties;

import pl.fester3k.androcode.interpreter.device.action.ActionResult;
import pl.fester3k.androcode.interpreter.device.action.BaseDeviceAction;
import pl.fester3k.androcode.interpreter.device.action.phone.helpers.CameraActivity;
import android.content.Context;
import android.content.Intent;

public class CameraAction extends BaseDeviceAction {
	public CameraAction(Context context, Properties params) {
		super(context, params);

	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent(context, CameraActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		return ActionResult.FAILED;
	}

}
