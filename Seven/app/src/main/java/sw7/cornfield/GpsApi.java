package sw7.cornfield;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import javax.xml.datatype.Duration;

public class GpsApi
{
    LocationManager service;
    static LocListener loc;

    public void askForGps(Context context)
    {
        // this code checks whether the gps is enabled. If not, the user is sent to the screen
        // where the gps can be activated
        service = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
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

            // start the GPS listener
            loc = new LocListener();
        }
    }

    public double getLon()
    {
        return loc.getLon();
    }

    public double getLat()
    {
        return loc.getLat();
    }
}
