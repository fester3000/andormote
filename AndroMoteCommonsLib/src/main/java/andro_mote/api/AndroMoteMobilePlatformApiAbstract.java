package andro_mote.api;

import andro_mote.api.exceptions.BroadcastReceiverClientNotSetException;
import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.MotionMode;
import andro_mote.commons.Packet;
import andro_mote.devices.ElectronicDeviceFactory;
import andro_mote.logger.AndroMoteLogger;
import android.app.Application;

/**
 * Abstrakcyjna implementacja API sterowania urządzeniem zewnętrznym będącym
 * platformami mobilnymi. Do implementacji konkretnego rozwiązania należy
 * rozwinąć tę klasę w klasie implementującej. Klasa poza sterowaniem platformą
 * mobilną jest odpowiedzialna za odbieranie wiadomości wysyłanych przez
 * platformę mobilną poprzez imeplementację interfejsu
 * {@link IMessagesFromDeviceReceiver}.
 * 
 * @author Maciej Gzik
 * 
 */
public abstract class AndroMoteMobilePlatformApiAbstract implements IAndroMoteApi, IAndroMoteMobilePlatformApi,
IAndroMoteDeviceReceiverClient, IAndroMoteDeviceDataProvider {
	private static final String ANDROMOTE_API = "ANDROMOTE_API";
	protected AndroMoteLogger logger = new AndroMoteLogger(AndroMoteMobilePlatformApiAbstract.class);

	protected final Application application;
	protected MessagesFromDeviceReceiver mobilePlatformMessageReceiver = null;

	public AndroMoteMobilePlatformApiAbstract(Application application) {
		this.application = application;
		mobilePlatformMessageReceiver = new MessagesFromDeviceReceiver(this, application);
		try {
			mobilePlatformMessageReceiver.startMessagesListener();
		} catch (BroadcastReceiverClientNotSetException e) {
			logger.error(ANDROMOTE_API, e);
			e.printStackTrace();
		}
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
	public boolean setMotionMode(MotionMode motionMode) throws MobilePlatformException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean setStepDuration(int stepDuration) throws MobilePlatformException {
		throw new UnsupportedOperationException();
	}

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

	public boolean startCommunicationWithDevice(ElectronicDeviceFactory deviceFactory)
			throws MobilePlatformException {
		// TODO Auto-generated method stub
		return false;
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

}
