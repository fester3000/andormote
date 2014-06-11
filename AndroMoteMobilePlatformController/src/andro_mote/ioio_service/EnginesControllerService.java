package andro_mote.ioio_service;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOService;

import java.io.Serializable;
import java.util.LinkedList;

import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.IntentsFieldsIdentifiers;
import andro_mote.commons.IntentsIdentifiers;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.Motion;
import andro_mote.compass.Compass;
import andro_mote.devices.ModelFactory;
import andro_mote.devices.NewBrightModel;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcelable;

public class EnginesControllerService extends IOIOService {
	private static final String TAG = EnginesControllerService.class.getName();

	private EnginesControllerBroadcastReceiver enginesReceiver = null;
	private AndroMoteLogger logger = new AndroMoteLogger(EnginesControllerBroadcastReceiver.class);
	EngineControllerLooper looper = null;

	private Compass compass = null;

	/**
	 * Aktualna prędkość silnika.
	 */
	public static double SPEED = 0.6;

	/**
	 * Minimalna prędkość silników. Zabezpieczenie przed małą mocą.
	 */
	public static double MIN_SPEED = 0.4;

	/**
	 * Maksymalny czas trwania kroku w trybie MOTION_MODE_STEPPER
	 */
	private static final int MAX_STEP_DURATION = 2000;

	/**
	 * Czas oczekiwania pomiędzy kolejnymi krokami.
	 */
	private static long pauseTimeBetweenSteps = 1000;

	private long stepDuration = 600;

	private LinkedList<Step> stepsQueue;

	/**
	 * Nazwa aktualnie obsługiwanego modelu.
	 */
	private String modelName = null;

	/**
	 * Krokowy tryb poruszania się samochodu z rozgłaszaniem wykonywanych
	 * kroków.
	 */
	public static String MOTION_MODE_STEPPER = "stepper";

	/**
	 * Ciągły tryb poruszania - kroki nie są zapisywane, ale zmiana kierunku
	 * ruchu jest atychmiastowa.
	 */
	public static String MOTION_MODE_CONTINUOUS = "continuous";

	/**
	 * Tryb ruchu samochodu.
	 * 
	 * @see EnginesControllerService.MOVING_MODE_CONTINUOUS
	 * @see EnginesControllerService.MOVING_MODE_STEPPER
	 */
	private String motionMode = MOTION_MODE_CONTINUOUS;

	/**
	 * Flaga informująca o tym czy wykonywana jest operacja np. skrętu o
	 * określony kąt, zawracania. Gdy flaga jest aktywna komendy sterujące
	 * odbierane przez serwis są ignorowane do momentu zakończenia wykoywanaego
	 * zadania - na każde zapytanie jest wysyłana intencja zwrotna.
	 */
	private boolean isOperationExecuted = false;

	/**
	 * Flaga określająca czy po wykonanym kroku następuje wysłanie paketu
	 * informującego o jego wykonaniu. Pakiet taki może być wysłany tylko w
	 * trybie krokowym. Wartośc flagi może być zmieniona poprzez pakiet
	 */
	private boolean sendStepExecutedPacket = true;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {

		logger.debug(TAG, "engineService; onStart(); startId=" + startId);
		try {
			Packet pack = (Packet) intent.getSerializableExtra(IntentsFieldsIdentifiers.EXTRA_PACKET);
			if (pack != null && pack.getDeviceName() != null) {
				logger.debug(TAG, "Engines Service: setting model name: " + pack.getDeviceName());
				this.modelName = pack.getDeviceName();
			} else {
				logger.debug(TAG,
						"Engines Service: input packet empty; setting model name: " + NewBrightModel.class.getName());
				this.modelName = NewBrightModel.class.getName();
			}
		} catch (NullPointerException e) {
			logger.error(TAG, e);
		} catch (Exception e) {
			logger.error(TAG, e);
		}

		if (this.compass == null) {
			this.compass = new Compass(this.getApplicationContext());
			this.compass.unregisterListeners();
		}

		super.onStart(intent, startId);
		this.initStepsList();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		logger.debug(TAG, "engineService; onStartCommand(); startId=" + startId);
		try {
			logger = new AndroMoteLogger(EnginesControllerBroadcastReceiver.class);
			AndroMoteLogger.ConfigureLogger("AndroMoteClient.log");
			logger.debug(TAG, "onStartCommand engine service");

			Packet pack = (Packet) intent.getParcelableExtra(IntentsFieldsIdentifiers.EXTRA_PACKET);
			if (pack != null && pack.getDeviceName() != null) {
				logger.debug(TAG, "Engines Service: setting model name: " + pack.getDeviceName());
				this.modelName = pack.getDeviceName();
			} else {
				logger.debug(TAG,
						"Engines Service: input packet empty; setting model name: " + NewBrightModel.class.getName());
				this.modelName = NewBrightModel.class.getName();
			}
		} catch (Exception e) {
			logger.error(TAG, e);
		}

		this.isOperationExecuted = false;

		initReceiver();

		if (this.compass == null) {
			this.compass = new Compass(this.getApplicationContext());
			this.compass.unregisterListeners();
		}

		super.onStartCommand(intent, flags, startId);

		return startId;
	}

