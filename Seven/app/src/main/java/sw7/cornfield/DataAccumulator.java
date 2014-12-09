package sw7.cornfield;

import android.os.CountDownTimer;

/**
 * Created by Johan 'Jizztærsker' on 30-10-2014.
 */
public class DataAccumulator {

    GPS Gps;
    Integer UserId;
    Integer GameId;

    private CountDownTimer Timer = new CountDownTimer(2000,2000) {

        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            Client dataClient = new Client();
            dataClient.send(EncodeActionXML.gameUpdate(UserId, GameId, Gps.getCurrentLocation()));
            Timer.start();
        }
    };

    public DataAccumulator(GPS gps, Integer userId, Integer gameId) {
        this.Gps = gps;
        this.UserId = userId;
        this.GameId = gameId;
        Timer.start();
    }
}