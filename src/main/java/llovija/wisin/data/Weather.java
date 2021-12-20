package llovija.wisin.data;

/**
 * Abstract class of weather
 * Created by Victor on 05/03/2015.
 */
public abstract class Weather {

    private String icon;
    private int clouds, humidity;
    private float temperature, max, min;

    /**
     * Constructor
     * @author Victor
     * @param icon
     * @param temperature
     * @param max
     * @param min
     * @param humidity
     * @param clouds
     */
    Weather(String icon, float temperature, float max, float min, int humidity, int clouds) {
        this.icon = icon;
        this.temperature = temperature;
        this.max = max;
        this.min = min;
        this.humidity = humidity;
        this.clouds = clouds;
    }

    public String getIcon() {
        return icon;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getClouds() {
        return clouds;
    }
}
