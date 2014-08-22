package andro_mote.commons;

import andro_mote.api.exceptions.UnknownDeviceException;

public class DeviceDefinitions {

	/**
	 * Identyfikatory urządzeń obsługiwanych przez API w formie implementowanej
	 * przez projekt AndroMote. W przypadku uruchomienia startu komunikacji z
	 * platformą, której nazwa nie została zdefiniowana następuje rzucenie
	 * wyjątku {@link UnknownDeviceException}
	 * 
	 * @author Maciej Gzik
	 * 
	 */
	public enum MobilePlatformType {
		/**
		 * Identyfikator platformy mobilnej wykorzystanej w pierwszej wersji
		 * projektu AndroMote: New Bright Model: http://www.newbright.com/product/view/id/16
		 */
		NewBrightModel,
		/**
		 * Platforma Rover5 produkcji DAGU
		 */
		ROVER5TwoEngines;
	}
	
	public enum MotorDriverType {
		PololuTwoEngines,
		RNVN2;
	}
}
