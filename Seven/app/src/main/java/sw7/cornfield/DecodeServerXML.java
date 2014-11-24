package sw7.cornfield;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Morten on 24-11-2014.
 */
public class DecodeServerXML {

    public static Map<String, String> login(String data) {

        InputSource xml = new InputSource(new StringReader(data));
        XPath xpath = XPathFactory.newInstance().newXPath();
        Map<String, String> loginValidation = null;

        try {
            Object login = xpath.evaluate("/Login", xml, XPathConstants.NODE);

            loginValidation.put("Valid", xpath.evaluate("Valid", login));
            loginValidation.put("UserId", xpath.evaluate("UserId", login));
        } catch (Exception e) {
        }

        return loginValidation;
    }

    public static boolean signUpCheck(String data) {

        InputSource xml = new InputSource(new StringReader(data));
        XPath xpath = XPathFactory.newInstance().newXPath();
        boolean validation = false;

        try {
            Object signUpCheck = xpath.evaluate("/SignUpCheck", xml, XPathConstants.NODE);

            if("true" == xpath.evaluate("Valid", signUpCheck)) {
                validation = true;
            }

        } catch (Exception e) {
        }
        return validation;
    }

    public static int signUp(String data) {

        InputSource xml = new InputSource(new StringReader(data));
        XPath xpath = XPathFactory.newInstance().newXPath();
        Integer userId = null;

        try {
            Object signUp = xpath.evaluate("/SignUp", xml, XPathConstants.NODE);
            userId = Integer.parseInt(xpath.evaluate("UserId", signUp));
        } catch (Exception e) {
        }
        return userId;
    }

    public static Integer createGame(String data) {

        InputSource xml = new InputSource(new StringReader(data));
        XPath xpath = XPathFactory.newInstance().newXPath();
        Integer gameId = null;

        try {
            Object createGame = xpath.evaluate("/CreateGame", xml, XPathConstants.NODE);
            gameId = Integer.parseInt(xpath.evaluate("GameId", createGame));
        } catch (Exception e) {
        }
        return gameId;
    }

    public static Boolean setPlayerInvites(String data) {

        InputSource xml = new InputSource(new StringReader(data));
        XPath xpath = XPathFactory.newInstance().newXPath();

        try {
            Object editPlayerInvites = xpath.evaluate("/SetPlayerInvites", xml, XPathConstants.NODE);
            if ("OK" == xpath.evaluate("Message", editPlayerInvites)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static Map<Integer, String> getPlayerInvites(String data) {

        XPath xpath = XPathFactory.newInstance().newXPath();
        List<String> strings = new ArrayList<String>();
        Map<Integer, String> invitees = null;

        try {
            XPathExpression expr = xpath.compile("//Core.Reference");
            NodeList playerList = (NodeList) expr.evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < playerList.getLength(); i++) {
                strings.add(playerList.item(i).getTextContent());
            }
        } catch (Exception e) {
        }

        List<String> splitValues;
        for(String invitee : strings) {
            splitValues = Arrays.asList(invitee.split("&"));
            invitees.put(Integer.parseInt(splitValues.get(0)), splitValues.get(1));
        }
        return invitees;
    }

    public static Map<Integer, String> getPublicGames(String data) {

        XPath xpath = XPathFactory.newInstance().newXPath();
        List<String> strings = new ArrayList<String>();
        Map<Integer, String> games = null;

        try {
            XPathExpression expr = xpath.compile("//Core.Reference");
            NodeList gameList = (NodeList) expr.evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < gameList.getLength(); i++) {
                strings.add(gameList.item(i).getTextContent());
            }
        } catch (Exception e) {
        }

        List<String> splitValues;
        for(String invitee : strings) {
            splitValues = Arrays.asList(invitee.split("&"));
            games.put(Integer.parseInt(splitValues.get(0)), splitValues.get(1));
        }
        return games;
    }

    public static Boolean joinGame(String data) {

        InputSource xml = new InputSource(new StringReader(data));
        XPath xpath = XPathFactory.newInstance().newXPath();

        try {
            Object joinGame = xpath.evaluate("/JoinGame", xml, XPathConstants.NODE);
            if ("OK" == xpath.evaluate("Message", joinGame)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static Map<Integer, String> getActiveGames(String data) {

        XPath xpath = XPathFactory.newInstance().newXPath();
        List<String> strings = new ArrayList<String>();
        Map<Integer, String> games = null;

        try {
            XPathExpression expr = xpath.compile("//Core.Reference");
            NodeList gameList = (NodeList) expr.evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < gameList.getLength(); i++) {
                strings.add(gameList.item(i).getTextContent());
            }
        } catch (Exception e) {
        }

        List<String> splitValues;
        for(String invitee : strings) {
            splitValues = Arrays.asList(invitee.split("&"));
            games.put(Integer.parseInt(splitValues.get(0)), splitValues.get(1));
        }
        return games;
    }

    public static Boolean leaveGame(String data) {

        InputSource xml = new InputSource(new StringReader(data));
        XPath xpath = XPathFactory.newInstance().newXPath();

        try {
            Object leaveGame = xpath.evaluate("/LeaveGame", xml, XPathConstants.NODE);
            if ("OK" == xpath.evaluate("Message", leaveGame)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }
}