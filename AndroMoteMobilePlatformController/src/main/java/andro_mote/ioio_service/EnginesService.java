package andro_mote.ioio_service;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOService;

import java.io.Serializable;
import java.util.LinkedList;

import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.DeviceDefinitions;
import andro_mote.commons.IntentsFieldsIdentifiers;
import andro_mote.commons.IntentsIdentifiers;
import andro_mote.commons.MotionModes;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.IPacketType;
import andro_mote.commons.PacketType.Motion;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.platform_controller.AndroMoteController;
import andro_mote.stepper.Step;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;

public class EnginesService extends IOIOService {
	private static final String TAG = EnginesService.class.getName();
	private final IBinder binder = new LocalBinder();
	
	/**
	 * Maksymalny czas trwania kroku w trybie MOTION_MODE_STEPPER
	 */
	private static final int MAX_STEP_DURATION = 2000;

	/**
	 * Minimalna prędkość silników. Zabezpieczenie przed małą mocą.
	 */
	private static final double MIN_SPEED = 0.4;

	private AndroMoteLogger log = new AndroMoteLogger(EnginesService.class);
	EngineControllerLooper looper = null;

	//	private Compass compass = null;

	/**
	 * Aktualna prędkość silnika.
	 */
	private static double currentSpeed = 0.6;

	/**
	 * Czas oczekiwania pomiędzy kolejnymi krokami.
	 */
	private static long pauseTimeBetweenSteps = 1000;

	public static long stepDuration = 600;

	private LinkedList<Step> stepsQueue;

	/**
	 * Nazwa aktualnie obsługiwanego modelu.
	 */
	private DeviceDefinitions.MobilePlatformType platformName = null;

	/**
	 * Nazwa aktualnie obsługiwanego modelu.
	 */
	private DeviceDefinitions.MotorDriverType driverName = null;

	/**
	 * Tryb ruchu pojazdu.
	 * 
	 * @see EnginesService.MOVING_MODE_CONTINUOUS
	 * @see EnginesService.MOVING_MODE_STEPPER
	 */
	private MotionModes motionMode = MotionModes.MOTION_MODE_CONTINUOUS;

	//	/**
	//	 * Flaga informująca o tym czy wykonywana jest operacja np. skrętu o
	//	 * określony kąt, zawracania. Gdy flaga jest aktywna komendy sterujące
	//	 * odbierane przez serwis są ignorowane do momentu zakończenia wykoywanaego
	//	 * zadania - na każde zapytanie jest wysyłana intencja zwrotna.
	//	 */
	public static boolean isOperationExecuted = false;

	/**
	 * Flaga określająca czy po wykonanym kroku następuje wysłanie paketu
	 * informującego o jego wykonaniu. Pakiet taki może być wysłany tylko w
	 * trybie krokowym. Wartośc flagi może być zmieniona poprzez pakiet
	 */
	private boolean sendStepExecutedPacket = true;

	/**
	 * Binder pozwalający na przypisanie usługi EnginesControllerService do innej usługi/aktywności
	 * wewnątrz aplikacji. Pozwala na bezpośrednie wywoływanie metod publicznych usługi
	 * @author Sebastian
	 *
	 */
	public class LocalBinder extends Binder {
		public EnginesService getService() {
			return EnginesService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		log.debug(TAG, "engineService; onStart(); startId=" + startId);
		trySetupDevicesWithExtrasFrom(intent); 
		super.onStart(intent, startId);
		initStepsList();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		log.debug(TAG, "engineService; onStartCommand(); startId=" + startId);
		AndroMoteLogger.ConfigureLogger("AndroMoteClient.log");
		log.debug(TAG, "onStartCommand engine service");
		trySetupDevicesWithExtrasFrom(intent); 
		//		this.isOperationExecuted = false;
		initStepsList();
		super.onStartCommand(intent, flags, startId);

		return startId;
	}
	
