package llovija.wisin.utilities;

import android.location.Location;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

/**
 * Obtains the Weather info from the server
 * Created by Victor on 05/03/2015.
 */
public class WeatherHTTPClient {

    private static String API_KEY = "67089916eb0604ad5a42a0be61ce3b99";

    /**
     * Obtains the current weather
     * @author Victor
     * @param location Location to use
     * @return String with the information
     */
    public String getCurrentWeatherData(Location location) {

        String language = Locale.getDefault().getLanguage();

        if (!language.equalsIgnoreCase("es") && !language.equalsIgnoreCase("ca")) {
            language = "en";
        }
        String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?lat=" + location.getLatitude()
                + "&lon=" + location.getLongitude() + "&lang=" + language + "&units=metric&type=accurate&APPID=" + API_KEY;

        HttpURLConnection con = null;
        InputStream is = null;

        try {
            //Set URL and connect
            con = (HttpURLConnection) (new URL(BASE_URL)).openConnection();
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
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Obtains the Hourly weather
     * @author Llorens
     * @param location Location to use
     * @return String with the information
     */
    public String getHourWeatherData(Location location) {

        String language = Locale.getDefault().getLanguage();

        if (!language.equalsIgnoreCase("es") && !language.equalsIgnoreCase("ca")) {
            language = "en";
        }
        String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?lat=" + location.getLatitude()
                + "&lon=" + location.getLongitude() + "&lang=" + language + "&units=metric&type=accurate&APPID=" + API_KEY;

        HttpURLConnection con = null;
        InputStream is = null;

        try {
            //Set URL and connect
            con = (HttpURLConnection) (new URL(BASE_URL)).openConnection();
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
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Obtains the Weekly weather
     * @author Javier
     * @param location Location to use
     * @return String with the information
     */
    public String getWeekWeatherData(Location location) {

        String language = Locale.getDefault().getLanguage();

        if (!language.equalsIgnoreCase("es") && !language.equalsIgnoreCase("ca")) {
            language = "en";
        }

        String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + location.getLatitude()
                + "&lon=" + location.getLongitude() +"&units=metric&cnt=10&lang=" + language+ "&APPID=" + API_KEY;

        HttpURLConnection con = null;
        InputStream is = null;

        try {
            //Set URL and connect
            con = (HttpURLConnection) (new URL(BASE_URL)).openConnection();
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
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return null;
    }
}
