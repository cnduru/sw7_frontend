package sw7.cornfield;

import android.content.Context;
import android.location.LocationManager;
import android.widget.Toast;
import android.location.Criteria;
import android.location.Location;
public class GpsApi
{
    LocationManager service;
    Double latitude = .0;
    Double longitude = .0;
    Context context;

    public LocationClass askForGps(Context con)
    {
        // this code checks whether the gps is enabled. If not, the user is sent to the screen
        // where the gps can be activated
        context = con;
        service = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, false);
        Location location = service.getLastKnownLocation(provider);

        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);


        if (!enabled)
        {
            //Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            //context.startActivity(intent);
            Toast.makeText(context, "GPS is not enabled.",
                    Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "GPS is enabled.",
                    Toast.LENGTH_SHORT).show();
        }

        return new LocationClass(service, location);
    }


    public void updateGPS(Location loc, String gsmStr) {

        MainActivity.lat.setText(String.format("Lat: %f", loc.getLatitude()));
        MainActivity.lng.setText(String.format("Lng: %f", loc.getLongitude()));
        MainActivity.gsm.setText(String.format("Gsm: %s", gsmStr));

    }

    public class LocationClass{
        public LocationManager locationManager;
        public Location location;
        public LocationClass(LocationManager locM, Location loc){
            locationManager = locM;
            location = loc;
        }
    }
}
