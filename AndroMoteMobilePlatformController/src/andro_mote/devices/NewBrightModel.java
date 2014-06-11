package andro_mote.devices;

import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.IPacketType;
import andro_mote.commons.PacketType.Motion;
import andro_mote.ioio_service.EngineControllerLooper;
import andro_mote.ioio_service.EnginesControllerService;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;

public class NewBrightModel extends AbstractModel {

	private static final String TAG = NewBrightModel.class.getName();
	private AndroMoteLogger logger = new AndroMoteLogger(NewBrightModel.class);

	private static final int STEPS_DELAY = 1000;
	
	// private double operationSpeed = 1;

	public NewBrightModel(EngineControllerLooper looper) {
		super(looper);
	}

	@Override
	public void steerLeft() {
		looper.servoVoltage = 1;
		looper.servoLeft = false;
		looper.servoRight = true;
	}

	@Override
	public void steerRight() {
		this.looper.servoVoltage = 1;
		this.looper.servoLeft = true;
		this.looper.servoRight = false;
	}

	@Override
	public void steerCenter() {
		this.looper.servoVoltage = 0;
		this.looper.servoLeft = false;
		this.looper.servoRight = true;
	}

	@Override
	public void moveForward(double speed) {
		this.looper.engineGearBackward = false;
		this.looper.engineGearForward = true;
		this.looper.engineFreq = speed;

	}

	@Override
	public void moveBackward(double speed) {
		this.looper.engineGearBackward = true;
		this.looper.engineGearForward = false;
		this.looper.engineFreq = speed;
	}

	@Override
	public void stop() {
		this.looper.engineFreq = 0;
		this.looper.engineGearBackward = false;
		this.looper.engineGearForward = false;
		this.steerCenter();
	}

	@Override
	public void turn90Right() {
		logger.debug(TAG, "NewBrightModel: turn90Right");
		if (!this.looper.getParentControllerService().isOperationExecuted()) {
			this.looper.getParentControllerService().setOperationExecuted(true);
			registerCompassListeners();
			TurnRightThread turnRightThread = new TurnRightThread(90);
			turnRightThread.startThread();
		}
	}

	@Override
	public void turn90Left() {
		logger.debug(TAG, "NewBrightModel: turn90Left");
		if (!this.looper.getParentControllerService().isOperationExecuted()) {
			this.looper.getParentControllerService().setOperationExecuted(true);
			registerCompassListeners();
			TurnLeftThread turnLeftThread = new TurnLeftThread(90);
			turnLeftThread.startThread();
		}
	}

	@Override
	public void turnRightDegrees(int degrees) {
		logger.debug(TAG, "NewBrightModel: turnRightDegrees: " + degrees);
		if (!this.looper.getParentControllerService().isOperationExecuted()) {
			this.looper.getParentControllerService().setOperationExecuted(true);

			// registering compass
			registerCompassListeners();

			TurnRightThread turnRightThread = new TurnRightThread(degrees);
			turnRightThread.startThread();
		}
	}

	@Override
	public void turnLeftDegrees(int degrees) {
		logger.debug(TAG, "NewBrightModel: turnLeftDegrees: " + degrees);
		if (!this.looper.getParentControllerService().isOperationExecuted()) {
			this.looper.getParentControllerService().setOperationExecuted(true);

			registerCompassListeners();

			TurnLeftThread turnLeftThread = new TurnLeftThread(degrees);
			turnLeftThread.startThread();
		}
	}

	private boolean isSteerLeft() {
		if (this.looper.servoVoltage == 1 && this.looper.servoLeft == false && this.looper.servoRight == true) {
			return true;
		} else
			return false;
	}

	private boolean isSteerRight() {
		if (this.looper.servoVoltage == 1 && this.looper.servoLeft == true && this.looper.servoRight == false) {
			return true;
		} else
			return false;
	}

	private boolean isSteerCenter() {
		if (this.looper.servoVoltage == 0 && this.looper.servoLeft == false && this.looper.servoRight == true) {
			return true;
		} else
			return false;
	}

