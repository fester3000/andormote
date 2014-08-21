package pl.fester3k.androcode.interpreter.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.androcode.interpreter.device.action.ActionResult;
import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.DeviceDefinitions.MobilePlatforms;
import andro_mote.commons.DeviceDefinitions.MotorDrivers;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Engine;
import andro_mote.platform_controller.AndroMoteMobilePlatformController;
import android.app.Application;
import android.util.Log;

public enum RideController {
	INSTANCE;
	private final Logger log = LoggerFactory.getLogger(RideController.class);
	private MotorDrivers motorDriver = MotorDrivers.RNVN2;
	private MobilePlatforms mobilePlatform = MobilePlatforms.ROVER5TwoEngines;
	private AndroMoteMobilePlatformController api = null;
	private Application application;
	
	public void onCreate(Application application) {
		this.application = application;
		this.api = new AndroMoteMobilePlatformController(application);		
		try {
			api.startCommunicationWithDevice(mobilePlatform, motorDriver);
		} catch (MobilePlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startEngineService();
	}
	
	public void destroy() {
		try {
			this.api.stopCommunicationWithDevice();
		} catch (MobilePlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ActionResult execute(Packet packet) {
		ActionResult result;
		try {	
			this.api.sendMessageToDevice(packet);
			result = ActionResult.COMPLETED;
		} catch (MobilePlatformException e) {
			e.printStackTrace();
			result = ActionResult.INTERRUPTED;
		}
		return result;
	}
	

	/**
	 * Start i ustawienia początkowe steronika silników.
	 */
	private void startEngineService() {
		// ustawienia silników w nowym wątku z powodu asynchronicznego wywołania
		// aktywacji usługi
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);

					initEngineService();
				} catch (InterruptedException e) {
					Log.e("HelloIOIO", e.getMessage(), e);
				}
			}
		};
		t.start();
	}

	/**
	 * Wstępna konfiguracja ustawień sterownika silników. Tryb ruchu - krokowy.
	 * Czas trwania jednego kroku - 500 ms. Prędkość tylnego silnika - 0,8.
	 */
	private void initEngineService() {
		// zmiana czasu trwaniajednego węzła
		Packet motionMode = new Packet(Engine.SET_CONTINUOUS_MODE);
		Packet stepDurationPacket = new Packet(Engine.SET_STEP_DURATION);
		stepDurationPacket.setStepDuration(500);
		Packet speedPacket = new Packet(Engine.SET_SPEED);
		speedPacket.setSpeed(0.8);
		try {
			this.api.sendMessageToDevice(motionMode);
			this.api.sendMessageToDevice(stepDurationPacket);
			this.api.sendMessageToDevice(speedPacket);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MobilePlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
