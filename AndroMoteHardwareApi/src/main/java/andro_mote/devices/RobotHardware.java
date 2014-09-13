package andro_mote.devices;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.DeviceDefinitions;
import andro_mote.commons.Packet;
import andro_mote.devices.generics.ElectronicDevice;
import andro_mote.devices.generics.ElectronicDeviceInterface;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;

public final class RobotHardware implements ElectronicDeviceInterface, ElectronicDevice {
	private static final String TAG = RobotHardware.class.getName();
	private AndroMoteLogger logger = new AndroMoteLogger(getClass());
	private final ElectronicDevice driver;
	private final ElectronicDeviceInterface platform;
	private final VehicleSettings settings;
	
	/**
	 * Konstruktor pojazdu na który składa się:
	 * @param platformType nazwa platformy jeżdżącej zgodnie z danymi przyjmowanymi przez fabrykę platform {@link PlatformFactory
	 * @param driverType nazwa sterownika silników zgodnie z danymi przyjmowanymi przez fabrykę sterowników {@link MotorDriverFactory}Driver
	 * @throws UnknownDeviceException
	 */
	public RobotHardware (DeviceDefinitions.MobilePlatformType platformType, DeviceDefinitions.MotorDriverType driverType) throws UnknownDeviceException {
		logger.debug(TAG, "testing: platformName: " + platformType);
		logger.debug(TAG, "testing: driverName: " + driverType);
		this.driver = VehicleComponentsFactory.INSTANCE.getMotorDriver(driverType, this);
		this.platform  = VehicleComponentsFactory.INSTANCE.getModel(platformType, driver, this);
		this.settings = VehicleComponentsFactory.INSTANCE.getVehicleSettings(platformType);
	}
	
	public ElectronicDeviceInterface getPlatform() {
		return platform;
	}
	public ElectronicDevice getDriver() {
		return driver;
	}

	@Override
	public void initIOIOPins(final IOIO ioio) throws ConnectionLostException {
		driver.initIOIOPins(ioio);
	}


	@Override
	public void writeNewIoioPinValues(final IOIO ioio) throws ConnectionLostException {
		driver.writeNewIoioPinValues(ioio);
	}

	@Override
	public void takeStep(Step step) {
		platform.takeStep(step);
	}

	@Override
	public void interpretPacket(Packet inputPacket) {
		platform.interpretPacket(inputPacket);		
	}

	@Override
	public void stop() {
		platform.stop();
	}

	public void readCurrentValues() throws InterruptedException, ConnectionLostException {
		driver.readCurrentValues();
		
	}

	public VehicleSettings getSettings() {
		return settings;
	}
}
