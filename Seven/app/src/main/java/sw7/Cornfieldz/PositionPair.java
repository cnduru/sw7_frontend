package sw7.Cornfieldz;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Morten on 05-12-2014.
 */
public class PositionPair {
    private Integer UserId;
    private LatLng Position;

    public PositionPair(Integer id, LatLng position) {
        this.UserId = id;
        this.Position = position;
    }

    public Integer getUserId() {
        return UserId;
    }

    public LatLng getPosition() {
        return Position;
    }
}