package sw7.Cornfieldz;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Johan 'Jizztærsker' on 02-12-2014.
 */
public class EncodeActionXML {

    public static String useItem(String game, String player, String item, String target) {

        String tag = "useItem";
        String entryA = "game";
        String entryB = "player";
        String entryC = "Item";
        String entryD = "Target";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + game + "</" + entryA + ">";
        xml += "<" + entryB + ">" + player + "</" + entryB + ">";
        xml += "<" + entryC + ">" + item + "</" + entryC + ">";
        xml += "<" + entryD + ">" + target + "</" + entryD + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    public static String pickupItem(String game, String player, String item) {

        String tag = "pickupItem";
        String entryA = "game";
        String entryB = "player";
        String entryC = "Item";
        String entryD = "Target";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + game + "</" + entryA + ">";
        xml += "<" + entryB + ">" + player + "</" + entryB + ">";
        xml += "<" + entryC + ">" + item + "</" + entryC + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    public static String scan(String game, String player, String latlng) {

        String tag = "scan";
        String entryA = "game";
        String entryB = "player";
        String entryC = "latlng";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + game + "</" + entryA + ">";
        xml += "<" + entryB + ">" + player + "</" + entryB + ">";
        xml += "<" + entryC + ">" + latlng + "</" + entryC + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    public static String gameUpdate(Integer userId, Integer gameId, LatLng position) {
        String tag = "UpdatePlayerLocation";
        String entryA = "UserId";
        String entryB = "GameId";
        String entryC = "Latitude";
        String entryD = "Longitude";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + userId + "</" + entryA + ">";
        xml += "<" + entryB + ">" + gameId + "</" + entryB + ">";
        xml += "<" + entryC + ">" + position.latitude + "</" + entryC + ">";
        xml += "<" + entryD + ">" + position.longitude + "</" + entryD + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    public static String shootAction(Integer gameId, Integer userId, Integer victimId, Integer itemId) {

        String tag = "ShootAction";
        String entryA = "GameId";
        String entryB = "UserId";
        String entryC = "Victim";
        String entryD = "ItemId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + gameId + "</" + entryA + ">";
        xml += "<" + entryB + ">" + userId + "</" + entryB + ">";
        xml += "<" + entryC + ">" + victimId + "</" + entryC + ">";
        xml += "<" + entryD + ">" + itemId + "</" + entryD + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }
}
