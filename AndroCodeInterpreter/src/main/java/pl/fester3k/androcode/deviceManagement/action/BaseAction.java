package pl.fester3k.androcode.deviceManagement.action;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;

public abstract class BaseAction {
	protected Logger logger = LoggerFactory.getLogger(BaseDeviceAction.class);
	protected Context context;
	protected Properties params = new Properties();


	public Properties getParams() {
		return params;
	}

	public void setParams(Properties params) {
		this.params = params;
	}
}
