package pl.fester3k.andromote.am2.functions.phone;

import pl.fester3k.andromote.functionalityFramework.datatypes.ActionResult;
import pl.fester3k.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;

public class DummyFunction extends BaseDeviceFunction {
	public DummyFunction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
//FIXME		Toast.makeText(context, "DummyAction in progress", Toast.LENGTH_SHORT).show();
		return ActionResult.COMPLETED;
	}

	@Override
	public void putParam(String propertyName, String value) {
		throw new UnsupportedOperationException();
	}
}
