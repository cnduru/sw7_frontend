package sw7.cornfield;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Morten on 09-12-2014.
 */
public class UserMarker {
    Integer UserId;
    MarkerOptions markerOptions;

    public UserMarker(Integer userId, LatLng position) {
        markerOptions = new MarkerOptions();
        markerOptions.title(UserId.toString());
        markerOptions.position(position);
    }

    public Integer getUserId() {
        return UserId;
    }

    public MarkerOptions getMarker() {
        return markerOptions;
    }
}
