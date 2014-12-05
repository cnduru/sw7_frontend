package sw7.cornfield;

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
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;

public class CreateGameActivity extends Activity {
    RadioGroup PrivacyGroup;
    RadioButton RadioPublic;
    RadioButton RadioPrivate;
    Spinner NumberOfTeamsView;
    EditText GameNameView;
    EditText GameStartView;
    EditText GameEndView;
    EditText StartLatView;
    EditText StartLngView;
    EditText EndLatView;
    EditText EndLngView;
    Button CreateGameButton;
    Button CancelButton;

    Boolean GameNameValid = false;
    Boolean GameStartValid = false;
    Boolean GameEndValid = false;
    Boolean StartLatValid = false;
    Boolean StartLngValid = false;
    Boolean EndLatValid = false;
    Boolean EndLngValid = false;

    String[] NumberOfTeamsValues = new String[]{"2 Teams", "3 Teams", "4 Teams"};
    ArrayAdapter<String> NumberOfTeamsAdapter;

    Boolean IsPrivateGame = false;
    Integer Teams = 2;
    Calendar GameStartTime = Calendar.getInstance();
    int GameDuration;
    LatLng SouthEastBoundary = new LatLng(0, 0);
    LatLng NorthWestBoundary = new LatLng(0, 0);

    Integer UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        UserId = getIntent().getIntExtra("UserId", -1);

        GameNameView = (EditText) findViewById(R.id.GameNameText);
        PrivacyGroup = (RadioGroup) findViewById(R.id.PrivacySetting);
        RadioPublic = (RadioButton) findViewById(R.id.radio_public);
        RadioPrivate = (RadioButton) findViewById(R.id.radio_private);
        NumberOfTeamsView = (Spinner) findViewById(R.id.NumberOfTeamsSpinner);
        GameStartView = (EditText) findViewById(R.id.StartTimeEditable);
        GameEndView = (EditText) findViewById(R.id.EndTimeEditable);
        StartLatView = (EditText) findViewById(R.id.StartLat);
        StartLngView = (EditText) findViewById(R.id.StartLng);
        EndLatView = (EditText) findViewById(R.id.EndLat);
        EndLngView = (EditText) findViewById(R.id.EndLng);
        CreateGameButton = (Button) findViewById(R.id.OkCreate);
        CancelButton = (Button) findViewById(R.id.Cancel);

        GameNameView.addTextChangedListener(GameNameListener);

        PrivacyGroup.setOnCheckedChangeListener(PrivacyListener);
        RadioPublic.setOnClickListener(PublicListener);
        RadioPrivate.setOnClickListener(PrivateListener);

        NumberOfTeamsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, NumberOfTeamsValues);
        NumberOfTeamsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        NumberOfTeamsView.setAdapter(NumberOfTeamsAdapter);
        NumberOfTeamsView.setOnItemSelectedListener(NumberOfTeamsListener);

        GameStartView.addTextChangedListener(GameStartListener);
        GameEndView.addTextChangedListener(GameEndListener);

        StartLatView.addTextChangedListener(StartLatListener);
        StartLngView.addTextChangedListener(StartLngListener);
        EndLatView.addTextChangedListener(EndLatListener);
        EndLngView.addTextChangedListener(EndLngListener);

        CreateGameButton.setOnClickListener(CreateGameListener);
        CancelButton.setOnClickListener(CancelListener);
    }

    private void updateCreateGameButton() {
        if (GameNameValid && GameStartValid && GameEndValid && StartLatValid && StartLngValid && EndLatValid && EndLngValid) {
            CreateGameButton.setEnabled(true);
        } else {
            CreateGameButton.setEnabled(false);
        }
    }

    View.OnClickListener CancelListener = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener CreateGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            //TODO: Send created variables to server
            Intent intent = new Intent(CreateGameActivity.this, InvitePlayersActivity.class);
            //TODO: Put GameId into intent. Invite friends will need it.
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
            String GameName = editable.toString();

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
            String GameStart = editable.toString();

            if (GameStart.length() == 0) {
                GameStartTime = Calendar.getInstance();
                GameStartValid = false;
            } else {
                GameStartTime.add(Calendar.HOUR, Integer.parseInt(GameStart));
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
            String GameEnd = editable.toString();

            if (GameEnd.length() == 0) {
                GameEndValid = false;

            } else {
                GameDuration = Integer.parseInt(GameEnd);
                GameEndValid = true;
            }
            updateCreateGameButton();
        }
    };

    private TextWatcher StartLatListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String startLat = editable.toString();
            if(startLat.length()  == 0) {
                SouthEastBoundary = new LatLng(0, SouthEastBoundary.longitude);
                StartLatValid = false;
            } else {
                SouthEastBoundary = new LatLng(Double.parseDouble(startLat), SouthEastBoundary.longitude);
                StartLatValid = true;
            }
            updateCreateGameButton();
        }
    };

    private TextWatcher StartLngListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String startLng = editable.toString();
            if(startLng.length()  == 0) {
                SouthEastBoundary = new LatLng(SouthEastBoundary.latitude, 0);
                StartLngValid = false;
            } else {
                SouthEastBoundary = new LatLng(SouthEastBoundary.latitude, Double.parseDouble(startLng));
                StartLngValid = true;
            }
            updateCreateGameButton();
        }
    };

    private TextWatcher EndLatListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String endLat = editable.toString();
            if(endLat.length()  == 0) {
                NorthWestBoundary = new LatLng(0, NorthWestBoundary.longitude);
                EndLatValid = false;
            } else {
                NorthWestBoundary = new LatLng(Double.parseDouble(endLat), NorthWestBoundary.longitude);
                EndLatValid = true;
            }
            updateCreateGameButton();
        }
    };

    private TextWatcher EndLngListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String endLng = editable.toString();
            if(endLng.length()  == 0) {
                NorthWestBoundary = new LatLng(NorthWestBoundary.latitude, 0);
                EndLngValid = false;
            } else {
                NorthWestBoundary = new LatLng(NorthWestBoundary.latitude, Double.parseDouble(endLng));
                EndLngValid = true;
            }
            updateCreateGameButton();
        }
    };

    private AdapterView.OnItemSelectedListener NumberOfTeamsListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0:
                    Teams = 2;
                    break;
                case 1:
                    Teams = 3;
                    break;
                case 2:
                    Teams = 4;
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
        }
    };

    private View.OnClickListener PublicListener = new View.OnClickListener(){
        public void onClick(View v) {
            RadioPrivate.setChecked(false);
            RadioPublic.setChecked(true);
        }
    };

    private View.OnClickListener PrivateListener = new View.OnClickListener(){
        public void onClick(View v) {
            RadioPrivate.setChecked(true);
            RadioPublic.setChecked(false);
        }
    };

    private RadioGroup.OnCheckedChangeListener PrivacyListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            // Check which radio button was clicked
            switch (i) {
                case R.id.radio_private:
                    IsPrivateGame = true;
                    break;
                case R.id.radio_public:
                    IsPrivateGame = false;
                    break;
            }
        }
    };
}