package pl.fester3k.androcode.interpreter.device;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.androcode.datatypes.Feature;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public enum CapabilitiesAnalyzer {
	INSTANCE;

	private final Logger log = LoggerFactory.getLogger(CapabilitiesAnalyzer.class);
	private Set<Feature> availableFeatures = new HashSet<Feature>();
	private Context context;
	private PackageManager packageManager;
	private SensorManager sensorManager;

	public void init(Context context) {
		this.context = context;
		packageManager = context.getPackageManager();
		sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		initAvailableFeatures();
	}
	
	public boolean hasFeature(Feature feature) {
		boolean result = false;
		if(availableFeatures.contains(feature)) {
			result = true;
		}
		return result;
	}

	private void initAvailableFeatures() {
		if(context != null) {
			checkRideCapabilities();

			checkSensorCapabilities();
			checkDevicesCapabilities();
			checkCommunicationCapabilities();

			checkApplicationLaunchCapability(); //Bez sensu
		}
	}
	
	

	private void checkCommunicationCapabilities() {
		checkTelephonyCapabiliy();
		checkInternetConnection();
		checkWIFIConnection();
		checkBluetoothConnection();
		checkCellDataTransferConnection();
		checkTetheringCapabilities();

	}

	private void checkDevicesCapabilities() {
		checkCameraCapability();
		checkFlashlightCapability();
		checkAudioCapabilities();
		checkRecordingCapabilities();

	}

	private void checkSensorCapabilities() {
		checkLocationProvider();
		checkPreciseLocationProvider();
		checkLightSensorCapability();
		checkGravitySensorCapability();
		checkRelativeHumiditySensorCapability();
		checkLinearAccelerationSensorCapability();
		checkGyroscopeSensorCapability();
		checkTemperatureSensorCapability();
		checkProximitySensorCapability();
		checkAccelerometerSensorCapability();
		checkMagneticSensorCapability();
		checkAudioLevelSensorCapability();
		checkPressureCapability();

	}


	private void checkRideCapabilities() {
		// TODO Auto-generated method stub

	}

	private void checkApplicationLaunchCapability() {
		// TODO Auto-generated method stub

	}

	private void checkTetheringCapabilities() {
		if(hasFeature(PackageManager.FEATURE_WIFI)) {
			availableFeatures.add(Feature.TETHERING);
		}
	}

	private void checkCellDataTransferConnection() {
		// TODO

	}

	private void checkBluetoothConnection() {
		if(hasFeature(PackageManager.FEATURE_BLUETOOTH)) {
			availableFeatures.add(Feature.BLUETOOTH_CONNECTION);
		}

	}

	private void checkWIFIConnection() {
		if(hasFeature(PackageManager.FEATURE_WIFI)) {
			availableFeatures.add(Feature.WIFI_CONNECTION);
		}
	}

	private void checkInternetConnection() {
		// TODO Auto-generated method stub

	}

	private void checkTelephonyCapabiliy() {
		if(hasFeature(PackageManager.FEATURE_TELEPHONY)) {
			availableFeatures.add(Feature.TELEPHONY_CONNECTION);
		}

	}

	private void checkRecordingCapabilities() {
		if(hasFeature(PackageManager.FEATURE_MICROPHONE)) {
			availableFeatures.add(Feature.AUDIO_IN);
		}
	}

	private void checkAudioCapabilities() {
		// TODO
	}

	private void checkFlashlightCapability() {
		if(hasFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
			availableFeatures.add(Feature.FLASHLIGHT);
		}
	}

	private void checkCameraCapability() {
		if(hasFeature(PackageManager.FEATURE_CAMERA)) {
			availableFeatures.add(Feature.CAMERA);
		}

	}

	private void checkPressureCapability() {
		if(hasSensor(Sensor.TYPE_PRESSURE)) {
			availableFeatures.add(Feature.PRESSURE_SENSOR);
		}

	}

	private void checkAudioLevelSensorCapability() {
		if(hasFeature(PackageManager.FEATURE_MICROPHONE)) {
			availableFeatures.add(Feature.AUDIO_SENSOR);
		}
	}
	
	private void checkGravitySensorCapability() {
		if(hasSensor(Sensor.TYPE_GRAVITY)) {
			availableFeatures.add(Feature.GRAVITY_SENSOR);
		}
	}
	
	private void checkLinearAccelerationSensorCapability() {
		if(hasSensor(Sensor.TYPE_LINEAR_ACCELERATION)) {
			availableFeatures.add(Feature.LINEAR_ACCELERATION_SENSOR);
		}
	}
	
	private void checkRelativeHumiditySensorCapability() {
		if(hasSensor(Sensor.TYPE_RELATIVE_HUMIDITY)) {
			availableFeatures.add(Feature.HUMIDITY_SENSOR);
		}
	}

	private void checkMagneticSensorCapability() {
		if(hasSensor(Sensor.TYPE_MAGNETIC_FIELD)) {
			availableFeatures.add(Feature.MAGNETIC_FIELD_SENSOR);
		}
	}

	private void checkAccelerometerSensorCapability() {
		if(hasSensor(Sensor.TYPE_ACCELEROMETER)) {
			availableFeatures.add(Feature.ACCELEROMETER_SENSOR);
		}
	}

	private void checkProximitySensorCapability() {
		if(hasSensor(Sensor.TYPE_PROXIMITY)) {
			availableFeatures.add(Feature.PROXIMITY_SENSOR);
		}
	}

	private void checkTemperatureSensorCapability() {
		if(hasSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)) {
			availableFeatures.add(Feature.TEMPERATURE_SENSOR);
		}
	}

	private void checkGyroscopeSensorCapability() {
		if(hasSensor(Sensor.TYPE_GYROSCOPE)) {
			availableFeatures.add(Feature.GYROSCOPE_SENSOR);
		}

	}

	private void checkLightSensorCapability() {
		if(hasSensor(Sensor.TYPE_LIGHT)) {
			availableFeatures.add(Feature.LIGHT_SENSOR);
		}
	}

	private void checkPreciseLocationProvider() {
		if(hasFeature(PackageManager.FEATURE_LOCATION_GPS)) {
			availableFeatures.add(Feature.LOCATION_GPS);
		}

	}

	private void checkLocationProvider() {
		if(hasFeature(PackageManager.FEATURE_LOCATION)) {
			availableFeatures.add(Feature.LOCATION);
		}
		
	}

	private boolean hasSensor(int sensorId) {
		boolean hasSensor = false;
		Sensor defaultSensor = sensorManager.getDefaultSensor(sensorId);
		if(defaultSensor != null) {
			hasSensor = true;
			log.info(defaultSensor.getName() + " exists");
		}
		return hasSensor;
	}
	
	private boolean hasFeature(String feature) {
		boolean hasSystemFeature = packageManager.hasSystemFeature(feature);
		if(hasSystemFeature) {
			log.info(feature + " exists");
		} else {
			log.info(feature + " is not present");
		}
		return hasSystemFeature;
	}

	@Override
	public String toString() {
		return "Available features: " + printAvailable();
	}

	private String printAvailable() {
		String availableFeaturesString = "";
		for (Feature feature : availableFeatures) {
			availableFeaturesString = availableFeaturesString + " , " + feature; 
		}
		return availableFeaturesString;
	}
	
	
}
