package andro_mote.api;

import andro_mote.api.exceptions.UnknownDeviceException;

public class DevicesDefinitions {

	/**
	 * Identyfikatory urządzeń obsługiwanych przez API w formie implementowanej
	 * przez projekt AndroMote. W przypadku uruchomienia startu komunikacji z
	 * platformą, której nazwa nie została zdefiniowana następuje rzucenie
	 * wyjątku {@link UnknownDeviceException}
	 * 
	 * @author Maciej Gzik
	 * 
	 */
	public enum MobilePlatforms {
		/**
		 * Identyfikator platformy mobilnej wykorzystanej w pierwszej wersji
		 * projektu AndroMote: New Bright Model: http://www.newbright.com/product/view/id/16
		 */
		NewBrightModel,
		
		/**
		 * Niezaimplementowany sprzętowo model robota własnej produkcji złożeonego z części Pololu: http://www.pololu.com/.
		 */
		PololuTwoEngines,
		
		/**
		 * Platforma Rover5 produkcji DAGU
		 */
		ROVER5TwoEngines;
	}
	
	public enum MotorDrivers {
		PololuTwoEngines,
		RNVN2;
	}
}
