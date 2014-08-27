package pl.fester3k.androcode.deviceManagement.action.phone;

import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.content.Context;

public class DummyAction extends BaseDeviceAction {
	public DummyAction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
//FIXME		Toast.makeText(context, "DummyAction in progress", Toast.LENGTH_SHORT).show();
		return ActionResult.COMPLETED;
	}

}
