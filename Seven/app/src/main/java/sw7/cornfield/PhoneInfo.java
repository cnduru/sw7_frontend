package sw7.cornfield;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

public class PhoneInfo {

    private TelephonyManager PhonyManager;
    private String OperatorName;
    private String DeviceId;
    private String PhoneType;
    private String NetworkName;
    private String GsmStrength;

    public PhoneInfo (Context context) {

        this.PhonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        //Set name of carrier
        OperatorName = PhonyManager.getNetworkOperatorName();

        //Set DeviceId (IMEI)
        DeviceId = PhonyManager.getDeviceId();

        //Get phone type (int), parse to string and set the type
        this.PhoneType = getPhoneTypeName(PhonyManager.getPhoneType());

        //Get the current connection type (int), parse to string and set the type
        NetworkName = getNetworkNameFromType(PhonyManager.getNetworkType());

        //TODO: This only works for GSM phones. Implement the other types or some kind of safety
        //Create listener to get updates for GSM Signal Strength. This assumes the phone is GSM
        PhoneStateListener listener = new PhoneStateListener(){
            public void onSignalStrengthsChanged(SignalStrength s) {
                //Update the signal strength (Range 0-31 where higher is better. 99 is no signal)
                GsmStrength = Integer.toString(s.getGsmSignalStrength());
            }
        };

        //Listen for changes on signal strength - Variable GsmStrength should now always be up to date
        PhonyManager.listen(listener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        //Get info from OpenSignalAPI
        getOpenSignalData();
    }

    public String getOperatorName() {
        return OperatorName;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public String getPhoneType() {
        return PhoneType;
    }

    public String getNetworkName() {
        return NetworkName;
    }

    public String getGsmStrength() {
        return GsmStrength;
    }

    private void getOpenSignalData() {

        // TODO: Hardcoded for now. Assuming 3G network, Aalborg coordinates and average of a 5x5km square
        //Create needed data to create API request
        int distance = 5;
        int network = 3;
        double lat = 57.022665;
        double lng = 9.991581;
        String key = "d165247e29d7af1122b00662c6468d17";
        String url = "http://api.opensignal.com/v2/networkrank.json?lat=" + lat + "&lng=" + lng + "&distance=" + distance + "&network_type=" + network + "&apikey=" + key + "";

        //Create task to get the OpenSignal data and execute it
        //Json can be extracted using something like this:
        //InputStream content = execute.getEntity().getContent();
        DownloadWebPageTask getJson = new DownloadWebPageTask();
        getJson.execute(url);
    }

    private String getNetworkNameFromType(int networkType){

        String networkTypeName;

        //Switch through the possible network types, translating to text
        switch (networkType) {
            case 1:
                networkTypeName = "GPRS";
                break;
            case 2:
                networkTypeName = "EDGE";
                break;
            case 3:
                networkTypeName = "UMTS";
                break;
            case 4:
                networkTypeName = "CDMA";
                break;
            case 5:
                networkTypeName = "EVDO rev. 0";
                break;
            case 6:
                networkTypeName = "EVDO rev. A";
                break;
            case 7:
                networkTypeName = "1xRTT";
                break;
            case 8:
                networkTypeName = "HSDPA";
                break;
            case 9:
                networkTypeName = "HSUPA";
                break;
            case 10:
                networkTypeName = "HSPA";
                break;
            case 11:
                networkTypeName = "iDen";
                break;
            case 12:
                networkTypeName = "EVDO rev. B";
                break;
            case 13:
                networkTypeName = "LTE";
                break;
            case 14:
                networkTypeName = "eHRPD";
                break;
            case 15:
                networkTypeName = "HSPA+";
                break;
            case 0:
                networkTypeName = "Unknown";
                break;
            default:
                networkTypeName = "Unknown";
                break;
        }
        return networkTypeName;
    }

    private String getPhoneTypeName(int phoneType){

        String phoneTypeName;

        //Switch through the possible phone types, translating to text
        switch(phoneType){
            case TelephonyManager.PHONE_TYPE_NONE:
                phoneTypeName =  "NONE";
                break;

            case TelephonyManager.PHONE_TYPE_GSM:
                phoneTypeName = "GSM";
                break;

            case TelephonyManager.PHONE_TYPE_CDMA:
                phoneTypeName = "CDMA";
                break;

            case TelephonyManager.PHONE_TYPE_SIP:
                phoneTypeName = "SIP";
                break;

            default:
                phoneTypeName = "Unknown";
        }
        return phoneTypeName;
    }
}
