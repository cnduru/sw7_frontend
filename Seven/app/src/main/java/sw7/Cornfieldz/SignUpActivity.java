package sw7.Cornfieldz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUpActivity extends Activity {

    EditText UsernameText;
    TextView UsernameStatusText;
    ImageView UsernameStatusImage;
    EditText PasswordText;
    ImageView PasswordStatusImage;
    EditText ConfirmPasswordText;
    ImageView ConfirmPasswordStatusImage;
    Button CreateUserButton;

    Boolean UsernameValid = false;
    Boolean PasswordValid = false;
    Boolean PasswordsMatch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        UsernameText = (EditText) findViewById(R.id.Username);
        UsernameStatusText = (TextView) findViewById(R.id.UsernameStatusText);
        UsernameStatusImage = (ImageView) findViewById(R.id.UsernameStatusImage);
        PasswordText = (EditText) findViewById(R.id.Password);
        PasswordStatusImage = (ImageView) findViewById(R.id.PasswordStatusImage);
        ConfirmPasswordText = (EditText) findViewById(R.id.ConfirmPassword);
        ConfirmPasswordStatusImage = (ImageView) findViewById(R.id.ConfirmPasswordStatusImage);
        CreateUserButton = (Button) findViewById(R.id.CreateUser);

        UsernameText.addTextChangedListener(UsernameValidator);
        PasswordText.addTextChangedListener(PasswordValidator);
        ConfirmPasswordText.addTextChangedListener(PasswordMatchValidator);
        CreateUserButton.setOnClickListener(SignUpListener);
    }

    private void updateSignUpButton() {
        if (UsernameValid && PasswordValid && PasswordsMatch) {
            CreateUserButton.setEnabled(true);
        } else {
            CreateUserButton.setEnabled(false);
        }
    }

    View.OnClickListener SignUpListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(SignUpActivity.this, OverviewActivity.class);
            intent.putExtra("Username", UsernameText.getText().toString());
            startActivity(intent);
            finish();
        }
    };

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
                //Check if username already exists in DB
                Boolean alreadyExists = false;
                if (alreadyExists) {
                    UsernameValid = false;
                    UsernameStatusImage.setImageResource(R.drawable.cross);
                    UsernameStatusText.setText("The username " + username + " is already taken");

                } else {
                    UsernameValid = true;
                    UsernameStatusImage.setImageResource(R.drawable.checkmark);
                    UsernameStatusText.setText("The username " + username + " is available");
                }
            } else {
                UsernameValid = false;
            }
            updateSignUpButton();
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
            if (password.length() >= 6 && ConfirmPasswordText.getText().toString().equals(password)) {
                PasswordValid = true;
                PasswordsMatch = true;
                PasswordStatusImage.setImageResource(R.drawable.checkmark);
                ConfirmPasswordStatusImage.setImageResource(R.drawable.checkmark);
            } else if (password.length() >= 6) {
                PasswordValid = true;
                PasswordsMatch = false;
                PasswordStatusImage.setImageResource(R.drawable.checkmark);
                ConfirmPasswordStatusImage.setImageResource(R.drawable.cross);
            } else{
                PasswordValid = false;
                PasswordStatusImage.setImageResource(R.drawable.cross);
            }
            updateSignUpButton();
        }
    };

    TextWatcher PasswordMatchValidator = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String confirmPassword = editable.toString();
            if (confirmPassword.equals(PasswordText.getText().toString())) {
                PasswordsMatch = true;
                ConfirmPasswordStatusImage.setImageResource(R.drawable.checkmark);
            } else {
                PasswordsMatch = false;
                ConfirmPasswordStatusImage.setImageResource(R.drawable.cross);
            }
            updateSignUpButton();
        }
    };
}