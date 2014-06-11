package andro_mote.api.exceptions;

public class UnknownDeviceException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnknownDeviceException(String msg) {
		super(msg);
	}
}
