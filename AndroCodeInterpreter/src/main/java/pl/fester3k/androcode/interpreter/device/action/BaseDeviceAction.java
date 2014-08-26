package pl.fester3k.androcode.interpreter.device.action;

import java.util.Properties;

import android.content.Context;

public abstract class BaseDeviceAction extends BaseAction {
	protected Properties params;
	public BaseDeviceAction(Context context, Properties params) {
		super(context);
		this.params = params;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseDeviceAction other = (BaseDeviceAction) obj;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		return true;
	}
	
	
}