	@Override
	public void onDestroy() {
		//		this.compass.unregisterListeners();
		super.onDestroy();
	}

	/**
	 * Receiver wiadomości dla sterownika silnikami.
	 * 
	 * @author Maciej Gzik
	 * 
	 */
	public void interpretPacket(Packet inputPacket) {
		log.debug(TAG, "engine service broadcast received: " + inputPacket.getPacketType());

		// odrzucenie pakietów Engine i Motion w przypadku wykonywania
		// zadania
		boolean isMotionOrEnginePacket = inputPacket.getPacketType() instanceof Motion || inputPacket.getPacketType() instanceof Engine;
		if (motionMode.equals(MotionModes.MOTION_MODE_CONTINUOUS) && isOperationExecuted && isMotionOrEnginePacket) {
			log.debug(TAG, "Engine Service: incoming packet instanceof: " + inputPacket.getPacketType()
					+ " rejected - operationIsBeingExecuted");
			Packet refusePacket = new Packet(PacketType.Engine.MOTION_COMMAND_REFUSE_OTHER_ACTION_EXECUTED);
			refusePacket.setPacket(inputPacket);
			sendPacketToController(refusePacket, IntentsIdentifiers.ACTION_MESSAGE_TO_CONTROLLER);
			return;
		}

		// zmiany ustawień dotyczących ruchu
		// pakiety ruchu
		if (inputPacket.getPacketType() instanceof PacketType.Motion) {
			if (motionMode.equals(MotionModes.MOTION_MODE_CONTINUOUS)) {
				interpretMotionPacketContinuous(inputPacket);
			} else if (motionMode.equals(MotionModes.MOTION_MODE_STEPPER)) {
				interpretMotionPacketStepper(inputPacket);
			}
		} else if (inputPacket.getPacketType() == PacketType.Engine.SET_STEPPER_MODE) {
			if (motionMode.equals(MotionModes.MOTION_MODE_CONTINUOUS)) {
				log.debug(TAG, "zmiana trybu ruchu na krokowy");
				setMotionMode(MotionModes.MOTION_MODE_STEPPER);
				// zerowanie kolejki ruchów
				if (isStepsQueueNotEmpty()) {
					stepsQueue.clear();
				}
			}
		} else if (inputPacket.getPacketType() == PacketType.Engine.SET_CONTINUOUS_MODE) {
			if (motionMode.equals(MotionModes.MOTION_MODE_STEPPER)) {
				log.debug(TAG, "zmiana trybu ruchu na ciągły");
				setMotionMode(MotionModes.MOTION_MODE_CONTINUOUS);
				if (isStepsQueueNotEmpty()) {
					stepsQueue = null;
				}
			}
		} else if (inputPacket.getPacketType() == PacketType.Engine.SET_STEP_DURATION) {
			log.debug(TAG,
					"EnginesControllerService: zmiana czasu trwania kroku na : " + inputPacket.getStepDuration());
			setStepDuration(inputPacket.getStepDuration());
		} else if (inputPacket.getPacketType() == PacketType.Engine.SET_SPEED) {
			log.debug(TAG, "setting speed to value: " + inputPacket.getSpeed());
			setSpeed(inputPacket.getSpeed());
		} else if (inputPacket.getPacketType() == PacketType.Connection.NODE_CONNECTION_STATUS_REQUEST) {
			log.debug(TAG, "engine service: sending motion mode: " + motionMode);
			Packet pack = null;
			if (motionMode.equals(MotionModes.MOTION_MODE_CONTINUOUS)) {
				pack = new Packet(PacketType.Engine.CONTINUOUS_MODE_RESPONSE);
			} else if (motionMode.equals(MotionModes.MOTION_MODE_STEPPER)) {
				pack = new Packet(PacketType.Engine.STEPPER_MODE_RESPONSE);
			}
			sendPacketToController(pack, IntentsIdentifiers.ACTION_MESSAGE_TO_CONTROLLER);
		} else if (inputPacket.getPacketType() == PacketType.Engine.SET_STEP_EXECUTION_CONFIRMATION) {
			boolean newState = false;
			if (inputPacket.getNewState() == 0) {
				newState = false;
			} else {
				newState = true;
			}
			setSendStepExecutedPacket(newState);
			log.debug(TAG, "set step execution confirmation: " + newState);
		}
	}

