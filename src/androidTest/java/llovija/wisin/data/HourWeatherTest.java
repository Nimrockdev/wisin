package llovija.wisin.data;

import junit.framework.TestCase;

import llovija.wisin.data.HourWeather;

/**
 * Created by Javier on 01/04/2015.
 */
public class HourWeatherTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }
    /**
	 * @author Javier
     * Test P02
     * @throws Exception
     */
    public void testConstructor() throws Exception {
        HourWeather current = new HourWeather(
                "icon",
                18.0f,
                20.0f,
                10.0f,
                88,
                92,
                "Badalona",
                "2015-03-23 09:00:00"                );

        assertEquals("icon",current.getIcon());
        assertEquals(18.0f, current.getTemperature());
        assertEquals(20.0f,current.getMax());
        assertEquals(10.0f,current.getMin());
        assertEquals(88,current.getHumidity());
        assertEquals(92,current.getClouds());
        assertEquals("Badalona",current.getCity());
        assertEquals("2015-03-23 09:00:00".substring("2015-03-23 09:00:00".indexOf(' '),"2015-03-23 09:00:00".length()-3),current.getHour());


    }


}
