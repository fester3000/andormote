package mobi.andromote.am2.functions;

import mobi.andromote.am2.functions.phone.CompassFunction;
import mobi.andromote.am2.functions.phone.DummyFunction;
import mobi.andromote.am2.functions.phone.EmailFunction;
import mobi.andromote.am2.functions.phone.FlashlightFunction;
import mobi.andromote.am2.functions.phone.LocationFunction;
import mobi.andromote.am2.functions.phone.PictureFunction;
import mobi.andromote.am2.functions.phone.RecAudioFunction;
import mobi.andromote.am2.functions.phone.RecVideoFunction;
import mobi.andromote.am2.functions.phone.SMSFunction;
import mobi.andromote.am2.functions.phone.TextToSpeechFunction;
import mobi.andromote.am2.functions.phone.WiFiConnectFunction;
import mobi.andromote.am2.functions.phone.sensors.AccelerometerSensorFunction;
import mobi.andromote.am2.functions.phone.sensors.AudioSensorFunction;
import mobi.andromote.am2.functions.phone.sensors.GravitySensorFunction;
import mobi.andromote.am2.functions.phone.sensors.GyroscopeSensorFunction;
import mobi.andromote.am2.functions.phone.sensors.HumidityFunction;
import mobi.andromote.am2.functions.phone.sensors.LightSensorFunction;
import mobi.andromote.am2.functions.phone.sensors.LinearAccelerationSensorFunction;
import mobi.andromote.am2.functions.phone.sensors.MagneticFieldSensorFunction;
import mobi.andromote.am2.functions.phone.sensors.PressureSensorFunction;
import mobi.andromote.am2.functions.phone.sensors.ProximitySensorFunction;
import mobi.andromote.am2.functions.phone.sensors.TemperatureSensorFunction;
import mobi.andromote.am2.functions.ride.RideBearingFunction;
import mobi.andromote.am2.functions.ride.RideFunction;
import mobi.andromote.am2.functions.ride.RideGPSFunction;
import mobi.andromote.am2.functions.ride.RideSetupFunction;
import mobi.andromote.functionalityFramework.functions.Function;
import mobi.andromote.functionalityFramework.functions.FunctionFactory;
import android.content.Context;
import android.os.Handler;

public class AndroMote2FunctionFactory implements FunctionFactory {
	private final Context context;
	private final Handler handler;

	public AndroMote2FunctionFactory(Context context, Handler handler) {
		this.context = context;
		this.handler = handler;
	}

	@Override
	public Function create(String functionName) {
		Function action = null;
		AndroMote2Feature specificFeature = AndroMote2Feature.valueOf(functionName);
			 
		switch (specificFeature) {
		case ACCELEROMETER_SENSOR:
			action = new AccelerometerSensorFunction(context);
			break;
		case AUDIO_SENSOR:
			action = new AudioSensorFunction(context, handler);
			break;
		case GRAVITY_SENSOR:
			action = new GravitySensorFunction(context);
			break;
		case GYROSCOPE_SENSOR:
			action = new GyroscopeSensorFunction(context);
			break;
		case HUMIDITY_SENSOR:
			action = new HumidityFunction(context);
			break;
		case LIGHT_SENSOR:
			action = new LightSensorFunction(context);
			break;
		case LINEAR_ACCELERATION_SENSOR:
			action = new LinearAccelerationSensorFunction(context);
			break;
		case MAGNETIC_FIELD_SENSOR:
			action = new MagneticFieldSensorFunction(context);
			break;
		case PRESSURE_SENSOR:
			action = new PressureSensorFunction(context);
			break;
		case PROXIMITY_SENSOR:
			action = new ProximitySensorFunction(context);
			break;
		case TEMPERATURE_SENSOR:
			action = new TemperatureSensorFunction(context);
			break;
		case RECORD_AUDIO:
			action = new RecAudioFunction(context);
			break;
//		case AUDIO_OUT:
//			action = new AudioOut(context);
//			break;
		case SMS:
			action = new SMSFunction(context);
			break;
		case WIFI_CONNECT:
			action = new WiFiConnectFunction(context);
			break;
		case COMPASS:
			action = new CompassFunction(context);
			break;
		case TTS:
			action = new TextToSpeechFunction(context);
			break;
		case PICTURE:
			action = new PictureFunction(context);
			break;
		case RECORD_VIDEO:
			action = new RecVideoFunction(context);
			break;
		case FLASHLIGHT:
			action = new FlashlightFunction(context);
			break;
		case EMAIL:
			action = new EmailFunction(context);
			break;
		case LOCATION:
			action = new LocationFunction(context, handler);
			break;
		case RIDE:
			action = new RideFunction(context);
			break;
		case RIDE_SETUP:
			action = new RideSetupFunction(context);
			break;
		case RIDE_BEARING:
			action = new RideBearingFunction(context);
			break;
		case RIDE_GPS:
			action = new RideGPSFunction(context);
			break;
		default:
			action = new DummyFunction(context);
			break;
		}
		return action;
	}
}
