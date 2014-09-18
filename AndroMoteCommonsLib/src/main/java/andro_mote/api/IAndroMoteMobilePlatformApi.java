package andro_mote.api;

import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.MotionMode;

/**
 * Interfejs opisujący przyjęte funkcjonalności jakie powinna posiadać
 * implementacja API dla połączenia telefonu z platformą mobilną jaką jest np.
 * realizowany projekt AndroMote.
 * 
 * @author Maciej Gzik
 * @author Sebastian Łuczak
 * 
 */
public interface IAndroMoteMobilePlatformApi {
	public boolean setMotionMode(MotionMode motionMode) throws MobilePlatformException, UnsupportedOperationException;

	public boolean setStepDuration(int stepDuration) throws MobilePlatformException, UnsupportedOperationException;

	public boolean stopMobilePlatform() throws MobilePlatformException, UnsupportedOperationException;
}
