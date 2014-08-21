package pl.fester3k.androcode.interpreter.device.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.content.Context;

public abstract class BaseAction implements Action {
	protected Activity activity;
	protected Logger logger;

	public BaseAction(Activity context) {
		this.activity = context;
		this.logger = LoggerFactory.getLogger(BaseAction.class);
	}
}
