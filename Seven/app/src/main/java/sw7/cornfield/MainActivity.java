package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;

import org.w3c.dom.Text;


public class MainActivity extends Activity {

    public static Context mainContext;
    LocationManager locMan;
    LocationListener onLocationChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this sets the context for the main activity to be used internally in this class
        mainContext = this.getApplicationContext();
        GpsApi gps = new GpsApi();
        locMan = gps.askForGps(mainContext);

        onLocationChange=new LocationListener() {
            public void onLocationChanged(Location location) {
                updateGPS(location);
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

        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 0,
                onLocationChange);


    }

    @Override
    public void onResume() {
        super.onResume();
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 0,
                onLocationChange);
    }

    void updateGPS(Location loc){
        TextView lon = (TextView) findViewById(R.id.textView);
        TextView lat = (TextView) findViewById(R.id.textView2);

        lon.setText(String.format("%f",loc.getLongitude()));
        lat.setText(String.format("%f",loc.getLatitude()));

        //Toast.makeText(this, String.format(
        //        "%f,%f", loc.getLongitude(),loc.getLatitude()),Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
