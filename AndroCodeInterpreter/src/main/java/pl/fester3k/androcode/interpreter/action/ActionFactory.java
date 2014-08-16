package pl.fester3k.androcode.interpreter.action;

import pl.fester3k.androcode.datatypes.Feature;

public class ActionFactory {
	public static Action createAction(Feature feature) {
		Action action = null;
		switch (feature) {
		case ACCELEROMETER_SENSOR:

			break;
		case AUDIO_SENSOR:

			break;
		case GRAVITY_SENSOR:

			break;
		case GYROSCOPE_SENSOR:

			break;
		case HUMIDITY_SENSOR:

			break;
		case LIGHT_SENSOR:

			break;
		case LINEAR_ACCELERATION_SENSOR:

			break;
		case MAGNETIC_FIELD_SENSOR:

			break;
		case PRESSURE_SENSOR:

			break;
		case PROXIMITY_SENSOR:

			break;
		case TEMPERATURE_SENSOR:

			break;
		case AUDIO_IN:

			break;
		case AUDIO_OUT:

			break;
		case BLUETOOTH_CONNECTION:

			break;
		case CAMERA:

			break;
		case DATA_TRANSFER_CONNECTION:

			break;
		case FLASHLIGHT:

			break;
		case INTERNET_CONNECTION:

			break;
		case LOCATION:

			break;
		case LOCATION_GPS:

			break;
		case RIDE:

			break;
		case TELEPHONY_CONNECTION:

			break;
		case TETHERING:

			break;
		case WIFI_CONNECTION:

			break;
		default:
			break;
		}
		return action;
	}
}
