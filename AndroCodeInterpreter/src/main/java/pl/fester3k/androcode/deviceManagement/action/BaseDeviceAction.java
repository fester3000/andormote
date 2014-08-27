package pl.fester3k.androcode.deviceManagement.action;

import android.content.Context;

public abstract class BaseDeviceAction extends BaseAction implements Action {
	
	public BaseDeviceAction(Context context) {
		this.context = context;
	}
	
	@Override
	public void cleanup() {
		logger.info("Cleanning up");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + ((params == null) ? 0 : params.hashCode());
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
		BaseDeviceAction other = (BaseDeviceAction) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		return true;
	}
	
	
}
