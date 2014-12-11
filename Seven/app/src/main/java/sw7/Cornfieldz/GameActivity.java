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

    private GPS Gps;
    private Client client;
    public GoogleMap GameMap;

    private Integer UserId;
    private Integer GameId;
    private Context ActivityContext;

    private List<Player> PlayerLocations;

    CountDownTimer Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ActivityContext = this;

        UserId = getIntent().getIntExtra("UserId", -1);
        GameId = getIntent().getIntExtra("GameId", -1);
        PlayerLocations = new ArrayList<Player>();

        GameMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.GameMapFragment)).getMap();

        GameMap.setMyLocationEnabled(true);
        GameMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        GameMap.getUiSettings().setZoomControlsEnabled(false);
        GameMap.getUiSettings().setMyLocationButtonEnabled(false);
        GameMap.getUiSettings().setScrollGesturesEnabled(false);

        GameMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                final Player player = PlayerLocations.get(Integer.parseInt(marker.getTitle()));
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityContext);
                builder.setTitle(player.getUsername());
                builder.setPositiveButton("Shoot", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Client dataClient = new Client();
                        dataClient.send(EncodeActionXML.shootAction(GameId, UserId, player.getUserId(), 1));
                        if (DecodeActionXML.shootAction(dataClient.read())) {
                            Toast success = Toast.makeText(getApplicationContext(), "You shot " + player.getUsername() + "!", Toast.LENGTH_SHORT);
                            success.show();
                        } else {
                            Toast failure = Toast.makeText(getApplicationContext(), "You missed" + player.getUsername() + " :(", Toast.LENGTH_SHORT);
                            failure.show();
                        }
                    }
                });
                builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                AlertDialog shootDialog = builder.create();
                shootDialog.show();
                return false;
            }
        });


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
}