package pl.fester3k.androcode.interpreter.device.action;

import java.util.Properties;

import android.app.Activity;
import android.content.Context;

public abstract class BaseDeviceAction extends BaseAction {
	protected Properties params;
	public BaseDeviceAction(Activity activity, Properties params) {
		super(activity);
		this.params = params;
	}
}
