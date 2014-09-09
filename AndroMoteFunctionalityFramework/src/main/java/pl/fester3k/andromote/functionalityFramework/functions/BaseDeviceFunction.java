package pl.fester3k.andromote.functionalityFramework.functions;

import android.content.Context;

public abstract class BaseDeviceFunction extends BaseFunction {	
	public BaseDeviceFunction(Context context) {
		this.context = context;
	}
	
	@Override
	public void cleanup() {
		logger.info("Cleanning up");
	}	
}
