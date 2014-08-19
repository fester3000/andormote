package pl.fester3k.androcode.interpreter.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.androcode.datatypes.Device;
import pl.fester3k.androcode.interpreter.device.action.Action;
import pl.fester3k.androcode.interpreter.device.action.ActionFactory;
import pl.fester3k.androcode.interpreter.device.action.BaseSensorAction;
import android.content.Context;

public enum DeviceManager {
	INSTANCE;
	private final Logger log = LoggerFactory.getLogger(DeviceManager.class);
	private ActionFactory actionFactory;
	
	public void init(Context context) {
		actionFactory = new ActionFactory(context);
	}
	
	public Object execute(Device device) {
		Object result = null;
		Action action = actionFactory.createAction(device.getFeature(), device.getParams());
		log.info("Running action");
		action.run();
		if(action instanceof BaseSensorAction) {
			log.info("Processing sensor action");
			result = ((BaseSensorAction)action).getResult();
			log.info("Result: " + result);
		}
		log.info("Action is done");
		return result;
	}
}
