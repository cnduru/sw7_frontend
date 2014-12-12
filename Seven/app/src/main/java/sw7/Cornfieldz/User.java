package sw7.Cornfieldz;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Morten on 05-12-2014.
 */
public class User {
    private Integer UserId;
    private String Username;
    private LatLng Position;

    public User(Integer userId, String username, LatLng position) {
        UserId = userId;
        Username = username;
        Position = position;
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