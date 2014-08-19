package pl.fester3k.androcode.interpreter.device.action.phone;

import java.util.Properties;

import pl.fester3k.androcode.interpreter.device.action.ActionResult;
import pl.fester3k.androcode.interpreter.device.action.BaseDeviceAction;
import android.content.Context;
import android.widget.Toast;

public class DummyAction extends BaseDeviceAction {
	public DummyAction(Context context, Properties params) {
		super(context, params);
	}

	@Override
	public ActionResult run() {
		Toast.makeText(context, "DummyAction in progress", Toast.LENGTH_SHORT).show();
		return ActionResult.COMPLETED;
	}

}
