package andro_mote.platform_controller;

import java.io.Serializable;

import andro_mote.api.AndroMoteMobilePlatformApiAbstract;
import andro_mote.api.IPacket;
import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.DeviceDefinitions.MobilePlatforms;
import andro_mote.commons.DeviceDefinitions.MotorDrivers;
import andro_mote.commons.IntentsFieldsIdentifiers;
import andro_mote.commons.IntentsIdentifiers;
import andro_mote.commons.MotionModes;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.Motion;
import andro_mote.logger.AndroMoteLogger;
import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;

/**
 * Implementacja API sterowania dla platformy mobilnej AndroMote. API obsługi
 * platformy mobilnej zaimplementowane w projekcie AndroMote. Pozwala w łatwy
 * sposób uruchomić i kontrolować urządzenie. W celu wykorzystania pozostałych
 * modułów aplikacji AndroMote (BT, serwis połączenia z serwerem) należy
 * korzystać bezpośrednio z klas w bibliotekach Android.
 * 
 * @author Maciej Gzik
 * @author Sebastian Łuczak
 * 
 */
public class AndroMoteMobilePlatformController extends AndroMoteMobilePlatformApiAbstract {

	private static final String TAG = AndroMoteMobilePlatformController.class.toString();
	private static AndroMoteLogger logger = new AndroMoteLogger(AndroMoteMobilePlatformController.class);

	/**
	 * Konstruktor obiektu API.
	 * 
	 * @param application
	 *            Obiekt aplikacji. Jest wymagany do utworzenia serwisu
	 *            sterowania silnikami oraz rejestrowania obiektów
	 *            rejestrujących zdarzenia sterownika silników.
	 */
	//FIXME OK
	public AndroMoteMobilePlatformController(Application application) {
		super(application);
	}

