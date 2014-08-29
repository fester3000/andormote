package pl.fester3k.androcode.datatypes;

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
	public enum Others {
		ACTIVITY_MODE, ACTION_ID, GET;
	}	
	public enum CAMERA {
		/**
		 * Flash mode parameter
		 * values: ON, OFF, AUTO, STILL
		 */ 
		FLASH,
		
		/**
		 * Picture resolution
		 * values: LOW, HIGH
		 */ 
		RESOLUTION,
		
		/**
		 * Result JPEG quality
		 * values: integer from 0 to 100
		 */ 
		QUALITY;
	}
	
	public enum VIDEO {
		MODE,
		FLASH,
		QUALITY;
	}
	
	public enum AUDIO_IN {
		MODE;
	}
	
	public enum FLASHLIGHT {
		MODE;
	}
	
	public enum SMS {
		TO,
		TEXT;
	}
	
	public enum EMAIL {
		TO,
		TEXT,
		TOPIC;
	}
	public enum WIFI {
		SSID,
		MODE,
		PASS;
	}
	public enum WIFI_MODE {
		OPEN,
		WEPx,
		WPAx;
	}
	public enum AUDIO_OUT {
		PATH;
	}
	public enum TTS {
		TEXT;
	}
}