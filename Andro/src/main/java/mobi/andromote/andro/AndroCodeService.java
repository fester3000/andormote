package mobi.andromote.andro;

import mobi.andromote.andro.logger.ConfigureLog4J;
import mobi.andromote.andro.webservice.WebService;

import org.apache.log4j.Logger;

import pl.fester3k.androcode.interpreter.device.CapabilitiesAnalyzer;
import pl.fester3k.androcode.interpreter.device.DeviceManager;
import pl.fester3k.androcode.interpreter.device.RideController;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class AndroCodeService extends Service {
	private final Logger log = Logger.getLogger(AndroMainActivity.class);
	WebService webService;
	private final IBinder binder = new LocalBinder();
	
	@Override
	public IBinder onBind(Intent intent) {
		ConfigureLog4J.configure();
		init();
		return binder;
	}
	
	public class LocalBinder extends Binder {
		AndroCodeService getService() {
			return AndroCodeService.this;
		}
	}
	
	private void init() {
		ConfigureLog4J.configure();
		RideController.INSTANCE.onCreate(getApplication());  //TODO wada - kolejnosc wywolania...
		CapabilitiesAnalyzer.INSTANCE.init(this);
		//TODO - wyswietlac w odrebnym fragmencie
		log.debug(CapabilitiesAnalyzer.INSTANCE.toString());
		DeviceManager.INSTANCE.init(this);
		webService = new WebService(this);
		webService.start();
	}

	
	@Override
	public boolean onUnbind(Intent intent) {
		webService.destroy();
		RideController.INSTANCE.destroy();
		return super.onUnbind(intent);
	}
}
