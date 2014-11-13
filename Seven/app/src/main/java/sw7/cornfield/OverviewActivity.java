package sw7.cornfield;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class OverviewActivity extends Activity {

    String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        TextView welcomeText = (TextView) findViewById(R.id.WelcomeText);

        Intent intent = getIntent();
        Username = intent.getStringExtra("Username");

        welcomeText.setText("Logged in as " + Username);
    }
}
