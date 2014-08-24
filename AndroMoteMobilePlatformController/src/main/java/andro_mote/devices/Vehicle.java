package andro_mote.devices;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import andro_mote.api.exceptions.UnknownDeviceException;
import andro_mote.commons.DeviceDefinitions;
import andro_mote.commons.Packet;
import andro_mote.devices.factories.VehicleComponentsFactory;
import andro_mote.devices.generics.MotorDriver;
import andro_mote.devices.generics.Platform;
import andro_mote.logger.AndroMoteLogger;
import andro_mote.stepper.Step;

public final class Vehicle implements Platform, MotorDriver {
	private static final String TAG = Vehicle.class.getName();
	private AndroMoteLogger logger = new AndroMoteLogger(getClass());
	private final MotorDriver driver;
	private final Platform platform;
	private final VehicleSettings settings;
	
	/**
	 * Konstruktor pojazdu na który składa się:
	 * @param platformType nazwa platformy jeżdżącej zgodnie z danymi przyjmowanymi przez fabrykę platform {@link PlatformFactory
	 * @param driverType nazwa sterownika silników zgodnie z danymi przyjmowanymi przez fabrykę sterowników {@link MotorDriverFactory}Driver
	 * @throws UnknownDeviceException
	 */
	public Vehicle (DeviceDefinitions.MobilePlatformType platformType, DeviceDefinitions.MotorDriverType driverType) throws UnknownDeviceException {
		logger.debug(TAG, "testing: platformName: " + platformType);
		logger.debug(TAG, "testing: driverName: " + driverType);
		this.driver = VehicleComponentsFactory.INSTANCE.getMotorDriver(driverType, this);
		this.platform  = VehicleComponentsFactory.INSTANCE.getModel(platformType, driver, this);
		this.settings = VehicleComponentsFactory.INSTANCE.getVehicleSettings(platformType);
	}
	
	public Platform getPlatform() {
		return platform;
	}
	public MotorDriver getDriver() {
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
	public void interpretMotionPacket(Packet inputPacket) {
		platform.interpretMotionPacket(inputPacket);		
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
