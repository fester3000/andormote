package pl.fester3k.androcode.datatypes;

import andro_mote.commons.PacketType.Motion;

/**
 * Contains enums with action parameter names
 */
public class ActionParams {
	public static final String ACTION = "ACTION";
	public static final int ACTION_PICTURE = 1;
	public static final int ACTION_FLASHLIGHT = 2;
	public static final int ACTION_VIDEO = 3;
	public static final int ACTION_AUDIO = 4;
	
	/**
	 * This class cannot be instantiated!!
	 */
	private ActionParams() {}
	public interface ActionParam {}

	public enum Others implements ActionParam {
		ACTIVITY_MODE, ACTION_ID, GET;
	}
	
	/**
	 * 
	 * @author Sebastian Luczak
	 *
	 */
	public enum PICTURE implements ActionParam {
		/**
		 * Tryb flasha w aparacie
		 * ON, OFF, AUTO, STILL
		 */ 
		FLASH,
		
		/**
		 * Wyjściowa rozdzielczość zdjęcia
		 * LOW, HIGH
		 */ 
		RESOLUTION,
		
		/**
		 * Jakość wyjściowego pliku JPEG
		 * wartość całkowita od 0 do 100
		 */ 
		QUALITY;
	}
	
	public enum VIDEO implements ActionParam {
		/**
		 * Stan nagrywania
		 * ON, OFF
		 */
		MODE,
		/**
		 * Tryb lampy doświetlającej przy nagrywaniu
		 * ON, OFF
		 */ 
		FLASH,
		/**
		 * Jakość nagrywanego obrazu
		 * LOW, HIGH
		 */ 
		QUALITY;
	}
	
	public enum AUDIO_IN implements ActionParam {
		/**
		 * Stan nagrywania
		 * ON, OFF 
		 */
		MODE;
	}
	
	public enum FLASHLIGHT implements ActionParam {
		/**
		 * Stan latarki
		 * ON, OFF
		 */
		MODE;
	}
	
	public enum SMS implements ActionParam {
		/**
		 * Numer telefonu odbiorcy wiadomości
		 */
		TO,
		/**
		 * Treść wiadomości
		 */
		TEXT;
	}
	
	public enum EMAIL implements ActionParam {
		/**
		 * Adres(y) odbiorców wiadomości e-mail
		 * Adresy e-mail oddzielane przecinkami:
		 * adres1@domena.tld, adres2@domena.tld
		 */
		TO,
		/**
		 * Treść wiadomości
		 */
		TEXT,
		/**
		 * Temat wiadomości
		 */
		SUBJECT;
	}
	public enum WIFI implements ActionParam {
		/**
		 * Nazwa dodawanej sieci
		 */
		SSID,
		/**
		 * Tryb {@link WIFI_MODE} zabezpieczenia sieci WIFI
		 */
		MODE,
		/**
		 * Hasło do sieci
		 */
		PASS;
	}
	public enum WIFI_MODE implements ActionParam {
		OPEN,
		WEPx,
		WPAx;
	}
//	public enum AUDIO_OUT {
//		/**
//		 * Ścieżka do odtwarzanego pliku
//		 */
//		PATH;
//	}
	public enum TTS implements ActionParam {
		/**
		 * Tresć do odczytania przez syntyzator mowy
		 */
		TEXT;
	}
	/**
	 * Parametry akcji - Sterowanie ręczne
	 * @author Sebastian Luczak
	 *
	 */
	public enum RIDE_MANUAL implements ActionParam {
		/**
		 * Prędkość/prędkość lewej gąsienicy (jeśli dotyczy)
		 */
		SPEED,
		/**
		 * Prędkość prawej gąsienicy (jeśli dotyczy)
		 */
		SPEED_B,
		/**
		 * Tryb ruchu zgodny z {@link Motion}
		 */
		MOTION,
	}
	
	/**
	 * parametry akcji - Konfiguracja platformy jezdnej
	 * @author Sebastian Luczak
	 *
	 */
	public enum RIDE_SETUP implements ActionParam {
		/**
		 * Ruch ciągły lub krokowy
		 * CONT, STEP
		 */
		MOTION_MODE,
		
		/**
		 * Czas trwania kroku w przypadku ruchu ciągłego
		 */
		STEP_DURATION;
	}
	
	/**
	 * parametry akcji - Obieranie kursu
	 * @author Sebastian Luczak
	 *
	 */
	public enum RIDE_BEARING implements ActionParam {
		/**
		 * Kierunek geograficzny do obrania przez platformę
		 * Podawany w stopniach 0-359 
		 */
		BEARING,
		
		/**
		 * Określa czy podana wartość BEARING to azymut, czy przesunięcie względem obecnego kursu 
		 */
		IS_OFFSET;
	}
	public enum RIDE_GPS implements ActionParam {
		LAT,
		LONG,
		SPEED;
	}
}