package sw7.cornfield;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Morten on 17-11-2014.
 */
public class InvitedPlayersAdapter extends ArrayAdapter<String> {
    ArrayList<String> InvitedPlayers = new ArrayList<String>();
    Context ActivityContext;
    int LayoutId;

    public InvitedPlayersAdapter(Context activityContext, int layoutId, ArrayList<String> invitedPlayers) {
        super(activityContext, layoutId, invitedPlayers);
        this.InvitedPlayers = invitedPlayers;
        this.ActivityContext = activityContext;
        this.LayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return InvitedPlayers.size();
    }

    @Override
    public String getItem(int position) {
        return InvitedPlayers.get(position);
    }

    @Override
    //TODO: Should return userId
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View playerView = convertView;

        if (playerView == null) {
            LayoutInflater inflater = (LayoutInflater) ActivityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            playerView = inflater.inflate(R.layout.player_list_item, null);
        }

        //TODO: In reality, this class should take the int, ask server for the associated username and display that
        TextView listItemText = (TextView)playerView.findViewById(R.id.PlayerName);
        listItemText.setText(InvitedPlayers.get(position));

        ImageButton uninviteButton = (ImageButton) playerView.findViewById(R.id.RemovePlayer);

        uninviteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: Tell server to remove player from invitation list
                InvitedPlayers.remove(position);
                notifyDataSetChanged();
            }
        });

        return playerView;
    }
}
