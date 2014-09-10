package andro_mote.devices.generics;

import andro_mote.commons.Packet;
import andro_mote.stepper.Step;

/**
 * Intefejs opisujący fukcjonalności modelu wykorzystywanego w projekcie
 * AndroMote.
 * 
 * @author Maciej Gzik
 * 
 */
public interface ElectronicDeviceInterface {
	public void interpretPacket(Packet inputPacket);
	
	/**
	 * Funkcja interpretująca kolejny krok z kolejki i uruchamiająca odpowidni
	 * wątek modyfikujący ustawienia pinów IOIO.
	 * 
	 * @param step
	 *            kolejny pakiet z kolejki kroków.
	 */
	public void takeStep(Step step);

	void stop();
}
