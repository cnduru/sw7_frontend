package sw7.cornfield;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Morten on 23-09-2014.
 */
public class GPSListener implements LocationListener {
    public void onLocationChanged(Location location) {
        Client client = new Client();
        client.sendData(location);
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

