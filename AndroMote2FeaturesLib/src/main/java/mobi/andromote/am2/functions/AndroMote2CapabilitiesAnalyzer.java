package mobi.andromote.am2.functions;

import java.util.HashSet;
import java.util.Set;

import mobi.andromote.functionalityFramework.CapabilitiesAnalyzer;
import mobi.andromote.functionalityFramework.datatypes.Feature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andro_mote.hardware.ElectronicsController;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class AndroMote2CapabilitiesAnalyzer implements CapabilitiesAnalyzer {
	protected final Logger log = LoggerFactory.getLogger(AndroMote2CapabilitiesAnalyzer.class);
	protected final Context context;
	
	private PackageManager packageManager;
	private SensorManager sensorManager;
	protected Set<AndroMote2Feature> availableFeatures;
	
	public AndroMote2CapabilitiesAnalyzer(Context context) {
		this.context = context;
		packageManager = context.getPackageManager();
		sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
	}
	
	@Override
	public boolean hasFeature(String feature) {
		boolean result = false;
		AndroMote2Feature specificFeature = AndroMote2Feature.valueOf(feature);
		
		if(availableFeatures.contains(specificFeature )) {
			result = true;
		}
		return result;
	}

	@Override
	public void checkCurrentCapabilities() {
		if(context != null) {
			availableFeatures = new HashSet<AndroMote2Feature>();

			checkSensorCapabilities();
			checkDevicesCapabilities();
			checkCommunicationCapabilities();
			checkRideCapabilities();
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
		if(ElectronicsController.INSTANCE.isRideAvailable()) {
			availableFeatures.add(AndroMote2Feature.RIDE);
			availableFeatures.add(AndroMote2Feature.RIDE_SETUP);
			if(availableFeatures.contains(AndroMote2Feature.COMPASS)) {
				availableFeatures.add(AndroMote2Feature.RIDE_BEARING);
			}
			if(availableFeatures.contains(AndroMote2Feature.LOCATION)) {
				availableFeatures.add(AndroMote2Feature.RIDE_GPS);
			}
		}
	}

	private void checkWIFIConnection() {
		if(deviceHasFeature(PackageManager.FEATURE_WIFI)) {
			availableFeatures.add(AndroMote2Feature.WIFI_CONNECT);
		}
	}

	private void checkInternetConnection() {
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(pickIsMobileDataTransferOn(connectivityManager) || pickIsWifiOn(connectivityManager)) {
			availableFeatures.add(AndroMote2Feature.EMAIL);
		}
	}

	private void checkTelephonyCapabiliy() {
		if(deviceHasFeature(PackageManager.FEATURE_TELEPHONY)) {
			availableFeatures.add(AndroMote2Feature.SMS);
		}
	}
	

	private void checkCompassCapability() {
		if(deviceHasSensor(Sensor.TYPE_ACCELEROMETER) && deviceHasSensor(Sensor.TYPE_MAGNETIC_FIELD)) {
			availableFeatures.add(AndroMote2Feature.COMPASS);
		}
	}

	private void checkTTSCapabilities() {
		availableFeatures.add(AndroMote2Feature.TTS);
	}

	private void checkRecordingCapabilities() {
		if(deviceHasFeature(PackageManager.FEATURE_MICROPHONE)) {
			availableFeatures.add(AndroMote2Feature.RECORD_AUDIO);
		}
	}

	private void checkFlashlightCapability() {
		if(deviceHasFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
			availableFeatures.add(AndroMote2Feature.FLASHLIGHT);
		}
	}

	private void checkCameraCapability() {
		if(deviceHasFeature(PackageManager.FEATURE_CAMERA)) {
			availableFeatures.add(AndroMote2Feature.PICTURE);
			availableFeatures.add(AndroMote2Feature.RECORD_VIDEO);
		}

	}

	private void checkPressureCapability() {
		if(deviceHasSensor(Sensor.TYPE_PRESSURE)) {
			availableFeatures.add(AndroMote2Feature.PRESSURE_SENSOR);
		}

	}

	private void checkAudioLevelSensorCapability() {
		if(deviceHasFeature(PackageManager.FEATURE_MICROPHONE)) {
			availableFeatures.add(AndroMote2Feature.AUDIO_SENSOR);
		}
	}
	
	private void checkGravitySensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_GRAVITY)) {
			availableFeatures.add(AndroMote2Feature.GRAVITY_SENSOR);
		}
	}
	
	private void checkLinearAccelerationSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_LINEAR_ACCELERATION)) {
			availableFeatures.add(AndroMote2Feature.LINEAR_ACCELERATION_SENSOR);
		}
	}
	
	private void checkRelativeHumiditySensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_RELATIVE_HUMIDITY)) {
			availableFeatures.add(AndroMote2Feature.HUMIDITY_SENSOR);
		}
	}

	private void checkMagneticSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_MAGNETIC_FIELD)) {
			availableFeatures.add(AndroMote2Feature.MAGNETIC_FIELD_SENSOR);
		}
	}

	private void checkAccelerometerSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_ACCELEROMETER)) {
			availableFeatures.add(AndroMote2Feature.ACCELEROMETER_SENSOR);
		}
	}

	private void checkProximitySensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_PROXIMITY)) {
			availableFeatures.add(AndroMote2Feature.PROXIMITY_SENSOR);
		}
	}

	private void checkTemperatureSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)) {
			availableFeatures.add(AndroMote2Feature.TEMPERATURE_SENSOR);
		}
	}

	private void checkGyroscopeSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_GYROSCOPE)) {
			availableFeatures.add(AndroMote2Feature.GYROSCOPE_SENSOR);
		}

	}

	private void checkLightSensorCapability() {
		if(deviceHasSensor(Sensor.TYPE_LIGHT)) {
			availableFeatures.add(AndroMote2Feature.LIGHT_SENSOR);
		}
	}

	private void checkLocationProvider() {
		if(deviceHasFeature(PackageManager.FEATURE_LOCATION)) {
			availableFeatures.add(AndroMote2Feature.LOCATION);
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
		boolean hasSystemConcreteFeature = packageManager.hasSystemFeature(feature);
		if(hasSystemConcreteFeature) {
			log.info(feature + " exists");
		} else {
			log.info(feature + " is not present");
		}
		return hasSystemConcreteFeature;
	}

	/* (non-Javadoc)
	 * @see pl.fester3k.andromote.functionalityFramework.ICapabilitiesAnalyzer#getAvailableFeatures()
	 */
	@Override
	public String getAvailableFeatures() {
		return "Available features: " + printAvailable();		
	}
	
	private String printAvailable() {
		String availableConcreteFeaturesString = "";
		for (Feature feature : availableFeatures) {
			availableConcreteFeaturesString = availableConcreteFeaturesString + " " + feature + ",\n"; 
		}
		return availableConcreteFeaturesString;
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
