package llovija.wisin.data;

/**
 * Class to create the hourly weather object
 * Created by Llorens on 23/03/2015.
 */
public class HourWeather extends Weather {
    private String hour;
    private String city;
    private String ico;

    /**
     * Constructor
     * @author Llorens
     * @param icon
     * @param temperature
     * @param max
     * @param min
     * @param humidity
     * @param clouds
     * @param city
     * @param time
     */
    public HourWeather(String icon, float temperature, float max, float min, int humidity, int clouds, String city, String time) {
        super(icon, temperature, max, min, humidity, clouds);
        this.ico=icon;
        this.city=city;
        this.hour= time.substring(time.indexOf(' '),time.length()-3);
    }

    /**
     * Constructor
     * @author Llorens
     * @param icon
     * @param temperature
     * @param max
     * @param min
     * @param humidity
     * @param clouds
     * @param time
     */
    public HourWeather(String icon, float temperature, float max, float min, int humidity, int clouds, String time) {
        super(icon, temperature, max, min, humidity, clouds);
        this.hour= time;
        this.ico=icon;
    }

    public String getCity() {
        return city;
    }

    public String getHour() {
        return hour;
    }

    public String getIco() {
        return ico;
    }
}

