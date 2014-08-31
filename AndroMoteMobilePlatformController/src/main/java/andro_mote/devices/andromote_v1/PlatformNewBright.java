package andro_mote.devices.andromote_v1;

import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.Motion;
import andro_mote.devices.Vehicle;
import andro_mote.devices.generics.PlatformAbstract;
import andro_mote.logger.AndroMoteLogger;

public class PlatformNewBright extends PlatformAbstract {
	private static final String TAG = PlatformNewBright.class.getName();
	private AndroMoteLogger logger = new AndroMoteLogger(PlatformNewBright.class);
	private static final int STEPS_DELAY = 1000;
	private final MotorDriverNewBrightCompatible driver;

	public PlatformNewBright(MotorDriverNewBrightCompatible driver, Vehicle device) {
		super(device);
		this.driver = driver;
	}

	@Override
	public void interpretMotionPacket(Packet inputPacket) {
		double speed = inputPacket.getSpeed();
		PacketType.Motion packetType = (Motion) inputPacket.getPacketType();
		switch(packetType) {
		case MOVE_LEFT_FORWARD : 
			moveForward(speed);
			steerLeft();
			break;
		case MOVE_FORWARD : 
			moveForward(speed);
			steerCenter();
			break;
		case MOVE_RIGHT_FORWARD : 
			moveForward(speed);
			steerRight();
			break;
		case MOVE_LEFT : 
			moveForward(0.0);
			steerLeft();
			break;
		case STOP : 
			stop();
			break;
		case MOVE_RIGHT : 
			stop();
			steerRight();
			break;
		case MOVE_LEFT_BACKWARD : 
			moveBackward(speed);
			steerLeft();
			break;
		case MOVE_BACKWARD : 
			moveBackward(speed);
			steerCenter();
			break;
		case MOVE_RIGHT_BACKWARD : 
			moveBackward(speed);
			steerRight();
			break;
//		case MOVE_LEFT_90_DEGREES_REQUEST : 
//			turn90Left();
//			break;
//		case MOVE_RIGHT_90_DEGREES_REQUEST : 
//			turn90Right();
//			break;
//		case MOVE_RIGHT_DEGREES_REQUEST : 
//			turnRightDegrees(inputPacket.getBearing());
//			break;
//		case MOVE_LEFT_DEGREES_REQUEST : 
//			turnLeftDegrees(inputPacket.getBearing());
//			break;
		default:
			break;
		}
	}

	
	public void steerLeft() {
		driver.setServoVoltage(1);
		driver.setServoLeft(false);
		driver.setServoRight(true);
	}

	
	public void steerRight() {
		driver.setServoVoltage(1);
		driver.setServoLeft(true);
		driver.setServoRight(false);
	}

	
	public void steerCenter() {
		driver.setServoVoltage(0);
		driver.setServoLeft(false);
		driver.setServoRight(true);
	}

	
	public void moveForward(double speed) {
		driver.setEngineGearBackward(false);
		driver.setEngineGearForward(true);
		driver.setEngineFreq(speed);

	}

	
	public void moveBackward(double speed) {
		driver.setEngineGearBackward(true);
		driver.setEngineGearForward(false);
		driver.setEngineFreq(speed);
	}

	
	public void stop() {
		driver.setEngineFreq(0);
		driver.setEngineGearBackward(false);
		driver.setEngineGearForward(false);
		this.steerCenter();
	}

	/**
	 * 
	 * @param packetType
	 */
	protected void setValuesForSimpleStep(Motion packetType) {
		double speed = parentDevice.getSettings().getSpeed();
		if (packetType == Motion.MOVE_LEFT_FORWARD) {
			moveForward(speed);
			steerLeft();
		} else if (packetType == PacketType.Motion.MOVE_FORWARD) {
			moveForward(speed);
			steerCenter();
		} else if (packetType == PacketType.Motion.MOVE_RIGHT_FORWARD) {
			moveForward(speed);
			steerRight();
		} else if (packetType == PacketType.Motion.MOVE_LEFT) {
			moveForward(0.0);
			steerLeft();
		} else if (packetType == PacketType.Motion.MOVE_RIGHT) {
			moveForward(0.0);
			steerRight();
		} else if (packetType == PacketType.Motion.STOP) {
			stop();
		} else if (packetType == PacketType.Motion.MOVE_LEFT_BACKWARD) {
			moveBackward(speed);
			steerLeft();
		} else if (packetType == PacketType.Motion.MOVE_BACKWARD) {
			moveBackward(speed);
			steerCenter();
		} else if (packetType == PacketType.Motion.MOVE_RIGHT_BACKWARD) {
			moveBackward(speed);
			steerRight();
		}
	}		