	private boolean isMoveForward() {
		if (this.looper.engineGearBackward == false && this.looper.engineGearForward == true
				&& this.looper.engineFreq != 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isMoveBackward() {
		if (this.looper.engineGearBackward == true && this.looper.engineGearForward == false
				&& this.looper.engineFreq != 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isStopped() {
		if (this.looper.engineFreq == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param packetType
	 */
	private void setValuesForSimpleStep(IPacketType packetType) {
		if (packetType == Motion.MOVE_LEFT_FORWARD_REQUEST) {
			looper.getModel().moveForward(EnginesControllerService.getSpeed());
			looper.getModel().steerLeft();
		} else if (packetType == PacketType.Motion.MOVE_FORWARD_REQUEST) {
			looper.getModel().moveForward(EnginesControllerService.getSpeed());
			looper.getModel().steerCenter();
		} else if (packetType == PacketType.Motion.MOVE_RIGHT_FORWARD_REQUEST) {
			looper.getModel().moveForward(EnginesControllerService.getSpeed());
			looper.getModel().steerRight();
		} else if (packetType == PacketType.Motion.MOVE_LEFT_REQUEST) {
			looper.getModel().moveForward(0.0);
			looper.getModel().steerLeft();
		} else if (packetType == PacketType.Motion.MOVE_RIGHT_REQUEST) {
			looper.getModel().moveForward(0.0);
			looper.getModel().steerRight();
		} else if (packetType == PacketType.Motion.STOP_REQUEST) {
			looper.getModel().stop();
		} else if (packetType == PacketType.Motion.MOVE_LEFT_BACKWARD_REQUEST) {
			looper.getModel().moveBackward(EnginesControllerService.getSpeed());
			looper.getModel().steerLeft();
		} else if (packetType == PacketType.Motion.MOVE_BACKWARD_REQUEST) {
			looper.getModel().moveBackward(EnginesControllerService.getSpeed());
			looper.getModel().steerCenter();
		} else if (packetType == PacketType.Motion.MOVE_RIGHT_BACKWARD_REQUEST) {
			looper.getModel().moveBackward(EnginesControllerService.getSpeed());
			looper.getModel().steerRight();
		}
	}

	/**
	 * Funkcja zwraca nazwę aktualnego kroku na podstawie aktualnych ustawień
	 * silników.
	 */
	public PacketType.IPacketType getCurrentStepName() {
		if (this.isSteerLeft() && isMoveForward()) {
			return PacketType.Motion.MOVE_LEFT_FORWARD_REQUEST;
		} else if (this.isSteerCenter() && isMoveForward()) {
			return PacketType.Motion.MOVE_FORWARD_REQUEST;
		} else if (this.isSteerRight() && isMoveForward()) {
			return PacketType.Motion.MOVE_RIGHT_FORWARD_REQUEST;
		} else if (this.isSteerLeft() && isStopped()) {
			return PacketType.Motion.MOVE_LEFT_REQUEST;
		} else if (this.isSteerCenter() && isStopped()) {
			return PacketType.Motion.STOP_REQUEST;
		} else if (this.isSteerRight() && isStopped()) {
			return PacketType.Motion.MOVE_RIGHT_REQUEST;
		} else if (this.isSteerLeft() && isMoveBackward()) {
			return PacketType.Motion.MOVE_LEFT_BACKWARD_REQUEST;
		} else if (this.isSteerLeft() && isMoveBackward()) {
			return PacketType.Motion.MOVE_LEFT_BACKWARD_REQUEST;
		} else if (this.isSteerCenter() && isMoveBackward()) {
			return PacketType.Motion.MOVE_BACKWARD_REQUEST;
		} else if (this.isSteerRight() && isMoveBackward()) {
			return PacketType.Motion.MOVE_RIGHT_BACKWARD_REQUEST;
		} else {
			return PacketType.Motion.STOP_REQUEST;
		}
	}

	@Override
	public void takeStep(Step step) {
		logger.debug(TAG, "NewbrightModel: step execution");
		if (step.getStepType() == Motion.MOVE_FORWARD_REQUEST
				|| step.getStepType() == Motion.MOVE_RIGHT_FORWARD_REQUEST
				|| step.getStepType() == Motion.MOVE_LEFT_FORWARD_REQUEST
				|| step.getStepType() == Motion.MOVE_LEFT_REQUEST || step.getStepType() == Motion.MOVE_RIGHT_REQUEST
				|| step.getStepType() == Motion.MOVE_LEFT_BACKWARD_REQUEST
				|| step.getStepType() == Motion.MOVE_RIGHT_BACKWARD_REQUEST
				|| step.getStepType() == Motion.MOVE_BACKWARD_REQUEST) {
			TakeStepThread makeStep = new TakeStepThread(step);
			makeStep.startThread();
		} else if (step.getStepType() == Motion.MOVE_LEFT_90_DEGREES_REQUEST) {
			this.turn90Left();
		} else if (step.getStepType() == Motion.MOVE_RIGHT_90_DEGREES_REQUEST) {
			this.turn90Right();
		} else if (step.getStepType() == Motion.MOVE_LEFT_DEGREES_REQUEST) {
			this.turnLeftDegrees(step.getDegrees());
		} else if (step.getStepType() == Motion.MOVE_RIGHT_DEGREES_REQUEST) {
			this.turnRightDegrees(step.getDegrees());
		}
	}

	private class TurnLeftThread implements Runnable {

		private Thread blinker = null;
		private int targetBearing = 0;
		private int startAzimut = 0;
		int step = 0;
		int maxSteps = 20;
		private int currentAzimut = 0;
		private int turn = 0;
		long stepStartTime = 0;
		long stepStopTime = 0;

		public TurnLeftThread(int turn) {
			this.turn = turn;
		}

		public void startThread() {
			logger.debug(TAG, "Starting TurnLeftThread... degrees: " + turn);
			blinker = new Thread(this);
			blinker.start();
		}

		public void stopThread() {
			logger.debug(TAG, "Stopping TurnLeftThread...");
			blinker = null;
		}

		@Override
		public void run() {
			this.blinker = Thread.currentThread();

			// oczekiwanie na pierwsze wczytanie wartości z kompasu - zmiana
			// dokładności i azymut
			// różny od 0.0 (bo prawdopodobieństwo takiej wartości jest
			// praktycznie zerowe na doublach, więc ten sposób może być dobry

			int waitCounter = 0;
			while ((looper.getParentControllerService().getCompass().getAccelerometerSensorAccuracy() == 0
					|| looper.getParentControllerService().getCompass().getMagneticFieldSensorAccuracy() == 0 || looper
					.getParentControllerService().getCompass().getBearing() == 0.0)
					&& waitCounter < 5) {
				try {
					logger.debug(TAG,
							"looper.getParentControllerService().getCompass().getAccelerometerSensorAccuracy(): "
									+ looper.getParentControllerService().getCompass().getAccelerometerSensorAccuracy());
					logger.debug(TAG,
							"looper.getParentControllerService().getCompass().getMagneticFieldSensorAccuracy(): "
									+ looper.getParentControllerService().getCompass().getMagneticFieldSensorAccuracy());
					logger.debug(TAG, "looper.getParentControllerService().getCompass().getBearing()"
							+ looper.getParentControllerService().getCompass().getBearing());
					Thread.sleep(1000);
					waitCounter++;
				} catch (InterruptedException e) {
					logger.error(TAG, e);
				}
				logger.debug(TAG, "NewBrightModel: waiting for registering sensors...");
			}
			if ((looper.getParentControllerService().getCompass().getAccelerometerSensorAccuracy() == 0 || looper
					.getParentControllerService().getCompass().getBearing() == 0.0)) {
				logger.debug(TAG,
						"NewBrightModel: turn left thread: sensors cannot be initialized. Step cannot be executed!");
				looper.getParentControllerService().sendStepExecutionErrorBroadcast(
						(Motion) Step.getTakenStep(Motion.MOVE_LEFT_DEGREES));
				looper.getParentControllerService().setOperationExecuted(false);
				return;
			}

			startAzimut = (int) looper.getParentControllerService().getCompass().getBearing();
			logger.debug(TAG, "startAzimut turn left: " + startAzimut);
			currentAzimut = (int) looper.getParentControllerService().getCompass().getBearing();
			logger.debug(TAG, "turn turn left: " + turn);

			// nowa wersja wyznaczania pozycji
			if ((startAzimut - turn) <= 0) {
				targetBearing = 360 + (startAzimut - turn);
			} else {
				targetBearing = startAzimut - turn;
			}
			logger.debug(TAG, "target azimut turn left: " + targetBearing);

			boolean nonZero = false;
			if ((startAzimut - targetBearing) > 0) {
				nonZero = true;
				logger.debug(TAG, "brak przejścia przez zero");
			}

			while (blinker == Thread.currentThread()) {
				if (nonZero) {
					stepStartTime = System.currentTimeMillis();
					while ((currentAzimut >= targetBearing || currentAzimut >= startAzimut) && step <= maxSteps) {
						try {
							Thread.sleep(100);
							// logTimestamp(logger, TAG);
							// do tyłu w prawo
							steerRight();
							moveBackward(EnginesControllerService.getSpeed());
							Thread.sleep(looper.getParentControllerService().getStepDuration());
							// logTimestamp(logger, TAG);
							stop();
							Thread.sleep(100);
							// logTimestamp(logger, TAG);
							steerLeft();
							moveForward(EnginesControllerService.getSpeed());
							Thread.sleep(looper.getParentControllerService().getStepDuration());
							// logTimestamp(logger, TAG);
							stop();
							// oczekiwanie na ustabilizowanie kompasu
							Thread.sleep(STEPS_DELAY);
							// logTimestamp(logger, TAG);
							currentAzimut = (int) looper.getParentControllerService().getCompass().getBearing();

							logger.debug(TAG, "NewBrightModel: turnleft: step: " + step + "(max_steps=" + maxSteps
									+ ");current azimut after loop: " + currentAzimut);
							step++;
						} catch (InterruptedException e) {
							logger.error(TAG, e);
						}
					}
					stepStopTime = System.currentTimeMillis();
				} else {
					stepStartTime = System.currentTimeMillis();
					while ((currentAzimut <= (startAzimut + 5) || (currentAzimut >= targetBearing)) && step <= maxSteps) {
						try {
							Thread.sleep(100);
							// logTimestamp(logger, TAG);
							// do tyłu w prawo
							steerRight();
							moveBackward(EnginesControllerService.getSpeed());
							Thread.sleep(looper.getParentControllerService().getStepDuration());
							// logTimestamp(logger, TAG);
							stop();
							Thread.sleep(500);
							// logTimestamp(logger, TAG);
							steerLeft();
							moveForward(EnginesControllerService.getSpeed());
							Thread.sleep(looper.getParentControllerService().getStepDuration());
							// logTimestamp(logger, TAG);
							stop();
							Thread.sleep(STEPS_DELAY);
							// logTimestamp(logger, TAG);
							currentAzimut = (int) looper.getParentControllerService().getCompass().getBearing();

							logger.debug(TAG, "NewBrightModel: turnLeft: step: " + step
									+ ";current azimut after loop: " + currentAzimut);
							step++;
						} catch (InterruptedException e) {
							logger.error(TAG, e);
						}
					}
					stepStopTime = System.currentTimeMillis();
				}

				logger.debug(TAG, "final position after turn left (" + turn + ": "
						+ (int) looper.getParentControllerService().getCompass().getBearing());
				looper.getParentControllerService().getCompass().unregisterListeners();

				looper.getParentControllerService().setOperationExecuted(false);

				if (looper.getParentControllerService().isSendStepExecutedPacket()) {
					logger.debug(TAG, "NewBrightModel: broadcasting step executed: " + Motion.MOVE_LEFT_DEGREES);
					looper.getParentControllerService().sendStepExecutedBroadcast(
							(Motion) Step.getTakenStep(Motion.MOVE_LEFT_DEGREES_REQUEST), stepStopTime - stepStartTime,
							EnginesControllerService.getSpeed());
				}

				unregisterCompassListeners();
				return;
			}
		}
	}

	private class TurnRightThread implements Runnable {

		private Thread blinker = null;
		private int targetBearing = 0;
		private int startAzimut = 0;
		int step = 0;
		int maxSteps = 20;
		private int currentAzimut = 0;
		private int turn = 0;
		long stepStartTime = 0;
		long stepStopTime = 0;

		public TurnRightThread(int turn) {
			this.turn = turn;
		}

		public void startThread() {
			logger.debug(TAG, "Starting TurnRightThread... degrees: " + turn);
			blinker = new Thread(this);
			blinker.start();
		}

		public void stopThread() {
			logger.debug(TAG, "Stopping TurnLeftThread...");
			blinker = null;
		}

		@Override
		public void run() {
			this.blinker = Thread.currentThread();

			// oczekiwanie na pierwsze wczytanie wartości z kompasu - zmiana
			// dokładności i azymut
			// różny od 0.0 (bo prawdopodobieństwo takiej wartości jest
			// praktycznie zerowe na doublach, więc ten sposób może być dobry
			int waitCounter = 0;
			while ((looper.getParentControllerService().getCompass().getAccelerometerSensorAccuracy() == 0 || looper
					.getParentControllerService().getCompass().getBearing() == 0.0)
					&& waitCounter < 5) {
				try {
					Thread.sleep(1000);
					waitCounter++;
				} catch (InterruptedException e) {
					logger.error(TAG, e);
				}
				logger.debug(TAG, "NewBrightModel: waiting for registering sensors...");
			}

			if ((looper.getParentControllerService().getCompass().getAccelerometerSensorAccuracy() == 0 || looper
					.getParentControllerService().getCompass().getBearing() == 0.0)) {
				logger.debug(TAG,
						"NewBrightModel: turn left thread: sensors cannot be initialized. Step cannot be executed!");
				looper.getParentControllerService().sendStepExecutionErrorBroadcast(
						(Motion) Step.getTakenStep(Motion.MOVE_RIGHT_DEGREES));
				looper.getParentControllerService().setOperationExecuted(false);
				return;
			}

			startAzimut = (int) looper.getParentControllerService().getCompass().getBearing();
			logger.debug(TAG, "startAzimut turn right: " + startAzimut);
			currentAzimut = (int) looper.getParentControllerService().getCompass().getBearing();
			logger.debug(TAG, "turn right: " + turn);

			// targetBearing = (startAzimut - turn) % 360;
			// if (targetBearing < 0)
			// targetBearing = 360 + targetBearing;

			// new version
			targetBearing = (startAzimut + turn) % 360;
			logger.debug(TAG, "target azimut turn right: " + targetBearing);

			boolean nonZero = false;
			if ((targetBearing - startAzimut) >= 0) {
				nonZero = true;
				logger.debug(TAG, "NewBrightModel: turnRight: brak przejścia przez zero");
			}

			while (blinker == Thread.currentThread()) {
				if (nonZero) {
					// warunek brzegowy
					if (targetBearing == 0)
						targetBearing = 359;
					/*
					 * Dwa przypadki: - currentAzimut <= targetBearing - obrot
					 * zgodnie z ruchem wskazowek zegara do uzyskania celu
					 * 
					 * currentAzimut <= startAzimut - błąd - np. nieprawidłowe
					 * wskazanie; lub niepotrzebne przejście przez zero
					 */
					stepStartTime = System.currentTimeMillis();
					while ((currentAzimut <= targetBearing || currentAzimut <= startAzimut) && step <= maxSteps) {
						try {
							Thread.sleep(100);
							// logTimestamp(logger, TAG);
							// do tyłu w prawo
							steerLeft();
							moveBackward(EnginesControllerService.getSpeed());
							Thread.sleep(looper.getParentControllerService().getStepDuration());
							// logTimestamp(logger, TAG);
							stop();
							Thread.sleep(100);
							// logTimestamp(logger, TAG);
							steerRight();
							moveForward(EnginesControllerService.getSpeed());
							Thread.sleep(looper.getParentControllerService().getStepDuration());
							// logTimestamp(logger, TAG);
							stop();
							// oczekiwanie na ustabilizowanie kompasu
							Thread.sleep(STEPS_DELAY);
							// logTimestamp(logger, TAG);
							currentAzimut = (int) looper.getParentControllerService().getCompass().getBearing();

							logger.debug(TAG, "NewBrightModel: turnRight: step: " + step + "(max_steps=" + maxSteps
									+ ");current azimut after loop: " + currentAzimut);
							step++;
						} catch (InterruptedException e) {
							logger.error(TAG, e);
						}
					}
					stepStopTime = System.currentTimeMillis();
				} else {
					stepStartTime = System.currentTimeMillis();
					while ((currentAzimut >= (startAzimut - 5) || (currentAzimut <= targetBearing)) && step <= maxSteps) {
						try {
							Thread.sleep(100);
							// logTimestamp(logger, TAG);
							// do tyłu w prawo
							steerLeft();
							moveBackward(EnginesControllerService.getSpeed());
							Thread.sleep(looper.getParentControllerService().getStepDuration());
							// logTimestamp(logger, TAG);
							stop();
							Thread.sleep(500);
							steerRight();
							moveForward(EnginesControllerService.getSpeed());
							Thread.sleep(looper.getParentControllerService().getStepDuration());
							stop();
							Thread.sleep(STEPS_DELAY);
							currentAzimut = (int) looper.getParentControllerService().getCompass().getBearing();

							logger.debug(TAG, "NewBrightModel: turnRight: step: " + step
									+ ";current azimut after loop: " + currentAzimut);
							step++;
						} catch (InterruptedException e) {
							logger.error(TAG, e);
						}
					}
					stepStopTime = System.currentTimeMillis();
				}

				logger.debug(TAG, "final position after turn left (" + turn + ": "
						+ (int) looper.getParentControllerService().getCompass().getBearing());
				looper.getParentControllerService().getCompass().unregisterListeners();

				looper.getParentControllerService().setOperationExecuted(false);
				unregisterCompassListeners();

				// wysyłanie pakietu z informacją o wykonanym kroku
				if (looper.getParentControllerService().isSendStepExecutedPacket()) {
					logger.debug(TAG, "NewBrightModel: broadcasting step executed: " + Motion.MOVE_RIGHT_DEGREES);
					looper.getParentControllerService().sendStepExecutedBroadcast(
							(Motion) Step.getTakenStep(Motion.MOVE_RIGHT_DEGREES_REQUEST),
							stepStopTime - stepStartTime, EnginesControllerService.getSpeed());
				}

				return;
			}
		}
	}

	private class TakeStepThread implements Runnable {

		private Thread blinker = null;
		private Step step = null;

		public TakeStepThread(Step step) {
			this.step = step;
		}

		public void startThread() {
			logger.debug(TAG, "Starting make step Thread...");
			blinker = new Thread(this);
			blinker.start();
		}

		public void stopThread() {
			logger.debug(TAG, "Stopping make step Thread...");
			blinker = null;
		}

		@Override
		public void run() {
			long stepStartTime = 0;
			long stepStopTime = 0;
			try {
				looper.getParentControllerService().setOperationExecuted(true);
				stepStartTime = System.currentTimeMillis();

				// zmiana stanu silników
				setValuesForSimpleStep(step.getStepType());

				// początek kroku
				Thread.sleep(looper.getParentControllerService().getStepDuration());
				stepStopTime = System.currentTimeMillis();
				// koniec kroku

				// zatrzymanie węzła
				NewBrightModel.this.stop();

				// przerwa pomiędzy kolejnymi krokami
				Thread.sleep(EnginesControllerService.getPauseTimeBetweenSteps());
			} catch (InterruptedException e) {
				logger.error(TAG, e);
			} finally {
				looper.getParentControllerService().setOperationExecuted(false);
			}

			// wysyłanie pakietu z informacją o wykonanym kroku
			if (looper.getParentControllerService().isSendStepExecutedPacket()) {
				logger.debug(TAG, "NewBrightModel: broadcasting step executed: " + step.getStepType());
				looper.getParentControllerService().sendStepExecutedBroadcast(
						(Motion) Step.getTakenStep((Motion) step.getStepType()), stepStopTime - stepStartTime,
						EnginesControllerService.getSpeed());
			}
		}
	}
}
