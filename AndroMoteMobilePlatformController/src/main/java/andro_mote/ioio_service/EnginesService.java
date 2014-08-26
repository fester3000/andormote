package andro_mote.ioio_service;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOService;

import java.util.LinkedList;

import andro_mote.api.LocalBroadcastDispatcher;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.DeviceDefinitions.MobilePlatformType;
import andro_mote.commons.DeviceDefinitions.MotorDriverType;
import andro_mote.commons.IntentsFieldsIdentifiers;
import andro_mote.commons.IntentsIdentifiers;
import andro_mote.commons.MotionMode;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.IPacketType;
import andro_mote.commons.PacketType.Motion;
import andro_mote.devices.Vehicle;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class EnginesService extends IOIOService {
	private AndroMoteLogger log = new AndroMoteLogger(EnginesService.class);
	private static final String TAG = EnginesService.class.getName();
	private LocalBroadcastDispatcher localBroadcastDispatcher;
	private final IBinder binder = new LocalBinder();

	/**
	 * Pojazd Andromote
	 */
	private Vehicle vehicle;

	EngineControllerLooper looper = null;
	//	private Compass compass = null;

	private LinkedList<Step> stepsQueue;

	/**
	 * Flaga informująca o tym czy wykonywana jest operacja np. skrętu o
	 * określony kąt, zawracania. Gdy flaga jest aktywna komendy sterujące
	 * odbierane przez serwis są ignorowane do momentu zakończenia wykoywanaego
	 * zadania - na każde zapytanie jest wysyłana intencja zwrotna.
	 */
	public static boolean isOperationExecuted = false;

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
		log.error(TAG, "onStart(); is depreciated!");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		AndroMoteLogger.ConfigureLogger("AndroMoteClient.log");
		log.debug(TAG, "engineService; onStartCommand(); startId=" + startId);
		localBroadcastDispatcher = LocalBroadcastDispatcher.INSTANCE;
		localBroadcastDispatcher.init(getApplicationContext());
		if(intent != null ) {
			trySetupVehicleWithExtrasFrom(intent);
		} else {
			try {
			intent.getAction();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		initStepsQueue();
		super.onStartCommand(intent, flags, startId);

		return startId;
	}

	@Override
	public void onDestroy() {
		//		this.compass.unregisterListeners();
		super.onDestroy();
	}

	@Override
	protected IOIOLooper createIOIOLooper() {
		log.debug(TAG, "EnginesControllerService: creating IOIOLooper");
		try {
			looper = new EngineControllerLooper(this, vehicle);
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

	public boolean isConnectedToIOIO() {
		boolean result = false;
		if(looper != null) {
			result = looper.isDeviceConnected();
		}
		return result;
	}

	/**
	 * Receiver wiadomości dla sterownika silnikami.
	 * 
	 * @author Maciej Gzik
	 * @author Sebastian
	 */
	public void interpretPacket(Packet inputPacket) {
		log.debug(TAG, "engine service broadcast received: " + inputPacket.getPacketType());

		// odrzucenie pakietów Engine i Motion w przypadku wykonywania
		// zadania
		boolean isMotionOrEnginePacket = inputPacket.getPacketType() instanceof Motion || inputPacket.getPacketType() instanceof Engine;
		MotionMode motionMode = vehicle.getSettings().getMotionMode();
		if (motionMode.equals(MotionMode.MOTION_MODE_CONTINUOUS) && isOperationExecuted && isMotionOrEnginePacket) {
			log.debug(TAG, "Engine Service: incoming packet instanceof: " + inputPacket.getPacketType()
					+ " rejected - operationIsBeingExecuted");
			Packet refusePacket = new Packet(PacketType.Engine.MOTION_COMMAND_REFUSE_OTHER_ACTION_EXECUTED);
			refusePacket.setPacket(inputPacket);
			localBroadcastDispatcher.sendPacketViaLocalBroadcast(refusePacket, IntentsIdentifiers.ACTION_MESSAGE_TO_CONTROLLER);
			return;
		}

		// zmiany ustawień dotyczących ruchu
		// pakiety ruchu
		if (inputPacket.getPacketType() instanceof PacketType.Motion) {
			if (motionMode.equals(MotionMode.MOTION_MODE_CONTINUOUS)) {
				interpretMotionPacketContinuous(inputPacket);
			} else if (motionMode.equals(MotionMode.MOTION_MODE_STEPPER)) {
				interpretMotionPacketStepper(inputPacket);
			}
		} else if (inputPacket.getPacketType() == PacketType.Engine.SET_SPEED) {
			log.debug(TAG, "setting speed to value: " + inputPacket.getSpeed());
			vehicle.getSettings().setSpeed(inputPacket.getSpeed());
		} else if (inputPacket.getPacketType() == PacketType.Engine.SET_SPEED_B) {
			log.debug(TAG, "setting speed to value: " + inputPacket.getSpeedB());
			vehicle.getSettings().setSpeedB(inputPacket.getSpeedB());

		} else if (inputPacket.getPacketType() == PacketType.Engine.SET_STEPPER_MODE) {
			if (motionMode.equals(MotionMode.MOTION_MODE_CONTINUOUS)) {
				log.debug(TAG, "zmiana trybu ruchu na krokowy");
				setMotionMode(MotionMode.MOTION_MODE_STEPPER);
				// zerowanie kolejki ruchów
				if (isStepsQueueNotEmpty()) {
					stepsQueue.clear();
				}
			}
		} else if (inputPacket.getPacketType() == PacketType.Engine.SET_CONTINUOUS_MODE) {
			if (motionMode.equals(MotionMode.MOTION_MODE_STEPPER)) {
				log.debug(TAG, "zmiana trybu ruchu na ciągły");
				setMotionMode(MotionMode.MOTION_MODE_CONTINUOUS);
				if (isStepsQueueNotEmpty()) {
					stepsQueue = null;
				}
			}
		} else if (inputPacket.getPacketType() == PacketType.Engine.SET_STEP_DURATION) {
			log.debug(TAG,
					"EnginesControllerService: zmiana czasu trwania kroku na : " + inputPacket.getStepDuration());
			vehicle.getSettings().setStepDuration(inputPacket.getStepDuration());
		} else if (inputPacket.getPacketType() == PacketType.Connection.NODE_CONNECTION_STATUS_REQUEST) {
			log.debug(TAG, "engine service: sending motion mode: " + motionMode);
			Packet pack = null;
			if (motionMode.equals(MotionMode.MOTION_MODE_CONTINUOUS)) {
				pack = new Packet(PacketType.Engine.CONTINUOUS_MODE_RESPONSE);
			} else if (motionMode.equals(MotionMode.MOTION_MODE_STEPPER)) {
				pack = new Packet(PacketType.Engine.STEPPER_MODE_RESPONSE);
			}
			localBroadcastDispatcher.sendPacketViaLocalBroadcast(pack, IntentsIdentifiers.ACTION_MESSAGE_TO_CONTROLLER);
		} 
	}

	/**
	 * Pobranie kolejnego kroku z kolejki.
	 */
	public synchronized Step getNextStep() {
		if (vehicle.getSettings().getMotionMode().equals(MotionMode.MOTION_MODE_STEPPER) && stepsQueue != null
				&& stepsQueue.size() > 0) {
			Step step = stepsQueue.getFirst();
			stepsQueue.removeFirst();
			log.debug(TAG, "EnginesControllerService: pobrano krok z listy kroków:" + step.getStepType());
			return step;
		} else {
			return null;
		}
	}

	private void trySetupVehicleWithExtrasFrom(Intent intent) {
		try {
			Packet pack = (Packet) intent.getParcelableExtra(IntentsFieldsIdentifiers.EXTRA_PACKET);
			if (pack != null && pack.getPlatformName() != null && pack.getDriverName() != null) {
				log.debug(TAG, "Engines Service: setting model name: " + pack.getPlatformName());
				MobilePlatformType platformName = pack.getPlatformName();
				MotorDriverType driverName = pack.getDriverName();
				log.debug(TAG, "setting platform... " + platformName );
				log.debug(TAG, "setting engine driver... " + driverName);
				vehicle = new Vehicle(platformName, driverName);
				//				if (this.compass == null) {
				//					this.compass = new Compass(this.getApplicationContext());
				//					this.compass.unregisterListeners();
				//				}
			} else {
				log.debug(TAG, "Engines Service: input packet has no device info;");
				//TODO zabezpieczyć przed sytuacją niedookreśloną			
			}
		} catch (UnknownDeviceException e) {
			e.printStackTrace();
		}
	}

	private boolean isStepsQueueNotEmpty() {
		return stepsQueue != null && stepsQueue.size() > 0;
	}

	/**
	 * inicjalizacja kolejki kroków
	 */
	private void initStepsQueue() {
		if (this.stepsQueue == null)
			this.stepsQueue = new LinkedList<Step>();
	}

	private void setMotionMode(MotionMode motionMode) {
		Packet pack = null;
		vehicle.getSettings().setMotionMode(motionMode);
		if (motionMode.equals(MotionMode.MOTION_MODE_CONTINUOUS)) {
			pack = new Packet(PacketType.Engine.CONTINUOUS_MODE_RESPONSE);
		} else if (motionMode.equals(MotionMode.MOTION_MODE_STEPPER)) {
			pack = new Packet(PacketType.Engine.STEPPER_MODE_RESPONSE);
		}
		log.debug(TAG, "engineService: set motionMode=" + motionMode + "; with engine packet broadcast");
		localBroadcastDispatcher.sendPacketViaLocalBroadcast(pack, IntentsIdentifiers.ACTION_MESSAGE_TO_CONTROLLER);
	}

	//	public Compass getCompass() {
	//		return compass;
	//	}
	//
	//	public void setCompass(Compass compass) {
	//		this.compass = compass;
	//	}


	private void interpretMotionPacketContinuous(Packet inputPacket) {
		if (inputPacket.getSpeed() >= vehicle.getSettings().getMIN_SPEED()) {
			vehicle.getSettings().setSpeed(inputPacket.getSpeed());
		}
		looper.executePacket(inputPacket);
		log.debug(TAG, "engine service received: " + inputPacket.getPacketType());

	}

	private void interpretMotionPacketStepper(Packet inputPacket) {
		IPacketType packetType = inputPacket.getPacketType();
		if(!(packetType instanceof Motion)) {
			log.error(TAG, packetType + " is not a motion packet!");
			return;
		}
		Motion motionPacketType = (Motion)packetType;
		log.debug(TAG, "EnginesControllerService; add packet to stepsList: " + motionPacketType);
		if (looper != null) {
			if (motionPacketType == PacketType.Motion.MOVE_LEFT_FORWARD_REQUEST) {
				stepsQueue.addLast(new Step(motionPacketType));
			} else if (motionPacketType == PacketType.Motion.MOVE_FORWARD_REQUEST) {
				stepsQueue.addLast(new Step(motionPacketType));
			} else if (motionPacketType == PacketType.Motion.MOVE_RIGHT_FORWARD_REQUEST) {
				stepsQueue.addLast(new Step(motionPacketType));
			} else if (motionPacketType == PacketType.Motion.MOVE_LEFT_REQUEST) {
				stepsQueue.addLast(new Step(motionPacketType));
			} else if (motionPacketType == PacketType.Motion.STOP_REQUEST) {
				log.debug(TAG, "PacketType.STOP w trybie steppera nie jest dodawany do kolejki!!!");
			} else if (motionPacketType == PacketType.Motion.MOVE_RIGHT_REQUEST) {
				stepsQueue.addLast(new Step(motionPacketType));
			} else if (motionPacketType == PacketType.Motion.MOVE_LEFT_BACKWARD_REQUEST) {
				stepsQueue.addLast(new Step(motionPacketType));
			} else if (motionPacketType == PacketType.Motion.MOVE_BACKWARD_REQUEST) {
				stepsQueue.addLast(new Step(motionPacketType));
			} else if (motionPacketType == PacketType.Motion.MOVE_RIGHT_BACKWARD_REQUEST) {
				stepsQueue.addLast(new Step(motionPacketType));
			} else if (motionPacketType == PacketType.Motion.MOVE_LEFT_90_DEGREES_REQUEST) {
				stepsQueue.addLast(new Step(motionPacketType));
			} else if (motionPacketType == PacketType.Motion.MOVE_RIGHT_90_DEGREES_REQUEST) {
				stepsQueue.addLast(new Step(motionPacketType));
			} else if (motionPacketType == PacketType.Motion.MOVE_LEFT_DEGREES_REQUEST) {
				Step step = new Step(motionPacketType);
				step.setDegrees(inputPacket.getBearing());
				stepsQueue.addLast(step);
			} else if (motionPacketType == PacketType.Motion.MOVE_RIGHT_DEGREES_REQUEST) {
				Step step = new Step(motionPacketType);
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
		localBroadcastDispatcher.sendPacketViaLocalBroadcast(stopServicePacket, IntentsIdentifiers.ACTION_MESSAGE_TO_CONTROLLER);
		this.stopSelf();
	}
}
