package andro_mote.devices.andromote_v2;

import andro_mote.commons.MotionMode;
import andro_mote.devices.DeviceSettings;
import andro_mote.ioio_service.IOIOLooperManagerService;
import andro_mote.logger.AndroMoteLogger;

public class AndroMote2Settings implements DeviceSettings {
	private static final String TAG = AndroMote2Settings.class.getName();
	private AndroMoteLogger log = new AndroMoteLogger(AndroMote2Settings.class);
		/**
		 * Maksymalny czas trwania kroku w trybie MOTION_MODE_STEPPER
		 */
		private final int MAX_STEP_DURATION = 2000;

		/**
		 * Minimalna prędkość silników. Zabezpieczenie przed małą mocą.
		 */
		private final double MIN_SPEED = -1.0;

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

		
		/* (non-Javadoc)
		 * @see andro_mote.devices.DeviceSettings#getMotionMode()
		 */
		@Override
		public MotionMode getMotionMode() {
			return motionMode;
		}
		
		/* (non-Javadoc)
		 * @see andro_mote.devices.DeviceSettings#getStepDuration()
		 */
		@Override
		public long getStepDuration() {
			return stepDuration;
		}
		
		@Override
		public long getPauseTimeBetweenSteps() {
			return pauseTimeBetweenSteps;
		}

		/* (non-Javadoc)
		 * @see andro_mote.devices.DeviceSettings#getSpeed()
		 */
		@Override
		public double getSpeed() {
			return currentSpeed;
		}
		
		/* (non-Javadoc)
		 * @see andro_mote.devices.DeviceSettings#getSpeed_B()
		 */
		@Override
		public double getSpeed_B() {
			return currentSpeed_B;
		}

		/* (non-Javadoc)
		 * @see andro_mote.devices.DeviceSettings#setSpeed(double)
		 */
		@Override
		public void setSpeed(double speed) {
			if (speed > 1 || speed < MIN_SPEED) {
				log.debug(TAG, "EnginesControllerService: niedopuszczalna nowa wartość prędkości silników: " + speed
						+ ". Zmiana nie została dokonana!.");
			} else {
				log.debug(TAG, "EnginesControllerService: zmiana prędkości silników na: " + speed);
				currentSpeed = speed;
			}
		}
		
		/* (non-Javadoc)
		 * @see andro_mote.devices.DeviceSettings#setSpeedB(double)
		 */
		@Override
		public void setSpeedB(double speed) {
			if (speed > 1 || speed < MIN_SPEED) {
				log.debug(TAG, "EnginesControllerService: niedopuszczalna nowa wartość prędkości silników: " + speed
						+ ". Zmiana nie została dokonana!.");
			} else {
				log.debug(TAG, "EnginesControllerService: zmiana prędkości silników na: " + speed);
				currentSpeed_B = speed;
			}
		}
		
		
		/* (non-Javadoc)
		 * @see andro_mote.devices.DeviceSettings#setStepDuration(long)
		 */
		@Override
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

		/* (non-Javadoc)
		 * @see andro_mote.devices.DeviceSettings#setMotionMode(andro_mote.commons.MotionMode)
		 */
		@Override
		public void setMotionMode(MotionMode motionMode) {
			this.motionMode = motionMode;
			
		}

		@Override
		public double getMIN_SPEED() {
			return MIN_SPEED;
		}

		public int getMAX_STEP_DURATION() {
			return MAX_STEP_DURATION;
		}
}