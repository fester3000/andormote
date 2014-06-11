package andro_mote.platform_controller;

import java.io.Serializable;

import andro_mote.api.AndroMoteMobilePlatformApiAbstract;
import andro_mote.api.IPacket;
import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.IntentsFieldsIdentifiers;
import andro_mote.commons.IntentsIdentifiers;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.Motion;
import andro_mote.ioio_service.EnginesControllerService;
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
 * 
 */
public class AndroMoteMobilePlatformController extends AndroMoteMobilePlatformApiAbstract {

	private static final String TAG = AndroMoteMobilePlatformController.class.toString();
	private static AndroMoteLogger logger = new AndroMoteLogger(AndroMoteMobilePlatformController.class);

	// private Application application = null;
	// private boolean isConnectionActive = false;

	/**
	 * Konstruktor obiektu API.
	 * 
	 * @param application
	 *            Obiekt aplikacji. Jest wymagany do utworzenia serwisu
	 *            sterowania silnikami oraz rejestrowania obiektów
	 *            rejestrujących zdarzenia sterownika silników.
	 */
	public AndroMoteMobilePlatformController(Application application) {
		super(application);
	}

	/**
	 * Start serwisu sterowania silnikami.
	 * 
	 * @param deviceName
	 *            Nazwa modelu, który jest podłaczony do mikrokontrolera IOIO.
	 *            Lista obsługiwanych modelów znajduje się w klasie
	 *            andro_mote.models.ModelFactory.
	 * @return flaga informująca o tym czy serwis sterowania silnikami został
	 *         uruchomiony.
	 * @throws MobilePlatformException
	 */
	public boolean startCommunicationWithDevice(String deviceName) throws MobilePlatformException {
		checkIfServiceIsStopped();
		checkIfApplicationIsNull();

		Intent startEngineServiceIntent = new Intent(IntentsIdentifiers.ACTION_START_ENGINES_CONTROLLER);
		Packet pack = new Packet(PacketType.Connection.MODEL_NAME);
		pack.setDeviceName(deviceName);
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
	public boolean stopCommunicationWithDevice() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent closeService = new Intent(IntentsIdentifiers.ACTION_START_ENGINES_CONTROLLER);
		application.stopService(closeService);
		isConnectionActive = false;
		logger.debug(TAG, "AndroMoteEngineControllerApi: stopEngineService: engine service stopped");

		return true;
	}

