package pl.fester3k.andromote.functionalityFramework.functions.phone;

import java.util.List;

import pl.fester3k.andromote.functionalityFramework.datatypes.ActionParams;
import pl.fester3k.andromote.functionalityFramework.datatypes.ActionParams.Others;
import pl.fester3k.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

public class LocationFunction extends BaseDeviceFunction implements LocationListener {
	private LocationManager locationManager;
	private Location location;
	private final Handler handler;
	public LocationFunction(Context context, Handler handler) {
		super(context);		
		locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		location = getLastKnownLocation();
		this.handler = handler;
	}

	@Override
	public Object run() {
		double result = 0.0;
		handler.post( new Runnable() {
			@Override
			public void run() {
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000L, 4.f, LocationFunction.this);
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000L, 4.f, LocationFunction.this);
			}
		});
		if(params.containsKey(ActionParams.Others.GET)) {
			String valueLabel = (String)params.get(ActionParams.Others.GET);
			if(valueLabel.equals("LAT")) {
				result = location.getLatitude();
			}
			if(valueLabel.equals("LONG")) {
				result = location.getLongitude();
			}
			if(valueLabel.equals("SPEED")) {
				result = location.getSpeed();
			}
			if(valueLabel.equals("ALT")) {
				result = location.getAltitude();
			}
		}
		return result;
	}
	
	@Override
	public void cleanup() {
		locationManager.removeUpdates(this);
		super.cleanup();
	}

	@Override
	public void onLocationChanged(Location location) {
		logger.debug("Location changed to " + location);
		this.location = location;
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}

	@Override
	public void onProviderEnabled(String provider) {}

	@Override
	public void onProviderDisabled(String provider) {}

	private Location getLastKnownLocation() {
	    List<String> providers = locationManager.getProviders(true);
	    Location bestLocation = null;
	    for (String provider : providers) {
	        Location l = locationManager.getLastKnownLocation(provider);
	        logger.debug("last known location, provider: %s, location: %s", provider,
	                l);

	        if (l == null) {
	            continue;
	        }
	        if (bestLocation == null
	                || l.getAccuracy() < bestLocation.getAccuracy()) {
	        	logger.debug("found best last known location: %s", l);
	            bestLocation = l;
	        }
	    }
	    if (bestLocation == null) {
	        return null;
	    }
	    return bestLocation;
	}
	
	@Override
	public void putParam(String propertyName, String value) {
		params.put(Others.valueOf(propertyName), value);
	}
}
