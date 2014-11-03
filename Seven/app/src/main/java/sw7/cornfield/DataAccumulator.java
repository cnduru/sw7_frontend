package sw7.cornfield;

import android.os.CountDownTimer;

/**
 * Created by Johan 'Jizztærsker' on 30-10-2014.
 */

public class DataAccumulator {

    PhoneInfo Phone;
    GPS Gps;

    private CountDownTimer Timer = new CountDownTimer(5000,5000) {
        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            MainActivity.client.sendData(Gps.getCurrentLocation(),Phone.getDeviceId(), Phone.getGsmStrength());
            Timer.start();
        }
    };

    public DataAccumulator(PhoneInfo phoneinfo, GPS gps) {
        this.Phone = phoneinfo;
        this.Gps = gps;
        Timer.start();
    }
}