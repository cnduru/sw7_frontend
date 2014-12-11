package sw7.Cornfieldz;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import android.content.Context;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class GPS implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, LocationListener {

    Context GameContext;
    LocationClient PositionClient;
    Location CurrentLocation;
    LocationRequest Request;

    public GPS(Context gameContext) {
        GameContext = gameContext;

        if(!gpsEnabled()) {
            Toast.makeText(GameContext, "Warning: GPS is disabled", Toast.LENGTH_SHORT).show();
        }

        PositionClient = new LocationClient(GameContext, this, this);

        Request = LocationRequest.create();
        Request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        Request.setInterval(4000);
        Request.setFastestInterval(3500);
    }

    public void stop() {
        if(PositionClient.isConnected()) {
            PositionClient.disconnect();
        }
    }

    public void start() {
        if(!PositionClient.isConnected()) {
            PositionClient.connect();
        }
    }

    public LatLng getCurrentLocation() {
        return new LatLng(CurrentLocation.getLatitude(), CurrentLocation.getLongitude());
    }

    private Boolean gpsEnabled(){
        LocationManager manager = (LocationManager) GameContext.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    //Needed for GooglePlayServicesClient.OnConnectionFailedListener
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(GameContext, "GPS connection failed", Toast.LENGTH_SHORT).show();
    }

    //Needed for GooglePlayServicesClient.ConnectionCallbacks
    @Override
    public void onConnected(Bundle dataBundle) {

        //Magic. Don't ask
        if(PositionClient != null) {
            PositionClient.requestLocationUpdates(Request, this);
        }

        if(PositionClient != null){
            CurrentLocation = PositionClient.getLastLocation();
        }
    }

    //Needed for GooglePlayServicesClient.ConnectionCallbacks
    @Override
    public void onDisconnected() {
        Toast.makeText(GameContext, "GPS disconnected.", Toast.LENGTH_SHORT).show();
    }

    // Listen for location changes
    @Override
    public void onLocationChanged(Location location) {
        //Update class variable
        CurrentLocation = PositionClient.getLastLocation();
        ((GameActivity)GameContext).GameMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(CurrentLocation.getLatitude(), CurrentLocation.getLongitude())));
    }
}