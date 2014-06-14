package andro_mote.api;

import andro_mote.api.exceptions.BroadcastReceiverClientNotSetException;
import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.MotionModes;
import andro_mote.commons.Packet;
import android.app.Application;

/**
 * Abstrakcyjna implementacja API sterowania urządzeniem zewnętrznym będącym
 * platformami mobilnymi. Do implementacji konkretnego rozwiązania należy
 * rozwinąć tę klasę w klasie implementującej. Klasa poza sterowaniem platformą
 * mobilną jest odpowiedzialna za odbieranie wiadomości wysyłanych przez
 * platformę mobilną poprzez imeplementację interfejsu
 * {@link IAndroMoteDeviceMessageReceiver}.
 * 
 * @author Maciej Gzik
 * 
 */
public abstract class AndroMoteMobilePlatformApiAbstract implements IAndroMoteApi, IAndroMoteMobilePlatformApi,
		IAndroMoteDeviceReceiverClient, IAndroMoteDeviceDataProvider {

	protected boolean isConnectionActive = false;

	protected Application application = null;
	protected AndroMoteDeviceMessageReceiverImpl mobilePlatformMessageReceiver = null;

	public AndroMoteMobilePlatformApiAbstract(Application application) {
		if (application != null) {
			this.application = application;
		}
		mobilePlatformMessageReceiver = new AndroMoteDeviceMessageReceiverImpl(this, application);
		try {
			mobilePlatformMessageReceiver.startListeningMessages();
		} catch (BroadcastReceiverClientNotSetException e) {

		}
	}

	@Override
	public boolean startCommunicationWithDevice(String deviceName) throws MobilePlatformException,
			UnknownDeviceException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean stopCommunicationWithDevice() throws MobilePlatformException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean checkIfConnectionIsActive() throws MobilePlatformException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean setPlatformSpeed(double speed) throws MobilePlatformException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean setMotionMode(MotionModes motionMode) throws MobilePlatformException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean setStepDuration(int stepDuration) throws MobilePlatformException {
		throw new UnsupportedOperationException();
	}

//	@Override
//	public boolean turn90RightDegrees() throws MobilePlatformException {
//		throw new UnsupportedOperationException();
//	}
//
//	@Override
//	public boolean turn90LeftDegrees() throws MobilePlatformException {
//		throw new UnsupportedOperationException();
//	}
//
//	@Override
//	public boolean turnRightDegrees(int bearing) throws MobilePlatformException {
//		throw new UnsupportedOperationException();
//	}
//
//	@Override
//	public boolean turnLeftDegrees(int bearing) throws MobilePlatformException {
//		throw new UnsupportedOperationException();
//	}

	public Application getApplication() {
		return application;
	}

	@Override
	public void deviceMessageReceived(Packet pack) throws MobilePlatformException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean sendMessageToDevice(IPacket pack) throws MobilePlatformException, UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public IPacket getData(IPacket dataDescriptorPacket) {
		throw new UnsupportedOperationException();
	}

}
