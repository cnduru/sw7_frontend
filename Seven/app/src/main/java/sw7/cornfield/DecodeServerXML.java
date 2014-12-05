package sw7.cornfield;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Morten on 24-11-2014.
 */
public class DecodeServerXML {

    public static Map<String, String> login(Document data) {
        Map<String, String> loginValidation = new HashMap<String, String>();

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/Login/UserId/text()";
            NodeList nl= (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/Login/Valid/text()";
            NodeList n2= (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            loginValidation.put("UserId", nl.item(0).getTextContent());
            loginValidation.put("Valid", n2.item(0).getTextContent());

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return loginValidation;
    }

    public static boolean signUpCheck(Document data) {
        boolean validation = false;

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/SignUpCheck/Valid/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            if(n1.item(0).getTextContent().equals("TRUE")) {
                validation = true;
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return validation;
    }

    public static Integer signUp(Document data) {
        Integer userId = null;

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/SignUp/UserId/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            userId = Integer.parseInt(n1.item(0).getTextContent());

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public static Integer createGame(Document data) {
        Integer gameId = null;

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/CreateGame/GameId/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            gameId = Integer.parseInt(n1.item(0).getTextContent());

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return gameId;
    }

    public static Boolean setPlayerInvites(Document data) {

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/SetPlayerInvites/Message/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            if (n1.item(0).getTextContent().equals("OK")) {
                return true;
            } else {
                return false;
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String, String> getPlayerInvites(Document data) {
        String[] tempList;
        Map<String, String> invitees = new HashMap<String, String>();

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/GetPlayerInvites/RetrieveId/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < n1.getLength(); i++) {
                tempList = n1.item(i).getTextContent().split("&");
                invitees.put(tempList[0], tempList[1]);
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return invitees;
    }

    public static Map<String, String> getPublicGames(Document data) {
        String[] tempList;
        Map<String, String> games = new HashMap<String, String>();

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/GetPublicGames/Game/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < n1.getLength(); i++) {
                tempList = n1.item(i).getTextContent().split("&");
                games.put(tempList[0], tempList[1]);
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return games;
    }

    public static Boolean joinGame(Document data) {

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/JoinGame/Message/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            if (n1.item(0).getTextContent().equals("OK")) {
                return true;
            } else {
                return false;
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String, String> getActiveGames(Document data) {
        String[] tempList;
        Map<String, String> games = new HashMap<String, String>();

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/GetActiveGames/Game/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < n1.getLength(); i++) {
                tempList = n1.item(i).getTextContent().split("&");
                games.put(tempList[0], tempList[1]);
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return games;
    }

    public static Boolean leaveGame(Document data) {

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/LeaveGame/Message/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            if (n1.item(0).getTextContent().equals("OK")) {
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