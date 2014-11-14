package sw7.cornfield;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class OverviewActivity extends Activity {

    TextView WelcomeText;
    Button ResumeGame;
    Button JoinGame;
    Button CreateGame;
    Button LogOut;

    String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        WelcomeText = (TextView) findViewById(R.id.WelcomeText);
        ResumeGame = (Button) findViewById(R.id.ResumeGame);
        JoinGame = (Button) findViewById(R.id.JoinGame);
        CreateGame = (Button) findViewById(R.id.CreateGame);
        LogOut = (Button) findViewById(R.id.LogOut);

        LogOut.setOnClickListener(LogOutListener);

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

}
