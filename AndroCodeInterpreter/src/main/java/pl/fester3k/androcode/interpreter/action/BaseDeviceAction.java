package pl.fester3k.androcode.interpreter.action;

import java.util.Properties;

import android.content.Context;

public abstract class BaseDeviceAction extends BaseAction {
	protected Properties params;
	public BaseDeviceAction(Context context, Properties params) {
		super(context);
		this.params = params;
	}

}
