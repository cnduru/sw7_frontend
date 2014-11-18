package sw7.cornfield;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class OverviewActivity extends Activity {

    TextView WelcomeText;
    Button ResumeGameButton;
    Button JoinGameButton;
    Button CreateGameButton;
    Button LogOutButton;

    String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        WelcomeText = (TextView) findViewById(R.id.WelcomeText);
        ResumeGameButton = (Button) findViewById(R.id.ResumeGame);
        JoinGameButton = (Button) findViewById(R.id.JoinGame);
        CreateGameButton = (Button) findViewById(R.id.CreateGame);
        LogOutButton = (Button) findViewById(R.id.LogOut);

        CreateGameButton.setOnClickListener(CreateGameListener);
        ResumeGameButton.setOnClickListener(ResumeGameListener);
        JoinGameButton.setOnClickListener(JoinGameListener);
        LogOutButton.setOnClickListener(LogOutListener);

        Intent intent = getIntent();
        Username = intent.getStringExtra("Username");

        WelcomeText.setText("Logged in as " + Username);
    }

    View.OnClickListener LogOutListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(OverviewActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener CreateGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(OverviewActivity.this, CreateGameActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener ResumeGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(OverviewActivity.this, GameChooserActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener JoinGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            //TODO: Implement activity for joining a public game
        }
    };

}
