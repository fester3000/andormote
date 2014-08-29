package pl.fester3k.androcode.deviceManagement;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.androcode.datatypes.Feature;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class CapabilitiesAnalyzer {
	private final Logger log = LoggerFactory.getLogger(CapabilitiesAnalyzer.class);
	private Set<Feature> availableFeatures = new HashSet<Feature>();
	private Context context;
	private PackageManager packageManager;
	private SensorManager sensorManager;

	public CapabilitiesAnalyzer(Context context) {
		this.context = context;
		packageManager = context.getPackageManager();
		sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		checkCurrentCapabilities();
	}
	
	public boolean hasFeature(Feature feature) {
		boolean result = false;
		if(availableFeatures.contains(feature)) {
			result = true;
		}
		return result;
	}

	public void checkCurrentCapabilities() {
		if(context != null) {
			availableFeatures = new HashSet<Feature>();
			checkRideCapabilities();

			checkSensorCapabilities();
			checkDevicesCapabilities();
			checkCommunicationCapabilities();
		}
	}
	
	

	private void checkCommunicationCapabilities() {
		checkTelephonyCapabiliy();
		checkInternetConnection();
		checkWIFIConnection();
	}

	private void checkDevicesCapabilities() {
		checkCameraCapability();
		checkFlashlightCapability();
		checkRecordingCapabilities();
		checkTTSCapabilities();

	}

	private void checkSensorCapabilities() {
		checkLocationProvider();
		checkLightSensorCapability();
		checkGravitySensorCapability();
		checkRelativeHumiditySensorCapability();
		checkLinearAccelerationSensorCapability();
		checkGyroscopeSensorCapability();
		checkTemperatureSensorCapability();
		checkProximitySensorCapability();
		checkAccelerometerSensorCapability();
		checkMagneticSensorCapability();
		checkCompassCapability();
		checkAudioLevelSensorCapability();
		checkPressureCapability();

	}

	private void checkRideCapabilities() {
		if(RideController.INSTANCE.isRideAvailable()) {
			availableFeatures.add(Feature.RIDE);
		}
	}

	private void checkWIFIConnection() {
		if(deviceHasFeature(PackageManager.FEATURE_WIFI)) {
			availableFeatures.add(Feature.WIFI_CONNECT);
		}
	}

	private void checkInternetConnection() {
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(pickIsMobileDataTransferOn(connectivityManager) || pickIsWifiOn(connectivityManager)) {
			availableFeatures.add(Feature.EMAIL);
		}
	}

	private void checkTelephonyCapabiliy() {
		if(deviceHasFeature(PackageManager.FEATURE_TELEPHONY)) {
			availableFeatures.add(Feature.SMS);
		}
	}
	

	private void checkCompassCapability() {
		if(deviceHasSensor(Sensor.TYPE_ACCELEROMETER) && deviceHasSensor(Sensor.TYPE_MAGNETIC_FIELD)) {
			availableFeatures.add(Feature.COMPASS);
		}
	}

	private void checkTTSCapabilities() {
		availableFeatures.add(Feature.TTS);
	}

	private void checkRecordingCapabilities() {
		if(deviceHasFeature(PackageManager.FEATURE_MICROPHONE)) {
			availableFeatures.add(Feature.RECORD_AUDIO);
		}
	}

	private void checkFlashlightCapability() {
		if(deviceHasFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
			availableFeatures.add(Feature.FLASHLIGHT);
		}
	}

	private void checkCameraCapability() {
		if(deviceHasFeature(PackageManager.FEATURE_CAMERA)) {
			availableFeatures.add(Feature.CAMERA);
			availableFeatures.add(Feature.RECORD_VIDEO);
		}

	}

	private void checkPressureCapability() {
		if(deviceHasSensor(Sensor.TYPE_PRESSURE)) {
			availableFeatures.add(Feature.PRESSURE_SENSOR);
		}

	}

	private void checkAudioLevelSensorCapability() {
		if(deviceHasFeature(PackageManager.FEATURE_MICROPHONE)) {
			availableFeatures.add(Feature.AUDIO_SENSOR);
		}
	}
	
	private void checkGravitySensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_GRAVITY)) {
			availableFeatures.add(Feature.GRAVITY_SENSOR);
		}
	}
	
	private void checkLinearAccelerationSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_LINEAR_ACCELERATION)) {
			availableFeatures.add(Feature.LINEAR_ACCELERATION_SENSOR);
		}
	}
	
	private void checkRelativeHumiditySensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_RELATIVE_HUMIDITY)) {
			availableFeatures.add(Feature.HUMIDITY_SENSOR);
		}
	}

	private void checkMagneticSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_MAGNETIC_FIELD)) {
			availableFeatures.add(Feature.MAGNETIC_FIELD_SENSOR);
		}
	}

	private void checkAccelerometerSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_ACCELEROMETER)) {
			availableFeatures.add(Feature.ACCELEROMETER_SENSOR);
		}
	}

	private void checkProximitySensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_PROXIMITY)) {
			availableFeatures.add(Feature.PROXIMITY_SENSOR);
		}
	}

	private void checkTemperatureSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)) {
			availableFeatures.add(Feature.TEMPERATURE_SENSOR);
		}
	}

	private void checkGyroscopeSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_GYROSCOPE)) {
			availableFeatures.add(Feature.GYROSCOPE_SENSOR);
		}

	}

	private void checkLightSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_LIGHT)) {
			availableFeatures.add(Feature.LIGHT_SENSOR);
		}
	}

	private void checkLocationProvider() {
		if(deviceHasFeature(PackageManager.FEATURE_LOCATION)) {
			availableFeatures.add(Feature.LOCATION);
		}
		
	}

	private boolean deviceHasSensor(int sensorId) {
		boolean hasSensor = false;
		Sensor defaultSensor = sensorManager.getDefaultSensor(sensorId);
		if(defaultSensor != null) {
			hasSensor = true;
			log.info(defaultSensor.getName() + " exists");
		}
		return hasSensor;
	}
	
	private boolean deviceHasFeature(String feature) {
		boolean hasSystemFeature = packageManager.hasSystemFeature(feature);
		if(hasSystemFeature) {
			log.info(feature + " exists");
		} else {
			log.info(feature + " is not present");
		}
		return hasSystemFeature;
	}

	public String getAvailableFeatures() {
		return "Available features: " + printAvailable();		
	}
	
	private String printAvailable() {
		String availableFeaturesString = "";
		for (Feature feature : availableFeatures) {
			availableFeaturesString = availableFeaturesString + " " + feature + ",\n"; 
		}
		return availableFeaturesString;
	}
	
	private static boolean pickIsMobileDataTransferOn(ConnectivityManager connectivityManager) {
		if(connectivityManager != null) {
			NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
			if(networkInfo != null) {
				State mobile =	networkInfo.getState();

				if (mobile != null && (mobile == State.CONNECTED || mobile == State.CONNECTING ) )
					return true;
			}
		}
		return false;	
	}

	private static boolean pickIsWifiOn(ConnectivityManager connectivityManager) {
		if(connectivityManager != null) {
			State wifi = connectivityManager.getNetworkInfo(1).getState();
			if (wifi == State.CONNECTED || wifi == State.CONNECTING)
				return true;
		}
		return false;	
	}
//
//	private static boolean pickIsGpsOn(Activity mainActivity) {
//		LocationManager locationManager = (LocationManager)mainActivity.getSystemService(Context.LOCATION_SERVICE);
//		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//	}
}
