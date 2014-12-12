package sw7.Cornfieldz;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Johan 'Jizztærsker' on 02-12-2014.
 */
public class DecodeActionXML {

    //TODO: This is not complete
    public static Map<String, String> useItem(String data) {
        InputSource xml = new InputSource(new StringReader(data));
        XPath xpath = XPathFactory.newInstance().newXPath();
        Map<String, String> useItemResponse = Collections.emptyMap();

        try {
            Object useItem = xpath.evaluate("/UseItem", xml, XPathConstants.NODE);

            useItemResponse.put("Target", xpath.evaluate("Target", useItem));
            useItemResponse.put("Result", xpath.evaluate("Result", useItem));
        } catch (Exception e) {
        }

        return useItemResponse;
    }

    //TODO: This is not complete
    public static Map<String, String> pickupItem(String data) {
        InputSource xml = new InputSource(new StringReader(data));
        XPath xpath = XPathFactory.newInstance().newXPath();
        Map<String, String> pickupItemResponse = Collections.emptyMap();

        try {
            Object pickupItem = xpath.evaluate("/UseItem", xml, XPathConstants.NODE);

            pickupItemResponse.put("Item", xpath.evaluate("Item", pickupItem));
            pickupItemResponse.put("ItemDescription", xpath.evaluate("ItemDescription", pickupItem));
        } catch (Exception e) {
        }
        return pickupItemResponse;
    }

    //TODO: This is not complete
    public static List<String> scan(String data) {
        InputSource xml = new InputSource(new StringReader(data));
        XPath xpath = XPathFactory.newInstance().newXPath();
        List<String> items = new ArrayList<String>();

        try {
            XPathExpression expr = xpath.compile(data);
            NodeList itemList = (NodeList) expr.evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < itemList.getLength(); i++) {
                items.add(itemList.item(i).getTextContent());
            }
        } catch (Exception e) {
        }

        return items;
    }

    public static List<User> gameUpdate(Document data) {
        List<User> games = new ArrayList<User>();

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "GameUpdate/Player/UserId/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "GameUpdate/Player/UserName/text()";
            NodeList n2 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "GameUpdate/Player/Latitude/text()";
            NodeList n3 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "GameUpdate/Player/Longitude/text()";
            NodeList n4 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < n1.getLength(); i++) {
                games.add(
                        new User(Integer.parseInt(n1.item(i).getTextContent()),
                        n2.item(i).getTextContent(),
                        new LatLng(Double.parseDouble(n3.item(i).getTextContent()),
                        Double.parseDouble(n4.item(i).getTextContent()))));
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return games;
    }

    public static boolean shootAction(Document data) {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "ShootAction/Message/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            if (n1.item(0).getTextContent().equals("TRUE")) {
                return true;
            } else {
                return false;
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
