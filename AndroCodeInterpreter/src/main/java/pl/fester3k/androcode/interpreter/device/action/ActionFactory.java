package pl.fester3k.androcode.interpreter.device.action;

import java.util.Properties;

import android.app.Activity;
import android.content.Context;
import pl.fester3k.androcode.datatypes.Feature;
import pl.fester3k.androcode.interpreter.device.action.phone.CameraAction;
import pl.fester3k.androcode.interpreter.device.action.phone.DummyAction;
import pl.fester3k.androcode.interpreter.device.action.phone.FlashlightAction;
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
	private Context context;

	public ActionFactory(Context context) {
		this.context = context;
	}

	public Action createAction(Feature feature, Properties params) {
		Action action = null;
		switch (feature) {
		case ACCELEROMETER_SENSOR:
			action = new AccelerometerSensorAction(context);
			break;
		case AUDIO_SENSOR:
			action = new DummyAction(context, params);
			break;
		case GRAVITY_SENSOR:
			action = new GravitySensorAction(context);
			break;
		case GYROSCOPE_SENSOR:
			action = new GyroscopeSensorAction(context);
			break;
		case HUMIDITY_SENSOR:
			action = new HumidityAction(context);
			break;
		case LIGHT_SENSOR:
			action = new LightSensorAction(context);
			break;
		case LINEAR_ACCELERATION_SENSOR:
			action = new LinearAccelerationSensorAction(context);
			break;
		case MAGNETIC_FIELD_SENSOR:
			action = new MagneticFieldSensorAction(context);
			break;
		case PRESSURE_SENSOR:
			action = new PressureSensorAction(context);
			break;
		case PROXIMITY_SENSOR:
			action = new ProximitySensorAction(context);
			break;
		case TEMPERATURE_SENSOR:
			action = new TemperatureSensorAction(context);
			break;
		case AUDIO_IN:
			action = new DummyAction(context, params);
			break;
		case AUDIO_OUT:
			action = new DummyAction(context, params);
			break;
		case BLUETOOTH_CONNECTION:
			action = new DummyAction(context, params);
			break;
		case CAMERA:
			action = new CameraAction(context, params);
			break;
		case DATA_TRANSFER_CONNECTION:
			action = new DummyAction(context, params);
			break;
		case FLASHLIGHT:
			action = new FlashlightAction(context, params);
			break;
		case INTERNET_CONNECTION:
			action = new DummyAction(context, params);
			break;
		case LOCATION:
			action = new DummyAction(context, params);
			break;
		case LOCATION_GPS:
			action = new DummyAction(context, params);
			break;
		case RIDE:
			action = new SimpleRideAction(context, params);
			break;
		case TELEPHONY_CONNECTION:
			action = new DummyAction(context, params);
			break;
		case TETHERING:
			action = new DummyAction(context, params);
			break;
		case WIFI_CONNECTION:
			action = new DummyAction(context, params);
			break;
		default:
			action = new DummyAction(context, params);
			break;
		}
		return action;
	}
}
