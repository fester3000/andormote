package pl.fester3k.androcode.interpreter.device.action;

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
		ACTIVITY_MODE, ACTION_ID;
	}	
	public enum CAMERA {
		MODE,
		FOCUS, 
		FLASH, 
		SIZE, 
		QUALITY;
	}
	
	public enum VIDEO {
		MODE;
	}
	
	public enum AUDIO {
		MODE;
	}
	
	public enum FLASHLIGHT {
		MODE;
	}
}