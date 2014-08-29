package pl.fester3k.androcode.deviceManagement.action;

import pl.fester3k.androcode.datatypes.Feature;
import pl.fester3k.androcode.deviceManagement.action.phone.AudioOut;
import pl.fester3k.androcode.deviceManagement.action.phone.CameraAction;
import pl.fester3k.androcode.deviceManagement.action.phone.DummyAction;
import pl.fester3k.androcode.deviceManagement.action.phone.EmailAction;
import pl.fester3k.androcode.deviceManagement.action.phone.LocationAction;
import pl.fester3k.androcode.deviceManagement.action.phone.FlashlightAction;
import pl.fester3k.androcode.deviceManagement.action.phone.RecAudioAction;
import pl.fester3k.androcode.deviceManagement.action.phone.RecVideoAction;
import pl.fester3k.androcode.deviceManagement.action.phone.SMSAction;
import pl.fester3k.androcode.deviceManagement.action.phone.TextToSpeechAction;
import pl.fester3k.androcode.deviceManagement.action.phone.WiFiConnectAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.AccelerometerSensorAction;
import pl.fester3k.androcode.deviceManagement.action.phone.sensors.AudioSensorAction;
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
import android.os.Handler;

public class ActionFactory {
	private final Context context;
	private final Handler handler;

	public ActionFactory(Context context, Handler handler) {
		this.context = context;
		this.handler = handler;
	}

	public Action createAction(Feature feature) {
		Action action = null;
		switch (feature) {
		case ACCELEROMETER_SENSOR:
			action = new AccelerometerSensorAction(context);
			break;
		case AUDIO_SENSOR:
			action = new AudioSensorAction(context, handler);
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
		case RECORD_AUDIO:
			action = new RecAudioAction(context);
			break;
		case AUDIO_OUT:
			action = new AudioOut(context);
			break;
		case TTS:
			action = new TextToSpeechAction(context);
			break;
		case CAMERA:
			action = new CameraAction(context);
			break;
		case RECORD_VIDEO:
			action = new RecVideoAction(context);
			break;
		case FLASHLIGHT:
			action = new FlashlightAction(context);
			break;
		case EMAIL:
			action = new EmailAction(context);
			break;
		case LOCATION:
			action = new LocationAction(context, handler);
			break;
		case RIDE:
			action = new SimpleRideAction(context);
			break;
		case SMS:
			action = new SMSAction(context);
			break;
		case WIFI_CONNECT:
			action = new WiFiConnectAction(context);
			break;
		default:
			action = new DummyAction(context);
			break;
		}
		return action;
	}
}