	//	
//		public void turn90Right() {
//			logger.debug(TAG, "NewBrightModel: turn90Right");
//			if (!this.driver.getParentControllerService().isOperationExecuted()) {
//				this.driver.getParentControllerService().setOperationExecuted(true);
//				registerCompassListeners();
//				TurnRightThread turnRightThread = new TurnRightThread(90);
//				turnRightThread.startThread();
//			}
//		}
	//
	//	
//		public void turn90Left() {
//			logger.debug(TAG, "NewBrightModel: turn90Left");
//			if (!this.driver.getParentControllerService().isOperationExecuted()) {
//				this.driver.getParentControllerService().setOperationExecuted(true);
//				registerCompassListeners();
//				TurnLeftThread turnLeftThread = new TurnLeftThread(90);
//				turnLeftThread.startThread();
//			}
//		}
	//
	//	
//		public void turnRightDegrees(int degrees) {
//			logger.debug(TAG, "NewBrightModel: turnRightDegrees: " + degrees);
//			if (!this.driver.getParentControllerService().isOperationExecuted()) {
//				this.driver.getParentControllerService().setOperationExecuted(true);
	//
//				// registering compass
//				registerCompassListeners();
	//
//				TurnRightThread turnRightThread = new TurnRightThread(degrees);
//				turnRightThread.startThread();
//			}
//		}
	//
	//	
//		public void turnLeftDegrees(int degrees) {
//			logger.debug(TAG, "NewBrightModel: turnLeftDegrees: " + degrees);
//			if (!this.driver.getParentControllerService().isOperationExecuted()) {
//				this.driver.getParentControllerService().setOperationExecuted(true);
	//
//				registerCompassListeners();
	//
//				TurnLeftThread turnLeftThread = new TurnLeftThread(degrees);
//				turnLeftThread.startThread();
//			}
//		}
	//
//		private boolean isSteerLeft() {
//			if (this.driver.servoVoltage == 1 && this.driver.servoLeft == false && this.driver.servoRight == true) {
//				return true;
//			} else
//				return false;
//		}
	//
//		private boolean isSteerRight() {
//			if (this.driver.servoVoltage == 1 && this.driver.servoLeft == true && this.driver.servoRight == false) {
//				return true;
//			} else
//				return false;
//		}
	//
//		private boolean isSteerCenter() {
//			if (this.driver.servoVoltage == 0 && this.driver.servoLeft == false && this.driver.servoRight == true) {
//				return true;
//			} else
//				return false;
//		}
	//
//		private boolean isMoveForward() {
//			if (this.driver.engineGearBackward == false && this.driver.engineGearForward == true
//					&& this.driver.engineFreq != 0) {
//				return true;
//			} else {
//				return false;
//			}
//		}
	//
//		private boolean isMoveBackward() {
//			if (this.driver.engineGearBackward == true && this.driver.engineGearForward == false
//					&& this.driver.engineFreq != 0) {
//				return true;
//			} else {
//				return false;
//			}
//		}
	//
//		private boolean isStopped() {
//			if (this.driver.engineFreq == 0) {
//				return true;
//			} else {
//				return false;
//			}
//		}
		
//	/**
//	 * Funkcja zwraca nazwę aktualnego kroku na podstawie aktualnych ustawień
//	 * silników.
//	 */
//	public PacketType.IPacketType getCurrentStepName() {
//		if (this.isSteerLeft() && isMoveForward()) {
//			return PacketType.Motion.MOVE_LEFT_FORWARD_REQUEST;
//		} else if (this.isSteerCenter() && isMoveForward()) {
//			return PacketType.Motion.MOVE_FORWARD_REQUEST;
//		} else if (this.isSteerRight() && isMoveForward()) {
//			return PacketType.Motion.MOVE_RIGHT_FORWARD_REQUEST;
//		} else if (this.isSteerLeft() && isStopped()) {
//			return PacketType.Motion.MOVE_LEFT_REQUEST;
//		} else if (this.isSteerCenter() && isStopped()) {
//			return PacketType.Motion.STOP_REQUEST;
//		} else if (this.isSteerRight() && isStopped()) {
//			return PacketType.Motion.MOVE_RIGHT_REQUEST;
//		} else if (this.isSteerLeft() && isMoveBackward()) {
//			return PacketType.Motion.MOVE_LEFT_BACKWARD_REQUEST;
//		} else if (this.isSteerLeft() && isMoveBackward()) {
//			return PacketType.Motion.MOVE_LEFT_BACKWARD_REQUEST;
//		} else if (this.isSteerCenter() && isMoveBackward()) {
//			return PacketType.Motion.MOVE_BACKWARD_REQUEST;
//		} else if (this.isSteerRight() && isMoveBackward()) {
//			return PacketType.Motion.MOVE_RIGHT_BACKWARD_REQUEST;
//		} else {
//			return PacketType.Motion.STOP_REQUEST;
//		}
//	}

//		else if (step.getStepType() == Motion.MOVE_LEFT_90_DEGREES_REQUEST) {
//			this.turn90Left();
//		} else if (step.getStepType() == Motion.MOVE_RIGHT_90_DEGREES_REQUEST) {
//			this.turn90Right();
//		} else if (step.getStepType() == Motion.MOVE_LEFT_DEGREES_REQUEST) {
//			this.turnLeftDegrees(step.getDegrees());
//		} else if (step.getStepType() == Motion.MOVE_RIGHT_DEGREES_REQUEST) {
//			this.turnRightDegrees(step.getDegrees());
//		}
//	}

//	private class TurnLeftThread implements Runnable {
//
//		private Thread blinker = null;
//		private int targetBearing = 0;
//		private int startAzimut = 0;
//		int step = 0;
//		int maxSteps = 20;
//		private int currentAzimut = 0;
//		private int turn = 0;
//		long stepStartTime = 0;
//		long stepStopTime = 0;
//
//		public TurnLeftThread(int turn) {
//			this.turn = turn;
//		}
//
//		public void startThread() {
//			logger.debug(TAG, "Starting TurnLeftThread... degrees: " + turn);
//			blinker = new Thread(this);
//			blinker.start();
//		}
//
//		public void stopThread() {
//			logger.debug(TAG, "Stopping TurnLeftThread...");
//			blinker = null;
//		}
//
//		@Override
//		public void run() {
//			this.blinker = Thread.currentThread();
//
//			// oczekiwanie na pierwsze wczytanie wartości z kompasu - zmiana
//			// dokładności i azymut
//			// różny od 0.0 (bo prawdopodobieństwo takiej wartości jest
//			// praktycznie zerowe na doublach, więc ten sposób może być dobry
//
//			int waitCounter = 0;
//			while ((driver.getParentControllerService().getCompass().getAccelerometerSensorAccuracy() == 0
//					|| driver.getParentControllerService().getCompass().getMagneticFieldSensorAccuracy() == 0 || driver
//					.getParentControllerService().getCompass().getBearing() == 0.0)
//					&& waitCounter < 5) {
//				try {
//					logger.debug(TAG,
//							"looper.getParentControllerService().getCompass().getAccelerometerSensorAccuracy(): "
//									+ driver.getParentControllerService().getCompass().getAccelerometerSensorAccuracy());
//					logger.debug(TAG,
//							"looper.getParentControllerService().getCompass().getMagneticFieldSensorAccuracy(): "
//									+ driver.getParentControllerService().getCompass().getMagneticFieldSensorAccuracy());
//					logger.debug(TAG, "looper.getParentControllerService().getCompass().getBearing()"
//							+ driver.getParentControllerService().getCompass().getBearing());
//					Thread.sleep(1000);
//					waitCounter++;
//				} catch (InterruptedException e) {
//					logger.error(TAG, e);
//				}
//				logger.debug(TAG, "NewBrightModel: waiting for registering sensors...");
//			}
//			if ((driver.getParentControllerService().getCompass().getAccelerometerSensorAccuracy() == 0 || driver
//					.getParentControllerService().getCompass().getBearing() == 0.0)) {
//				logger.debug(TAG,
//						"NewBrightModel: turn left thread: sensors cannot be initialized. Step cannot be executed!");
//				driver.getParentControllerService().sendStepExecutionErrorBroadcast(
//						(Motion) Step.getTakenStep(Motion.MOVE_LEFT_DEGREES));
//				driver.getParentControllerService().setOperationExecuted(false);
//				return;
//			}
//
//			startAzimut = (int) driver.getParentControllerService().getCompass().getBearing();
//			logger.debug(TAG, "startAzimut turn left: " + startAzimut);
//			currentAzimut = (int) driver.getParentControllerService().getCompass().getBearing();
//			logger.debug(TAG, "turn turn left: " + turn);
//
//			// nowa wersja wyznaczania pozycji
//			if ((startAzimut - turn) <= 0) {
//				targetBearing = 360 + (startAzimut - turn);
//			} else {
//				targetBearing = startAzimut - turn;
//			}
//			logger.debug(TAG, "target azimut turn left: " + targetBearing);
//
//			boolean nonZero = false;
//			if ((startAzimut - targetBearing) > 0) {
//				nonZero = true;
//				logger.debug(TAG, "brak przejścia przez zero");
//			}
//
//			while (blinker == Thread.currentThread()) {
//				if (nonZero) {
//					stepStartTime = System.currentTimeMillis();
//					while ((currentAzimut >= targetBearing || currentAzimut >= startAzimut) && step <= maxSteps) {
//						try {
//							Thread.sleep(100);
//							// logTimestamp(logger, TAG);
//							// do tyłu w prawo
//							steerRight();
//							moveBackward(EnginesControllerService.getSpeed());
//							Thread.sleep(driver.getParentControllerService().getStepDuration());
//							// logTimestamp(logger, TAG);
//							stop();
//							Thread.sleep(100);
//							// logTimestamp(logger, TAG);
//							steerLeft();
//							moveForward(EnginesControllerService.getSpeed());
//							Thread.sleep(driver.getParentControllerService().getStepDuration());
//							// logTimestamp(logger, TAG);
//							stop();
//							// oczekiwanie na ustabilizowanie kompasu
//							Thread.sleep(STEPS_DELAY);
//							// logTimestamp(logger, TAG);
//							currentAzimut = (int) driver.getParentControllerService().getCompass().getBearing();
//
//							logger.debug(TAG, "NewBrightModel: turnleft: step: " + step + "(max_steps=" + maxSteps
//									+ ");current azimut after loop: " + currentAzimut);
//							step++;
//						} catch (InterruptedException e) {
//							logger.error(TAG, e);
//						}
//					}
//					stepStopTime = System.currentTimeMillis();
//				} else {
//					stepStartTime = System.currentTimeMillis();
//					while ((currentAzimut <= (startAzimut + 5) || (currentAzimut >= targetBearing)) && step <= maxSteps) {
//						try {
//							Thread.sleep(100);
//							// logTimestamp(logger, TAG);
//							// do tyłu w prawo
//							steerRight();
//							moveBackward(EnginesControllerService.getSpeed());
//							Thread.sleep(driver.getParentControllerService().getStepDuration());
//							// logTimestamp(logger, TAG);
//							stop();
//							Thread.sleep(500);
//							// logTimestamp(logger, TAG);
//							steerLeft();
//							moveForward(EnginesControllerService.getSpeed());
//							Thread.sleep(driver.getParentControllerService().getStepDuration());
//							// logTimestamp(logger, TAG);
//							stop();
//							Thread.sleep(STEPS_DELAY);
//							// logTimestamp(logger, TAG);
//							currentAzimut = (int) driver.getParentControllerService().getCompass().getBearing();
//
//							logger.debug(TAG, "NewBrightModel: turnLeft: step: " + step
//									+ ";current azimut after loop: " + currentAzimut);
//							step++;
//						} catch (InterruptedException e) {
//							logger.error(TAG, e);
//						}
//					}
//					stepStopTime = System.currentTimeMillis();
//				}
//
//				logger.debug(TAG, "final position after turn left (" + turn + ": "
//						+ (int) driver.getParentControllerService().getCompass().getBearing());
//				driver.getParentControllerService().getCompass().unregisterListeners();
//
//				driver.getParentControllerService().setOperationExecuted(false);
//
//				if (driver.getParentControllerService().isSendStepExecutedPacket()) {
//					logger.debug(TAG, "NewBrightModel: broadcasting step executed: " + Motion.MOVE_LEFT_DEGREES);
//					driver.getParentControllerService().sendStepExecutedBroadcast(
//							(Motion) Step.getTakenStep(Motion.MOVE_LEFT_DEGREES_REQUEST), stepStopTime - stepStartTime,
//							EnginesControllerService.getSpeed());
//				}
//
//				unregisterCompassListeners();
//				return;
//			}
//		}
//	}
//
//	private class TurnRightThread implements Runnable {
//
//		private Thread blinker = null;
//		private int targetBearing = 0;
//		private int startAzimut = 0;
//		int step = 0;
//		int maxSteps = 20;
//		private int currentAzimut = 0;
//		private int turn = 0;
//		long stepStartTime = 0;
//		long stepStopTime = 0;
//
//		public TurnRightThread(int turn) {
//			this.turn = turn;
//		}
//
//		public void startThread() {
//			logger.debug(TAG, "Starting TurnRightThread... degrees: " + turn);
//			blinker = new Thread(this);
//			blinker.start();
//		}
//
//		public void stopThread() {
//			logger.debug(TAG, "Stopping TurnLeftThread...");
//			blinker = null;
//		}
//
//		@Override
//		public void run() {
//			this.blinker = Thread.currentThread();
//
//			// oczekiwanie na pierwsze wczytanie wartości z kompasu - zmiana
//			// dokładności i azymut
//			// różny od 0.0 (bo prawdopodobieństwo takiej wartości jest
//			// praktycznie zerowe na doublach, więc ten sposób może być dobry
//			int waitCounter = 0;
//			while ((driver.getParentControllerService().getCompass().getAccelerometerSensorAccuracy() == 0 || driver
//					.getParentControllerService().getCompass().getBearing() == 0.0)
//					&& waitCounter < 5) {
//				try {
//					Thread.sleep(1000);
//					waitCounter++;
//				} catch (InterruptedException e) {
//					logger.error(TAG, e);
//				}
//				logger.debug(TAG, "NewBrightModel: waiting for registering sensors...");
//			}
//
//			if ((driver.getParentControllerService().getCompass().getAccelerometerSensorAccuracy() == 0 || driver
//					.getParentControllerService().getCompass().getBearing() == 0.0)) {
//				logger.debug(TAG,
//						"NewBrightModel: turn left thread: sensors cannot be initialized. Step cannot be executed!");
//				driver.getParentControllerService().sendStepExecutionErrorBroadcast(
//						(Motion) Step.getTakenStep(Motion.MOVE_RIGHT_DEGREES));
//				driver.getParentControllerService().setOperationExecuted(false);
//				return;
//			}
//
//			startAzimut = (int) driver.getParentControllerService().getCompass().getBearing();
//			logger.debug(TAG, "startAzimut turn right: " + startAzimut);
//			currentAzimut = (int) driver.getParentControllerService().getCompass().getBearing();
//			logger.debug(TAG, "turn right: " + turn);
//
//			// targetBearing = (startAzimut - turn) % 360;
//			// if (targetBearing < 0)
//			// targetBearing = 360 + targetBearing;
//
//			// new version
//			targetBearing = (startAzimut + turn) % 360;
//			logger.debug(TAG, "target azimut turn right: " + targetBearing);
//
//			boolean nonZero = false;
//			if ((targetBearing - startAzimut) >= 0) {
//				nonZero = true;
//				logger.debug(TAG, "NewBrightModel: turnRight: brak przejścia przez zero");
//			}
//
//			while (blinker == Thread.currentThread()) {
//				if (nonZero) {
//					// warunek brzegowy
//					if (targetBearing == 0)
//						targetBearing = 359;
//					/*
//					 * Dwa przypadki: - currentAzimut <= targetBearing - obrot
//					 * zgodnie z ruchem wskazowek zegara do uzyskania celu
//					 * 
//					 * currentAzimut <= startAzimut - błąd - np. nieprawidłowe
//					 * wskazanie; lub niepotrzebne przejście przez zero
//					 */
//					stepStartTime = System.currentTimeMillis();
//					while ((currentAzimut <= targetBearing || currentAzimut <= startAzimut) && step <= maxSteps) {
//						try {
//							Thread.sleep(100);
//							// logTimestamp(logger, TAG);
//							// do tyłu w prawo
//							steerLeft();
//							moveBackward(EnginesControllerService.getSpeed());
//							Thread.sleep(driver.getParentControllerService().getStepDuration());
//							// logTimestamp(logger, TAG);
//							stop();
//							Thread.sleep(100);
//							// logTimestamp(logger, TAG);
//							steerRight();
//							moveForward(EnginesControllerService.getSpeed());
//							Thread.sleep(driver.getParentControllerService().getStepDuration());
//							// logTimestamp(logger, TAG);
//							stop();
//							// oczekiwanie na ustabilizowanie kompasu
//							Thread.sleep(STEPS_DELAY);
//							// logTimestamp(logger, TAG);
//							currentAzimut = (int) driver.getParentControllerService().getCompass().getBearing();
//
//							logger.debug(TAG, "NewBrightModel: turnRight: step: " + step + "(max_steps=" + maxSteps
//									+ ");current azimut after loop: " + currentAzimut);
//							step++;
//						} catch (InterruptedException e) {
//							logger.error(TAG, e);
//						}
//					}
//					stepStopTime = System.currentTimeMillis();
//				} else {
//					stepStartTime = System.currentTimeMillis();
//					while ((currentAzimut >= (startAzimut - 5) || (currentAzimut <= targetBearing)) && step <= maxSteps) {
//						try {
//							Thread.sleep(100);
//							// logTimestamp(logger, TAG);
//							// do tyłu w prawo
//							steerLeft();
//							moveBackward(EnginesControllerService.getSpeed());
//							Thread.sleep(driver.getParentControllerService().getStepDuration());
//							// logTimestamp(logger, TAG);
//							stop();
//							Thread.sleep(500);
//							steerRight();
//							moveForward(EnginesControllerService.getSpeed());
//							Thread.sleep(driver.getParentControllerService().getStepDuration());
//							stop();
//							Thread.sleep(STEPS_DELAY);
//							currentAzimut = (int) driver.getParentControllerService().getCompass().getBearing();
//
//							logger.debug(TAG, "NewBrightModel: turnRight: step: " + step
//									+ ";current azimut after loop: " + currentAzimut);
//							step++;
//						} catch (InterruptedException e) {
//							logger.error(TAG, e);
//						}
//					}
//					stepStopTime = System.currentTimeMillis();
//				}
//
//				logger.debug(TAG, "final position after turn left (" + turn + ": "
//						+ (int) driver.getParentControllerService().getCompass().getBearing());
//				driver.getParentControllerService().getCompass().unregisterListeners();
//
//				driver.getParentControllerService().setOperationExecuted(false);
//				unregisterCompassListeners();
//
//				// wysyłanie pakietu z informacją o wykonanym kroku
//				if (driver.getParentControllerService().isSendStepExecutedPacket()) {
//					logger.debug(TAG, "NewBrightModel: broadcasting step executed: " + Motion.MOVE_RIGHT_DEGREES);
//					driver.getParentControllerService().sendStepExecutedBroadcast(
//							(Motion) Step.getTakenStep(Motion.MOVE_RIGHT_DEGREES_REQUEST),
//							stepStopTime - stepStartTime, EnginesControllerService.getSpeed());
//				}
//
//				return;
//			}
//		}
//	}
//
	
}
