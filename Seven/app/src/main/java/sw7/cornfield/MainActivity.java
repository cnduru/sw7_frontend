package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import java.sql.Statement;
import java.sql.*;

public class MainActivity extends Activity {
    public static Context mainContext;
    LocationManager locMan;
    GPSListener onLocationChange;
    private Socket socket;
    private static final String SERVER_IP = "172.25.18.10";
    public static final int SERVER_PORT = 44444;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Johan known what this is for...
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        // Set context for the main activity to be used internally in this class
        mainContext = this.getApplicationContext();
        GpsApi gps = new GpsApi();
        GpsApi.LocationClass lc = gps.askForGps(mainContext);
        locMan = lc.locationManager;
        gps.updateGPS(lc.location);

        tempButtons();

        new Thread(new ClientThread()).start();
        sendData(lc.location);


        PhoneInfo info = new PhoneInfo();
        info.intializePhoneData(mainContext);

        onLocationChange = new GPSListener();
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

    public void tempButtons() {
        Button knap = (Button)findViewById(R.id.knap);
        knap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client user = new Client();
            }
        });

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

                socket = new Socket(serverAddr, SERVER_PORT);

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }
}
