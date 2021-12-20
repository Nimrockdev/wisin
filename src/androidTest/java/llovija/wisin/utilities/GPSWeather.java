package llovija.wisin.utilities;

import android.app.Application;
import android.test.ApplicationTestCase;

import llovija.wisin.utilities.GPSTracker;
import llovija.wisin.utilities.WeatherHTTPClient;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class GPSWeather extends ApplicationTestCase<Application> {
    public GPSWeather() {
        super(Application.class);
    }

    /**
	 * @author Llorenç
     * Test P22
     * @throws Exception
     */
    public void testGPSCurrentWeather() throws Exception {

        GPSTracker gps = new GPSTracker(mContext);
        String data = new WeatherHTTPClient().getCurrentWeatherData(gps.getLocation());
        assertNotNull(data);
    }

    /**
	 * @author Javier
     * Test P23
     * @throws Exception
     */
    public void testGPSHourWeather()throws Exception {


        GPSTracker gps = new GPSTracker(mContext);
        String data = new WeatherHTTPClient().getHourWeatherData(gps.getLocation());
        assertNotNull(data);
    }

    /**
	 * @author Victor
     * Test P24
     * @throws Exception
     */
    public void testGPSWeekWeather()throws Exception {


        GPSTracker gps = new GPSTracker(mContext);
        String data = new WeatherHTTPClient().getWeekWeatherData(gps.getLocation());
        assertNotNull(data);
    }
}