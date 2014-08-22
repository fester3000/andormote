package pl.fester3k.androcode.interpreter.device.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.content.Context;

public abstract class BaseAction implements Action {
	protected Context context;
	protected Logger logger;

	public BaseAction(Context context) {
		this.context = context;
		this.logger = LoggerFactory.getLogger(BaseAction.class);
	}
}
