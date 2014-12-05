package sw7.cornfield;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class LobbyActivity extends Activity {

    Button EnterGameButton;
    Button SettingsButton;
    Button InvitePlayersButton;
    GoogleMap PlayArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        EnterGameButton = (Button) findViewById(R.id.EnterGame);
        SettingsButton = (Button) findViewById(R.id.GameSettings);
        InvitePlayersButton = (Button) findViewById(R.id.InvitePlayers);
        PlayArea = ((MapFragment) getFragmentManager().findFragmentById(R.id.Map)).getMap();

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

        EnterGameButton.setOnClickListener(EnterGameListener);
        SettingsButton.setOnClickListener(SettingsListener);
        InvitePlayersButton.setOnClickListener(InvitePlayersListener);
    }

    View.OnClickListener SettingsListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LobbyActivity.this, CreateGameActivity.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener InvitePlayersListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LobbyActivity.this, InvitePlayersActivity.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener EnterGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LobbyActivity.this, GameActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
