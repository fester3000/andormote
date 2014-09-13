package mobi.andromote.am2.functions;

import mobi.andromote.functionalityFramework.datatypes.Feature;


//TODO Zabezpieczyc sie przed bledami w pisowni feature
public enum AndroMote2Feature implements Feature {
	RIDE, //DONE
	RIDE_SETUP,
	RIDE_BEARING,
	RIDE_GPS,	
	SMS, //DONE
	EMAIL, //DONE
	WIFI_CONNECT, //DONE
	PICTURE,  //DONE 
	RECORD_VIDEO, //DONE
	FLASHLIGHT, //DONE

	RECORD_AUDIO, 	//DONE
	TTS, 		//DONE //TODO Dodać w przyszłości tryb nadpisywania i oczekiwania na zakończenie 
	
	LOCATION, 		//DONE
	COMPASS,		//DONE
	LIGHT_SENSOR,   //DONE
	GRAVITY_SENSOR, //DONE 
	HUMIDITY_SENSOR,//DONE  
	LINEAR_ACCELERATION_SENSOR, //DONE 
	GYROSCOPE_SENSOR,  //DONE
	TEMPERATURE_SENSOR,  //DONE
	PROXIMITY_SENSOR, //DONE
	ACCELEROMETER_SENSOR, //DONE
	MAGNETIC_FIELD_SENSOR, //DONE
	AUDIO_SENSOR, 	//DONE
	PRESSURE_SENSOR;//DONE  
}
