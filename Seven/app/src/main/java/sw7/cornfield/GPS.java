package sw7.cornfield;

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
    LocationClient Client;
    Location CurrentLocation;
    LocationRequest Request;

    public GPS(Context gameContext) {

        //Set class variables
        this.GameContext = gameContext;

        //Display warning if GPS is disabled
        if(!gpsEnabled()) {
            Toast.makeText(GameContext, "Warning: GPS is disabled", Toast.LENGTH_SHORT).show();
        }

        //Setup the LocationClient
        Client = new LocationClient(GameContext, this, this);

        // Define requests for positions. Desired interval is 5 seconds, and never updates faster than 3 seconds.
        Request = LocationRequest.create();
        Request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        Request.setInterval(5000);
        Request.setFastestInterval(3000);
    }

    public void stop() {
        if(Client.isConnected()) {
            Client.disconnect();
        }
    }

    public void start() {
        if(!Client.isConnected()) {
            Client.connect();
        }
    }

    public Location getCurrentLocation() {
        return this.CurrentLocation;
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
        if(Client != null) {
            Client.requestLocationUpdates(Request, this);
        }

        if(Client != null){
            CurrentLocation = Client.getLastLocation();
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
        CurrentLocation = Client.getLastLocation();
        ((GameActivity)GameContext).GameMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(CurrentLocation.getLatitude(), CurrentLocation.getLongitude())));
    }
}