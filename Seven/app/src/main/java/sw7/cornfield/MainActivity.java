package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MainActivity extends Activity {

    private TextView LatView;
    private TextView LngView;
    private GoogleMap MapView;
    private PhoneInfo Phone;
    private GPS Gps;
    private DataAccumulator Accumulator;

    public static Context mainContext;
    public static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LatView = (TextView) findViewById(R.id.lat);
        LngView = (TextView) findViewById(R.id.lng);
        MapView = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        MapView.setMyLocationEnabled(true);

        // Set context for the main activity to be used internally in this class
        mainContext = this.getApplicationContext();

        client = new Client();

        Phone = new PhoneInfo(mainContext);
        Gps = new GPS(mainContext, LatView, LngView, MapView);
        Accumulator = new DataAccumulator(Phone, Gps);
    }


    @Override
    public void onStart() {
        super.onStart();
        Gps.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        Gps.stop();
    }
}