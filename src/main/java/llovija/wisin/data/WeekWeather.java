package llovija.wisin.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to create the weekly weather object
 * Created by Javier on 24/03/2015.
 */

public class WeekWeather extends Weather {

    private String description, city, day;
    private float pressure, windSpeed, windDirection;

    /**
     * Constructor
     * @author Javier
     * @param icon
     * @param temp
     * @param max
     * @param min
     * @param humidity
     * @param clouds
     * @param city
     * @param day
     * @param pressure
     * @param description
     * @param windSpeed
     * @param windDirection
     */
    public WeekWeather(String icon, float temp, float max, float min, int humidity, int clouds, String city, int day, float pressure, String description, float windSpeed, float windDirection) {
        super(icon, temp, max, min, humidity, clouds);
        this.city = city;
        this.day = formatDay(day);
        this.pressure = pressure;
        this.description = description;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public WeekWeather(String icon, float temp, float max, float min, int humidity, int clouds, String day, float pressure, float windSpeed, float windDirection, String description) {
        super(icon, temp, max, min, humidity, clouds);
        this.city = city;
        this.day = day;
        this.pressure = pressure;
        this.description = description;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }
    /**
     * Turns the UNIX format date into a String format dd/MM/yyyy
     * @author Javier
     * @param day integer with the date in format UNIX
     * @return String format dd/MM/yyyy
     */
    public String formatDay(int day){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        cal.setTimeInMillis((long) day * 1000);

        return formatter.format(cal.getTime());
    }

    public String getDay() {
        return day;
    }

    public Float getPressure() {
        return pressure;
    }

    public String getDescription() {
        return description;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public Float getWindDirection() {
        return windDirection;
    }

    public String getCity() {
        return city;
    }
}