	private boolean isStepsQueueNotEmpty() {
		return stepsQueue != null && stepsQueue.size() > 0;
	}
	
	private void trySetupDevicesWithExtrasFrom(Intent intent) {
		try {
			Packet pack = (Packet) intent.getParcelableExtra(IntentsFieldsIdentifiers.EXTRA_PACKET);
			if (pack != null && pack.getPlatformName() != null && pack.getDriverName() != null) {
				log.debug(TAG, "Engines Service: setting model name: " + pack.getPlatformName());
				this.platformName = pack.getPlatformName();
				this.driverName = pack.getDriverName();
				//				if (this.compass == null) {
				//					this.compass = new Compass(this.getApplicationContext());
				//					this.compass.unregisterListeners();
				//				}
			} else {
				log.debug(TAG, "Engines Service: input packet has no device info;");
				//TODO zabezpieczyć przed sytuacją niedookreśloną			
			}
		} catch (NullPointerException e) {
			log.error(TAG, e);
		}
	}

	/**
	 * Czyszczenie kolejki kroków.
	 */
	public void clearStepsQueue() {
		if (this.stepsQueue != null && this.stepsQueue.size() != 0) {
			this.stepsQueue = null;
			this.stepsQueue = new LinkedList<Step>();
		}
	}

	/**
	 * Pobranie kolejnego kroku z kolejki.
	 */
	public synchronized Step getNextStep() {
		if (motionMode.equals(MotionModes.MOTION_MODE_STEPPER) && stepsQueue != null
				&& stepsQueue.size() > 0) {
			Step step = stepsQueue.getFirst();
			stepsQueue.removeFirst();
			log.debug(TAG, "EnginesControllerService: pobrano krok z listy kroków:" + step.getStepType());
			return step;
		} else {
			return null;
		}
	}
	
	/**
	 * Broadcast pakietu zawierającego informacje o wykonanym kroku.
	 * 
	 * @param responsePacket
	 *            pakiet z informacjami o wykonanym kroku
	 */
	public void sendStepExecutedBroadcast(PacketType.Motion stepType, long stepDuration, double speed) {
		//FIXME
		if (!this.isSendStepExecutedPacket()) {
			log.debug(TAG, "EngineControllerLooper: sendStepBroadcast: flaga sendStepExecutedPacket ma wartość false - pakiet zwrotny nie zostanie wysłany.");
			return;
		}
		log.debug(TAG, "engineController looper: broadcast informacji o wykonanym kroku: " + stepType);

		Packet responsePacket = new Packet(Engine.STEP_TAKEN_PACKET);
		responsePacket.setStepDirection(stepType);
		responsePacket.setStepDuration(stepDuration);
		responsePacket.setSpeed(speed);

		sendPacketToController(responsePacket, IntentsIdentifiers.ACTION_ENGINE_STEP);
	}

	/**
	 * Broadcast pakietu zawierającego informacje o błędzie w trakcie wykonywania kroku.
	 * 
	 * @param responsePacket
	 *            pakiet z informacjami o wykonanym kroku
	 */
	public void sendStepExecutionErrorBroadcast(Motion takenStep) {
		log.debug(TAG, "engineController looper: broadcast informacji o błędzie w trakcie wykonywania kroku: " + takenStep);
		Packet responsePacket = new Packet(Engine.MOTION_COMMAND_REFUSED_SENSOR_ERROR);
		responsePacket.setStepDirection(takenStep);
		sendPacketToController(responsePacket, IntentsIdentifiers.ACTION_ENGINE_STEP);
	}
	
