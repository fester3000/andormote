package pl.fester3k.androcode.deviceManagement.action.phone.helpers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Klasa obsługująca sensory położenia telefonu
 * 
 * @author Maciej Gzik
 * 
 */
public class Compass implements SensorEventListener {

	/**
	 * Liczba sekund w czasie których sesnory powinny zostać zarejstrowane.
	 * Wartość dobrana na podstawie testów.
	 */
	public static final int SENSORS_REGISTERING_TIMEOUT = 10;

	/**
	 * Stała wartość wykorzystywana do uśredniania wyników otrzymanych z
	 * sensorów. Zastosowany filtr dolnoprzepustowy pozwala zniwelować błędy
	 * sensorów, które pojawiają się przy wykonywaniu ruchu telefonem.
	 * Wskazywane są wtedy nieprawidłowe wartości np. kolejne wskazania azymutu
	 * różniły się od siebie o 180 stopni. Zastosowany algorytm jest
	 * wykorzystywany w systemie iOS.
	 */
	private static float ALPHA = 0.2f;

	private Context appContext = null;

	private SensorManager mSensorManager = null;
	Sensor accelerometer = null;
	Sensor magnetometer = null;

	float[] mAcceleration;
	float[] mGeomagnetic;

	float R[] = new float[9];
	float I[] = new float[9];

	float orientation[] = new float[3];

	private String TAG = Compass.class.getName();
	private Logger logger = LoggerFactory.getLogger(Compass.class);

	private double azimut;

	private double pitch;

	private double roll;

	private double bearing;

	private int accelerometerSensorAccuracy = 0;
	private int magneticFieldSensorAccuracy = 0;

	private int setBearingIntervalCounter = 0;

	private int sensorRefreshCounter;

	public Compass(Context context) {
		if (context != null) {
			logger.debug(TAG, "CompassLib: init compass");
			this.appContext = context;
			mSensorManager = (SensorManager) this.appContext.getSystemService(Context.SENSOR_SERVICE);
			accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

			// registerListeners(SensorManager.SENSOR_DELAY_GAME);
		} else {
			logger.debug(TAG, "AndroMoteCompassLib cannot be initialized: application context is null");
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// logger.debug(TAG, "Compass: sensorAccuracyChanged: " +
		// sensor.getName() + "; new accuracy: " + accuracy);
		if (sensor.getType() == Sensor.TYPE_ACCELEROMETER)
			this.setAccelerometerSensorAccuracy(accuracy);
		else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
			this.setMagneticFieldSensorAccuracy(accuracy);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (sensorRefreshCounter++ % 5 == 0) {
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				mAcceleration = lowPassFilter(event.values.clone(), mAcceleration);
			}
			if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				mGeomagnetic = lowPassFilter(event.values.clone(), mGeomagnetic);
			}
			if (mAcceleration != null && mGeomagnetic != null) {
				if (setBearingIntervalCounter++ % 5 == 0) {
					boolean success = SensorManager.getRotationMatrix(R, I, mAcceleration, mGeomagnetic);
					// logger.debug(TAG, "success=" + success);
					if (success) {
						SensorManager.getOrientation(R, orientation);
						setAzimut(Math.toDegrees(orientation[0]));
						setPitch(Math.toDegrees(orientation[1]));
						setRoll(Math.toDegrees(orientation[2]));

						if (azimut < 0) {
							this.setBearing(360 + azimut);
						} else {
							this.setBearing(azimut);
						}
					}
				}
			}
		}
	}

	/**
	 * Usunięcie listenerów z sensor managera
	 */
	public void unregisterListeners() {
		mSensorManager.unregisterListener(this);

		this.setAccelerometerSensorAccuracy(0);
		this.setMagneticFieldSensorAccuracy(0);
	}

	/**
	 * Rejestracja listenerów TYPE_ACCELEROMETER oraz TYPE_MAGNETIC_FIELD w
	 * wybranym typem otrzymywania danych
	 * 
	 * @param sensorDelayType
	 *            Identyfiaktor typu rejestracji sensora z klasy
	 *            android.hardware.SensorManager. Określa szybkość otrzymywanych
	 *            danych
	 */
	public void registerListeners(int sensorDelayType) {
		logger.debug(TAG, "Compass: registering compass listeners...");
		mSensorManager = (SensorManager) this.appContext.getSystemService(Context.SENSOR_SERVICE);

		mSensorManager.registerListener(this, accelerometer, sensorDelayType);
		mSensorManager.registerListener(this, magnetometer, sensorDelayType);
		logger.debug(TAG, "Compass: registering compass listeners...");
	}

	/**
	 * Filtr dolnoprzepustowy filtrujący otrzymane wyniki pod względem
	 * nieprawidłowych wskazań żyroskopu.
	 * 
	 * @param input
	 *            Zestaw danych wejściowych
	 * @param output
	 * @return
	 */
	public float[] lowPassFilter(float[] input, float[] output) {
		if (output == null) {
			logger.debug(TAG, "output is null; input[0]: " + input[0]);
			return input;
		}

		if (output[0] == 0 && output[1] == 0 && output[2] == 0) {
			logger.debug(TAG, "output values = 0");
			return input;
		}

		for (int i = 0; i < input.length; i++) {
			output[i] = (input[i] * ALPHA) + (output[i] * (1.0f - ALPHA));
		}

		return output;
	}

	// /////////////////////////////// getters setters

	public double getAzimut() {
		return azimut;
	}

	public double getPitch() {
		return pitch;
	}

	public double getRoll() {
		return roll;
	}

	/**
	 * Funkcja zwracająca wartość azymutu w stopniach.
	 * 
	 * @return azymut
	 */
	public double getBearing() {
		return bearing;
	}

	/**
	 * Funkcja zwraca kierunek telefonu w stopniach.
	 * 
	 * @param bearing
	 *            kierunek telefonu
	 */
	public void setBearing(double bearing) {
		// logger.debug(TAG, "zmiana położenia w stopniach: " + degrees);
		this.bearing = bearing;
	}

	public void setAzimut(double azimut) {
		this.azimut = azimut;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	public void setRoll(double roll) {
		this.roll = roll;
	}

	public int getAccelerometerSensorAccuracy() {
		return accelerometerSensorAccuracy;
	}

	public void setAccelerometerSensorAccuracy(int accelerometerSensorAccuracy) {
		this.accelerometerSensorAccuracy = accelerometerSensorAccuracy;
	}

	public int getMagneticFieldSensorAccuracy() {
		return magneticFieldSensorAccuracy;
	}

	public void setMagneticFieldSensorAccuracy(int magneticFieldSensorAccuracy) {
		this.magneticFieldSensorAccuracy = magneticFieldSensorAccuracy;
	}

}
