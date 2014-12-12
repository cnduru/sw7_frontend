package sw7.Cornfieldz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends Activity {
    Context ActivityContext;

    public GoogleMap GameMap;
    GPS Gps;
    List<User> PlayerLocations = new ArrayList<User>();
    User ClickedPlayer;

    Integer UserId;
    Integer GameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ActivityContext = this;

        UserId = getIntent().getIntExtra("UserId", -1);
        GameId = getIntent().getIntExtra("GameId", -1);

        GameMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.GameMapFragment)).getMap();
        GameMap.setMyLocationEnabled(true);
        GameMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        GameMap.getUiSettings().setZoomControlsEnabled(false);
        GameMap.getUiSettings().setMyLocationButtonEnabled(false);
        GameMap.getUiSettings().setScrollGesturesEnabled(false);
        GameMap.setOnMarkerClickListener(PlayerClickListener);

        Gps = new GPS(this);
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

    GoogleMap.OnMarkerClickListener PlayerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            ClickedPlayer = PlayerLocations.get(Integer.parseInt(marker.getTitle()));
            ShootDialog.create();
            ShootDialog.show();
            return false;
        }
    };


    AlertDialog.Builder ShootDialog = new AlertDialog.Builder(ActivityContext)
            .setTitle(ClickedPlayer.getUsername())
            .setPositiveButton("Shoot", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Client client = new Client();
                    client.send(EncodeActionXML.shootAction(GameId, UserId, ClickedPlayer.getUserId(), 1));
                    if (DecodeActionXML.shootAction(client.read())) {
                        Toast success = Toast.makeText(getApplicationContext(), "You shot " + ClickedPlayer.getUsername() + "!", Toast.LENGTH_SHORT);
                        success.show();
                    } else {
                        Toast failure = Toast.makeText(getApplicationContext(), "You missed" + ClickedPlayer.getUsername() + " :(", Toast.LENGTH_SHORT);
                        failure.show();
                    }
                    client.close();
                }
            })
            .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });

    CountDownTimer Timer = new CountDownTimer(4000, 4000) {

        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            if (Gps.getCurrentLocation() != null) {
                Client client = new Client();
                client.send(EncodeActionXML.gameUpdate(UserId, GameId, Gps.getCurrentLocation()));
                PlayerLocations = DecodeActionXML.gameUpdate(client.read());
                client.close();
                GameMap.clear();

                for (Integer i = 0; i < PlayerLocations.size(); i++) {
                    MarkerOptions options = new MarkerOptions();
                    options.title(i.toString());
                    options.position(PlayerLocations.get(i).getPosition());
                    GameMap.addMarker(options);
                }
            }
            Timer.start();
        }
    };
}