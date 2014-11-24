package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class GameActivity extends Activity {

    private GPS Gps;
    private DataAccumulator Accumulator;
    private Context mainContext;

    public static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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