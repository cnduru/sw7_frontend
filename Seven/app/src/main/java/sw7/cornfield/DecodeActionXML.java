package sw7.cornfield;

import com.google.android.gms.maps.model.LatLng;

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
import javax.xml.xpath.XPathFactory;

/**
 * Created by Johan 'Jizztærsker' on 02-12-2014.
 */
public class DecodeActionXML {
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

    public static List<String> update(String data) {

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

}