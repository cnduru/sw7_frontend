package sw7.Cornfieldz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morten on 20-11-2014.
 */
public class ResumeGameListAdapter extends ArrayAdapter<Pair> {
    List<Pair> GameList = new ArrayList<Pair>();
    Context ActivityContext;
    int LayoutId;

    public ResumeGameListAdapter(Context activityContext, int layoutId, List<Pair> gameList) {
        super(activityContext, layoutId, gameList);

        GameList = gameList;
        ActivityContext = activityContext;
        LayoutId = layoutId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View gameView = convertView;

        if (gameView == null) {
            LayoutInflater inflater = (LayoutInflater) ActivityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gameView = inflater.inflate(R.layout.resume_game_list_item, null);
        }

        TextView enterGameButton = (TextView) gameView.findViewById(R.id.EnterGameButton);
        enterGameButton.setText(GameList.get(position).getName());
        enterGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityContext, LobbyActivity.class);
                intent.putExtra("UserId", ((ResumeGameActivity)ActivityContext).UserId);
                intent.putExtra("GameId", GameList.get(position).getId());
                ActivityContext.startActivity(intent);
                ((Activity) ActivityContext).finish();
            }
        });

        ImageButton leaveGameButton = (ImageButton) gameView.findViewById(R.id.LeaveGameButton);
        leaveGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Client client = new Client();
                client.send(EncodeServerXML.leaveGame(((ResumeGameActivity)ActivityContext).UserId, GameList.get(position).getId()));
                if (DecodeServerXML.leaveGame(client.read())) {
                    GameList.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast leaveError = Toast.makeText(ActivityContext.getApplicationContext(), "Something failed. Please try again.", Toast.LENGTH_SHORT);
                    leaveError.show();
                }
                client.close();
            }
        });
        return gameView;
    }
}
