package llovija.wisin.utilities;


import android.location.Location;
import android.test.AndroidTestCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Locale;

import llovija.wisin.utilities.WeatherHTTPClient;


public class WeatherHTTPClientTest extends AndroidTestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
	 * @author Llorenç
     * Test P09
     */
    public void testGetCurrentWeatherData() {
        Location location = new Location("");
        location.setLatitude(41.450039d);
        location.setLongitude(2.24741d);

        WeatherHTTPClient WHC = new WeatherHTTPClient();
        String info = WHC.getCurrentWeatherData(location);
        String info2 = getWeather(location,0);
        assertEquals(info,info2);
    }

    /**
	 * @author Javier
     * Test P10
     */
    public void testGetHourWeatherData() {
        Location location = new Location("");
        location.setLatitude(41.450039d);
        location.setLongitude(2.24741d);

        WeatherHTTPClient WHC = new WeatherHTTPClient();
        String info = WHC.getHourWeatherData(location);
        String info2 = getWeather(location,1);
        assertEquals(info,info2);
    }

    /**
	 * @author Victor
     * Test P11
     */
    public void testGetWeekWeatherData() {
        Location location = new Location("");
        location.setLatitude(41.450039d);
        location.setLongitude(2.24741d);

        WeatherHTTPClient WHC = new WeatherHTTPClient();
        String info = WHC.getWeekWeatherData(location);
        String info2 = getWeather(location,2);
        assertEquals(info,info2);
    }

    private String getWeather(Location location, int i) {
        String API_KEY = "67089916eb0604ad5a42a0be61ce3b99";

        String language = Locale.getDefault().getLanguage();

        if (!language.equalsIgnoreCase("es") && !language.equalsIgnoreCase("ca")) {
            language = "en";
        }
        String BASE_URL[] = new String[3];
        BASE_URL[0] = "http://api.openweathermap.org/data/2.5/weather?lat=" + location.getLatitude()
                + "&lon=" + location.getLongitude() + "&lang=" + language + "&units=metric&type=accurate&APPID=" + API_KEY;
        BASE_URL[1] = "http://api.openweathermap.org/data/2.5/forecast?lat=" + location.getLatitude()
                + "&lon=" + location.getLongitude() + "&lang=" + language + "&units=metric&type=accurate&APPID=" + API_KEY;
        BASE_URL[2] = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + location.getLatitude()
                + "&lon=" + location.getLongitude() +"&units=metric&cnt=10&lang=" + language+ "&APPID=" + API_KEY;;
        HttpURLConnection con = null;
        InputStream is = null;

        try {
            //Set URL and connect
            con = (HttpURLConnection) (new URL(BASE_URL[i])).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            //Receive the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            //Close connections
            is.close();
            con.disconnect();
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return null;
    }
}