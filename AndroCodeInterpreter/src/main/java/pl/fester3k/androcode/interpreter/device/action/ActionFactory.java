package pl.fester3k.androcode.interpreter.device.action;

import java.util.Properties;

import android.app.Activity;
import android.content.Context;
import pl.fester3k.androcode.datatypes.Feature;
import pl.fester3k.androcode.interpreter.device.action.phone.CameraAction;
import pl.fester3k.androcode.interpreter.device.action.phone.DummyAction;
import pl.fester3k.androcode.interpreter.device.action.phone.sensors.AccelerometerSensorAction;
import pl.fester3k.androcode.interpreter.device.action.phone.sensors.GravitySensorAction;
import pl.fester3k.androcode.interpreter.device.action.phone.sensors.GyroscopeSensorAction;
import pl.fester3k.androcode.interpreter.device.action.phone.sensors.HumidityAction;
import pl.fester3k.androcode.interpreter.device.action.phone.sensors.LightSensorAction;
import pl.fester3k.androcode.interpreter.device.action.phone.sensors.LinearAccelerationSensorAction;
import pl.fester3k.androcode.interpreter.device.action.phone.sensors.MagneticFieldSensorAction;
import pl.fester3k.androcode.interpreter.device.action.phone.sensors.PressureSensorAction;
import pl.fester3k.androcode.interpreter.device.action.phone.sensors.ProximitySensorAction;
import pl.fester3k.androcode.interpreter.device.action.phone.sensors.TemperatureSensorAction;
import pl.fester3k.androcode.interpreter.device.action.ride.SimpleRideAction;

public class ActionFactory {
	private Activity activity;

	public ActionFactory(Activity activity) {
		this.activity = activity;
	}

	public Action createAction(Feature feature, Properties params) {
		Action action = null;
		switch (feature) {
		case ACCELEROMETER_SENSOR:
			action = new AccelerometerSensorAction(activity);
			break;
		case AUDIO_SENSOR:
			action = new DummyAction(activity, params);
			break;
		case GRAVITY_SENSOR:
			action = new GravitySensorAction(activity);
			break;
		case GYROSCOPE_SENSOR:
			action = new GyroscopeSensorAction(activity);
			break;
		case HUMIDITY_SENSOR:
			action = new HumidityAction(activity);
			break;
		case LIGHT_SENSOR:
			action = new LightSensorAction(activity);
			break;
		case LINEAR_ACCELERATION_SENSOR:
			action = new LinearAccelerationSensorAction(activity);
			break;
		case MAGNETIC_FIELD_SENSOR:
			action = new MagneticFieldSensorAction(activity);
			break;
		case PRESSURE_SENSOR:
			action = new PressureSensorAction(activity);
			break;
		case PROXIMITY_SENSOR:
			action = new ProximitySensorAction(activity);
			break;
		case TEMPERATURE_SENSOR:
			action = new TemperatureSensorAction(activity);
			break;
		case AUDIO_IN:
			action = new DummyAction(activity, params);
			break;
		case AUDIO_OUT:
			action = new DummyAction(activity, params);
			break;
		case BLUETOOTH_CONNECTION:
			action = new DummyAction(activity, params);
			break;
		case CAMERA:
			action = new CameraAction(activity, params);
			break;
		case DATA_TRANSFER_CONNECTION:
			action = new DummyAction(activity, params);
			break;
		case FLASHLIGHT:
			action = new DummyAction(activity, params);
			break;
		case INTERNET_CONNECTION:
			action = new DummyAction(activity, params);
			break;
		case LOCATION:
			action = new DummyAction(activity, params);
			break;
		case LOCATION_GPS:
			action = new DummyAction(activity, params);
			break;
		case RIDE:
			action = new SimpleRideAction(activity, params);
			break;
		case TELEPHONY_CONNECTION:
			action = new DummyAction(activity, params);
			break;
		case TETHERING:
			action = new DummyAction(activity, params);
			break;
		case WIFI_CONNECTION:
			action = new DummyAction(activity, params);
			break;
		default:
			action = new DummyAction(activity, params);
			break;
		}
		return action;
	}
}
