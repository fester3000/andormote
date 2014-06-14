package andro_mote.hello_world;

import java.io.Serializable;

import org.apache.log4j.BasicConfigurator;

import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.DeviceDefinitions.MobilePlatforms;
import andro_mote.commons.DeviceDefinitions.MotorDrivers;
import andro_mote.commons.IntentsFieldsIdentifiers;
import andro_mote.commons.IntentsIdentifiers;
import andro_mote.commons.MotionModes;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.Motion;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.platform_controller.AndroMoteMobilePlatformController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Aplikaja HelloWorld dla AndroMote.
 * 
 * @author Maciej Gzik
 * 
 */
public class AndroMoteHelloWorldActivity extends Activity implements OnClickListener {

	public static final String TAG = AndroMoteHelloWorldActivity.class.toString();

	private MotorDrivers motorDriver = MotorDrivers.RNVN2;
	private MobilePlatforms mobilePlatform = MobilePlatforms.ROVER5TwoEngines;

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

	private AndroMoteMobilePlatformController api = null;

	AndroMoteLogger logger = new AndroMoteLogger(AndroMoteHelloWorldActivity.class);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.initComponents();
		BasicConfigurator.configure();
		this.api = new AndroMoteMobilePlatformController(this.getApplication());
		startEngineService();
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
		pack.setPlatformName(mobilePlatform);
		pack.setDriverName(motorDriver);
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

	@Override
	public void onClick(View v) {
		Packet packet = null;
		switch(v.getId()) {
		case R.id.lfButton:
			packet = new Packet(Motion.MOVE_LEFT_FORWARD_REQUEST);
			break;
		case R.id.fButton:
			packet = new Packet(Motion.MOVE_FORWARD_REQUEST);
			break;
		case R.id.rfButton:
			packet = new Packet(Motion.MOVE_RIGHT_FORWARD_REQUEST);
			break;
		case R.id.lButton:
			packet = new Packet(Motion.MOVE_LEFT_BACKWARD_REQUEST);
			break;
		case R.id.stopButton:
			packet = new Packet(Motion.STOP_REQUEST);
			break;
		case R.id.rButton:
			packet = new Packet(Motion.MOVE_RIGHT_REQUEST);
			break;
		case R.id.lbButton:
			packet = new Packet(Motion.MOVE_LEFT_BACKWARD_REQUEST);
			break;
		case R.id.bButton:
			packet = new Packet(Motion.MOVE_BACKWARD_REQUEST);
			break;
		case R.id.rbButton:
			packet = new Packet(Motion.MOVE_RIGHT_BACKWARD_REQUEST);
			break;
		case R.id.startServiceButton:
			try {
				this.api.startCommunicationWithDevice(mobilePlatform, motorDriver);
			} catch (MobilePlatformException e1) {
				e1.printStackTrace();
			}
			break;
		case R.id.stopServiceButton:
			try {
				this.api.stopCommunicationWithDevice();
			} catch (MobilePlatformException e1) {
				e1.printStackTrace();
			}
			break;
		case R.id.setContinuousModeButton:
			try {
				this.api.setMotionMode(MotionModes.MOTION_MODE_CONTINUOUS);
			} catch (MobilePlatformException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case R.id.setStepperModeButton:
			try {
				this.api.setMotionMode(MotionModes.MOTION_MODE_STEPPER);
			} catch (MobilePlatformException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		} 
		if(packet!= null) {
			try {	
				packet.setSpeed(0.7);
				this.api.sendMessageToDevice(packet);
			} catch (MobilePlatformException e) {
				e.printStackTrace();
			}
		}
	}
}
