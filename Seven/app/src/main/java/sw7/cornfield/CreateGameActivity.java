package sw7.cornfield;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.text.DateFormat;
import java.util.Calendar;


public class CreateGameActivity extends Activity {

    RadioGroup Privacy;
    RadioButton RadioPublic;
    RadioButton RadioPrivate;
    Spinner NumberOfTeams;
    EditText GameStart;
    EditText GameEnd;
    EditText StartLat;
    EditText StartLng;
    EditText EndLat;
    EditText EndLng;
    Button CreateGameButton;
    Button CancelButton;

    String[] NumberOfTeamsValues = new String[]{"2 Teams", "3 Teams", "4 Teams"};
    ArrayAdapter<String> NumberOfTeamsAdapter;

    Boolean IsPrivateGame = false;
    Integer Teams = 2;
    Calendar GameStartTime = Calendar.getInstance();
    int GameDuration;
    LatLng SouthEastBoundry = new LatLng(0, 0);
    LatLng NorthWestBoundry = new LatLng(0, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        Privacy = (RadioGroup) findViewById(R.id.PrivacySetting);
        RadioPublic = (RadioButton) findViewById(R.id.radio_public);
        RadioPrivate = (RadioButton) findViewById(R.id.radio_private);
        NumberOfTeams = (Spinner) findViewById(R.id.NumberOfTeams);
        GameStart = (EditText) findViewById(R.id.StartTimeEditable);
        GameEnd = (EditText) findViewById(R.id.EndTimeEditable);
        StartLat = (EditText) findViewById(R.id.StartLat);
        StartLng = (EditText) findViewById(R.id.StartLng);
        EndLat = (EditText) findViewById(R.id.EndLat);
        EndLng = (EditText) findViewById(R.id.EndLng);
        CreateGameButton = (Button) findViewById(R.id.OkCreate);
        CancelButton = (Button) findViewById(R.id.Cancel);

        Privacy.setOnCheckedChangeListener(PrivacyListener);
        RadioPublic.setOnClickListener(PublicListener);
        RadioPrivate.setOnClickListener(PrivateListener);

        NumberOfTeamsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, NumberOfTeamsValues);
        NumberOfTeamsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        NumberOfTeams.setAdapter(NumberOfTeamsAdapter);
        NumberOfTeams.setOnItemSelectedListener(NumberOfTeamsListener);

        GameStart.addTextChangedListener(GameStartListener);
        GameEnd.addTextChangedListener(GameEndListener);

        StartLat.addTextChangedListener(StartLatListener);
        StartLng.addTextChangedListener(StartLngListener);
        EndLat.addTextChangedListener(EndLatListener);
        EndLng.addTextChangedListener(EndLngListener);


    }

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
            } else {
                GameStartTime.add(Calendar.HOUR, Integer.parseInt(GameStart));
            }
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

            } else {
                GameDuration = Integer.parseInt(GameEnd);
            }
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
                SouthEastBoundry = new LatLng(0, SouthEastBoundry.longitude);
            } else {
                SouthEastBoundry = new LatLng(Double.parseDouble(startLat), SouthEastBoundry.longitude);
            }
            Log.e("test", "Southeast bounds are: Lat " + SouthEastBoundry.latitude + " Lng " + SouthEastBoundry.longitude);
            Log.e("test", "Northwest bounds are: Lat " + NorthWestBoundry.latitude + " Lng " + NorthWestBoundry.longitude);
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
                SouthEastBoundry = new LatLng(SouthEastBoundry.latitude, 0);
            } else {
                SouthEastBoundry = new LatLng(SouthEastBoundry.latitude, Double.parseDouble(startLng));
            }
            Log.e("test", "Southeast bounds are: Lat " + SouthEastBoundry.latitude + " Lng " + SouthEastBoundry.longitude);
            Log.e("test", "Northwest bounds are: Lat " + NorthWestBoundry.latitude + " Lng " + NorthWestBoundry.longitude);
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
                NorthWestBoundry = new LatLng(0, NorthWestBoundry.longitude);
            } else {
                NorthWestBoundry = new LatLng(Double.parseDouble(endLat), NorthWestBoundry.longitude);
            }
            Log.e("test", "Southeast bounds are: Lat " + SouthEastBoundry.latitude + " Lng " + SouthEastBoundry.longitude);
            Log.e("test", "Northwest bounds are: Lat " + NorthWestBoundry.latitude + " Lng " + NorthWestBoundry.longitude);
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
                NorthWestBoundry = new LatLng(NorthWestBoundry.latitude, 0);
            } else {
                NorthWestBoundry = new LatLng(NorthWestBoundry.latitude, Double.parseDouble(endLng));
            }
            Log.e("test", "Southeast bounds are: Lat " + SouthEastBoundry.latitude + " Lng " + SouthEastBoundry.longitude);
            Log.e("test", "Northwest bounds are: Lat " + NorthWestBoundry.latitude + " Lng " + NorthWestBoundry.longitude);
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