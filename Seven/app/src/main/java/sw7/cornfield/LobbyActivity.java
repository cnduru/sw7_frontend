package sw7.cornfield;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LobbyActivity extends Activity {

    Button EnterGameButton;
    Button SettingsButton;
    Button InvitePlayersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        EnterGameButton = (Button) findViewById(R.id.EnterGame);
        SettingsButton = (Button) findViewById(R.id.GameSettings);
        InvitePlayersButton = (Button) findViewById(R.id.InvitePlayers);


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
