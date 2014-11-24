package sw7.cornfield;


import java.util.List;

/**
 * Created by Morten on 24-11-2014.
 */
public class XMLConverter {

    public static String convertLogin(String username, String password) {

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

    public static String convertSignUpCheck(String username) {

        String tag = "SignUpCheck";
        String entryA = "Username";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + username + "</" + entryA + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String convertSignUp(String username, String password) {

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

    public static String convertCreateGame(String name, String isPrivateGame, String numberOfTeams, String gameStart, String gameEnd, String southEastBoundry, String northEastBoundry) {

        String tag = "CreateGame";
        String entryA = "Name";
        String entryB = "Privacy";
        String entryC = "NumberOfTeams";
        String entryD = "GameStart";
        String entryE = "GameEnd";
        String entryF = "SouthEastBoundry";
        String entryG = "NorthEastBoundry";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + name + "</" + entryA + ">";
        xml += "<" + entryB + ">" + isPrivateGame + "</" + entryB + ">";
        xml += "<" + entryC + ">" + numberOfTeams + "</" + entryC + ">";
        xml += "<" + entryD + ">" + gameStart + "</" + entryD + ">";
        xml += "<" + entryE + ">" + gameEnd + "</" + entryE + ">";
        xml += "<" + entryF + ">" + southEastBoundry + "</" + entryF + ">";
        xml += "<" + entryG + ">" + northEastBoundry + "</" + entryG + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String convertEditPlayerInvites(String gameId, List<String> players) {

        String tag = "EditPlayerInvites";
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

    public static String convertGetPlayerInvites(String gameId) {

        String tag = "GetPlayerInvites";
        String entryA = "GameId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + gameId + "</" + entryA + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String convertGetPublicGames() {

        String tag = "GetPublicGames";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String convertJoinGame(String userId, String gameId) {

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

    public static String convertGetActiveGames(String userId) {

        String tag = "GetActiveGames";
        String entryA = "UserId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + userId + "</" + entryA + ">";
        xml += "</" + tag + ">";

        return xml;
    }

    public static String convertLeaveGame(String userId, String gameId) {

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