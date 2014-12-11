package sw7.Cornfieldz;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Morten on 24-11-2014.
 */
public class EncodeServerXML {

    private static String createDateTimeXML(String xml, Calendar dateTime) {
        String entryA = "Year";
        String entryB = "Month";
        String entryC = "Day";
        String entryD = "Hour";
        String entryE = "Minute";

        xml += "<" + entryA + ">" + dateTime.get(Calendar.YEAR) + "</" + entryA + ">";
        xml += "<" + entryB + ">" + dateTime.get(Calendar.MONTH) + "</" + entryB + ">";
        xml += "<" + entryC + ">" + dateTime.get(Calendar.DAY_OF_MONTH) + "</" + entryC + ">";
        xml += "<" + entryD + ">" + dateTime.get(Calendar.HOUR_OF_DAY) + "</" + entryD + ">";
        xml += "<" + entryE + ">" + dateTime.get(Calendar.MINUTE) + "</" + entryE + ">";

        return xml;
    }

    private static String createCoordinateXML(String xml, LatLng coordinate) {

        String entryA = "Latitude";
        String entryB = "Longitude";

        xml += "<" + entryA + ">" + "<" + coordinate.latitude + "</" + entryA + ">";
        xml += "<" + entryB + ">" + "<" + coordinate.longitude + "</" + entryA + ">";

        return xml;
    }

    public static String login(String username, String password) {
        String tag = "Login";
        String entryA = "Username";
        String entryB = "Password";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + username + "</" + entryA + ">";
        xml += "<" + entryB + ">" + password + "</" + entryB + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    //TODO: This is not complete
    public static String signUpCheck(String username) {
        String tag = "SignUpCheck";
        String entryA = "Username";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + username + "</" + entryA + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    //TODO: This is not complete
    public static String signUp(String username, String password) {
        String tag = "SignUp";
        String entryA = "Username";
        String entryB = "Password";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + username + "</" + entryA + ">";
        xml += "<" + entryB + ">" + password + "</" + entryB + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    public static String createGame(String name, Boolean isPrivateGame, Integer numberOfTeams, Calendar gameStartCalendar, Integer gameEnd, LatLng southEastBoundary, LatLng northWestBoundary, Integer hostId) {
        String tag = "CreateGame";
        String entryA = "Name";
        String entryB = "Privacy";
        String entryC = "NumberOfTeams";
        String entryD = "GameStart";
        String entryE = "GameEnd";
        String entryF = "SouthEastBoundary";
        String entryG = "NorthWestBoundary";
        String entryH = "HostId";


        Calendar gameEndCalendar = gameStartCalendar;
        gameEndCalendar.add(Calendar.HOUR, gameEnd);


        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + name + "</" + entryA + ">";

        if(isPrivateGame) {
        xml += "<" + entryB + ">" + "PRIVATE" + "</" + entryB + ">";
        } else {
            xml += "<" + entryB + ">" + "PUBLIC" + "</" + entryB + ">";
        }

        xml += "<" + entryC + ">" + numberOfTeams + "</" + entryC + ">";
        xml += "<" + entryD + ">" + createDateTimeXML(xml, gameStartCalendar) +  "</" + entryD + ">";
        xml += "<" + entryE + ">" + createDateTimeXML(xml, gameEndCalendar) + "</" + entryE + ">";
        xml += "<" + entryF + ">" + createCoordinateXML(xml, southEastBoundary) + "</" + entryF + ">";
        xml += "<" + entryG + ">" + createCoordinateXML(xml, northWestBoundary) + "</" + entryG + ">";
        xml += "<" + entryH + ">" + hostId + "</" + entryG + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    //TODO: This is not complete
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
        xml += "<EOF>";

        return xml;
    }

    //TODO: This is not complete
    public static String getPlayerInvites(String gameId) {
        String tag = "GetPlayerInvites";
        String entryA = "GameId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + gameId + "</" + entryA + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    public static String getPublicGames() {
        String tag = "GetPublicGames";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    public static String joinGame(Integer userId, Integer gameId) {
        String tag = "JoinGame";
        String entryA = "UserId";
        String entryB = "GameId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + userId + "</" + entryA + ">";
        xml += "<" + entryB + ">" + gameId + "</" + entryB + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    public static String getMyGames(Integer userId) {
        String tag = "GetMyGames";
        String entryA = "UserId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + userId + "</" + entryA + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }

    public static String leaveGame(Integer userId, Integer gameId) {
        String tag = "LeaveGame";
        String entryA = "UserId";
        String entryB = "GameId";

        String xml = "";

        xml += "<" + tag + ">";
        xml += "<" + entryA + ">" + userId + "</" + entryA + ">";
        xml += "<" + entryB + ">" + gameId + "</" + entryB + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

        return xml;
    }
}