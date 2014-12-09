package sw7.Cornfieldz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class LobbyActivity extends Activity {

    TextView GameNameView;
    Button EnterGameButton;
    Button SettingsButton;
    Button InvitePlayersButton;
    GoogleMap PlayArea;

    Integer UserId;
    Integer GameId;

    //Hardcoded for test
    Integer HostId = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        UserId = getIntent().getIntExtra("UserId", -1);
        GameId = getIntent().getIntExtra("GameId", -1);

        GameNameView = (TextView) findViewById(R.id.GameName);
        EnterGameButton = (Button) findViewById(R.id.EnterGame);
        SettingsButton = (Button) findViewById(R.id.GameSettings);
        InvitePlayersButton = (Button) findViewById(R.id.InvitePlayers);
        PlayArea = ((MapFragment) getFragmentManager().findFragmentById(R.id.Map)).getMap();

        EnterGameButton.setOnClickListener(EnterGameListener);

        if(HostId == UserId) {
            SettingsButton.setOnClickListener(SettingsListener);
            InvitePlayersButton.setOnClickListener(InvitePlayersListener);
        } else {
            SettingsButton.setEnabled(false);
            InvitePlayersButton.setEnabled(false);
        }

        GameNameView.setText("Name (ID " + GameId + ")");

        //Hardcoded for test
        final LatLngBounds playAreaBounds = new LatLngBounds(new LatLng(57.0046047, 9.8616402), new LatLng(57.0786811,9.9666766));

        //Get the overlaycolor and add it to the map
        BitmapDescriptor overlay = BitmapDescriptorFactory.fromResource(R.drawable.overlay);
        PlayArea.addGroundOverlay(new GroundOverlayOptions().image(overlay).positionFromBounds(playAreaBounds).transparency(0.8f));

        //Remove user controls
        PlayArea.getUiSettings().setAllGesturesEnabled(false);
        PlayArea.getUiSettings().setZoomControlsEnabled(false);

        //Move camera to play area
        PlayArea.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                PlayArea.moveCamera(CameraUpdateFactory.newLatLngBounds(playAreaBounds, 20));
            }
        });
    }

    View.OnClickListener SettingsListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LobbyActivity.this, CreateGameActivity.class);
            intent.putExtra("UserId", UserId);
            intent.putExtra("GameId", GameId);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener InvitePlayersListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LobbyActivity.this, InvitePlayersActivity.class);
            intent.putExtra("UserId", UserId);
            intent.putExtra("GameId", GameId);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener EnterGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LobbyActivity.this, GameActivity.class);
            intent.putExtra("UserId", UserId);
            intent.putExtra("GameId", GameId);
            startActivity(intent);
            finish();
        }
    };
}
