package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.sql.Statement;
import java.sql.*;

public class MainActivity extends Activity {
    public static Context mainContext;
    public static TextView lat;
    public static TextView lng;
    public static TextView gsm;

    public static GoogleMap map;
    LocationManager locMan;
    GPSListener locationListener;
    public static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ClientListener.class);
        startActivity(intent);
        /* first of 4 blocks remove to get old func back
        client = new Client();
        tempButtons();

        //Johan known what this is for...
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        // Set context for the main activity to be used internally in this class
        mainContext = this.getApplicationContext();
        lat = (TextView) findViewById(R.id.lat);
        lng = (TextView) findViewById(R.id.lng);
        gsm = (TextView) findViewById(R.id.gsm);
        GpsApi gps = new GpsApi();
        GpsApi.LocationClass lc = gps.askForGps(mainContext);
        locMan = lc.locationManager;
        gps.updateGPS(lc.location, "0");

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lc.location.getLatitude(), lc.location.getLongitude()), 14));
        map.setMyLocationEnabled(true);

        final PhoneInfo info = new PhoneInfo();
        info.intializePhoneData(mainContext);

        locationListener = new GPSListener();
        */
    }

    public void tempButtons() {
        Button knap = (Button)findViewById(R.id.knap);
        knap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client user = new Client();
                user.close();
            }
        });

        Button dbKnap = (Button)findViewById(R.id.dbknap);
        dbKnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    insertDB();
                    System.out.println("Succes");
                } catch (Exception e) {
                    System.out.println("it broke");
                    System.out.println(e.toString());
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
/*        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10000, 0,
                locationListener);
*/    }

    @Override
    public void onPause() {
        super.onPause();
//        locMan.removeUpdates(locationListener);
    }

    public void insertDB() throws SQLException {
        dbCon con = new dbCon();
        Statement st = con.DBC.createStatement();
        String name = "johan1";
        String dist = "johansfar1";
        String ins = "INSERT INTO \"public\".\"usertable\"(\"distributer\",\"phonetype\") VALUES('" + dist + "' ,'" + name + "')";
        //String ins = "INSERT INTO public.usertable(distributer,phonetype) VALUES (" + dist + "," + "name)";
        st.execute(ins);
        con.Close();
    }
}