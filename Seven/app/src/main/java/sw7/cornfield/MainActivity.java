package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import java.sql.Statement;
import java.sql.*;

public class MainActivity extends Activity {
    public static Context mainContext;
    LocationManager locMan;
    GPSListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempButtons();

        //Johan known what this is for...
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        // Set context for the main activity to be used internally in this class
        mainContext = this.getApplicationContext();
        GpsApi gps = new GpsApi();
        GpsApi.LocationClass lc = gps.askForGps(mainContext);
        locMan = lc.locationManager;
        gps.updateGPS(lc.location);

        final PhoneInfo info = new PhoneInfo();
        info.intializePhoneData(mainContext);

        locationListener = new GPSListener();
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
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10000, 0,
                locationListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        locMan.removeUpdates(locationListener);
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
