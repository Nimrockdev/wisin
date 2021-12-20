package llovija.wisin.utilities;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;

import org.json.JSONException;

import java.util.ArrayList;

import llovija.wisin.data.CurrentWeather;
import llovija.wisin.data.CurrentWeatherDataSource;
import llovija.wisin.data.HourWeather;
import llovija.wisin.data.HourWeatherDataSource;
import llovija.wisin.data.WeekWeather;
import llovija.wisin.data.WeekWeatherDataSource;
import llovija.wisin.utilities.GPSTracker;
import llovija.wisin.utilities.JSONParser;
import llovija.wisin.utilities.WeatherHTTPClient;

/**
 * Created by Llorenç on 02/04/2015.
 */
public class GPSWeatherParser  extends ApplicationTestCase<Application> {
    public GPSWeatherParser() {
        super(Application.class);
    }

    /**
     * * @author Victor
     * Test P25
     * @throws JSONException
     */
    public void testGPSCurrentWeatherParser() throws JSONException {

        GPSTracker gps = new GPSTracker(mContext);
        String data = new WeatherHTTPClient().getCurrentWeatherData(gps.getLocation());
        CurrentWeather current = new JSONParser().getWeather(data);
        assertNotNull(current);
    }

    /**
     * * @author Victor
     * Test P26
     * @throws Exception
     */
    public void testGPSHourWeatherParser()throws Exception {


        GPSTracker gps = new GPSTracker(mContext);
        String data = new WeatherHTTPClient().getHourWeatherData(gps.getLocation());
        ArrayList<HourWeather> hourWeather = new JSONParser().getHourWeather(data);
        assertNotNull(hourWeather);
        assertEquals(9,hourWeather.size());
    }

    /**
     * * @author Victor
     * Test P27
     * @throws Exception
     */
    public void testGPSDaysWeatherParser()throws Exception {


        GPSTracker gps = new GPSTracker(mContext);
        String data = new WeatherHTTPClient().getWeekWeatherData(gps.getLocation());
        ArrayList<WeekWeather> weekWeather = new JSONParser().getWeekWeatherData(data);
        assertNotNull(weekWeather);
        assertEquals(7,weekWeather.size());
    }
    /**
     * * @author Llorenç
     * Test P28
     * @throws JSONException
     */
    public void testGPSWeatherParserCurrentDB() throws JSONException {

        GPSTracker gps = new GPSTracker(mContext);
        CurrentWeatherDataSource wds = new CurrentWeatherDataSource(mContext);
        String data = new WeatherHTTPClient().getCurrentWeatherData(gps.getLocation());
        CurrentWeather current = new JSONParser().getWeather(data);
        wds.deleteWeatherById(new String[]{Integer.toString(90)});
        wds.saveWeatherRow(current, 90, 0);
        Cursor c = wds.getOneWeatherById(new String[]{Integer.toString(90)});
        Cursor c2 = wds.getOneCurrentWeatherById(new String[]{Integer.toString(90)});

        c.moveToNext();
        c2.moveToNext();
        CurrentWeather current2 = new CurrentWeather(c.getString(2), c.getFloat(3), c.getFloat(5), c.getFloat(4),
                c.getInt(6), c.getInt(7), c2.getString(8), c2.getString(1), c2.getString(7), c2.getInt(5), c2.getInt(6), c2.getFloat(2), c2.getFloat(3), c2.getFloat(4));

        assertEquals(current.getIcon(),current2.getIcon());
        assertEquals(current.getTemperature(), current2.getTemperature());
        assertEquals(current.getMax(),current2.getMax());
        assertEquals(current.getMin(),current2.getMin());
        assertEquals(current.getHumidity(),current2.getHumidity());
        assertEquals(current.getClouds(),current2.getClouds());
        assertEquals(current.getDescription(),current2.getDescription());
        assertEquals(current.getCondition(),current2.getCondition());
        assertEquals(current.getSunrise(),current2.getSunrise());
        assertEquals(current.getSunset(),current2.getSunset());
        assertEquals(current.getPressure(),current2.getPressure());
        assertEquals(current.getWindSpeed(),current2.getWindSpeed());
        assertEquals(current.getWindDirection(),current2.getWindDirection());
    }

    /**
     * * @author Javier
     * Test P29
     * @throws JSONException
     */
    public void testGPSWeatherParserHourDB() throws JSONException {

        GPSTracker gps = new GPSTracker(mContext);
        HourWeatherDataSource wds = new HourWeatherDataSource(mContext);
        String data = new WeatherHTTPClient().getHourWeatherData(gps.getLocation());
        ArrayList<HourWeather> current = new JSONParser().getHourWeather(data);
        wds.deleteWeatherById(new String[]{Integer.toString(90)});
        wds.saveWeatherRow(current, 90, 0);
        Cursor c = wds.getWeatherById(new String[]{Integer.toString(90)});
        Cursor c2 = wds.getHourWeatherById(new String[]{Integer.toString(90)});

        c.moveToNext();
        c2.moveToNext();
        HourWeather current2 = new HourWeather(c.getString(2), c.getFloat(3), c.getFloat(5), c.getFloat(4), c.getInt(6), c.getInt(7), c2.getString(2));

        assertEquals(current.get(0).getIcon(),current2.getIcon());
        assertEquals(current.get(0).getTemperature(), current2.getTemperature());
        assertEquals(current.get(0).getMax(),current2.getMax());
        assertEquals(current.get(0).getMin(),current2.getMin());
        assertEquals(current.get(0).getHumidity(),current2.getHumidity());
        assertEquals(current.get(0).getClouds(),current2.getClouds());
        assertEquals(current.get(0).getHour(),current2.getHour());
    }

    /**
     * * @author Victor
     * Test P30
     * @throws JSONException
     */
    public void testGPSWeatherParserWeekDB() throws JSONException {

        GPSTracker gps = new GPSTracker(mContext);
        WeekWeatherDataSource wds = new WeekWeatherDataSource(mContext);
        String data = new WeatherHTTPClient().getWeekWeatherData(gps.getLocation());
        ArrayList<WeekWeather> current = new JSONParser().getWeekWeatherData(data);
        wds.deleteWeatherById(new String[]{Integer.toString(90)});
        wds.saveWeatherRow(current, 90, 0);
        Cursor c = wds.getWeatherById(new String[]{Integer.toString(90)});
        Cursor c2 = wds.getWeekWeatherById(new String[]{Integer.toString(90)});

        c.moveToNext();
        c2.moveToNext();

        WeekWeather current2 = new WeekWeather(c.getString(2), c.getFloat(3), c.getFloat(5), c.getFloat(4), c.getInt(6), c.getInt(7), c2.getString(2), c2.getFloat(3), c2.getFloat(4), c2.getFloat(5), c2.getString(6));

        assertEquals(current.get(0).getIcon(),current2.getIcon());
        assertEquals(current.get(0).getTemperature(), current2.getTemperature());
        assertEquals(current.get(0).getMax(),current2.getMax());
        assertEquals(current.get(0).getMin(),current2.getMin());
        assertEquals(current.get(0).getHumidity(),current2.getHumidity());
        assertEquals(current.get(0).getClouds(),current2.getClouds());
        assertEquals(current.get(0).getDay(),current2.getDay());
        assertEquals(current.get(0).getPressure(),current2.getPressure());
        assertEquals(current.get(0).getDescription(),current2.getDescription());
        assertEquals(current.get(0).getWindSpeed(),current2.getWindSpeed());
        assertEquals(current.get(0).getWindDirection(),current2.getWindDirection());
    }
}
