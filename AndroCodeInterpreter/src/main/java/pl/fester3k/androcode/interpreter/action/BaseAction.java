package pl.fester3k.androcode.interpreter.action;

import android.content.Context;

public abstract class BaseAction implements Action {
	protected Context context;

	public BaseAction(Context context) {
		this.context = context;
	}
}
