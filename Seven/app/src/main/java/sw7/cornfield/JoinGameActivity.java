package sw7.cornfield;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class JoinGameActivity extends Activity {

    public Client DataClient;

    private ListView GameListView;
    private JoinGameListAdapter GameAdapter;
    private List<Pair> GameList = new ArrayList<Pair>();

    public Integer UserId;
    private String GetPublicGamesRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        GetPublicGamesRequest = EncodeServerXML.getPublicGames();
        DataClient = new Client();
        DataClient.send(GetPublicGamesRequest);
        GameList = DecodeServerXML.getPublicGames(DataClient.read());
        DataClient.close();

        UserId = getIntent().getIntExtra("UserId", -1);

        GameListView = (ListView) findViewById(R.id.GameList);
        GameAdapter = new JoinGameListAdapter(this, R.layout.join_game_list_item, GameList);
        GameListView.setAdapter(GameAdapter);
    }
}
