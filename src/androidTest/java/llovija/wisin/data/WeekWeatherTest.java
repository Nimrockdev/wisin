package llovija.wisin.data;

import junit.framework.TestCase;

import llovija.wisin.data.WeekWeather;

/**
 * Created by Victor on 07/04/2015.
 */
public class WeekWeatherTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }
    /**
	 * @author Victor
     * Test P02
     * @throws Exception
     */
    public void testConstructor() throws Exception {
        WeekWeather current = new WeekWeather(
                "icon",
                18.0f,
                20.0f,
                10.0f,
                88,
                92,
                "Badalona",
                1428458400,
                1021.77f,
                "Pluja lleugera",
                8.51f,
                39f
                );

        assertEquals("icon",current.getIcon());
        assertEquals(18.0f, current.getTemperature());
        assertEquals(20.0f,current.getMax());
        assertEquals(10.0f,current.getMin());
        assertEquals(88,current.getHumidity());
        assertEquals(92,current.getClouds());
        assertEquals("Badalona",current.getCity());
        assertEquals("08/04/2015",current.getDay());
        assertEquals(1021.77f,current.getPressure());
        assertEquals("Pluja lleugera",current.getDescription());
        assertEquals(8.51f,current.getWindSpeed());
        assertEquals(39f,current.getWindDirection());
    }


}