package sw7.cornfield;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morten on 20-11-2014.
 */
public class JoinGameListAdapter extends ArrayAdapter<Pair> {

    List<Pair> GameList = new ArrayList<Pair>();
    Context ActivityContext;
    int LayoutId;

    public JoinGameListAdapter(Context activityContext, int layoutId, List<Pair> gameList) {
        super(activityContext, layoutId, gameList);
        this.GameList = gameList;
        this.ActivityContext = activityContext;
        this.LayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return GameList.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View gameView = convertView;

        if (gameView == null) {
            LayoutInflater inflater = (LayoutInflater) ActivityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gameView = inflater.inflate(R.layout.join_game_list_item, null);
        }

        TextView joinGameButton = (TextView) gameView.findViewById(R.id.JoinGameButton);
        joinGameButton.setText(GameList.get(position).getName());
        joinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client dataClient = new Client();
                dataClient.send(EncodeServerXML.joinGame(((JoinGameActivity) ActivityContext).UserId, GameList.get(position).getId()));
                if (DecodeServerXML.joinGame(dataClient.read())) {
                    Intent intent = new Intent(ActivityContext, LobbyActivity.class);
                    intent.putExtra("UserId", ((JoinGameActivity) ActivityContext).UserId);
                    intent.putExtra("GameId", GameList.get(position).getId());
                    ActivityContext.startActivity(intent);
                    ((JoinGameActivity) ActivityContext).finish();
                } else {
                    Toast joinError = Toast.makeText(ActivityContext.getApplicationContext(), "Something failed. Please try again.", Toast.LENGTH_SHORT);
                    joinError.show();
                }
            }
        });
        return gameView;
    }
}
