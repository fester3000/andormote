package pl.fester3k.androcode.interpreter.device.action.phone;

import java.util.Properties;

import pl.fester3k.androcode.interpreter.device.action.ActionResult;
import pl.fester3k.androcode.interpreter.device.action.BaseDeviceAction;
import pl.fester3k.androcode.interpreter.device.helpers.camera.PhotoActivity;
import android.app.Activity;
import android.content.Intent;

public class CameraAction extends BaseDeviceAction {
	public CameraAction(Activity activity, Properties params) {
		super(activity, params);

	}

	@Override
	public ActionResult run() {
		Intent intent = new Intent(activity, PhotoActivity.class);
		activity.startActivity(intent);
		return ActionResult.FAILED;
	}

}
