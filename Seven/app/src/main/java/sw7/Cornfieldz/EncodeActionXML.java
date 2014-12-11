package sw7.Cornfieldz;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Johan 'Jizztærsker' on 02-12-2014.
 */
public class EncodeActionXML {

    //TODO: This is not complete
    public static String pickupItem(Integer userId, Integer gameId, Integer itemId) {

        String tag = "PickupItem";
        String entryA = "UserId";
        String entryB = "GameId";
        String entryC = "ItemId";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + userId + Tag.close(entryA);
        xml += Tag.open(entryB) + gameId + Tag.close(entryB);
        xml += Tag.open(entryC) + itemId + Tag.close(entryC);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    //TODO: This is not complete
    public static String scan(Integer userId, Integer gameId, LatLng position) {

        String tag = "Scan";
        String entryA = "UserId";
        String entryB = "GameId";
        String entryC = "Latitude";
        String entryD = "Longitude";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + userId + Tag.close(entryA);
        xml += Tag.open(entryB) + gameId + Tag.close(entryB);
        xml += Tag.open(entryC) + position.latitude + Tag.close(entryC);
        xml += Tag.open(entryD) + position.longitude + Tag.close(entryD);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    public static String gameUpdate(Integer userId, Integer gameId, LatLng position) {
        String tag = "UpdatePlayerLocation";
        String entryA = "UserId";
        String entryB = "GameId";
        String entryC = "Latitude";
        String entryD = "Longitude";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + userId + Tag.close(entryA);
        xml += Tag.open(entryB) + gameId + Tag.close(entryB);
        xml += Tag.open(entryC) + position.latitude + Tag.close(entryC);
        xml += Tag.open(entryD) + position.longitude + Tag.close(entryD);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    public static String shootAction(Integer userId, Integer gameId, Integer targetId, Integer itemId) {
        String tag = "ShootAction";
        String entryA = "GameId";
        String entryB = "UserId";
        String entryC = "Victim";
        String entryD = "ItemId";

        String xml = "";
        xml += Tag.open(entryA) + userId + Tag.close(entryB);
        xml += Tag.open(entryB) + gameId + Tag.close(entryA);
        xml += Tag.open(entryC) + itemId + Tag.close(entryC);
        xml += Tag.open(entryD) + targetId + Tag.close(entryD);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }
}
