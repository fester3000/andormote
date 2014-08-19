package andro_mote.api;

import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.MotionModes;

/**
 * Interfejs opisujący przyjęte funkcjonalności jakie powinna posiadać
 * implementacja API dla połączenia telefonu z platformą mobilną jaką jest np.
 * realizowany projekt AndroMote.
 * 
 * @author Maciej Gzik
 * 
 */
public interface IAndroMoteMobilePlatformApi {
	
	public boolean setPlatformSpeed(double speed) throws MobilePlatformException, UnsupportedOperationException;

	public boolean setMotionMode(MotionModes motionMode) throws MobilePlatformException, UnsupportedOperationException;

	public boolean setStepDuration(int stepDuration) throws MobilePlatformException, UnsupportedOperationException;

	public boolean stopMobilePlatform() throws MobilePlatformException, UnsupportedOperationException;

//	public boolean moveLeftForward() throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean moveForward() throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean moveRightForward() throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean moveLeft() throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean moveRight() throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean moveLeftBackward() throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean moveBackward() throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean moveRightBackward() throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean turn90RightDegrees() throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean turn90LeftDegrees() throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean turnRightDegrees(int bearing) throws MobilePlatformException, UnsupportedOperationException;
//
//	public boolean turnLeftDegrees(int bearing) throws MobilePlatformException, UnsupportedOperationException;
}
