package sw7.cornfield;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class JoinGameActivity extends Activity {

    private ListView GameListView;
    private JoinGameListAdapter GameAdapter;
    private List<Pair> GameList = new ArrayList<Pair>();

    public Integer UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        UserId = getIntent().getIntExtra("UserId", -1);

        //For testing purposes
        GameList.add(new Pair(1, "Game1"));
        GameList.add(new Pair(2, "Game2"));
        GameList.add(new Pair(3, "Game3"));

        GameListView = (ListView) findViewById(R.id.GameList);
        GameAdapter = new JoinGameListAdapter(this, R.layout.join_game_list_item, GameList);
        GameListView.setAdapter(GameAdapter);
    }
}
