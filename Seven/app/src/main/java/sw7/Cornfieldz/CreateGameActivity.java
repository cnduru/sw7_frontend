package sw7.Cornfieldz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;

public class CreateGameActivity extends Activity {
    RadioButton PublicRadio;
    RadioButton PrivateRadio;
    Spinner TeamCountView;
    EditText GameNameView;
    EditText GameStartView;
    EditText GameEndView;
    EditText SELatView;
    EditText SELngView;
    EditText NWLatView;
    EditText NWLngView;
    Button CreateGameButton;
    Button CancelGameButton;

    Boolean GameNameValid = false;
    Boolean GameStartValid = false;
    Boolean GameEndValid = false;
    Boolean SELatValid = false;
    Boolean SELngValid = false;
    Boolean NWLatValid = false;
    Boolean NWLngValid = false;

    String[] TeamCountDisplayValues = new String[]{"2 Teams", "3 Teams", "4 Teams"};
    ArrayAdapter<String> TeamCountAdapter;

    String GameName;
    Boolean IsPrivateGame = false;
    Integer TeamCount = 2;
    Calendar GameStart = Calendar.getInstance();
    Integer GameDuration;
    LatLng SEBoundary = new LatLng(0, 0);
    LatLng NWBoundary = new LatLng(0, 0);

    Integer UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        UserId = getIntent().getIntExtra("UserId", -1);

        GameNameView = (EditText) findViewById(R.id.GameNameText);
        PublicRadio = (RadioButton) findViewById(R.id.radio_public);
        PrivateRadio = (RadioButton) findViewById(R.id.radio_private);
        TeamCountView = (Spinner) findViewById(R.id.NumberOfTeamsSpinner);
        GameStartView = (EditText) findViewById(R.id.StartTimeEditable);
        GameEndView = (EditText) findViewById(R.id.EndTimeEditable);
        SELatView = (EditText) findViewById(R.id.StartLat);
        SELngView = (EditText) findViewById(R.id.StartLng);
        NWLatView = (EditText) findViewById(R.id.EndLat);
        NWLngView = (EditText) findViewById(R.id.EndLng);
        CreateGameButton = (Button) findViewById(R.id.OkCreate);
        CancelGameButton = (Button) findViewById(R.id.Cancel);

        GameNameView.addTextChangedListener(GameNameListener);

        PublicRadio.setOnClickListener(PublicListener);
        PrivateRadio.setOnClickListener(PrivateListener);

        TeamCountAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, TeamCountDisplayValues);
        TeamCountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        TeamCountView.setAdapter(TeamCountAdapter);
        TeamCountView.setOnItemSelectedListener(TeamCountListener);

        GameStartView.addTextChangedListener(GameStartListener);
        GameEndView.addTextChangedListener(GameEndListener);

        SELatView.addTextChangedListener(SELatListener);
        SELngView.addTextChangedListener(SELngListener);
        NWLatView.addTextChangedListener(NWLatListener);
        NWLngView.addTextChangedListener(NWLngListener);

        CreateGameButton.setOnClickListener(CreateGameListener);
        CancelGameButton.setOnClickListener(CancelGameListener);
    }

    private void updateCreateGameButton() {
        if (GameNameValid && GameStartValid && GameEndValid && SELatValid && SELngValid && NWLatValid && NWLngValid) {
            CreateGameButton.setEnabled(true);
        } else {
            CreateGameButton.setEnabled(false);
        }
    }

    View.OnClickListener CancelGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener CreateGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            Client client = new Client();
            client.send(EncodeServerXML.createGame(GameName, IsPrivateGame, TeamCount, GameStart, GameDuration, SEBoundary, NWBoundary, UserId));
            Integer gameId = DecodeServerXML.createGame(client.read());
            client.close();
            Intent intent = new Intent(CreateGameActivity.this, InvitePlayersActivity.class);
            intent.putExtra("UserId", UserId);
            intent.putExtra("GameId", gameId);
            startActivity(intent);
            finish();
        }
    };

    private TextWatcher GameNameListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            GameName = editable.toString();

            if (GameName.length() == 0) {
                GameNameValid = false;
            } else {
                GameNameValid = true;
            }
            updateCreateGameButton();
        }
    };

    private TextWatcher GameStartListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String content = editable.toString();

            if (content.length() == 0) {
                GameStart = Calendar.getInstance();
                GameStartValid = false;
            } else {
                GameStart.add(Calendar.HOUR, Integer.parseInt(content));
                GameStartValid = true;
            }
            updateCreateGameButton();
        }
    };

    private TextWatcher GameEndListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String content = editable.toString();

            if (content.length() == 0) {
                GameEndValid = false;

            } else {
                GameDuration = Integer.parseInt(content);
                GameEndValid = true;
            }
            updateCreateGameButton();
        }
    };

    private TextWatcher SELatListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String content = editable.toString();
            if(content.length()  == 0) {
                SEBoundary = new LatLng(0, SEBoundary.longitude);
                SELatValid = false;
            } else {
                SEBoundary = new LatLng(Double.parseDouble(content), SEBoundary.longitude);
                SELatValid = true;
            }
            updateCreateGameButton();
        }
    };

    private TextWatcher SELngListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String content = editable.toString();
            if(content.length()  == 0) {
                SEBoundary = new LatLng(SEBoundary.latitude, 0);
                SELngValid = false;
            } else {
                SEBoundary = new LatLng(SEBoundary.latitude, Double.parseDouble(content));
                SELngValid = true;
            }
            updateCreateGameButton();
        }
    };

    private TextWatcher NWLatListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String content = editable.toString();
            if(content.length()  == 0) {
                NWBoundary = new LatLng(0, NWBoundary.longitude);
                NWLatValid = false;
            } else {
                NWBoundary = new LatLng(Double.parseDouble(content), NWBoundary.longitude);
                NWLatValid = true;
            }
            updateCreateGameButton();
        }
    };

    private TextWatcher NWLngListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String content = editable.toString();
            if(content.length()  == 0) {
                NWBoundary = new LatLng(NWBoundary.latitude, 0);
                NWLngValid = false;
            } else {
                NWBoundary = new LatLng(NWBoundary.latitude, Double.parseDouble(content));
                NWLngValid = true;
            }
            updateCreateGameButton();
        }
    };

    private AdapterView.OnItemSelectedListener TeamCountListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0:
                    TeamCount = 2;
                    break;
                case 1:
                    TeamCount = 3;
                    break;
                case 2:
                    TeamCount = 4;
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
        }
    };

    private View.OnClickListener PublicListener = new View.OnClickListener(){
        public void onClick(View v) {
            PrivateRadio.setChecked(false);
            PublicRadio.setChecked(true);
            IsPrivateGame = false;
        }
    };

    private View.OnClickListener PrivateListener = new View.OnClickListener(){
        public void onClick(View v) {
            PrivateRadio.setChecked(true);
            PublicRadio.setChecked(false);
            IsPrivateGame = true;
        }
    };
}