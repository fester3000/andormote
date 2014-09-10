package mobi.andromote.andro;


import mobi.andromote.andro.logger.ConfigureLog4J;
import mobi.andromote.andro.webservice.WebService;

import org.apache.log4j.Logger;

import pl.fester3k.andromote.am2.functions.AndroMote2CapabilitiesAnalyzer;
import pl.fester3k.andromote.am2.functions.AndroMote2FunctionFactory;
import pl.fester3k.andromote.functionalityFramework.CapabilitiesAnalyzer;
import pl.fester3k.andromote.functionalityFramework.FunctionManager;
import pl.fester3k.andromote.functionalityFramework.datatypes.ServiceWithHandler;
import pl.fester3k.andromote.functionalityFramework.functions.FunctionFactory;
import andro_mote.commons.DeviceDefinitions.MobilePlatformType;
import andro_mote.commons.DeviceDefinitions.MotorDriverType;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

public class AndroCodeService extends Service implements ServiceWithHandler {
	private final Logger log = Logger.getLogger(AndroMainActivity.class);
	WebService webService;
	private final IBinder binder = new LocalBinder();
	private final Handler handler = new Handler();
	
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
	public class LocalBinder extends Binder {
		AndroCodeService getService() {
			return AndroCodeService.this;
		}
	}
	
	private void init() {
		ConfigureLog4J.configure();
		MotorDriverType motorDriver = MotorDriverType.RNVN2;
		MobilePlatformType mobilePlatform = MobilePlatformType.ROVER5TwoEngines;
		FunctionFactory functionFactory = new AndroMote2FunctionFactory(this, getHandler());
		CapabilitiesAnalyzer capabilitiesAnalyzer = new AndroMote2CapabilitiesAnalyzer(this); 
		FunctionManager.INSTANCE.init(getApplication(), capabilitiesAnalyzer, functionFactory, mobilePlatform, motorDriver);
		
		webService = new WebService(this);
		webService.start();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		FunctionManager.INSTANCE.onDestroy();
		webService.destroy();
		super.onDestroy();
	}


	public Handler getHandler() {
		return handler;
	}
	
	
}
