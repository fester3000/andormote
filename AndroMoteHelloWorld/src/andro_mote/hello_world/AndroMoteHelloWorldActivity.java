package andro_mote.hello_world;

import java.io.Serializable;

import org.apache.log4j.BasicConfigurator;

import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.IntentsFieldsIdentifiers;
import andro_mote.commons.IntentsIdentifiers;
import andro_mote.commons.MotionModes;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.Engine;
import andro_mote.devices.RNVN2Model;

import andro_mote.ioio_service.EnginesControllerService;
import andro_mote.ioio_service.RoverEngineControllerLooper;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.platform_controller.AndroMoteMobilePlatformController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Aplikaja HelloWorld dla AndroMote.
 * 
 * @author Maciej Gzik
 * 
 */
public class AndroMoteHelloWorldActivity extends Activity implements OnClickListener {

	public static final String TAG = AndroMoteHelloWorldActivity.class.toString();
	private Button mLFButton = null;
	private Button mFButton = null;
	private Button mRFButton = null;
	private Button mLButton = null;
	private Button mStopButton = null;
	private Button mRButton = null;
	private Button mLBButton = null;
	private Button mBButton = null;
	private Button mRBButton = null;

	private Button startServiceButton = null;
	private Button stopServiceButton = null;
	private Button stepperButton = null;
	private Button continousButton = null;
	private Button left90Button = null;
	private Button right90Button = null;

	private AndroMoteMobilePlatformController api = null;

	AndroMoteLogger logger = new AndroMoteLogger(AndroMoteHelloWorldActivity.class);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.initComponents();
		BasicConfigurator.configure();

		this.api = new AndroMoteMobilePlatformController(this.getApplication());
	}

	@Override
	protected void onDestroy() {
		Log.d("test", "onDestroy");
		stopEngineService();
		super.onDestroy();
	}

	/**
	 * Inicjalizacja komponentów układu graficznego
	 */
	private void initComponents() {
		this.mLFButton = (Button) findViewById(R.id.lfButton);
		this.mFButton = (Button) findViewById(R.id.fButton);
		this.mRFButton = (Button) findViewById(R.id.rfButton);
		this.mLButton = (Button) findViewById(R.id.lButton);
		this.mStopButton = (Button) findViewById(R.id.stopButton);
		this.mRButton = (Button) findViewById(R.id.rButton);
		this.mLBButton = (Button) findViewById(R.id.lbButton);
		this.mBButton = (Button) findViewById(R.id.bButton);
		this.mRBButton = (Button) findViewById(R.id.rbButton);

		this.startServiceButton = (Button) findViewById(R.id.startServiceButton);
		this.stopServiceButton = (Button) findViewById(R.id.stopServiceButton);
		this.left90Button = (Button) findViewById(R.id.left90Button);
		this.right90Button = (Button) findViewById(R.id.right90Button);
		this.stepperButton = (Button) findViewById(R.id.setStepperModeButton);
		this.continousButton = (Button) findViewById(R.id.setContinuousModeButton);

		this.mLFButton.setOnClickListener(this);
		this.mFButton.setOnClickListener(this);
		this.mRFButton.setOnClickListener(this);
		this.mLButton.setOnClickListener(this);
		this.mStopButton.setOnClickListener(this);
		this.mRButton.setOnClickListener(this);
		this.mLBButton.setOnClickListener(this);
		this.mBButton.setOnClickListener(this);
		this.mRBButton.setOnClickListener(this);
		this.startServiceButton.setOnClickListener(this);
		this.stopServiceButton.setOnClickListener(this);
		this.left90Button.setOnClickListener(this);
		this.right90Button.setOnClickListener(this);
		this.stepperButton.setOnClickListener(this);
		this.continousButton.setOnClickListener(this);

	}

	/**
	 * Start i ustawienia początkowe steronika silników.
	 */
	private void startEngineService() {
		// start serwisu silników
		Intent startEngineServiceIntent = new Intent(IntentsIdentifiers.ACTION_ENGINES_CONTROLLER);
		Packet pack = new Packet(PacketType.Connection.MODEL_NAME);
		pack.setDeviceName(RNVN2Model.class.getName());
		startEngineServiceIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) pack);
		startService(startEngineServiceIntent);

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
		this.sendPacketToEngineService(new Packet(Engine.SET_STEPPER_MODE));

		// zmiana czasu trwaniajednego węzła
		Packet stepDurationPacket = new Packet(Engine.SET_STEP_DURATION);
		stepDurationPacket.setStepDuration(500);
		this.sendPacketToEngineService(stepDurationPacket);

		Packet speedPacket = new Packet(Engine.SET_SPEED);
		speedPacket.setSpeed(0.8);
		this.sendPacketToEngineService(speedPacket);
	}

	/**
	 * Zatrzymanie serwisu kontroli silników. Po zakończeniu działania aplikacji
	 * konieczne jest zatrzymanie sterownika silników - najlepiej w metodzie
	 * onDestroy aktywności. Wynika to z konieczności zamknięcia połączenia z
	 * mikrokontrolerem przed kolejną próbą połaczenia.
	 */
	private void stopEngineService() {
		Intent closeService = new Intent(IntentsIdentifiers.ACTION_ENGINES_CONTROLLER);
		stopService(closeService);
	}

	/**
	 * Wysłanie pakietu do serwisu silników.
	 * 
	 * @param packet
	 *            Wysyłany pakiet {@link Packet}
	 */
	public void sendPacketToEngineService(Packet packet) {
		Intent broadcastEngine = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		broadcastEngine.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) packet);
		sendBroadcast(broadcastEngine);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.lfButton) {
			try {
				this.api.moveLeftForward();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.fButton) {
			try {
				this.api.moveForward();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.rfButton) {
			try {
				this.api.moveRightForward();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.lButton) {
			try {
				this.api.moveLeft();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.stopButton) {
			try {
				this.api.stopMobilePlatform();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.rButton) {
			try {
				this.api.moveRight();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.lbButton) {
			try {
				this.api.moveLeftBackward();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.bButton) {
			try {
				this.api.moveBackward();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.rbButton) {
			try {
				this.api.moveRight();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.left90Button) {
			try {
				this.api.turn90LeftDegrees();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.right90Button) {
			try {
				this.api.turn90RightDegrees();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.startServiceButton) {
			try {
				this.api.startCommunicationWithDevice("RNVN2Model");
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.stopServiceButton) {
			try {
				this.api.stopCommunicationWithDevice();
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.setContinuousModeButton) {
			try {
				this.api.setMotionMode(MotionModes.MOTION_MODE_CONTINUOUS);
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.setStepperModeButton) {
			try {
				this.api.setMotionMode(MotionModes.MOTION_MODE_STEPPER);
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		}
	}
}
