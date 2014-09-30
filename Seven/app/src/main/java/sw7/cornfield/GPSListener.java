package sw7.cornfield;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Morten on 23-09-2014.
 */
public class GPSListener implements LocationListener {
    PhoneInfo info;

    public GPSListener() {
        info = new PhoneInfo();
        info.intializePhoneData(MainActivity.mainContext);
    }


    public void onLocationChanged(Location location) {
        String signal = info.getGsmStrength();
        MainActivity.map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
        MainActivity.client.sendLocation(location, signal);

    }

    public void onProviderDisabled(String provider) {
        // required for interface, not used
    }

    public void onProviderEnabled(String provider) {
        // required for interface, not used
    }

    public void onStatusChanged(String provider, int status,
                                Bundle extras) {
        // required for interface, not used
    }
};

