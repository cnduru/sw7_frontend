package sw7.cornfield;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class JoinGameActivity extends Activity {

    ListView GameListView;
    JoinGameListAdapter GameAdapter;

    //TODO: This should just be an Integer when database is ready
    ArrayList<String> GameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        //For testing purposes
        GameList.add("Game1");
        GameList.add("Game2");
        GameList.add("Game3");

        GameListView = (ListView) findViewById(R.id.GameList);
        GameAdapter = new JoinGameListAdapter(this, R.layout.join_game_list_item, GameList);
        GameListView.setAdapter(GameAdapter);
    }
}
