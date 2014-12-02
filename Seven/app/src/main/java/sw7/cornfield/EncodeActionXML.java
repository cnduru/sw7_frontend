package sw7.cornfield;

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

        return xml;
    }

    public static String update(String game, String player, String latlng) {

        String tag = "update";
        String entryA = "game";
        String entryB = "player";
        String entryC = "latlng";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + game + "</" + entryA + ">";
        xml += "<" + entryB + ">" + player + "</" + entryB + ">";
        xml += "<" + entryC + ">" + latlng + "</" + entryC + ">";
        xml += "</" + tag + ">";

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

        return xml;
    }
}
