package pl.fester3k.androcode.interpreter.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.androcode.interpreter.device.action.ActionResult;
import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.DeviceDefinitions.MobilePlatformType;
import andro_mote.commons.DeviceDefinitions.MotorDriverType;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.Motion;
import andro_mote.platform_controller.AndroMoteController;
import android.app.Application;
import android.util.Log;

public enum RideController {
	INSTANCE;
	private final Logger log = LoggerFactory.getLogger(RideController.class);
	private MotorDriverType motorDriver = MotorDriverType.RNVN2;
	private MobilePlatformType mobilePlatform = MobilePlatformType.ROVER5TwoEngines;
	private AndroMoteController api = null;


	public void onCreate(Application application) {
		this.api = new AndroMoteController(application);		
		try {
			api.startCommunicationWithDevice(mobilePlatform, motorDriver);
		} catch (MobilePlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startAndromoteControllerService();
	}

	public void destroy() {
		stopAndromoteControllerService();
	}

	public ActionResult execute(Packet packet) {
		ActionResult result;
		try {	
			api.sendMessageToDevice(packet);
			result = ActionResult.COMPLETED;
		} catch (MobilePlatformException e) {
			e.printStackTrace();
			result = ActionResult.INTERRUPTED;
		}
		return result;
	}
	
	public void stopRide() {
		Packet packet = new Packet(Motion.STOP_REQUEST);
		try {
			api.sendMessageToDevice(packet);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MobilePlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Start i ustawienia początkowe steronika silników.
	 */
	private void startAndromoteControllerService() {
		// ustawienia silników w nowym wątku z powodu asynchronicznego wywołania
		// aktywacji usługi
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);

					initAndromoteControllerService();
				} catch (InterruptedException e) {
					log.error( e.getMessage());
				}
			}
		};
		t.start();
	}

	/**
	 * Stop usługi silnikowej
	 */
	private void stopAndromoteControllerService() {
		try {
			api.stopCommunicationWithDevice();
		} catch (MobilePlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Wstępna konfiguracja ustawień sterownika silników. 
	 */
	private void initAndromoteControllerService() {
		// zmiana czasu trwaniajednego węzła
		Packet motionMode = new Packet(Engine.SET_CONTINUOUS_MODE);
		Packet stepDurationPacket = new Packet(Engine.SET_STEP_DURATION);
		stepDurationPacket.setStepDuration(500);
		Packet speedPacket = new Packet(Engine.SET_SPEED);
		speedPacket.setSpeed(0.8);
		try {
			api.sendMessageToDevice(motionMode);
			api.sendMessageToDevice(stepDurationPacket);
			api.sendMessageToDevice(speedPacket);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MobilePlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isRideAvailable() {
		return api.checkIfConnectionIsActive();
	}
}
