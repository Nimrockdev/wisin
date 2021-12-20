package llovija.wisin.utilities;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import llovija.wisin.R;
import llovija.wisin.ui.SimpleToast;

/**
 * Obtains the location
 * Created by Victor on 02/03/2015.
 */
public class GPSTracker extends Service implements LocationListener{
    private final Context mContext;

    // User data
    //private SharedPreferences prefs = getSharedPreferences("GPSPreferences", MODE_PRIVATE);

    //GPS status
    boolean isGPSEnabled = false;

    //Network status
    boolean isNetworkEnabled = false;

    //Flag to know if we can update the location
    boolean canGetLocation = false;

    //Flag to know if we need to save
    boolean willSave = false;


    Location location;
    double latitude;
    double longitude;

    //The minimum distance to change the location
    private static final long MIN_DISTANCE_CHANGE = 5000; //5km

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_CHANGE = 1000 * 60 * 30; // 30 minutes
    public GPSTracker(Context context){
        this.mContext = context;
        getLocation();
        //Save the new preferences
        if (willSave){
            //savePreferences();
        }
    }

    /**
     * Obtains the current location
     * @author Victor
     * @return The location
     */
    protected LocationManager locationManager;
    public Location getLocation(){
        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            //Getting the GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            //Getting the Network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isNetworkEnabled && !isGPSEnabled){
                // no network provider is enabled
                new SimpleToast(this).toast(R.string.no_network);
                //Delete preferences
                //savePreferences();
                //Set true to save
                willSave = true;
            }else{
                this.canGetLocation=true;
                //Priority to use the GPS position if it's possible
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_CHANGE,
                                MIN_DISTANCE_CHANGE, this);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                //If GPS is not enabled use Network
                }else if (isNetworkEnabled){
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_CHANGE,
                            MIN_DISTANCE_CHANGE, this);
                    Log.d("Network", "Network Enabled");
                    if (locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(location != null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return location;
    }
  /*  private void savePreferences(){
        SharedPreferences.Editor editor = prefs.edit();
        if(willSave){
            String latitudeString = Double.toString(latitude);
            String longitudeString = Double.toString(longitude);
            editor.putString("Latitude", latitudeString);
            editor.putString("Longitude", longitudeString);
        }else {
            editor.remove("Latitude");
            editor.remove("Longitude");
        }
        editor.apply();
    }*/

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