	/**
	 * Start serwisu sterowania silnikami.
	 * 
	 * @param platformName
	 *            Nazwa modelu, który jest podłaczony do mikrokontrolera IOIO.
	 *            Lista obsługiwanych modelów znajduje się w klasie
	 *            andro_mote.models.ModelFactory.
	 * @return flaga informująca o tym czy serwis sterowania silnikami został
	 *         uruchomiony.
	 * @throws MobilePlatformException
	 */
	//FIXME OK
	public boolean startCommunicationWithDevice(MobilePlatforms platformName, MotorDrivers driverName) throws MobilePlatformException {
		checkIfServiceIsStopped();
		checkIfApplicationIsNull();

		//FIXME Przemianować na uniwersalną nazwę
		Intent startEngineServiceIntent = new Intent(IntentsIdentifiers.ACTION_ENGINES_CONTROLLER);
		Packet pack = new Packet(PacketType.Connection.MODEL_NAME);
		pack.setPlatformName(platformName);
		pack.setDriverName(driverName);
		startEngineServiceIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) pack);
		ComponentName name = application.startService(startEngineServiceIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: startEngineService: component name: " + name);

		isConnectionActive = true;
		return true;
	}

	/**
	 * Zatrzymanie serwisu silników odpowiadające zerwaniu połączenia z
	 * platformą IOIO.
	 * 
	 * @return flaga informująca o tym czy serwis obiekt Intent został
	 *         prawdiłowo wysłany - nie gwarantuje to zatrzymania serwisu
	 *         siników.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	//FIXME OK
	public boolean stopCommunicationWithDevice() throws MobilePlatformException {
		checkExecutionPreconditions();

		Intent closeService = new Intent(IntentsIdentifiers.ACTION_ENGINES_CONTROLLER);
		application.stopService(closeService);
		isConnectionActive = false;
		logger.debug(TAG, "AndroMoteEngineControllerApi: stopEngineService: engine service stopped");

		return true;
	}

	/**
	 * Zmiana prędkości silników.
	 * 
	 * @param speed
	 *            nowa wartośc prędkości silników. Wartość pomiędzy
	 *            EnginesControllerService.MIN_SPEED a 1.
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	//FIXME OK
	public boolean setPlatformSpeed(double speed) throws MobilePlatformException {
		checkExecutionPreconditions();
		
		Packet setSpeedPacket = new Packet(Engine.SET_SPEED);
		setSpeedPacket.setSpeed(speed);
		createAndSendIntentWithExtra(setSpeedPacket);
		logger.debug(TAG, "AndroMoteEngineControllerApi: setSpeed: speed set to: " + speed);

		return true;
	}

	/**
	 * Zmiana trybu ruchu modelu.
	 * 
	 * @param motionMode
	 *            Przyjmowane wartości:
	 *            EnginesControllerService.MOTION_MODE_CONTINUOUS oraz
	 *            EnginesControllerService.MOTION_MODE_STEPPER
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
//
//	
	@Override
	public boolean setMotionMode(MotionModes motionMode) throws MobilePlatformException {
		checkExecutionPreconditions();

		Packet setMotionModePacket = null;
		if (motionMode.equals(MotionModes.MOTION_MODE_CONTINUOUS)) {
			setMotionModePacket = new Packet(Engine.SET_CONTINUOUS_MODE);
		} else {
			setMotionModePacket = new Packet(Engine.SET_STEPPER_MODE);
		}
		createAndSendIntentWithExtra(setMotionModePacket);
		logger.debug(TAG, "AndroMoteEngineControllerApi: setMotionMode: motionMode set to: " + motionMode);

		return true;
	}

	/**
	 * Zmiana czasu trwania jednego kroku w trybie STEPPER
	 * 
	 * @param stepDuration
	 *            Czas trwania jednego kroku. Wartość w ms pomiędzy 1 a
	 *            EnginesControllerService.MAX_STEP_DURATION
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	//FIXME OK
	@Override
	public boolean setStepDuration(int stepDuration) throws MobilePlatformException {
		checkExecutionPreconditions();

		Packet setStepDurationPacket = new Packet(Engine.SET_STEP_DURATION);
		setStepDurationPacket.setStepDuration(stepDuration);
		createAndSendIntentWithExtra(setStepDurationPacket);
		logger.debug(TAG, "AndroMoteEngineControllerApi: setStepDuration: step duration set to: " + stepDuration);

		return true;
	}

	/**
	 * Przekazanie instrukcji do wykonania przez pojazd
	 * 
	 * @param packet pakiet wysylany do urzadzenia
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	@Override
	public boolean sendMessageToDevice(IPacket packet) throws MobilePlatformException, UnsupportedOperationException {
		checkExecutionPreconditions();
		createAndSendIntentWithExtra(packet);
		logger.debug(TAG,
				"AndroMoteApi: sending message to servicedevice;PacketType: " + ((Packet) packet).getPacketType());
		return true;
	}


	/**
	 * Zlecenie zatrzymania węzła.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	@Override
	public boolean stopMobilePlatform() throws MobilePlatformException {
		checkExecutionPreconditions();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(Motion.STOP_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: stop");

		return true;
	}

	//FIXME Czy to jest potrzebne?
	@Override
	public boolean checkIfConnectionIsActive() throws MobilePlatformException, UnsupportedOperationException {
		try {
			this.checkIfServiceIsStarted();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// PRIVATE
	private void createAndSendIntentWithExtra(IPacket pack) {
		Intent sendInfoIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		sendInfoIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) pack);
		application.sendBroadcast(sendInfoIntent);
	}

	private void checkExecutionPreconditions() throws MobilePlatformException {
		checkIfServiceIsStarted();
		checkIfApplicationIsNull();
	}

	private void checkIfServiceIsStarted() throws MobilePlatformException {
		if (!isConnectionActive) {
			throw new MobilePlatformException(
					"Engine service is stopped. Start service using startEngineService method");
		}
	}

	private void checkIfServiceIsStopped() throws MobilePlatformException {
		if (isConnectionActive) {
			throw new MobilePlatformException("Engine service already started.");
		}
	}

	private void checkIfApplicationIsNull() throws MobilePlatformException {
		if (application == null) {
			throw new MobilePlatformException(
					"Application object is null. Set application object using setApplication() method!");
		}
	}

	private void onEngineServiceClosed() {
		logger.debug(TAG, "AndroMoteEngineController: engine service closed on error");
		this.isConnectionActive = false;
	}

	// GETERS && SETTERS
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public void deviceMessageReceived(Packet pack) throws MobilePlatformException {
		logger.debug(TAG,
				"AndroMoteMobilePlatformController: packet from mobile platform received: " + pack.getPacketType());
	}
}
