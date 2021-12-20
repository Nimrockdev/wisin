package llovija.wisin.data;

import junit.framework.TestCase;

import llovija.wisin.data.CurrentWeather;

/**
 * Created by Llorenç on 01/04/2015.
 */
public class CurrentWeatherTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
	 * @author Llorenç
     * Test P01
     * @throws Exception
     */
    public void testConstructor() throws Exception {
        CurrentWeather current = new CurrentWeather(
                "icon",
                18.0f,
                20.0f,
                10.0f,
                88,
                92,
                "Bon dia",
                "Badalona",
                "Sol",
                1423,
                1424,
                1023f,
                14.5f,
                45.6f);

        assertEquals("icon",current.getIcon());
        assertEquals(18.0f, current.getTemperature());
        assertEquals(20.0f,current.getMax());
        assertEquals(10.0f,current.getMin());
        assertEquals(88,current.getHumidity());
        assertEquals(92,current.getClouds());
        assertEquals("Bon dia",current.getDescription());
        assertEquals("Badalona",current.getCity());
        assertEquals("Sol",current.getCondition());
        assertEquals(1423,current.getSunrise());
        assertEquals(1424,current.getSunset());
        assertEquals(1023f,current.getPressure());
        assertEquals(14.5f,current.getWindSpeed());
        assertEquals(45.6f,current.getWindDirection());


    }

}
