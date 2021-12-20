package llovija.wisin.utilities;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.test.ApplicationTestCase;
import android.util.Log;

import llovija.wisin.utilities.GPSTracker;
import llovija.wisin.utilities.WeatherHTTPClient;

/**
 * Created by Llorens on 01/04/2015.
 */
public class GPSTrackerTest extends ApplicationTestCase<Application> {

    protected LocationManager locationManager;
    Location location;
    double latitude;
    double longitude;

       public GPSTrackerTest() {
            super(Application.class);
        }






    /**
     * Test P07
     * @throws Exception
     */
    public void testGetLocationGPSEnabled() throws Exception {

        GPSTracker gps = new GPSTracker(mContext);


        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        assertEquals(latitude, gps.getLocation().getLatitude());
        assertEquals(longitude, gps.getLocation().getLongitude());

    }

    /**
	 * @author Llorenç
     * Test P08
     * @throws Exception
     */
    public void testGetLocationNetworkEnabled() throws Exception {


        GPSTracker gps = new GPSTracker(mContext);

        locationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);

        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        assertEquals(latitude, gps.getLocation().getLatitude());
        assertEquals(longitude, gps.getLocation().getLongitude());

    }


}
