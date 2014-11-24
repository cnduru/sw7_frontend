package sw7.cornfield;

import java.util.List;

/**
 * Created by Morten on 24-11-2014.
 */
public class EncodeServerXML {

    public static String login(String username, String password) {

        String tag = "Login";
        String entryA = "Username";
        String entryB = "Password";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + username + "</" + entryA + ">";
        xml += "<" + entryB + ">" + password + "</" + entryB + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String signUpCheck(String username) {

        String tag = "SignUpCheck";
        String entryA = "Username";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + username + "</" + entryA + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String signUp(String username, String password) {

        String tag = "SignUp";
        String entryA = "Username";
        String entryB = "Password";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + username + "</" + entryA + ">";
        xml += "<" + entryB + ">" + password + "</" + entryB + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String createGame(String name, String isPrivateGame, String numberOfTeams, String gameStart, String gameEnd, String southEastBoundry, String northEastBoundry, String hostId) {

        String tag = "CreateGame";
        String entryA = "Name";
        String entryB = "Privacy";
        String entryC = "NumberOfTeams";
        String entryD = "GameStart";
        String entryE = "GameEnd";
        String entryF = "SouthEastBoundry";
        String entryG = "NorthEastBoundry";
        String entryH = "HostId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + name + "</" + entryA + ">";
        xml += "<" + entryB + ">" + isPrivateGame + "</" + entryB + ">";
        xml += "<" + entryC + ">" + numberOfTeams + "</" + entryC + ">";
        xml += "<" + entryD + ">" + gameStart + "</" + entryD + ">";
        xml += "<" + entryE + ">" + gameEnd + "</" + entryE + ">";
        xml += "<" + entryF + ">" + southEastBoundry + "</" + entryF + ">";
        xml += "<" + entryG + ">" + northEastBoundry + "</" + entryG + ">";
        xml += "<" + entryH + ">" + hostId + "</" + entryG + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String setPlayerInvites(String gameId, List<String> players) {

        String tag = "SetPlayerInvites";
        String entryA = "GameId";
        String entryB = "Players";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + gameId + "</" + entryA + ">";

        for(String player : players) {
            xml += "<" + entryB + ">" + player + "</" + entryB + ">";
        }

        xml += "</" + tag + ">";

        return xml;
    }

    public static String getPlayerInvites(String gameId) {

        String tag = "GetPlayerInvites";
        String entryA = "GameId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + gameId + "</" + entryA + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String getPublicGames() {

        String tag = "GetPublicGames";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String joinGame(String userId, String gameId) {

        String tag = "JoinGame";
        String entryA = "UserId";
        String entryB = "GameId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + userId + "</" + entryA + ">";
        xml += "<" + entryB + ">" + gameId + "</" + entryB + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String getActiveGames(String userId) {

        String tag = "GetActiveGames";
        String entryA = "UserId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + userId + "</" + entryA + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String leaveGame(String userId, String gameId) {

        String tag = "LeaveGame";
        String entryA = "UserId";
        String entryB = "GameId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + userId + "</" + entryA + ">";
        xml += "<" + entryB + ">" + gameId + "</" + entryB + ">";
        xml += "</" + tag + ">";

        return xml;
    }
}