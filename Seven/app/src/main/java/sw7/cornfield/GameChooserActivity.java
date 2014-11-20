package sw7.cornfield;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class GameChooserActivity extends Activity {

    ListView GameListView;
    GameListAdapter GameAdapter;

    //TODO: This should just be an Integer when database is ready
    ArrayList<String> GameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_chooser);

        //For testing purposes
        GameList.add("Game1");
        GameList.add("Game2");
        GameList.add("Game3");

        GameListView = (ListView) findViewById(R.id.GameList);
        GameAdapter = new GameListAdapter(this, R.layout.player_list_item, GameList);
        GameListView.setAdapter(GameAdapter);
    }
}
