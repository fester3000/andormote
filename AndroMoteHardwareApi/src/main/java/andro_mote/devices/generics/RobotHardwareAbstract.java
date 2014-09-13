package andro_mote.devices.generics;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import andro_mote.api.LocalBroadcastDispatcher;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.IntentsIdentifiers;
import andro_mote.commons.Packet;
import andro_mote.commons.PacketType.Engine;
import andro_mote.commons.PacketType.Motion;
import andro_mote.devices.DeviceSettings;
import andro_mote.devices.RobotHardware;
import andro_mote.ioio_service.IOIOLooperManagerService;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;
import android.text.format.Time;

public abstract class RobotHardwareAbstract implements RobotHardware {
	private static final String TAG = RobotHardwareAbstract.class.getName();
	protected AndroMoteLogger logger = new AndroMoteLogger(getClass());
	protected final ElectronicDeviceAbstract device;
	
	/**
	 * Konstruktor pojazdu na który składa się:
	 * @param platformType nazwa platformy jeżdżącej zgodnie z danymi przyjmowanymi przez fabrykę platform {@link PlatformFactory
	 * @param driverType nazwa sterownika silników zgodnie z danymi przyjmowanymi przez fabrykę sterowników {@link MotorDriverFactory}Driver
	 * @throws UnknownDeviceException
	 */
	public RobotHardwareAbstract (ElectronicDeviceAbstract device) {
		this.device = device;
	}

	/* (non-Javadoc)
	 * @see andro_mote.devices.RobotHardware#initIOIOPins(ioio.lib.api.IOIO)
	 */
	@Override
	public void initIOIOPins(final IOIO ioio) throws ConnectionLostException {
		device.initIOIOPins(ioio);
	}

	/* (non-Javadoc)
	 * @see andro_mote.devices.RobotHardware#writeNewIoioPinValues(ioio.lib.api.IOIO)
	 */
	@Override
	public void writeNewIOIOPinValues(final IOIO ioio) throws ConnectionLostException {
		device.writeNewIoioPinValues(ioio);
	}

	/* (non-Javadoc)
	 * @see andro_mote.devices.RobotHardware#readCurrentValues()
	 */
	@Override
	public void readNewPinValues() throws InterruptedException, ConnectionLostException {
		device.readCurrentValues();
		
	}

	
	/* (non-Javadoc)
	 * @see andro_mote.devices.RobotHardware#takeStep(andro_mote.stepper.Step)
	 */
	@Override
	public void takeStep(Step step) {
		logger.debug(TAG, "step execution");
		if (step.getStepType() == Motion.MOVE_FORWARD
				|| step.getStepType() == Motion.MOVE_RIGHT_FORWARD
				|| step.getStepType() == Motion.MOVE_LEFT_FORWARD
				|| step.getStepType() == Motion.MOVE_LEFT 
				|| step.getStepType() == Motion.MOVE_RIGHT
				|| step.getStepType() == Motion.MOVE_LEFT_BACKWARD
				|| step.getStepType() == Motion.MOVE_RIGHT_BACKWARD
				|| step.getStepType() == Motion.MOVE_BACKWARD) {
			TakeStepThread makeStep = new TakeStepThread(step);
			makeStep.startThread();
		} 
	}

	/**
	 * Logowanie aktualnego czasu na podany logger.
	 * 
	 * @param logger
	 * @param TAG
	 */
	protected void logTimestamp(AndroMoteLogger logger, String TAG) {
		Time now = new Time();
		now.setToNow();
		logger.debug(TAG, now.format("%d.%m.%Y %H:%M:%S:%f"));
	}

	/**
	 * Wątek wykonujący krok mobilnego węzła. W tym czasie blokowane są
	 * pozostałe akcje węzła. Wymagana jest implementacja dla każdego modelu i
	 * każdego możliwego dla niego rodzaju kroku. Po zakończeniu wykonywania
	 * kroku typ wykonanego kroku powinien zostać wysłany w Intent typu:
	 * {@link IntentsIdentifiers.ACTION_ENGINE_STEP}
	 * 
	 * @author Maciej Gzik
	 * 
	 */
	private class TakeStepThread implements Runnable {
		private Thread blinker = null;
		private Step step = null;

		public TakeStepThread(Step step) {
			this.step = step;
		}

		public void startThread() {
			logger.debug(TAG, "Starting make step Thread...");
			blinker = new Thread(this);
			blinker.start();
		}

		public void stopThread() {
			logger.debug(TAG, "Stopping make step Thread...");
			blinker = null;
		}

		@Override
		public void run() {
			long stepStartTime = 0;
			long stepStopTime = 0;
			DeviceSettings settings = getSettings();
			try {
				IOIOLooperManagerService.isOperationExecuted = true;
				stepStartTime = System.currentTimeMillis();

				// zmiana stanu silników
				setValuesForSimpleStep(step.getStepType());

				// początek kroku
				Thread.sleep(settings.getStepDuration());
				stepStopTime = System.currentTimeMillis();
				// koniec kroku

				// zatrzymanie węzła
				stop();

				// przerwa pomiędzy kolejnymi krokami
				Thread.sleep(settings.getPauseTimeBetweenSteps());
				IOIOLooperManagerService.isOperationExecuted = false;
			} catch (InterruptedException e) {
				logger.error(TAG, e);
			} finally {
				IOIOLooperManagerService.isOperationExecuted = false;
			}

			// wysyłanie pakietu z informacją o wykonanym kroku
			//FIXME przywrocic funkcje rozsylania broadcastow na zasadzie wzorca obserwator
			logger.debug(TAG, "Broadcasting step executed: " + step.getStepType());
			Packet responsePacket = new Packet(Engine.STEP_TAKEN_PACKET);
			responsePacket.setStepDirection(Step.getTakenStep(step.getStepType()));
			responsePacket.setStepDuration(stepStopTime - stepStartTime);
			responsePacket.setSpeed(settings.getSpeed());
			responsePacket.setSpeedB(settings.getSpeed());

			LocalBroadcastDispatcher.INSTANCE.sendPacketViaLocalBroadcast(responsePacket, IntentsIdentifiers.ACTION_ENGINE_STEP);
		}
	}
}
