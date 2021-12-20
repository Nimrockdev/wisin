package llovija.wisin.data;

/**
 * Class to create the current weather object
 * Created by Victor on 02/03/2015.
 */
public class CurrentWeather extends Weather {
    private String description, city, condition, ico;
    private int sunrise, sunset;
    private float pressure, windSpeed, windDirection;

    /**
     * Constructor of the class
     * @author Victor
     * @param icon
     * @param temperature
     * @param max
     * @param min
     * @param humidity
     * @param clouds
     * @param description
     * @param city
     * @param condition
     * @param sunrise
     * @param sunset
     * @param pressure
     * @param windSpeed
     * @param windDirection
     */
    public CurrentWeather(String icon, float temperature, float max, float min, int humidity, int clouds,
                   String description, String city, String condition, int sunrise, int sunset,
                   float pressure, float windSpeed, float windDirection) {
        super(icon, temperature, max, min, humidity, clouds);
        this.description = description;
        this.city = city;
        this.condition = condition;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;

        this.ico=icon;
    }
    public String getIcon() {
        return ico;
    }


    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getCondition() {
        return condition;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public float getPressure() {
        return pressure;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getWindDirection() {
        return windDirection;
    }
}
