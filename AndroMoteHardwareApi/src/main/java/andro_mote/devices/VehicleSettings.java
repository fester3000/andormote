package andro_mote.devices;

import andro_mote.commons.MotionMode;
import andro_mote.ioio_service.IOIOLooperManagerService;
import andro_mote.logger.AndroMoteLogger;

public class VehicleSettings {
	private static final String TAG = VehicleSettings.class.getName();
	private AndroMoteLogger log = new AndroMoteLogger(VehicleSettings.class);
		/**
		 * Maksymalny czas trwania kroku w trybie MOTION_MODE_STEPPER
		 */
		private final int MAX_STEP_DURATION; //2000

		/**
		 * Minimalna prędkość silników. Zabezpieczenie przed małą mocą.
		 */
		private final double MIN_SPEED; // 0.3

		/**
		 * Aktualna prędkość silnika.
		 */
		private double currentSpeed;
		
		/**
		 * Aktualna prędkość drugiego silnika.
		 */
		private double currentSpeed_B;

		/**
		 * Czas oczekiwania pomiędzy kolejnymi krokami.
		 */
		private long pauseTimeBetweenSteps;

		/**
		 * Czas trwania pojedynczego kroku
		 */
		public long stepDuration;

		/**
		 * Tryb ruchu pojazdu.
		 * 
		 * @see IOIOLooperManagerService.MOVING_MODE_CONTINUOUS
		 * @see IOIOLooperManagerService.MOVING_MODE_STEPPER
		 */
		private MotionMode motionMode = MotionMode.MOTION_MODE_CONTINUOUS;

		private VehicleSettings(Builder builder) {
			this.MAX_STEP_DURATION = builder.MAX_STEP_DURATION;
			this.MIN_SPEED = builder.MIN_SPEED;
			this.currentSpeed = builder.currentSpeed;
			this.currentSpeed_B = builder.currentSpeed_B;
			this.pauseTimeBetweenSteps = builder.pauseTimeBetweenSteps;
			this.stepDuration = builder.stepDuration;
			this.motionMode = builder.motionMode;
		}
		
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
			if (speed > 1 || speed < MIN_SPEED) {
				log.debug(TAG, "EnginesControllerService: niedopuszczalna nowa wartość prędkości silników: " + speed
						+ ". Zmiana nie została dokonana!.");
			} else {
				log.debug(TAG, "EnginesControllerService: zmiana prędkości silników na: " + speed);
				currentSpeed_B = speed;
			}
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
		
		public static class Builder {
			private final int MAX_STEP_DURATION;
			private final double MIN_SPEED;
			
			private double currentSpeed = 0.6;
			private double currentSpeed_B = 0.6;
			private long pauseTimeBetweenSteps = 1000;
			private long stepDuration = 600;
			private MotionMode motionMode = MotionMode.MOTION_MODE_CONTINUOUS;
			
			public Builder(int maxStepDuration, double minSpeed) {
				this.MAX_STEP_DURATION = maxStepDuration;
				this.MIN_SPEED = minSpeed;
			}
			
			public Builder currentSpeed(double currentSpeed) {
				this.currentSpeed = currentSpeed;
				return this;
			}
			
			public Builder currentSpeed_B(double currentSpeed_B) {
				this.currentSpeed_B = currentSpeed_B;
				return this;
			}
			
			public Builder pauseTimeBetweenSteps(long pauseTimeBetweenSteps) {
				this.pauseTimeBetweenSteps = pauseTimeBetweenSteps;
				return this;
			}
			
			public Builder stepDuration(long stepDuration) {
				this.stepDuration = stepDuration;
				return this;
			}
			
			public Builder motionMode(MotionMode motionMode) {
				this.motionMode = motionMode;
				return this;
			}
			
			public VehicleSettings build() {
				return new VehicleSettings(this);
			}
		}
}