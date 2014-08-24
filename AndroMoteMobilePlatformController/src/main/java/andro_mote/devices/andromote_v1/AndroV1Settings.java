package andro_mote.devices.andromote_v1;

import andro_mote.commons.MotionMode;
import andro_mote.devices.generics.DeviceSettings;
import andro_mote.logger.AndroMoteLogger;


public enum AndroV1Settings implements DeviceSettings {
	INSTANCE;
	private static final String TAG = DeviceSettings.class.getName();
	private AndroMoteLogger log = new AndroMoteLogger(DeviceSettings.class);
		/**
		 * Maksymalny czas trwania kroku w trybie MOTION_MODE_STEPPER
		 */
		private final int MAX_STEP_DURATION = 2000;

		/**
		 * Minimalna prędkość silników. Zabezpieczenie przed małą mocą.
		 */
		private final double MIN_SPEED = 0.4;

		/**
		 * Aktualna prędkość silnika.
		 */
		private double currentSpeed = 0.6;
		
		/**
		 * Aktualna prędkość drugiego silnika.
		 */
		private double currentSpeed_B = 0.6;

		/**
		 * Czas oczekiwania pomiędzy kolejnymi krokami.
		 */
		private long pauseTimeBetweenSteps = 1000;

		public long stepDuration = 600;

		/**
		 * Tryb ruchu pojazdu.
		 * 
		 * @see EnginesService.MOVING_MODE_CONTINUOUS
		 * @see EnginesService.MOVING_MODE_STEPPER
		 */
		private MotionMode motionMode = MotionMode.MOTION_MODE_CONTINUOUS;

		public MotionMode getMotionMode() {
			return motionMode;
		}
		
		public long getStepDuration() {
			return stepDuration;
		}
		
		public long getPauseTimeBetweenSteps() {
			return pauseTimeBetweenSteps;
		}

		public double getSpeed() {
			return currentSpeed;
		}
		
		public double getSpeed_B() {
			return currentSpeed_B;
		}

		public void setSpeed(double speed) {
			if (speed > 1 || speed < MIN_SPEED) {
				log.debug(TAG, "EnginesControllerService: niedopuszczalna nowa wartość prędkości silników: " + speed
						+ ". Zmiana nie została dokonana!.");
			} else {
				log.debug(TAG, "EnginesControllerService: zmiana prędkości silników na: " + speed);
				currentSpeed = speed;
			}
		}
		
		public void setSpeedB(double speed) {
			throw new UnsupportedOperationException();
		}
		
		
		public void setStepDuration(long stepDuration) {
			if (stepDuration > MAX_STEP_DURATION) {
				log.debug(
						TAG,
						"EnginesControllerService: próba ustawienia większego niż dopuszczalny czasu trwania jednego kroku. Ustawiona wartość: "
								+ MAX_STEP_DURATION);
				this.stepDuration = MAX_STEP_DURATION;
			} else {
				log.debug(TAG, "EnginesControllerService: zmiana czasu trwania kroku. Nowa wartość [ms]: "
						+ stepDuration);
				this.stepDuration = stepDuration;
			}
		}
		

		public void setMotionMode(MotionMode motionMode) {
			this.motionMode = motionMode;
			
		}

		public double getMIN_SPEED() {
			return MIN_SPEED;
		}

		public int getMAX_STEP_DURATION() {
			return MAX_STEP_DURATION;
		}
	}