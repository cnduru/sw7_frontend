package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
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


public class InvitePlayersActivity extends Activity {

    EditText InvitePlayerText;
    ImageButton InvitePlayerButton;
    ListView InvitedPlayersView;
    Button DoneButton;

    InvitedPlayersAdapter PlayersAdapter;

    //TODO: This should just be an Integer when database is ready
    ArrayList<String> InvitedPlayers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_players);

        InvitePlayerText = (EditText) findViewById(R.id.Username);
        InvitePlayerButton = (ImageButton) findViewById(R.id.UsernameAddButton);
        InvitedPlayersView = (ListView) findViewById(R.id.PlayerList);
        DoneButton = (Button) findViewById(R.id.Done);

        InvitePlayerText.addTextChangedListener(InvitePlayerTextListener);
        InvitePlayerButton.setOnClickListener(InvitePlayerListener);
        InvitePlayerButton.setEnabled(false);
        DoneButton.setOnClickListener(DoneListener);

        PlayersAdapter = new InvitedPlayersAdapter(this, R.layout.game_list_item, InvitedPlayers);
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
                //TODO: Ask server if user exists - If so, add user id to list and to server invite list (Currently just adds to list whatever string is entered)
                if (true) {
                    InvitedPlayers.add(InvitePlayerText.getText().toString());
                    InvitePlayerText.setText("");
                    PlayersAdapter.notifyDataSetChanged();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(InvitePlayerText.getWindowToken(), 0);

                } else {
                    Toast inviteError = Toast.makeText(getApplicationContext(), "That user does not exist", Toast.LENGTH_SHORT);
                    inviteError.show();
                }
            } else {
                InvitePlayerText.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(InvitePlayerText.getWindowToken(), 0);

                Toast inviteError = Toast.makeText(getApplicationContext(), "This user has already been invited", Toast.LENGTH_SHORT);
                inviteError.show();
            }
        }
    };

    View.OnClickListener DoneListener = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };
}