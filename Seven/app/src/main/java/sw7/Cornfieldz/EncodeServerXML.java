package sw7.Cornfieldz;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Morten on 24-11-2014.
 */
public class EncodeServerXML {

    private static String createDateTimeXML(Calendar dateTime) {
        String entryA = "Year";
        String entryB = "Month";
        String entryC = "Day";
        String entryD = "Hour";
        String entryE = "Minute";

        String xml = "";
        xml += Tag.open(entryA) + dateTime.get(Calendar.YEAR) + Tag.close(entryA);
        xml += Tag.open(entryB) + dateTime.get(Calendar.MONTH) + Tag.close(entryB);
        xml += Tag.open(entryC) + dateTime.get(Calendar.DAY_OF_MONTH) + Tag.close(entryC);
        xml += Tag.open(entryD) + dateTime.get(Calendar.HOUR_OF_DAY) + Tag.close(entryD);
        xml += Tag.open(entryE) + dateTime.get(Calendar.MINUTE) + Tag.close(entryE);

        return xml;
    }

    private static String createCoordinateXML(LatLng coordinate) {
        String entryA = "Latitude";
        String entryB = "Longitude";

        String xml = "";
        xml += Tag.open(entryA) + coordinate.latitude + Tag.close(entryA);
        xml += Tag.open(entryB) + coordinate.longitude + Tag.close(entryB);

        return xml;
    }

    public static String login(String username, String password) {
        String tag = "Login";
        String entryA = "Username";
        String entryB = "Password";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + username + Tag.close(entryA);
        xml += Tag.open(entryB) + password + Tag.close(entryB);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    //TODO: This is not complete
    public static String signUpCheck(String username) {
        String tag = "SignUpCheck";
        String entryA = "Username";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + username + Tag.close(entryA);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    //TODO: This is not complete
    public static String signUp(String username, String password) {
        String tag = "SignUp";
        String entryA = "Username";
        String entryB = "Password";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + username + Tag.close(entryA);
        xml += Tag.open(entryB) + password + Tag.close(entryB);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    public static String createGame(String name, Boolean isPrivateGame, Integer teamCount, Calendar gameStartCalendar, Integer gameDuration, LatLng southEastBoundary, LatLng northWestBoundary, Integer hostId) {
        Integer publicGame = 1;
        Integer privateGame = 2;

        String tag = "CreateGame";
        String entryA = "Name";
        String entryB = "Privacy";
        String entryC = "NumberOfTeams";
        String entryD = "GameStart";
        String entryE = "GameEnd";
        String entryF = "SouthEastBoundary";
        String entryG = "NorthWestBoundary";
        String entryH = "HostId";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + name + Tag.close(entryA);

        if(isPrivateGame) {
            xml += Tag.open(entryB) + privateGame + Tag.close(entryB);
        } else {
            xml += Tag.open(entryB) + publicGame + Tag.close(entryB);
        }

        xml += Tag.open(entryC) + teamCount + Tag.close(entryC);
        xml += Tag.open(entryD) + createDateTimeXML(gameStartCalendar) +  Tag.close(entryD);

        gameStartCalendar.add(Calendar.HOUR, gameDuration);

        xml += Tag.open(entryE) + createDateTimeXML(gameStartCalendar) +  Tag.close(entryE);
        xml += Tag.open(entryF) + createCoordinateXML(southEastBoundary) + Tag.close(entryF);
        xml += Tag.open(entryG) + createCoordinateXML(northWestBoundary) + Tag.close(entryG);
        xml += Tag.open(entryH) + hostId + Tag.close(entryH);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    //TODO: This is not complete
    public static String inviteUser(String userName, Integer gameId) {
        String tag = "InviteUser";
        String entryA = "UserName";
        String entryB = "GameId";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + userName + Tag.close(entryA);
        xml += Tag.open(entryB) + gameId + Tag.close(entryB);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    //TODO: This is not complete
    public static String getPlayerInvites(String gameId) {
        String tag = "GetPlayerInvites";
        String entryA = "GameId";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + gameId + Tag.close(entryA);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    public static String getPublicGames() {
        String tag = "GetPublicGames";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    public static String joinGame(Integer userId, Integer gameId) {
        String tag = "JoinGame";
        String entryA = "UserId";
        String entryB = "GameId";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + userId + Tag.close(entryA);
        xml += Tag.open(entryB) + gameId + Tag.close(entryB);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    public static String getMyGames(Integer userId) {
        String tag = "GetMyGames";
        String entryA = "UserId";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + userId + Tag.close(entryA);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }

    public static String leaveGame(Integer userId, Integer gameId) {
        String tag = "LeaveGame";
        String entryA = "UserId";
        String entryB = "GameId";

        String xml = "";
        xml += Tag.open(tag);
        xml += Tag.open(entryA) + userId + Tag.close(entryA);
        xml += Tag.open(entryB) + gameId + Tag.close(entryB);
        xml += Tag.close(tag);
        xml += Tag.endXML();

        return xml;
    }
}