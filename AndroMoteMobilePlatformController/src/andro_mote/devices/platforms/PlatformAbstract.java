package andro_mote.devices.platforms;

import andro_mote.commons.PacketType;
import andro_mote.compass.Compass;
import andro_mote.devices.motorDrivers.MotorDriver;
import andro_mote.ioio_service.EngineControllerLooper;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;
import android.hardware.SensorManager;
import android.text.format.Time;

public class PlatformAbstract implements Platform {

	private static final String TAG = PlatformAbstract.class.toString();

	protected MotorDriver driver;

	private AndroMoteLogger logger = new AndroMoteLogger(PlatformAbstract.class);

	public PlatformAbstract(MotorDriver driver) {
		this.driver = driver;
	}

	@Override
	public void steerLeft() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void steerRight() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void steerCenter() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void moveForward(double speed) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void moveBackward(double speed) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void turn90Right() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void turn90Left() {
		throw new UnsupportedOperationException();
	}

	@Override
	public PacketType.IPacketType getCurrentStepName() {
		return null;
	}

	@Override
	public void turnRightDegrees(int degrees) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void turnLeftDegrees(int degrees) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void takeStep(Step step) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Funkcja obliczająca o ile stopni i jakim kierunku należy skręcić, aby
	 * osiągnąć kierunek destinationBearing mając azymut początkowy
	 * startBearing. Skręt maksymalnie o 180 stopni. W przypadku skrętu w lewo
	 * zwracana wartość jest ujmena (-180 - 0) a dla skrętu prawo - dodatnie (0
	 * - 180).
	 * 
	 * @param startBearing
	 *            poczatkowa pozycja węzła
	 * @param destinationBearing
	 *            docelowy kierunek węzła
	 * @return liczba stopni o jakie należy zmienić kierunek początkowy aby
	 *         osiągnąć kierunek docelowy
	 */
	public static int calculateTurn(int startBearing, int destinationBearing) {
		// 1
		if (startBearing >= 180 && startBearing <= 360 && destinationBearing >= 180 && destinationBearing <= 360) {
			return -(startBearing - destinationBearing);
		}
		// 2
		else if (startBearing <= 180 && startBearing >= 0 && destinationBearing >= 180 && destinationBearing <= 360) {
			if ((destinationBearing - startBearing) <= 180) {
				return (destinationBearing - startBearing);
			} else {
				return -((360 - destinationBearing) + startBearing);
			}
		}
		// 3
		else if (startBearing >= 180 && startBearing <= 360 && destinationBearing >= 0 && destinationBearing <= 180) {
			if ((startBearing - destinationBearing) <= 180) {
				return -(startBearing - destinationBearing);
			} else {
				return ((360 - startBearing) + destinationBearing);
			}
		}
		// 4
		else if (startBearing <= 180 && startBearing >= 0 && destinationBearing <= 180 && destinationBearing >= 0) {
			return -(startBearing - destinationBearing);
		} else {
			return 0;
		}
	}

	/**
	 * Rejestracja listenerów kompasu - rejestracja jest realizowana na czas
	 * wykonywania skrętu lub innej wymagającej pomiaru położenia czynności.
	 */
	protected void registerCompassListeners() {
		if (driver != null && driver.getParentControllerService().getCompass() != null) {
			logger.debug(TAG, "Model: registering compass");
			(new Thread(new RegisterCompassListenersThread(driver.getParentControllerService().getCompass()))).start();
		} else {
			logger.debug(TAG, "Model: compass cannot be registered");
		}
	}

	/**
	 * Wyłaczenie kompasu po skończeniu wykonywania kroku.
	 */
	protected void unregisterCompassListeners() {
		if (driver != null && driver.getParentControllerService().getCompass() != null) {
			logger.debug(TAG, "Model: unregistering compass");
			(new Thread(new UnregisterCompassListenersThread(driver.getParentControllerService().getCompass())))
					.start();
			// looper.getParentControllerService().getCompass().unregisterListeners();
		} else {
			logger.debug(TAG, "Model: compass cannot be unregistered");
		}
	}

	/**
	 * Logowanie aktualnego czasu na podany logger.
	 * 
	 * @param logger
	 * @param TAG
	 */
	protected void logTimestamp(AndroMoteLogger logger, String TAG) {
		Time now = new Time();
		now.setToNow();
		logger.debug(TAG, now.format("%d.%m.%Y %H:%M:%S:%f"));
	}

	/**
	 * Wątek odpowiedzialny za zlecenie rejestracji listenerów w kompasie
	 * 
	 * @author Maciej Gzik
	 * 
	 */
	protected class RegisterCompassListenersThread implements Runnable {

		private Compass compass = null;

		public RegisterCompassListenersThread(Compass compass) {
			this.compass = compass;
		}

		@Override
		public void run() {
			logger.debug(TAG, "RegisterCompassListenersThread: registering compass");
			compass.registerListeners(SensorManager.SENSOR_DELAY_GAME);
		}
	}

	/**
	 * Wątek odpowiedzialny za zlecenie wyrejestrowania listenerów kompasu.
	 */
	protected class UnregisterCompassListenersThread implements Runnable {

		private Compass compass = null;

		public UnregisterCompassListenersThread(Compass compass) {
			this.compass = compass;
		}

		@Override
		public void run() {
			logger.debug(TAG, "RegisterCompassListenersThread: registering compass");
			compass.unregisterListeners();
		}
	}
}
