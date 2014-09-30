package sw7.cornfield;


import android.app.IntentService;
import android.content.Intent;

public class dataCollector extends IntentService {
    public dataCollector() {
        super("dataCollector");
    }

    /* The IntentService calls this method from the default worker thread with
    * the intent that started the service. When this method returns, IntentService
    * stops the service, as appropriate.
    */
    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        long endTime = System.currentTimeMillis() + 5*1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception ignored) {
                }
            }
        }
    }
}