package sw7.Cornfieldz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ResumeGameActivity extends Activity {

    public Client DataClient;

    private ListView GameListView;
    private ResumeGameListAdapter GameAdapter;
    private List<Pair> GameList = new ArrayList<Pair>();

    public Integer UserId;
    private String GetMyGamesRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_game);

        UserId = getIntent().getIntExtra("UserId", -1);

        GetMyGamesRequest = EncodeServerXML.getMyGames(UserId);
        DataClient = new Client();
        DataClient.send(GetMyGamesRequest);
        GameList = DecodeServerXML.getMyGames(DataClient.read());
        DataClient.close();

        GameListView = (ListView) findViewById(R.id.GameList);
        GameAdapter = new ResumeGameListAdapter(this, R.layout.player_list_item, GameList);
        GameListView.setAdapter(GameAdapter);
    }
}
