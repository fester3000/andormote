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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + ((logger == null) ? 0 : logger.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseAction other = (BaseAction) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (logger == null) {
			if (other.logger != null)
				return false;
		} else if (!logger.equals(other.logger))
			return false;
		return true;
	}
	
	
}
