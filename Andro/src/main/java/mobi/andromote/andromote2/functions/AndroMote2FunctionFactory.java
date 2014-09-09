package mobi.andromote.andromote2.functions;

import mobi.andromote.andromote2.functions.Feature;
import pl.fester3k.andromote.functionalityFramework.functions.Function;
import pl.fester3k.andromote.functionalityFramework.functions.FunctionFactory;
import pl.fester3k.andromote.functionalityFramework.functions.phone.CompassFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.DummyFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.EmailFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.FlashlightFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.LocationFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.PictureFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.RecAudioFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.RecVideoFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.SMSFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.TextToSpeechFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.WiFiConnectFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.AccelerometerSensorFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.AudioSensorFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.GravitySensorFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.GyroscopeSensorFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.HumidityFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.LightSensorFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.LinearAccelerationSensorFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.MagneticFieldSensorFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.PressureSensorFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.ProximitySensorFunction;
import pl.fester3k.andromote.functionalityFramework.functions.phone.sensors.TemperatureSensorFunction;
import pl.fester3k.andromote.functionalityFramework.functions.ride.RideBearingFunction;
import pl.fester3k.andromote.functionalityFramework.functions.ride.RideFunction;
import pl.fester3k.andromote.functionalityFramework.functions.ride.RideGPSFunction;
import pl.fester3k.andromote.functionalityFramework.functions.ride.RideSetupFunction;
import android.content.Context;
import android.os.Handler;

public class AndroMote2FunctionFactory implements FunctionFactory {
	private final Context context;
	private final Handler handler;

	public AndroMote2FunctionFactory(Context context, Handler handler) {
		this.context = context;
		this.handler = handler;
	}

	public Function create(String functionName) {
		Function action = null;
		Feature specificFeature = Feature.valueOf(functionName);
			 
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
