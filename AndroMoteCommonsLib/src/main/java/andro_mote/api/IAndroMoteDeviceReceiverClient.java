package andro_mote.api;

import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.Packet;

/**
 * Interfejs, który musi zostać zaimplementowany przez klasę, będącą klientem
 * dla implementacji odbiornika informacji z urządzenia zewnętrznego
 * {@link IAndroMoteDeviceReceiverClient}
 * 
 * @author Maciej Gzik
 * 
 */
public interface IAndroMoteDeviceReceiverClient {

	/**
	 * Funkcja wywoływana przez odbiornik wiadomości od urządzenia zewnętrznego
	 * po odebraniu z niego wiadomości.
	 * 
	 * @param pack
	 *            Odebrany od urządzenia zewnętrznego pakiet.
	 */
	public void deviceMessageReceived(Packet pack) throws MobilePlatformException;

}
