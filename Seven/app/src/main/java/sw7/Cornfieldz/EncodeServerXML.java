package sw7.Cornfieldz;

import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        xml += "<EOF>";

        return xml;
    }

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

    public static String createGame(String name, Boolean isPrivateGame, Integer numberOfTeams, Calendar gameStart, Integer gameEnd, LatLng southEastBoundary, LatLng northWestBoundary, Integer hostId) {

        String tag = "CreateGame";
        String entryA = "Name";
        String entryB = "Privacy";
        String entryC = "NumberOfTeams";
        String entryD = "GameStart";
        String entryE = "GameEnd";
        String entryF = "SouthEastBoundary";
        String entryG = "NorthWestBoundary";
        String entryH = "Latitude";
        String entryI = "Longitude";
        String entryJ = "HostId";

        String entryK = "Year";
        String entryL = "Month";
        String entryM = "Day";
        String entryN = "Hour";
        String entryO = "Minute";

        Calendar gameEndCalendar = gameStart.getInstance();
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
        xml += "<" + entryD + ">";
        xml += "<" + entryK + ">" + gameStart.get(Calendar.YEAR) + "</" + entryK + ">";
        xml += "<" + entryL + ">" + gameStart.get(Calendar.MONTH) + "</" + entryL + ">";
        xml += "<" + entryM + ">" + gameStart.get(Calendar.DAY_OF_MONTH) + "</" + entryM + ">";
        xml += "<" + entryN + ">" + gameStart.get(Calendar.HOUR_OF_DAY) + "</" + entryN + ">";
        xml += "<" + entryO + ">" + gameStart.get(Calendar.MINUTE) + "</" + entryO + ">";
        xml += "</" + entryD + ">";
        xml += "<" + entryE + ">";
        xml += "<" + entryK + ">" + gameEndCalendar.get(Calendar.YEAR) + "</" + entryK + ">";
        xml += "<" + entryL + ">" + gameEndCalendar.get(Calendar.MONTH) + "</" + entryL + ">";
        xml += "<" + entryM + ">" + gameEndCalendar.get(Calendar.DAY_OF_MONTH) + "</" + entryM + ">";
        xml += "<" + entryN + ">" + gameEndCalendar.get(Calendar.HOUR_OF_DAY) + "</" + entryN + ">";
        xml += "<" + entryO + ">" + gameEndCalendar.get(Calendar.MINUTE) + "</" + entryO + ">";
        xml += "</" + entryE + ">";
        xml += "<" + entryF + ">" + "<" + entryH + ">" + "<" + southEastBoundary.latitude + "</" + entryH + ">" + "<" + entryI + ">" + "<" + southEastBoundary.longitude + "</" + entryI + ">" + "</" + entryF + ">";
        xml += "<" + entryG + ">" + "<" + entryH + ">" + "<" + northWestBoundary.latitude + "</" + entryH + ">" + "<" + entryI + ">" + "<" + northWestBoundary.longitude + "</" + entryI + ">" + "</" + entryG + ">";
        xml += "<" + entryJ + ">" + hostId + "</" + entryG + ">";
        xml += "</" + tag + ">";
        xml += "<EOF>";

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
        xml += "<EOF>";

        return xml;
    }

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