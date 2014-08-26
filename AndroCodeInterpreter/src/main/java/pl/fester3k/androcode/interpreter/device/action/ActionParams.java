package pl.fester3k.androcode.interpreter.device.action;

/**
 * Contains enums with action parameter names
 */
public class ActionParams {
	public static final int CAMERA_PICTURE = 0;
	public static final int CAMERA_FLASHLIGHT = 1;
	public static final int CAMERA_VIDEO = 2;
	/**
	 * This class cannot be instantiated!!
	 */
	private ActionParams() {}
	public enum Others {
		ACTIVITY_MODE, ACTION_ID;
	}	
	public enum Camera {
		FOCUS, 
		FLASH, 
		SIZE, 
		JPEG_QUALITY;
	}
	
	public enum FLASHLIGHT {
		MODE;
	}
}