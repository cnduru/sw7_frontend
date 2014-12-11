package sw7.Cornfieldz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Morten on 17-11-2014.
 */
public class InvitedPlayersListAdapter extends ArrayAdapter<Pair> {
    ArrayList<Pair> InvitedPlayers = new ArrayList<Pair>();
    Context ActivityContext;
    int LayoutId;

    public InvitedPlayersListAdapter(Context activityContext, int layoutId, ArrayList<Pair> invitedPlayers) {
        super(activityContext, layoutId, invitedPlayers);
        this.InvitedPlayers = invitedPlayers;
        this.ActivityContext = activityContext;
        this.LayoutId = layoutId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View playerView = convertView;

        if (playerView == null) {
            LayoutInflater inflater = (LayoutInflater) ActivityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            playerView = inflater.inflate(R.layout.player_list_item, null);
        }

        TextView listItemText = (TextView)playerView.findViewById(R.id.PlayerName);
        listItemText.setText(InvitedPlayers.get(position).getName());

        ImageButton uninviteButton = (ImageButton) playerView.findViewById(R.id.RemovePlayerButton);

        uninviteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Client client = new Client();
                client.send(EncodeServerXML.leaveGame(InvitedPlayers.get(position).getId(), ((InvitePlayersActivity) ActivityContext).GameId));
                if (DecodeServerXML.leaveGame(client.read())) {
                    InvitedPlayers.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast uninviteError = Toast.makeText(ActivityContext.getApplicationContext(), "Something failed. Please try again.", Toast.LENGTH_SHORT);
                    uninviteError.show();
                }
            }
        });

        return playerView;
    }
}