	@Override
	public void onDestroy() {
		this.compass.unregisterListeners();
		this.unregisterEngineMessagesReceiver();
		super.onDestroy();

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
		if (motionMode.equals(EnginesControllerService.MOTION_MODE_STEPPER) && stepsQueue != null
				&& stepsQueue.size() > 0) {
			Step step = stepsQueue.getFirst();
			stepsQueue.removeFirst();
			logger.debug(TAG, "EnginesControllerService: pobrano krok z listy kroków:" + step.getStepType());
			return step;
		} else {
			return null;
		}
	}

	public void broadcastStepExecutedMessage(Motion stepType) {

	}

	private void initReceiver() {
		if (this.enginesReceiver == null) {
			IntentFilter filter = new IntentFilter(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
			this.enginesReceiver = new EnginesControllerBroadcastReceiver();
			this.registerReceiver(this.enginesReceiver, filter);
		}
	}

	private void unregisterEngineMessagesReceiver() {
		if (this.enginesReceiver != null) {
			this.unregisterReceiver(this.enginesReceiver);
			this.enginesReceiver = null;
		}
	}

	/**
	 * inicjalizacja kolejki kroków
	 */
	private void initStepsList() {
		if (this.stepsQueue == null)
			this.stepsQueue = new LinkedList<Step>();
	}

	private void interpretMotionPacketContinuous(Packet inputPacket) {
		if (inputPacket.getSpeed() >= EnginesControllerService.MIN_SPEED) {
			this.setSpeed(inputPacket.getSpeed());
		}

		logger.debug(TAG, "engine service received: " + inputPacket.getPacketType());
		if (inputPacket.getPacketType() == PacketType.Motion.MOVE_LEFT_FORWARD_REQUEST) {
			if (looper != null) {
				looper.getModel().moveForward(getSpeed());
				looper.getModel().steerLeft();
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_FORWARD_REQUEST) {
			if (looper != null) {
				looper.getModel().moveForward(getSpeed());
				looper.getModel().steerCenter();
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_RIGHT_FORWARD_REQUEST) {
			if (looper != null) {
				looper.getModel().moveForward(getSpeed());
				looper.getModel().steerRight();
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_LEFT_REQUEST) {
			if (looper != null) {
				looper.getModel().moveForward(0.0);
				looper.getModel().steerLeft();
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.STOP_REQUEST) {
			if (looper != null) {
				looper.getModel().stop();
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_RIGHT_REQUEST) {
			if (looper != null && looper.getModel() != null) {
				try {
					looper.getModel().stop();
					looper.getModel().steerRight();
				} catch (Exception e) {
					logger.error(TAG, e);
				}
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_LEFT_BACKWARD_REQUEST) {
			if (looper != null) {
				looper.getModel().moveBackward(getSpeed());
				looper.getModel().steerLeft();
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_BACKWARD_REQUEST) {
			if (looper != null) {
				looper.getModel().moveBackward(getSpeed());
				looper.getModel().steerCenter();
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_RIGHT_BACKWARD_REQUEST) {
			if (looper != null) {
				looper.getModel().moveBackward(getSpeed());
				looper.getModel().steerRight();
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_LEFT_90_DEGREES_REQUEST) {
			if (looper != null) {
				if (!this.isOperationExecuted) {
					looper.getModel().turn90Left();
				}
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_RIGHT_90_DEGREES_REQUEST) {
			if (looper != null) {
				if (!this.isOperationExecuted) {
					looper.getModel().turn90Right();
				}
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_RIGHT_DEGREES_REQUEST) {
			if (looper != null) {
				if (!this.isOperationExecuted) {
					looper.getModel().turnRightDegrees(inputPacket.getBearing());
				}
			}
		} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_LEFT_DEGREES_REQUEST) {
			if (looper != null) {
				if (!this.isOperationExecuted) {
					looper.getModel().turnLeftDegrees(inputPacket.getBearing());
				}
			}
		}
	}

	private void interpretMotionPacketStepper(Packet inputPacket) {
		logger.debug(TAG, "EnginesControllerService; add packet to stepsList: " + inputPacket.getPacketType());
		if (looper != null) {
			if (inputPacket.getPacketType() == PacketType.Motion.MOVE_LEFT_FORWARD_REQUEST) {
				stepsQueue.addLast(new Step(inputPacket.getPacketType()));
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_FORWARD_REQUEST) {
				stepsQueue.addLast(new Step(inputPacket.getPacketType()));
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_RIGHT_FORWARD_REQUEST) {
				stepsQueue.addLast(new Step(inputPacket.getPacketType()));
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_LEFT_REQUEST) {
				stepsQueue.addLast(new Step(inputPacket.getPacketType()));
			} else if (inputPacket.getPacketType() == PacketType.Motion.STOP_REQUEST) {
				logger.debug(TAG, "PacketType.STOP w trybie steppera nie jest dodawany do kolejki!!!");
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_RIGHT_REQUEST) {
				stepsQueue.addLast(new Step(inputPacket.getPacketType()));
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_LEFT_BACKWARD_REQUEST) {
				stepsQueue.addLast(new Step(inputPacket.getPacketType()));
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_BACKWARD_REQUEST) {
				stepsQueue.addLast(new Step(inputPacket.getPacketType()));
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_RIGHT_BACKWARD_REQUEST) {
				stepsQueue.addLast(new Step(inputPacket.getPacketType()));
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_LEFT_90_DEGREES_REQUEST) {
				stepsQueue.addLast(new Step(inputPacket.getPacketType()));
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_RIGHT_90_DEGREES_REQUEST) {
				stepsQueue.addLast(new Step(inputPacket.getPacketType()));
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_LEFT_DEGREES_REQUEST) {
				Step step = new Step(inputPacket.getPacketType());
				step.setDegrees(inputPacket.getBearing());
				stepsQueue.addLast(step);
			} else if (inputPacket.getPacketType() == PacketType.Motion.MOVE_RIGHT_DEGREES_REQUEST) {
				Step step = new Step(inputPacket.getPacketType());
				step.setDegrees(inputPacket.getBearing());
				stepsQueue.addLast(step);
			}
		}

	}

	/**
	 * Receiver wiadomości dla sterownika silnikami.
	 * 
	 * @author Maciej Gzik
	 * 
	 */
	private class EnginesControllerBroadcastReceiver extends BroadcastReceiver {

		@Override
		public synchronized void onReceive(Context context, Intent intent) {

			Packet inputPacket = (Packet) intent.getSerializableExtra(IntentsFieldsIdentifiers.EXTRA_PACKET);

			logger.debug(TAG, "engine service broadcast received: " + inputPacket.getPacketType());

			// odrzucenie pakietów Engine i Motion w przypadku wykonywania
			// zadania
			if (motionMode.equals(EnginesControllerService.MOTION_MODE_CONTINUOUS) && isOperationExecuted
					&& (inputPacket.getPacketType() instanceof Motion || inputPacket.getPacketType() instanceof Engine)) {
				logger.debug(TAG, "Engine Service: incoming packet instanceof: " + inputPacket.getPacketType()
						+ " rejected - operationIsExecuted");
				Packet refusePacket = new Packet(PacketType.Engine.MOTION_COMMAND_REFUSE_OTHER_ACTION_EXECUTED);
				refusePacket.setPacket(inputPacket);
				broadcastEngineIntent(refusePacket);
				return;
			}

			// zmiany ustawień dotyczących ruchu
			if (inputPacket.getPacketType() == PacketType.Engine.SET_STEPPER_MODE) {
				if (EnginesControllerService.this.motionMode.equals(EnginesControllerService.MOTION_MODE_CONTINUOUS)) {
					logger.debug(TAG, "zmiana trybu ruchu na krokowy");
					EnginesControllerService.this.setControllMode(EnginesControllerService.MOTION_MODE_STEPPER);
					// zerowanie kolejki ruchów
					if (stepsQueue != null && stepsQueue.size() > 0) {
						stepsQueue.clear();
					}
				}
			} else if (inputPacket.getPacketType() == PacketType.Engine.SET_CONTINUOUS_MODE) {
				if (EnginesControllerService.this.motionMode.equals(EnginesControllerService.MOTION_MODE_STEPPER)) {
					logger.debug(TAG, "zmiana trybu ruchu na ciągły");
					EnginesControllerService.this.setControllMode(EnginesControllerService.MOTION_MODE_CONTINUOUS);
					if (stepsQueue != null && stepsQueue.size() > 0) {
						stepsQueue = null;
					}
				}
			} else if (inputPacket.getPacketType() == PacketType.Engine.SET_STEP_DURATION) {
				logger.debug(TAG,
						"EnginesControllerService: zmiana czasu trwania kroku na : " + inputPacket.getStepDuration());
				setStepDuration(inputPacket.getStepDuration());
			} else if (inputPacket.getPacketType() == PacketType.Engine.SET_SPEED) {
				logger.debug(TAG, "setting speed to value: " + inputPacket.getSpeed());
				setSpeed(inputPacket.getSpeed());
			} else if (inputPacket.getPacketType() == PacketType.Connection.NODE_CONNECTION_STATUS_REQUEST) {
				logger.debug(TAG, "engine service: sending motion mode: " + motionMode);
				Packet pack = null;
				if (motionMode.equals(MOTION_MODE_CONTINUOUS)) {
					pack = new Packet(PacketType.Engine.CONTINUOUS_MODE_RESPONSE);
				} else if (motionMode.equals(MOTION_MODE_STEPPER)) {
					pack = new Packet(PacketType.Engine.STEPPER_MODE_RESPONSE);
				}
				broadcastEngineIntent(pack);
			} else if (inputPacket.getPacketType() == PacketType.Engine.SET_STEP_EXECUTION_CONFIRMATION) {
				boolean newState = false;
				if (inputPacket.getNewState() == 0) {
					newState = false;
				} else {
					newState = true;
				}
				setSendStepExecutedPacket(newState);
				logger.debug(TAG, "set step execution confirmation: " + newState);
			}
			// pakiety ruchu
			else if (inputPacket.getPacketType() instanceof PacketType.Motion) {
				if (motionMode.equals(EnginesControllerService.MOTION_MODE_CONTINUOUS)) {
					interpretMotionPacketContinuous(inputPacket);
				} else if (motionMode.equals(EnginesControllerService.MOTION_MODE_STEPPER)) {
					interpretMotionPacketStepper(inputPacket);
				}
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
		this.broadcastEngineIntent(stopServicePacket);
		this.stopSelf();
	}

	/**
	 * Broadcast pakietu zawierającego informacje o wykonanym kroku.
	 * 
	 * @param responsePacket
	 *            pakiet z informacjami o wykonanym kroku
	 */
	public void sendStepExecutedBroadcast(PacketType.Motion stepType, long stepDuration, double speed) {
		if (!this.isSendStepExecutedPacket()) {
			logger.debug(
					TAG,
					"EngineControllerLooper: sendStepBroadcast: flaga sendStepExecutedPacket ma wartość false - pakiet zwrotny nie zostanie wysłany.");
			return;
		}
		Intent stepBroadcast = new Intent(IntentsIdentifiers.ACTION_ENGINE_STEP);
		logger.debug(TAG, "engineController looper: broadcast informacji o wykonanym kroku: " + stepType);

		Packet responsePacket = new Packet(Engine.STEP_TAKEN_PACKET);
		responsePacket.setStepDirection(stepType);
		responsePacket.setStepDuration(stepDuration);
		responsePacket.setSpeed(speed);

		stepBroadcast.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) responsePacket);
		sendBroadcast(stepBroadcast);
	}

	public void sendStepExecutionErrorBroadcast(Motion takenStep) {
		Intent stepBroadcast = new Intent(IntentsIdentifiers.ACTION_ENGINE_STEP);
		logger.debug(TAG, "engineController looper: broadcast informacji o błędzie w trakcie wykonywania kroku: "
				+ takenStep);
		Packet responsePacket = new Packet(Engine.MOTION_COMMAND_REFUSED_SENSOR_ERROR);
		responsePacket.setStepDirection(takenStep);
		stepBroadcast.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) responsePacket);
		sendBroadcast(stepBroadcast);
	}

	private void broadcastEngineIntent(Packet pack) {
		Intent intentBroadcast = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		intentBroadcast.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Parcelable) pack);
		sendBroadcast(intentBroadcast);
	}

	@Override
	protected IOIOLooper createIOIOLooper() {
		logger.debug(TAG, "EnginesControllerService: creating IOIOLooper");
		try {
			looper = new EngineControllerLooper();
			logger.debug(TAG, "looper created...");
		} catch (ConnectionLostException e) {
			logger.error(TAG, e);
		} catch (InterruptedException e) {
			logger.error(TAG, e);
		}
		logger.debug(TAG, "setting looper in service...");
		looper.setParentControllerService(this);
		logger.debug(TAG, "creating model...");

		try {
			looper.setModel(ModelFactory.getModel(modelName, looper));
		} catch (UnknownDeviceException e) {
			logger.error(TAG, e);
			this.stopEngineServiceOnError();
		}

		logger.debug(TAG, "model created...");
		return looper;
	}

	public String getControllMode() {
		return motionMode;
	}

	public void setControllMode(String controllMode) {
		this.motionMode = controllMode;
		Packet pack = null;
		if (controllMode.equals(MOTION_MODE_CONTINUOUS)) {
			pack = new Packet(PacketType.Engine.CONTINUOUS_MODE_RESPONSE);
		} else if (controllMode.equals(MOTION_MODE_STEPPER)) {
			pack = new Packet(PacketType.Engine.STEPPER_MODE_RESPONSE);
		}
		logger.debug(TAG, "engineService: set controllMode=" + controllMode + ";with engine packet broadcast");
		broadcastEngineIntent(pack);
	}

	public long getStepDuration() {
		return stepDuration;
	}

	public void setStepDuration(long stepDuration) {
		if (stepDuration > EnginesControllerService.MAX_STEP_DURATION) {
			logger.debug(
					TAG,
					"EnginesControllerService: próba ustawienia większego niż dopuszczalny czasu trwania jednego kroku. Ustawiona wartość: "
							+ EnginesControllerService.MAX_STEP_DURATION);
			this.stepDuration = MAX_STEP_DURATION;
		} else {
			logger.debug(TAG, "EnginesControllerService: zmiana czasu trwania kroku. Nowa wartość [ms]: "
					+ stepDuration);
			this.stepDuration = stepDuration;
		}
	}

	public static long getPauseTimeBetweenSteps() {
		return pauseTimeBetweenSteps;
	}

	public static double getSpeed() {
		return SPEED;
	}

	public void setSpeed(double speed) {
		if (speed > 1 || speed < MIN_SPEED) {
			logger.debug(TAG, "EnginesControllerService: niedopuszczalna nowa wartość prędkości silników: " + speed
					+ ". Zmiana nie została dokonana!.");
			if (SPEED < MIN_SPEED) {
				SPEED = MIN_SPEED;
			}
		} else {
			logger.debug(TAG, "EnginesControllerService: zmiana prędkości silników na: " + speed);
			EnginesControllerService.SPEED = speed;
		}
	}

	public boolean isOperationExecuted() {
		return isOperationExecuted;
	}

	public void setOperationExecuted(boolean isOperationExecuted) {
		this.isOperationExecuted = isOperationExecuted;
	}

	public Compass getCompass() {
		return compass;
	}

	public void setCompass(Compass compass) {
		this.compass = compass;
	}

	public boolean isSendStepExecutedPacket() {
		return sendStepExecutedPacket;
	}

	public void setSendStepExecutedPacket(boolean sendStepExecutedPacket) {
		logger.debug(TAG, "EnginesControllerService: set sendStepExecutedPacket. New value: " + sendStepExecutedPacket);
		this.sendStepExecutedPacket = sendStepExecutedPacket;
	}

}