	public MotionModes getMotionMode() {
		return motionMode;
	}

	public void setMotionMode(MotionModes motionMode) {
		this.motionMode = motionMode;
		Packet pack = null;
		if (motionMode.equals(MotionModes.MOTION_MODE_CONTINUOUS)) {
			pack = new Packet(PacketType.Engine.CONTINUOUS_MODE_RESPONSE);
		} else if (motionMode.equals(MotionModes.MOTION_MODE_STEPPER)) {
			pack = new Packet(PacketType.Engine.STEPPER_MODE_RESPONSE);
		}
		log.debug(TAG, "engineService: set controlMode=" + motionMode + ";with engine packet broadcast");
		sendPacketToController(pack, IntentsIdentifiers.ACTION_MESSAGE_TO_CONTROLLER);
	}

	public long getStepDuration() {
		return stepDuration;
	}

	public void setStepDuration(long stepDuration) {
		if (stepDuration > EnginesService.MAX_STEP_DURATION) {
			log.debug(
					TAG,
					"EnginesControllerService: próba ustawienia większego niż dopuszczalny czasu trwania jednego kroku. Ustawiona wartość: "
							+ EnginesService.MAX_STEP_DURATION);
			this.stepDuration = MAX_STEP_DURATION;
		} else {
			log.debug(TAG, "EnginesControllerService: zmiana czasu trwania kroku. Nowa wartość [ms]: "
					+ stepDuration);
			this.stepDuration = stepDuration;
		}
	}

	public static long getPauseTimeBetweenSteps() {
		return pauseTimeBetweenSteps;
	}

	public static double getSpeed() {
		return currentSpeed;
	}

	public void setSpeed(double speed) {
		if (speed > 1 || speed < MIN_SPEED) {
			log.debug(TAG, "EnginesControllerService: niedopuszczalna nowa wartość prędkości silników: " + speed
					+ ". Zmiana nie została dokonana!.");
			if (currentSpeed < MIN_SPEED) {
				currentSpeed = MIN_SPEED;
			}
		} else {
			log.debug(TAG, "EnginesControllerService: zmiana prędkości silników na: " + speed);
			EnginesService.currentSpeed = speed;
		}
	}

	public boolean isOperationExecuted() {
		return isOperationExecuted;
	}

	public void setOperationExecuted(boolean isOperationExecuted) {
		this.isOperationExecuted = isOperationExecuted;
	}

	//	public Compass getCompass() {
	//		return compass;
	//	}
	//
	//	public void setCompass(Compass compass) {
	//		this.compass = compass;
	//	}

	public boolean isSendStepExecutedPacket() {
		return sendStepExecutedPacket;
	}

	public void setSendStepExecutedPacket(boolean sendStepExecutedPacket) {
		log.debug(TAG, "EnginesControllerService: set sendStepExecutedPacket. New value: " + sendStepExecutedPacket);
		this.sendStepExecutedPacket = sendStepExecutedPacket;
	}

	public static int getMaxStepDuration() {
		return MAX_STEP_DURATION;
	}

	public static double getMinSpeed() {
		return MIN_SPEED;
	}

	public boolean isConnectedToIOIO() {
		boolean result = false;
		if(looper != null) {
			result = looper.isDeviceConnected();
		}
		return result;
	}

	/**
	 * inicjalizacja kolejki kroków
	 */
	private void initStepsList() {
		if (this.stepsQueue == null)
			this.stepsQueue = new LinkedList<Step>();
	}

	private void interpretMotionPacketContinuous(Packet inputPacket) {
		//TODO składować gdzie indziej
		if (inputPacket.getSpeed() >= EnginesService.MIN_SPEED) {
			this.setSpeed(inputPacket.getSpeed());
		}
		looper.executePacket(inputPacket);
		log.debug(TAG, "engine service received: " + inputPacket.getPacketType());

	}

