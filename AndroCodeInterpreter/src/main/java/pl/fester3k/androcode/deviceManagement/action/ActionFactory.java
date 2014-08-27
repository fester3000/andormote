package pl.fester3k.androcode.deviceManagement.action;

import pl.fester3k.androcode.datatypes.Feature;
import pl.fester3k.androcode.deviceManagement.action.phone.CameraAction;
import pl.fester3k.androcode.deviceManagement.action.phone.DummyAction;
import pl.fester3k.androcode.deviceManagement.action.phone.FlashlightAction;
import pl.fester3k.androcode.deviceManagement.action.phone.RecAudioAction;
import pl.fester3k.androcode.deviceManagement.action.phone.RecVideoAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.AccelerometerSensorAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.GravitySensorAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.GyroscopeSensorAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.HumidityAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.LightSensorAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.LinearAccelerationSensorAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.MagneticFieldSensorAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.PressureSensorAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.ProximitySensorAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.TemperatureSensorAction;
import pl.fester3k.androcode.deviceManagement.action.ride.SimpleRideAction;
import android.content.Context;

public class ActionFactory {
	private Context context;

	public ActionFactory(Context context) {
		this.context = context;
	}

	public Action createAction(Feature feature) {
		Action action = null;
		switch (feature) {
		case ACCELEROMETER_SENSOR:
			action = new AccelerometerSensorAction(context);
			break;
		case AUDIO_SENSOR:
			action = new DummyAction(context);
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
			action = new RecAudioAction(context);
			break;
		case AUDIO_OUT:
			action = new DummyAction(context);
			break;
//		case BLUETOOTH_CONNECTION:
//			action = new DummyAction(context, feature);
//			break;
		case CAMERA:
			action = new CameraAction(context);
			break;
		case RECORD_VIDEO:
			action = new RecVideoAction(context);
			break;
		case FLASHLIGHT:
			action = new FlashlightAction(context);
			break;
		case EMAIL_SENDER:
			action = new DummyAction(context);
			break;
		case LOCATION:
			action = new DummyAction(context);
			break;
		case LOCATION_GPS:
			action = new DummyAction(context);
			break;
		case RIDE:
			action = new SimpleRideAction(context);
			break;
		case SMS_SENDER:
			action = new DummyAction(context);
			break;
		case TETHERING:
			action = new DummyAction(context);
			break;
		case WIFI_CONNECTION:
			action = new DummyAction(context);
			break;
		default:
			action = new DummyAction(context);
			break;
		}
		return action;
	}
}
