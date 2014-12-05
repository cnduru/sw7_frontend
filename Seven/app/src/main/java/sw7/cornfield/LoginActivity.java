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

import java.util.Map;

public class LoginActivity extends Activity {

    Client DataClient;
    EditText UsernameText;
    EditText PasswordText;
    Button LoginButton;
    Button SignUpButton;
    Boolean UsernameValid = false;
    Boolean PasswordValid = false;

    String LoginData;

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


        /*
        //THE REMAINS OF ONCREATE IS FOR TESTING

        // XML - OUT
        List<String> k = new ArrayList<String>();
        k.add("k");
        k.add("k");
        k.add("k");

        Log.e("test",EncodeServerXML.createGame("k", "k", "k", "k", "k", "k", "k", "k"));
        Log.e("test",EncodeServerXML.getActiveGames("k"));
        Log.e("test",EncodeServerXML.getPlayerInvites("k"));
        Log.e("test",EncodeServerXML.getPublicGames());
        Log.e("test",EncodeServerXML.joinGame("k", "k"));
        Log.e("test",EncodeServerXML.leaveGame("k", "k"));
        Log.e("test",EncodeServerXML.login("k", "k"));
        Log.e("test",EncodeServerXML.setPlayerInvites("k", k));
        Log.e("test",EncodeServerXML.signUp("k", "k"));
        Log.e("test",EncodeServerXML.signUpCheck("k"));

        Log.e("test", "END OF XML - IN");

        // XML - IN

        String A = "<Login><Valid>OK</Valid><UserId>35</UserId></Login>";
        String B = "<SignUpCheck><Valid>OK</Valid></SignUpCheck>";
        String C = "<SignUp><UserId>69</UserId></SignUp>";
        String D = "<CreateGame><GameId>60</GameId></CreateGame>";
        String E = "<SetPlayerInvites><Message>OK</Message></SetPlayerInvites>";
        String F = "<GetPlayerInvites><UserId>1</UserId><UserId>2</UserId><UserId>3</UserId></GetPlayerInvites>";
        String G = "<GetPublicGames><GameId>6</GameId><GameId>7</GameId><GameId>8</GameId></GetPublicGames>";
        String H = "<JoinGame><Message>OK</Message></JoinGame>";
        String I = "<GetActiveGames><GameId>12</GameId><GameId>13</GameId><GameId>14</GameId></GetActiveGames>\"";
        String J = "<LeaveGame><Message>OK</Message></LeaveGame>";

        Map<String, String> map = DecodeServerXML.login(A);
        Log.e("test", "Login test: " + map.get("Valid"));

        Boolean kk = DecodeServerXML.signUpCheck(B);
        Log.e("test", kk.toString());

        int userid = DecodeServerXML.signUp(C);
        Log.e("test", Integer.toString(userid));

        int game = DecodeServerXML.createGame(D);
        Log.e("test", Integer.toString(game));

        Boolean yes = DecodeServerXML.setPlayerInvites(E);
        Log.e("test", yes.toString());

        Map<Integer, String> hej = DecodeServerXML.getPlayerInvites(F);
        Log.e("test", hej.toString());

        Map<Integer, String> kkk = DecodeServerXML.getPublicGames(G);
        Log.e("test", kkk.toString());

        Boolean sup = DecodeServerXML.joinGame(H);
        Log.e("test", sup.toString());

        Map<Integer, String> kek = DecodeServerXML.getActiveGames(I);
        Log.e("test", kek.toString());

        Boolean nejjj = DecodeServerXML.leaveGame(J);
        Log.e("test", nejjj.toString());

        */
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
            LoginData = EncodeServerXML.login(UsernameText.getText().toString(), PasswordText.getText().toString());
            DataClient = new Client();
            DataClient.send(LoginData);
            Map<String, String> ResponseData = DecodeServerXML.login(DataClient.read());

            //If user and password is found in DB, login. Otherwise display error.
            if(ResponseData.get("Valid").equals("TRUE")) {
                Intent intent = new Intent(LoginActivity.this, OverviewActivity.class);
                intent.putExtra("Username", UsernameText.getText().toString());
                intent.putExtra("UserId", Integer.parseInt(ResponseData.get("UserId")));
                startActivity(intent);
                DataClient.close();
                finish();
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
            finish();
        }
    };
}
