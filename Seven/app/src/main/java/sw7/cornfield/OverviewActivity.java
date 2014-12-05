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

    Integer UserId;
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

        UserId = getIntent().getIntExtra("UserId", -1);
        Username = getIntent().getStringExtra("Username");

        WelcomeText.setText("Welcome " + Username + ". Your ID is " + UserId + ".");
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
            intent.putExtra("UserId", UserId);
            startActivity(intent);
        }
    };

    View.OnClickListener ResumeGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(OverviewActivity.this, ResumeGameActivity.class);
            intent.putExtra("UserId", UserId);
            startActivity(intent);
        }
    };

    View.OnClickListener JoinGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(OverviewActivity.this, JoinGameActivity.class);
            intent.putExtra("UserId", UserId);
            startActivity(intent);
        }
    };

}
