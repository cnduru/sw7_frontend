package sw7.cornfield;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Morten on 23-09-2014.
 */
public class GPSListener implements LocationListener {

    Location CurrentLocation;

    public void onLocationChanged(Location location) {
        this.CurrentLocation = location;
        new GpsApi().updateGPS(location);
        MainActivity.map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(MainActivity.map.getMyLocation().getLatitude(), MainActivity.map.getMyLocation().getLongitude())));
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

    public Location getCurrentLocation() {
        return CurrentLocation;
    }
};

