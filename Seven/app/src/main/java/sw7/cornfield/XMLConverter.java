package sw7.cornfield;

import java.util.Map;

/**
 * Created by Morten on 24-11-2014.
 */
public class XMLConverter {

    public String XMLConverter(String id, Map<String, String> variables) {

        String xml = "";
        xml += "<" + id + ">";

        for (Map.Entry entry : variables.entrySet()) {
            xml += "<" + entry.getKey() + ">";
            xml += entry.getValue();
            xml += "</" + entry.getKey() + ">";
        }

        xml += "</" + id + ">";
        return xml;
    }
}