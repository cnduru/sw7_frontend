package sw7.Cornfieldz;

/**
 * Created by Morten on 11-12-2014.
 */
public class Tag {

    public static String open(String tag) {
        return "<" + tag + ">";
    }

    public static String close(String tag) {
        return "</" + tag + ">";
    }

    public static String endXML() {
        return "<EOF>";
    }
}
