package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;

import org.w3c.dom.Text;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import java.sql.Statement;
import java.sql.*;
import java.util.*;

public class MainActivity extends Activity {

    public static Context mainContext;
    LocationManager locMan;
    LocationListener onLocationChange;
    private ServerSocket serverSocket;
    Handler updateConversationHandler;
    Thread serverThread = null;
    private Socket socket;
    private static final String SERVER_IP = "172.25.18.10";
    public static final int SERVERPORT = 44444;

    //TextView text = (TextView) findViewById(R.id.textView3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        // Set context for the main activity to be used internally in this class
        mainContext = this.getApplicationContext();
        GpsApi gps = new GpsApi();
        GpsApi.LocationClass lc = gps.askForGps(mainContext);
        locMan = lc.locationManager;
        updateGPS(lc.location);


        tempButtons();

        onLocationChange = new LocationListener() {
            public void onLocationChanged(Location location) {
                updateGPS(location);
                sendData(location);
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
        new Thread(new ClientThread()).start();
        sendData(lc.location);
        /*updateConversationHandler = new Handler();
        serverThread = new Thread(new ServerThread());
        serverThread.start();*/
    }

        PhoneInfo info = new PhoneInfo();
        info.intializePhoneData(mainContext);
    }
    private void sendData(Location location) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(String.format("%f,%f",
                    location.getLatitude(), location.getLongitude()));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void updateGPS(Location loc){

        TextView lat = (TextView) findViewById(R.id.lat);
        TextView lon = (TextView) findViewById(R.id.lng);

        lat.setText(String.format("%f", loc.getLatitude()));
        lon.setText(String.format("%f", loc.getLongitude()));
    }

    public void tempButtons() {
        Button knap = (Button)findViewById(R.id.knap);
        knap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client user = new Client();
                1000, 0,
            }
        });
                onLocationChange);
    }
@Override
    public void onResume() {
        super.onResume();
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10000, 0,
                onLocationChange);
    }

    @Override
    public void onPause() {
        super.onPause();
        locMan.removeUpdates(onLocationChange);
    }
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

       
 void updateGPS(Location loc) {
        TextView lon = (TextView) findViewById(R.id.textView1);
        TextView lat = (TextView) findViewById(R.id.textView2);

        lon.setText(String.format("Longitude: %f", loc.getLongitude()));
        lat.setText(String.format("Latitude: %f", loc.getLatitude()));
    }

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

    class ClientThread implements Runnable {

        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

                socket = new Socket(serverAddr, SERVERPORT);

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }
}
