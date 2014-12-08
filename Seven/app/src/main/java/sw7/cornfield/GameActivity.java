package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
import android.graphics.Camera;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class GameActivity extends Activity {

    private GPS Gps;
    private DataAccumulator Accumulator;
    private Client client;
    public GoogleMap GameMap;

    private Integer UserId;
    private Integer GameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        UserId = getIntent().getIntExtra("UserId", -1);
        GameId = getIntent().getIntExtra("GameId", -1);

        GameMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.GameMapFragment)).getMap();

        GameMap.setMyLocationEnabled(true);
        GameMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        GameMap.getUiSettings().setAllGesturesEnabled(false);
        GameMap.getUiSettings().setZoomControlsEnabled(false);
        GameMap.getUiSettings().setZoomGesturesEnabled(true);

        //Stuff
        client = new Client();
        Gps = new GPS(this);
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