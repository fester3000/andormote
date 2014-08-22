package pl.fester3k.androcode.interpreter.device.action.phone;

import java.util.Properties;

import pl.fester3k.androcode.interpreter.device.action.ActionResult;
import pl.fester3k.androcode.interpreter.device.action.BaseDeviceAction;
import android.content.Context;

public class CameraAction extends BaseDeviceAction {
	public CameraAction(Context context, Properties params) {
		super(context, params);

	}

	@Override
	public ActionResult run() {
//FIXME		Intent intent = new Intent(activity, PhotoActivity.class);
//		activity.startActivity(intent);
		return ActionResult.FAILED;
	}

}
