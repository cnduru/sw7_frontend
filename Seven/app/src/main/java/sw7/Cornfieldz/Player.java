package sw7.Cornfieldz;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Morten on 05-12-2014.
 */
public class Player {
    private Integer UserId;
    private String Username;
    private LatLng Position;

    public Player(Integer id, String username, LatLng position) {
        this.UserId = id;
        this.Username = username;
        this.Position = position;
    }

    public Integer getUserId() {
        return UserId;
    }

    public String getUsername() {
        return Username;
    }

    public LatLng getPosition() {
        return Position;
    }
}