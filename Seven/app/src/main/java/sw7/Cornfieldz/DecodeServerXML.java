package sw7.Cornfieldz;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    //TODO: This is not complete
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

    //TODO: This is not complete
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

    public static List<Pair> getPlayerInvites(Document data) {
        List<Pair> invitees = new ArrayList<Pair>();

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/GetPlayerInvites/Player/UserId/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/GetPlayerInvites/Player/UserName/text()";
            NodeList n2 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < n1.getLength(); i++) {
                invitees.add(new Pair(Integer.parseInt(n1.item(i).getTextContent()), n2.item(i).getTextContent()));
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return invitees;
    }

    public static Map<String, String> inviteUser (Document data) {
        Map<String, String> inviteUserValidation = new HashMap<String, String>();

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/InviteUser/UserId/text()";
            NodeList nl= (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/InviteUser/Message/text()";
            NodeList n2= (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            inviteUserValidation.put("UserId", nl.item(0).getTextContent());
            inviteUserValidation.put("Message", n2.item(0).getTextContent());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return inviteUserValidation;
    }

    public static List<Pair> getPublicGames(Document data) {
        List<Pair> games = new ArrayList<Pair>();

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/GetPublicGames/Game/GameId/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "GetPublicGames/Game/GameName/text()";
            NodeList n2 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < n1.getLength(); i++) {
                games.add(new Pair(Integer.parseInt(n1.item(i).getTextContent()), n2.item(i).getTextContent()));
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

    public static List<Pair> getMyGames(Document data) {
        List<Pair> games = new ArrayList<Pair>();

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/GetMyGames/Game/GameId/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "GetMyGames/Game/GameName/text()";
            NodeList n2 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            for (int i = 0; i < n1.getLength(); i++) {
                games.add(new Pair(Integer.parseInt(n1.item(i).getTextContent()), n2.item(i).getTextContent()));
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

    public static Map<String, String> getLobbyInfo (Document data) {
        Map<String, String> lobbyInfo = new HashMap<String, String>();

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String exp = "/LobbyInfo/Privacy/text()";
            NodeList n1 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/NumberOfTeams/text()";
            NodeList n2 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/GameEnd/Year/text()";
            NodeList n3 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/GameEnd/Month/text()";
            NodeList n4 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/GameEnd/Day/text()";
            NodeList n5 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/GameEnd/Hour/text()";
            NodeList n6 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/GameEnd/Minute/text()";
            NodeList n7 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/NorthWestBoundary/Latitude/text()";
            NodeList n8 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/NorthWestBoundary/Longitude/text()";
            NodeList n9 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/SouthEastBoundary/Latitude/text()";
            NodeList n10 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/SouthEastBoundary/Longitude/text()";
            NodeList n11 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/HostId/text()";
            NodeList n12 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);
            exp = "/LobbyInfo/Alias/text()";
            NodeList n13 = (NodeList) xpath.compile(exp).evaluate(data, XPathConstants.NODESET);

            lobbyInfo.put("Privacy", n1.item(0).getTextContent());
            lobbyInfo.put("TeamCount", n2.item(0).getTextContent());
            lobbyInfo.put("Year", n3.item(0).getTextContent());
            lobbyInfo.put("Month", n4.item(0).getTextContent());
            lobbyInfo.put("Day", n5.item(0).getTextContent());
            lobbyInfo.put("Hour", n6.item(0).getTextContent());
            lobbyInfo.put("Minute", n7.item(0).getTextContent());
            lobbyInfo.put("NWLatitude", n8.item(0).getTextContent());
            lobbyInfo.put("NWLongitude", n9.item(0).getTextContent());
            lobbyInfo.put("SELatitude", n10.item(0).getTextContent());
            lobbyInfo.put("SELongitude", n11.item(0).getTextContent());
            lobbyInfo.put("HostId", n12.item(0).getTextContent());
            lobbyInfo.put("Name", n13.item(0).getTextContent());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return lobbyInfo;
    }
}