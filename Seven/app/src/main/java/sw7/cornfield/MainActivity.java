package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MainActivity extends Activity {

    private GPS Gps;
    private DataAccumulator Accumulator;
    private Context mainContext;

    public static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set context for the main activity to be used internally in this class
        mainContext = this.getApplicationContext();

        client = new Client();

        Gps = new GPS(mainContext);
        Accumulator = new DataAccumulator(Gps);
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