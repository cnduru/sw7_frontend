package sw7.Cornfieldz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class JoinGameActivity extends Activity {
    public Integer UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        UserId = getIntent().getIntExtra("UserId", -1);

        Client client = new Client();
        client.send(EncodeServerXML.getPublicGames());
        JoinGameListAdapter GameAdapter = new JoinGameListAdapter(this, R.layout.join_game_list_item, DecodeServerXML.getPublicGames(client.read()));
        client.close();

        ListView GameListView = (ListView) findViewById(R.id.GameList);
        GameListView.setAdapter(GameAdapter);
    }
}
