package llovija.wisin.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import llovija.wisin.data.CurrentWeather;
import llovija.wisin.data.HourWeather;
import llovija.wisin.data.WeekWeather;

/**
 * Parse a string into a Weather object
 * Created by Victor on 05/03/2015.
 */
public class JSONParser {
    /**
     * Parse current weather info into a CurrentWeather object
     *
     * @author Victor
     * @param info returned server information in JSON format
     * @return CurrentWeather object with the current information of the weather
     * @throws JSONException
     */
    public static CurrentWeather getWeather(String info) throws JSONException {
        String icon, condition, description, city;
        float temperature, max, min, pressure,  windSpeed, windDirection, rain;
        int clouds, sunrise, sunset, humidity;


        CurrentWeather weather;

        // Create JSONObject from the data
        JSONObject jObj = new JSONObject(info);
        city = getString("name", jObj);

        //Extract the info
        JSONObject jData = getObject("sys", jObj);
        sunrise = getInt("sunrise", jData);
        sunset = getInt("sunset", jData);

        //The weather info is received in an Array
        JSONArray jArr = jObj.getJSONArray("weather");

        //For the current weather we only need the first position
        JSONObject jCurrent = jArr.getJSONObject(0);
        icon = getString("icon", jCurrent);
        condition = getString("main", jCurrent);
        description = getString("description", jCurrent);


        JSONObject jMain = getObject("main", jObj);
        humidity = getInt("humidity", jMain);
        pressure = getFloat("pressure", jMain);
        max = getFloat("temp_max", jMain);
        min = getFloat("temp_min", jMain);
        temperature = getFloat("temp", jMain);

        //Wind
        JSONObject wObj = getObject("wind", jObj);
        windSpeed = getFloat("speed", wObj);
        windDirection = getFloat("deg", wObj);

        //Clouds
        JSONObject cObj = getObject("clouds", jObj);
        clouds = getInt("all", cObj);


        weather = new CurrentWeather(icon, temperature, max, min, humidity, clouds, description,
                city, condition, sunrise, sunset, pressure, windSpeed, windDirection);
        return weather;
    }

    /**
     * @author Llorenç
     * @param info returned server information in JSON format
     * @return Array with weather info every 3 hours
     * @throws JSONException
     */
    public static ArrayList<HourWeather> getHourWeather(String info) throws JSONException {
        String icon, city, time;
        float temperature, max, min;
        int clouds, humidity;

        ArrayList<HourWeather> weather = new ArrayList<HourWeather>();


        // Create JSONObject from the data
        JSONObject jObj = new JSONObject(info);
        //Extract the city name
        JSONObject jCity = getObject("city", jObj);
        city = getString("name", jCity);

        //Extract the info list
        JSONArray jArr = jObj.getJSONArray("list");

        //Each row corresponds with the information of three hours

        for(int i = 0; i<9; i++) {
            //Extract the row
            JSONObject jHour = jArr.getJSONObject(i);

            JSONObject jMain = getObject("main", jHour);
            //Extract humidity info
            humidity = getInt("humidity", jMain);
            //Extract Temperature Max info
            max = getFloat("temp_max", jMain);
            //Extract Temperature Min info
            min = getFloat("temp_min", jMain);
            //Extract Temperature info
            temperature = getFloat("temp", jMain);

            //Clouds
            JSONObject cObj = getObject("clouds", jHour);
            clouds = getInt("all", cObj);
            //Hour
            time = getString("dt_txt",jHour);
            //Icon
            JSONArray jArr2 = jHour.getJSONArray("weather");
            JSONObject iObj = jArr2.getJSONObject(0);
            icon = getString("icon", iObj);

            weather.add(new HourWeather(icon, temperature, max, min, humidity, clouds, city, time));
        }
        return weather;
    }


    /**
     * @author Javier
     * @param info returned server information in JSON format
     * @return Array with weather info every 7 days
     * @throws JSONException
     */
    public static ArrayList<WeekWeather> getWeekWeatherData(String info) throws JSONException {

        String icon, city, description;
        float max, min,temperature,pressure, windSpeed, windDirection;
        int  clouds, humidity, day;

        ArrayList<WeekWeather> weather = new ArrayList<WeekWeather>();


        // Create JSONObject from the data
        JSONObject jObj = new JSONObject(info);
        //Extract the city name
        JSONObject jCity = getObject("city", jObj);
        city = getString("name", jCity);

        //Extract the info list
        JSONArray jArr = jObj.getJSONArray("list");
        //Each row corresponds with the information of each day
        int num= jArr.length();
        for(int i = 0; i<7; i++) {
            //Extract the row
            JSONObject jDay = jArr.getJSONObject(i);

            day = getInt("dt", jDay);

            JSONObject jTemp = getObject("temp", jDay);

            //Extract humidity info
            humidity = getInt("humidity", jDay);

            pressure = getFloat("pressure", jDay);

            //Extract Temperature Max info
            max = getFloat("max", jTemp);

            //Extract Temperature Min info
            min = getFloat("min", jTemp);

            //Extract Temperature info
            temperature = getFloat("min", jTemp);
            windSpeed = getFloat("speed", jDay);
            windDirection = getFloat("deg", jDay);

            //Clouds
            clouds = getInt("clouds", jDay);

            //Icon
            JSONArray jArr2 = jDay.getJSONArray("weather");
            JSONObject iObj = jArr2.getJSONObject(0);
            icon = getString("icon", iObj);
            description = getString("description", iObj);

            weather.add(new WeekWeather(icon, temperature, max, min, humidity, clouds, city, day, pressure, description,windSpeed,windDirection));
        }
        return weather;
    }

    /**
     * Obtains a JSON object by tagName
     * @author Victor
     * @param tagName Tag to find in the object
     * @param jObj Object to find into
     * @return JSONObject
     * @throws JSONException
     */
    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    /**
     * Obtains a string from a JSONObject
     * @author Victor
     * @param tagName Tag to find in the object
     * @param jObj Object to find into
     * @return String associated to the tag
     * @throws JSONException
     */
    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    /**
     * Obtains a float from a JSONObject
     * @author Victor
     * @param tagName Tag to find in the object
     * @param jObj Object to find into
     * @return Float associated to the tag
     * @throws JSONException
     */
    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return Math.round(jObj.getDouble(tagName));
    }

    /**
     * Obtains an int from a JSONObject
     * @author Victor
     * @param tagName Tag to find in the object
     * @param jObj Object to find into
     * @return Int associated to the tag
     * @throws JSONException
     */
    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
