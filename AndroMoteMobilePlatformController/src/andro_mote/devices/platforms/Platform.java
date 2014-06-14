package andro_mote.devices.platforms;

import andro_mote.commons.IntentsIdentifiers;
import andro_mote.commons.PacketType;
import andro_mote.commons.exceptions.SensorRegisteringError;
import andro_mote.stepper.Step;

/**
 * Intefejs opisujący fukcjonalności modelu wykorzystywanego w projekcie
 * AndroMote.
 * 
 * @author Maciej Gzik
 * 
 */
public interface Platform {

	/**
	 * Skręt w lewo.
	 */
	public void steerLeft();

	public void steerRight();

	public void steerCenter();

	public void moveForward(double speed);

	public void moveBackward(double speed);

	public void stop();

	/**
	 * Skręt w prawo 90* w prawo
	 */
	public void turn90Right();

	/**
	 * Skręt 95* w lewo
	 * 
	 * @throws SensorRegisteringError
	 */
	public void turn90Left();

	/**
	 * Skręt o okresloną liczbę stopni w prawo. Obrót może być realizowany z
	 * wykorzystaniem żyroskopu lub GPS.
	 * 
	 * @param degrees
	 *            liczba stopni obrotu
	 */
	public void turnRightDegrees(int degrees);

	/**
	 * Skręt o okresloną liczbę stopni w lewo. Obrót może być realizowany z
	 * wykorzystaniem żyroskopu lub GPS.
	 * 
	 * @param degrees
	 *            liczba stopni obrotu
	 */
	public void turnLeftDegrees(int degrees);

	public PacketType.IPacketType getCurrentStepName();

	/**
	 * Funkcja interpretująca kolejny krok z kolejki i uruchamiająca odpowidni
	 * wątek modyfikujący ustawienia pinów IOIO.
	 * 
	 * @param step
	 *            kolejny pakiet z kolejki kroków.
	 */
	public void takeStep(Step step);

	/**
	 * Wątek wykonujący krok mobilnego węzła. W tym czasie blokowane są
	 * pozostałe akcje węzła. Wymagana jest implementacja dla każdego modelu i
	 * każdego możliwego dla niego rodzaju kroku. Po zakończeniu wykonywania
	 * kroku typ wykonanego kroku powinien zostać wysłany w Intent typu:
	 * {@link IntentsIdentifiers.ACTION_ENGINE_STEP}
	 * 
	 * @author Maciej Gzik
	 * 
	 */
	public class TakeStepThread implements Runnable {
		@Override
		public void run() {
		}
	}
}
