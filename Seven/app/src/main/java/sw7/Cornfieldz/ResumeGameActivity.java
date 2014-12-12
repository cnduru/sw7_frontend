package sw7.Cornfieldz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ResumeGameActivity extends Activity {
    public Integer UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_game);

        UserId = getIntent().getIntExtra("UserId", -1);

        Client client= new Client();
        client.send(EncodeServerXML.getMyGames(UserId));
        ResumeGameListAdapter GameAdapter = new ResumeGameListAdapter(this, R.layout.player_list_item, DecodeServerXML.getMyGames(client.read()));
        client.close();

        ListView GameListView = (ListView) findViewById(R.id.GameList);
        GameListView.setAdapter(GameAdapter);
    }
}
