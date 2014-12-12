package sw7.Cornfieldz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvitePlayersActivity extends Activity {
    EditText InvitePlayerText;
    ImageButton InvitePlayerButton;
    ListView InvitedPlayersView;
    Button DoneButton;

    Integer UserId;
    public Integer GameId;

    InvitedPlayersListAdapter PlayersAdapter;
    List<Pair> InvitedPlayers = new ArrayList<Pair>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_players);

        UserId = getIntent().getIntExtra("UserId", -1);
        GameId = getIntent().getIntExtra("GameId", -1);

        Client client = new Client();
        client.send(EncodeServerXML.getPlayerInvites(GameId));
        InvitedPlayers = DecodeServerXML.getPlayerInvites(client.read());
        client.close();

        InvitePlayerText = (EditText) findViewById(R.id.Username);
        InvitePlayerButton = (ImageButton) findViewById(R.id.UsernameAddButton);
        InvitedPlayersView = (ListView) findViewById(R.id.PlayerList);
        DoneButton = (Button) findViewById(R.id.Done);

        InvitePlayerText.addTextChangedListener(InvitePlayerTextListener);
        InvitePlayerButton.setOnClickListener(InvitePlayerListener);
        InvitePlayerButton.setEnabled(false);
        DoneButton.setOnClickListener(DoneListener);

        PlayersAdapter = new InvitedPlayersListAdapter(this, R.layout.resume_game_list_item, InvitedPlayers);
        InvitedPlayersView.setAdapter(PlayersAdapter);
    }

    private TextWatcher InvitePlayerTextListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String InvitePlayerText = editable.toString();

            if (InvitePlayerText.length() == 0) {
                InvitePlayerButton.setClickable(false);
                InvitePlayerButton.setEnabled(false);
            } else {
                InvitePlayerButton.setClickable(true);
                InvitePlayerButton.setEnabled(true);
            }
        }
    };

    View.OnClickListener InvitePlayerListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (!InvitedPlayers.contains(InvitePlayerText.getText().toString())) {
                Client client = new Client();
                client.send(EncodeServerXML.inviteUser(InvitePlayerText.getText().toString(), GameId));
                Map<String, String> inviteUserValidation = DecodeServerXML.inviteUser(client.read());
                if (inviteUserValidation.get("Message").equals("TRUE")) {
                    InvitedPlayers.add(new Pair(Integer.parseInt(inviteUserValidation.get("UserId")), InvitePlayerText.getText().toString()));
                    InvitePlayerText.setText("");
                    PlayersAdapter.notifyDataSetChanged();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(InvitePlayerText.getWindowToken(), 0);
                } else {
                    Toast inviteError = Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT);
                    inviteError.show();
                }
            } else {
                InvitePlayerText.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(InvitePlayerText.getWindowToken(), 0);

                Toast inviteError = Toast.makeText(getApplicationContext(), "User already invited", Toast.LENGTH_SHORT);
                inviteError.show();
            }
        }
    };

    View.OnClickListener DoneListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(InvitePlayersActivity.this, LobbyActivity.class);
            intent.putExtra("UserId", UserId);
            intent.putExtra("GameId", GameId);
            startActivity(intent);
            finish();
        }
    };
}