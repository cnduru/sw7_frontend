package sw7.cornfield;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends Activity {

    private GPS Gps;
    private DataAccumulator Accumulator;
    private Client client;
    public GoogleMap GameMap;

    private Integer UserId;
    private Integer GameId;
    private Context ActivityContext;

    private List<PositionPair> PlayerLocations;
    private List<UserMarker> PlayerMarkers;

    CountDownTimer Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ActivityContext = this;

        UserId = getIntent().getIntExtra("UserId", -1);
        GameId = getIntent().getIntExtra("GameId", -1);
        PlayerLocations = new ArrayList<PositionPair>();
        PlayerMarkers = new ArrayList<UserMarker>();

        GameMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.GameMapFragment)).getMap();

        GameMap.setMyLocationEnabled(true);
        GameMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        GameMap.getUiSettings().setZoomControlsEnabled(false);
        GameMap.getUiSettings().setMyLocationButtonEnabled(false);
        GameMap.getUiSettings().setScrollGesturesEnabled(false);


        client = new Client();
        Gps = new GPS(this);

        Timer = new CountDownTimer(4000,4000) {

            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                if (Gps.getCurrentLocation() != null) {
                    Client dataClient = new Client();
                    dataClient.send(EncodeActionXML.gameUpdate(UserId, GameId, Gps.getCurrentLocation()));
                    PlayerLocations = DecodeActionXML.gameUpdate(dataClient.read());
                    dataClient.close();
                    GameMap.clear();
                    PlayerMarkers.clear();

                    for (PositionPair pp : PlayerLocations) {
                        PlayerMarkers.add(new UserMarker(pp.getUserId(), pp.getPosition()));
                    }

                    for (UserMarker marker : PlayerMarkers) {
                        GameMap.addMarker(marker.markerOptions);
                    }
                }
                Timer.start();
            }
        };
        Timer.start();
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

    GoogleMap.OnMarkerClickListener MarkerListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            Integer userId = Integer.parseInt(marker.getTitle().toString());
            shootDialog.setTitle("User " + userId);
            shootDialog.show();
            return true;
        }
    };

    AlertDialog shootDialog = new AlertDialog.Builder(ActivityContext)
            .setPositiveButton("Shoot", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            //Shoot the guy
        }
    })
            .setNegativeButton("Back", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            shootDialog.dismiss();
        }
    })
            .setIcon(android.R.drawable.ic_dialog_alert).show();
}