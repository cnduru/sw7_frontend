package sw7.cornfield;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity {

    EditText username;
    EditText password;
    Button loginButton;
    Button createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);
        loginButton = (Button) findViewById(R.id.Login);
        createUser = (Button) findViewById(R.id.CreateUser);

        loginButton.setOnClickListener(loginListener);
    }

    View.OnClickListener loginListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, OverviewActivity.class);
            intent.putExtra("Username", username.getText().toString());
            startActivity(intent);
        }
    };
}
