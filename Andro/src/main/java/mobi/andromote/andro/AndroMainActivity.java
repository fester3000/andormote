package mobi.andromote.andro;

import org.apache.log4j.Logger;

import mobi.andromote.andro.logger.ConfigureLog4J;
import mobi.andromote.andro.webservice.WebService;
import pl.fester3k.androcode.interpreter.device.CapabilitiesAnalyzer;
import pl.fester3k.androcode.interpreter.device.DeviceManager;
import pl.fester3k.androcode.interpreter.device.RideController;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

public class AndroMainActivity extends Activity {
	private final Logger log = Logger.getLogger(AndroMainActivity.class);
	static final int REQUEST_TAKE_PHOTO = 1;
	WebService webService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ConfigureLog4J.configure();
		CapabilitiesAnalyzer.INSTANCE.init(this);
		log.debug(CapabilitiesAnalyzer.INSTANCE.toString());
		DeviceManager.INSTANCE.init(this);
		webService = new WebService(this);
		webService.start();
		RideController.INSTANCE.onCreate(getApplication());
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		webService.destroy();
		RideController.INSTANCE.destroy();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	    }
	}
}
