package sw7.cornfield;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Morten on 20-11-2014.
 */
public class JoinGameListAdapter extends ArrayAdapter<String> {

    ArrayList<String> GameList = new ArrayList<String>();
    Context ActivityContext;
    int LayoutId;

    public JoinGameListAdapter(Context activityContext, int layoutId, ArrayList<String> gameList) {
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
    public String getItem(int position) {
        return GameList.get(position);
    }

    @Override
    //TODO: Should return gameId
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View gameView = convertView;

        if (gameView == null) {
            LayoutInflater inflater = (LayoutInflater) ActivityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gameView = inflater.inflate(R.layout.join_game_list_item, null);
        }

        //TODO: In reality, this class should take the int, ask server for the associated username and display that
        TextView joinGameButton = (TextView) gameView.findViewById(R.id.JoinGameButton);
        joinGameButton.setText(GameList.get(position));
        joinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityContext, GameActivity.class);
                ActivityContext.startActivity(intent);
                ((Activity) ActivityContext).finish();
            }
        });
        return gameView;
    }
}
