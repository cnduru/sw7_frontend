package sw7.cornfield;

import android.content.Context;
import android.os.AsyncTask;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PhoneInfo {

    private void getOpenSignalData() {

        // TODO: Hardcoded for now. Assuming 3G network, Aalborg coordinates and average of a 5x5km square
        int distance = 5;
        int network = 3;
        double lat = 57.022665;
        double lng = 9.991581;
        String key = "ed1fe765c4c5cfc287675a85b74b546d";
        String url = "http://api.opensignal.com/v2/networkrank.json?lat=" + lat + "&lng=" + lng + "&distance=" + distance + "&network_type=" + network + "&apikey=" + key + "";

        //Create task to get the OpenSignal data and execute it
        DownloadWebPageTask getJson = new DownloadWebPageTask();
        getJson.execute(url);
    }

    private String _operatorName, _deviceId, _phoneType, _networkName, _gsmStrength;

    public String getOperatorName() {
        return _operatorName;
    }

    public String getDeviceId() {
        return _deviceId;
    }

    public String getPhoneType() {
        return _phoneType;
    }

    public String getNetworkName() {
        return _networkName;
    }

    public String getGsmStrength() {
        return _gsmStrength;
    }

     //Create a TelephonyManager to extract information
    private TelephonyManager phonyManager;
    Context ct;

    public void intializePhoneData(Context context){

        ct = context;
        phonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        //Set name of carrier
        _operatorName = phonyManager.getNetworkOperatorName();

        //Set DeviceId (IMEI)
        _deviceId = phonyManager.getDeviceId();

        //Get phone type, parse to string and set the type
        int phoneTypeNum = phonyManager.getPhoneType();
        String phoneTypeName = getPhoneTypeName(phoneTypeNum);
       _phoneType = phoneTypeName;

        //Get the current connection type, parse to string and set the type
        String networkName = getNetworkNameFromType(phonyManager.getNetworkType());
        _networkName = networkName;

        //TODO: This only works for GSM phones. Implement the other types or some kind of safety
        //Create listener to get updates for GSM Signal Strength. This assumes the phone is GSM
        PhoneStateListener listener = new PhoneStateListener(){
            public void onSignalStrengthsChanged(SignalStrength s)
            {
                //Update the signal strength (Range 0-31 where higher is better. 99 is no signal)
                _gsmStrength = Integer.toString(s.getGsmSignalStrength());
            }
        };

        //Listen for changes
        phonyManager.listen(listener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
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
