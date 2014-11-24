package sw7.cornfield;

import android.os.CountDownTimer;

/**
 * Created by Johan 'Jizztærsker' on 30-10-2014.
 */
public class DataAccumulator {

    GPS Gps;

    private CountDownTimer Timer = new CountDownTimer(2000,2000) {
        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            //MainActivity.client.sendData(Gps.getCurrentLocation());
            Timer.start();
        }
    };

    public DataAccumulator(GPS gps) {
        this.Gps = gps;
        Timer.start();
    }
}