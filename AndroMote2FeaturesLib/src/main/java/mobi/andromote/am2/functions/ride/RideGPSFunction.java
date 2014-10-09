package mobi.andromote.am2.functions.ride;

import mobi.andromote.androcode.utils.Utils;
import mobi.andromote.functionalityFramework.datatypes.ActionResult;
import mobi.andromote.functionalityFramework.datatypes.FunctionParam;
import mobi.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;

public class RideGPSFunction extends BaseDeviceFunction {

	public enum RIDE_GPS implements FunctionParam {
		LAT,
		LONG,
		SPEED;
	}
	public RideGPSFunction(Context context) {
		super(context);

	}

	@Override
	public ActionResult run() {
		//TODO
//		ActionResult result;
//		Packet packet;
//		logger.debug("Ride action run");
//		if(params.containsKey("MOTION_TYPE")) {
//			String motionType = params.get("MOTION_TYPE");
//			packet = new Packet(Motion.valueOf(motionType));
//			if(params.containsKey("SPEED")) {
//				String speedParam = params.get("SPEED");
//				double speed = Double.valueOf(speedParam);
//				packet.setSpeed(speed);	
//			}
//			if(params.containsKey("SPEED_B")) {
//				String speedParam = params.get("SPEED_B");
//				double speed = Double.valueOf(speedParam);
//				packet.setSpeedB(speed);	
//			}
//			result = RideController.INSTANCE.execute(packet);
//		} else if(params.containsKey("SPEED")) {
//			String speedParam = params.get("SPEED");
//			double speed = Double.valueOf(speedParam);
//			packet = new Packet(Engine.SET_SPEED);
//			packet.setSpeed(speed);
//			result = RideController.INSTANCE.execute(packet);
//		}
//		if(params.containsKey("SPEED_B")) {
//			String speedParam = params.get("SPEED_B");
//			double speed = Double.valueOf(speedParam);
//			packet = new Packet(Engine.SET_SPEED_B);
//			packet.setSpeedB(speed);	
//			result = RideController.INSTANCE.execute(packet);
//		} else {
//			result = ActionResult.FAILED;
//		}
		return null;
	}
	
	@Override
	public void putParam(String propertyName, String value) {
		params.put(RIDE_GPS.valueOf(propertyName), value);
	}

	/**
	 * Wyliczenie dystansu pomiędzy lokacjami. Wynik zwracany w metrach.
	 * Algorytm zapewnia stosunkowo dobrą dokładnośc
	 * 
	 * @param position1
	 *            lokalizacja początkowa
	 * @param position2
	 *            lokalizacja końcowa
	 * @return wyznaczona odległość
	 * @author Maciej Gzik
	 */
	public static double calculateDistance(MyLatLng position1, MyLatLng position2) {
		double theta = position1.getLongitude() - position2.getLongitude();
		double dist = Math.sin(Utils.deg2rad(position1.getLatitude())) * Math.sin(Utils.deg2rad(position2.getLatitude()))
				+ Math.cos(Utils.deg2rad(position1.getLatitude())) * Math.cos(Utils.deg2rad(position2.getLatitude()))
				* Math.cos(Utils.deg2rad(theta));
		dist = Math.acos(dist);
		dist = Utils.rad2deg(dist);
		dist = dist * 60 * 1.1515;

		// przleiczenie wartości na metry
		dist = (dist * 1.609344) * 1000;

		return (dist);
	}

	/**
	 * Metoda obliczająca azymut jaki należy obrać, aby dostać się po linii
	 * prostej z punktu startowego do docelowego.
	 * 
	 * @param startLocation
	 *            pozycja początkowa
	 * @param destination
	 *            pozycja końcowa
	 * @return Azymut w stopniach.
	 * @author Maciej Gzik
	 */
	public static double calculateBearing(MyLatLng startLocation, MyLatLng destination) {

		double dLon = Utils.deg2rad(destination.getLongitude() - startLocation.getLongitude());
		double lat1 = Utils.deg2rad(startLocation.getLatitude());
		double lat2 = Utils.deg2rad(destination.getLatitude());
		double y = Math.sin(dLon) * Math.cos(lat2);
		double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(dLon);
		int brng = (int) ((Utils.rad2deg(Math.atan2(y, x)) + 360) % 360);
		return brng;
		//
		//

		// double R = 6371000;
		// double dLat = deg2rad(destination.latitude - startLocation.latitude);
		// double dLon = deg2rad(destination.longitude -
		// startLocation.longitude);
		// double lat1 = deg2rad(startLocation.latitude);
		// double lat2 = deg2rad(destination.latitude);
		//
		// double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon /
		// 2) * Math.sin(dLon / 2) * Math.cos(lat1)
		// * Math.cos(lat2);
		//
		// double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		// double d = R * c;
		//
		// return (d);
	}
	
	private class MyLatLng {
		private final double latitude;
		private final double longitude;
		
		public MyLatLng(double latitude, double longitude) {
			super();
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public double getLatitude() {
			return latitude;
		}

		public double getLongitude() {
			return longitude;
		}
	}
}