	private void interpretMotionPacketStepper(Packet inputPacket) {
		IPacketType packetType = inputPacket.getPacketType();
		log.debug(TAG, "EnginesControllerService; add packet to stepsList: " + packetType);
		if (looper != null) {
			if (packetType == PacketType.Motion.MOVE_LEFT_FORWARD_REQUEST) {
				stepsQueue.addLast(new Step(packetType));
			} else if (packetType == PacketType.Motion.MOVE_FORWARD_REQUEST) {
				stepsQueue.addLast(new Step(packetType));
			} else if (packetType == PacketType.Motion.MOVE_RIGHT_FORWARD_REQUEST) {
				stepsQueue.addLast(new Step(packetType));
			} else if (packetType == PacketType.Motion.MOVE_LEFT_REQUEST) {
				stepsQueue.addLast(new Step(packetType));
			} else if (packetType == PacketType.Motion.STOP_REQUEST) {
				log.debug(TAG, "PacketType.STOP w trybie steppera nie jest dodawany do kolejki!!!");
			} else if (packetType == PacketType.Motion.MOVE_RIGHT_REQUEST) {
				stepsQueue.addLast(new Step(packetType));
			} else if (packetType == PacketType.Motion.MOVE_LEFT_BACKWARD_REQUEST) {
				stepsQueue.addLast(new Step(packetType));
			} else if (packetType == PacketType.Motion.MOVE_BACKWARD_REQUEST) {
				stepsQueue.addLast(new Step(packetType));
			} else if (packetType == PacketType.Motion.MOVE_RIGHT_BACKWARD_REQUEST) {
				stepsQueue.addLast(new Step(packetType));
			} else if (packetType == PacketType.Motion.MOVE_LEFT_90_DEGREES_REQUEST) {
				stepsQueue.addLast(new Step(packetType));
			} else if (packetType == PacketType.Motion.MOVE_RIGHT_90_DEGREES_REQUEST) {
				stepsQueue.addLast(new Step(packetType));
			} else if (packetType == PacketType.Motion.MOVE_LEFT_DEGREES_REQUEST) {
				Step step = new Step(packetType);
				step.setDegrees(inputPacket.getBearing());
				stepsQueue.addLast(step);
			} else if (packetType == PacketType.Motion.MOVE_RIGHT_DEGREES_REQUEST) {
				Step step = new Step(packetType);
				step.setDegrees(inputPacket.getBearing());
				stepsQueue.addLast(step);
			}
		}

	}
	
	/**
	 * Funkcja wywoływana w przypadku błędu inicjalizacji, błędu działania
	 * serwisu silników. kończy działanie serwisu i wysyła informację w pakiecie
	 * typu
	 */
	private void stopEngineServiceOnError() {
		Packet stopServicePacket = new Packet(Engine.ENGINE_SERVICE_STOP_ERROR);
		this.sendPacketToController(stopServicePacket, IntentsIdentifiers.ACTION_MESSAGE_TO_CONTROLLER);
		this.stopSelf();
	}

	
	/**
	 * Pozwala na przesłanie do {@link AndroMoteController} pakietu zawierającego wiadomość
	 * @param pack
	 */
	private void sendPacketToController(Packet pack, String intentAction) {
		Intent intentBroadcast = new Intent(intentAction);
		intentBroadcast.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Parcelable) pack);
		LocalBroadcastManager.getInstance(this).sendBroadcast(intentBroadcast);
	}

	@Override
	protected IOIOLooper createIOIOLooper() {
		log.debug(TAG, "EnginesControllerService: creating IOIOLooper");
		try {
			looper = new EngineControllerLooper(this, platformName, driverName);
			log.debug(TAG, "looper created...");
			log.debug(TAG, "setting looper in service...");
		} catch (ConnectionLostException e) {
			log.error(TAG, e);
		} catch (UnknownDeviceException e) {
			log.error(TAG, e);
			this.stopEngineServiceOnError();
		} catch (InterruptedException e) {
			log.error(TAG, e);
		}
		return looper;
	}

	
}