	@Override
	public boolean checkIfConnectionIsActive() throws MobilePlatformException, UnsupportedOperationException {
		try {
			this.checkIfServiceIsStarted();
			return true;
		} catch (Exception e) {
			return false;
		}
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
	public boolean setPlatformSpeed(double speed) throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent setSpeed = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		Packet setSpeedPacket = new Packet(Engine.SET_SPEED);
		setSpeedPacket.setSpeed(speed);
		setSpeed.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) setSpeedPacket);
		application.sendBroadcast(setSpeed);
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
	@Override
	public boolean setMotionMode(String motionMode) throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent setMotionModeIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		Packet setMotionModePacket = null;
		if (motionMode.equals(EnginesControllerService.MOTION_MODE_CONTINUOUS)) {
			setMotionModePacket = new Packet(Engine.SET_CONTINUOUS_MODE);
		} else {
			setMotionModePacket = new Packet(Engine.SET_STEPPER_MODE);
		}

		setMotionModeIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) setMotionModePacket);
		application.sendBroadcast(setMotionModeIntent);
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
	@Override
	public boolean setStepDuration(int stepDuration) throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent setStepDurationIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		Packet setStepDurationPacket = new Packet(Engine.SET_STEP_DURATION);
		setStepDurationPacket.setStepDuration(stepDuration);
		setStepDurationIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) setStepDurationPacket);
		application.sendBroadcast(setStepDurationIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: setStepDuration: step duration set to: " + stepDuration);

		return true;
	}

	/**
	 * Zlecenie wykonania ruchu do przodu w lewo. W zależności od trybu jest to
	 * nieprzerwany ruch lub zakolejkowanie kroku.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	@Override
	public boolean moveLeftForward() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(
				Motion.MOVE_LEFT_FORWARD_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: moveLeftForward");

		return true;
	}

	/**
	 * Zlecenie wykonania ruchu do przodu. W zależności od trybu jest to
	 * nieprzerwany ruch lub zakolejkowanie kroku.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	@Override
	public boolean moveForward() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveForwardIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveForwardIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(
				Motion.MOVE_FORWARD_REQUEST));
		application.sendBroadcast(moveForwardIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: moveForward");

		return true;
	}

	/**
	 * Zlecenie wykonania ruchu do przodu w prawo. W zależności od trybu jest to
	 * nieprzerwany ruch lub zakolejkowanie kroku.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	@Override
	public boolean moveRightForward() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(
				Motion.MOVE_RIGHT_FORWARD_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: moveRightForward");

		return true;
	}

	/**
	 * Zlecenie wykonania ruchu w lewo. W zależności od trybu jest to
	 * nieprzerwany ruch lub zakolejkowanie kroku.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	@Override
	public boolean moveLeft() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(Motion.MOVE_LEFT_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: moveLeft");

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
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(Motion.STOP_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: stop");

		return true;
	}

	/**
	 * Zlecenie wykonania ruchu w prawo. W zależności od trybu jest to
	 * nieprzerwany ruch lub zakolejkowanie kroku.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	public boolean moveRight() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent
				.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(Motion.MOVE_RIGHT_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: moveRight");

		return true;
	}

	/**
	 * Zlecenie wykonania ruchu do tyłu w lewo. W zależności od trybu jest to
	 * nieprzerwany ruch lub zakolejkowanie kroku.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	public boolean moveLeftBackward() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(
				Motion.MOVE_LEFT_BACKWARD_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: moveLeftBackward");

		return true;
	}

	/**
	 * Zlecenie wykonania ruchu do tyłu. W zależności od trybu jest to
	 * nieprzerwany ruch lub zakolejkowanie kroku.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	public boolean moveBackward() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(
				Motion.MOVE_BACKWARD_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: moveBackward");

		return true;
	}

	/**
	 * Zlecenie wykonania ruchu do tyłu w prawo. W zależności od trybu jest to
	 * nieprzerwany ruch lub zakolejkowanie kroku.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	public boolean moveRightBackward() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(
				Motion.MOVE_RIGHT_BACKWARD_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: moveRightBackward");

		return true;
	}

	/**
	 * Zlecenie wykonania skrętu w prawo o 90 stopni. Niezależnie od trybu ruchu
	 * węzła jest to zakolejkowanie kroku.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	public boolean turn90RightDegrees() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(
				Motion.MOVE_RIGHT_90_DEGREES_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: turn90RightDegrees");

		return true;
	}

	/**
	 * Zlecenie wykonania skrętu w lewo o 90 stopni. Niezależnie od trybu ruchu
	 * węzła jest to zakolejkowanie kroku.
	 * 
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	public boolean turn90LeftDegrees() throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) new Packet(
				Motion.MOVE_LEFT_90_DEGREES_REQUEST));
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: turn90LeftDegrees");

		return true;
	}

	/**
	 * Zlecenie wykonania skrętu w prawo o kreśloną przez użytkownika liczbę
	 * stopni. Niezależnie od trybu ruchu węzła jest to zakolejkowanie kroku.
	 * 
	 * @param bearing
	 *            liczba stopni o jakie ma zostać wykonany skręt
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	public boolean turnRightDegrees(int bearing) throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		Packet pack = new Packet(Motion.MOVE_RIGHT_DEGREES_REQUEST);
		pack.setBearing(bearing);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) pack);
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: turnRightDegrees: " + bearing);

		return true;
	}

	/**
	 * Zlecenie wykonania skrętu w lewo o kreśloną przez użytkownika liczbę
	 * stopni. Niezależnie od trybu ruchu węzła jest to zakolejkowanie kroku.
	 * 
	 * @param bearing
	 *            liczba stopni o jakie ma zostać wykonany skręt
	 * @return Flaga informująca o tym czy obiekt Intent został prawidłowo
	 *         wysłany.
	 * @throws EngineServiceException
	 *             Wyjątek rzucany w przypadku wykonania nieprawidłowego
	 *             działania na serwisie silnków - szczegóły w obiekcie wyjątku.
	 */
	public boolean turnLeftDegrees(int bearing) throws MobilePlatformException {
		this.checkIfServiceIsStarted();
		this.checkIfApplicationIsNull();

		Intent moveIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);
		Packet pack = new Packet(Motion.MOVE_RIGHT_DEGREES_REQUEST);
		pack.setBearing(bearing);
		moveIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) pack);
		application.sendBroadcast(moveIntent);
		logger.debug(TAG, "AndroMoteEngineControllerApi: turnLeftDegrees: " + bearing);

		return true;
	}

	@Override
	public boolean sendMessageToDevice(IPacket pack) throws MobilePlatformException, UnsupportedOperationException {
		Intent sendInfoIntent = new Intent(IntentsIdentifiers.ACTION_MESSAGE_TO_DEVICE_CONTROLLER);

		sendInfoIntent.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Serializable) pack);
		application.sendBroadcast(sendInfoIntent);
		logger.debug(TAG,
				"AndroMoteApi: sending message to servicedevice;PacketType: " + ((Packet) pack).getPacketType());
		return true;
	}

	// PRIVATE

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
