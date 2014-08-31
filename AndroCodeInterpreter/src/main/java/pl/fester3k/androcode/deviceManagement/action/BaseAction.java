package pl.fester3k.androcode.deviceManagement.action;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.androcode.datatypes.ActionParams.ActionParam;
import android.content.Context;

public abstract class BaseAction {
	protected Logger logger = LoggerFactory.getLogger(BaseDeviceAction.class);
	protected Context context;
	protected Map<ActionParam, String> params = new HashMap<ActionParam, String>();
}
