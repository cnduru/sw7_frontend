package sw7.cornfield;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    EditText UsernameText;
    EditText PasswordText;
    Button LoginButton;
    Button SignUpButton;
    Boolean UsernameValid = false;
    Boolean PasswordValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UsernameText = (EditText) findViewById(R.id.Username);
        PasswordText = (EditText) findViewById(R.id.Password);
        LoginButton = (Button) findViewById(R.id.Login);
        SignUpButton = (Button) findViewById(R.id.SignUp);

        UsernameText.addTextChangedListener(UsernameValidator);
        PasswordText.addTextChangedListener(PasswordValidator);
        LoginButton.setOnClickListener(LoginListener);
        SignUpButton.setOnClickListener(SignUpListener);
    }

    private void updateLoginButton() {
        if (UsernameValid && PasswordValid) {
            LoginButton.setEnabled(true);
        } else {
            LoginButton.setEnabled(false);
        }
    }

    TextWatcher UsernameValidator = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String username = editable.toString();

            if (username.length() > 0) {
                UsernameValid = true;
            } else {
                UsernameValid = false;
            }
            updateLoginButton();
        }
    };

    TextWatcher PasswordValidator = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String password = editable.toString();

            if (password.length() > 0) {
                PasswordValid = true;
            } else {
                PasswordValid = false;
            }
            updateLoginButton();
        }
    };

    View.OnClickListener LoginListener = new View.OnClickListener() {
        public void onClick(View v) {
            //If user and password is found in DB, login. Otherwise display error.
            if(true) {
                Intent intent = new Intent(LoginActivity.this, OverviewActivity.class);
                intent.putExtra("Username", UsernameText.getText().toString());
                startActivity(intent);
            } else {
                Toast loginError = Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT);
                loginError.show();
                PasswordText.setText("");
            }
        }
    };

    View.OnClickListener SignUpListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    };
}
