package llovija.wisin.data;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;

/**
 * Created by llorens on 10/05/15.
 */
public class CurrentWeatherDataSourceTest extends ApplicationTestCase {


    public CurrentWeatherDataSourceTest() {
        super(Application.class);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    CurrentWeatherDataSource wds;

    /**
     * @author Llorenç
     * Test P19
     * @throws Exception
     */
    public void testDeleteConstructor() throws Exception {
        wds = new CurrentWeatherDataSource(mContext);
        boolean fet = wds.deleteWeatherById(new String[] { Integer.toString(99)});
        assertEquals(true, fet);
    }


    /**
     * @author Llorenç
     * Test P16
     * @throws Exception
     */
    public void testInsertConstructor() throws Exception {
        wds = new CurrentWeatherDataSource(mContext);
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
        boolean fet = wds.saveWeatherRow(current,99,99);
        assertEquals(true, fet);
    }





    /**
     * @author Llorenç
     * Test P13
     * @throws Exception
     */
    public void testObtainConstructor() throws Exception {
        wds = new CurrentWeatherDataSource(mContext);
        Cursor c =wds.getOneWeatherById(new String[] { Integer.toString(99)});
        Cursor c2 = wds.getOneCurrentWeatherById(new String[] { Integer.toString(99)});
        c.moveToNext();
        c2.moveToNext();
        CurrentWeather current = new CurrentWeather(c.getString(2), c.getFloat(3), c.getFloat(5), c.getFloat(4),
                c.getInt(6), c.getInt(7), c2.getString(8), c2.getString(1), c2.getString(7), c2.getInt(5), c2.getInt(6), c2.getFloat(2), c2.getFloat(3), c2.getFloat(4));

        assertEquals("icon",current.getIcon());
        assertEquals(18.0f, current.getTemperature());
        assertEquals(20.0f,current.getMax());
        assertEquals(10.0f,current.getMin());
        assertEquals(88,current.getHumidity());
        assertEquals(92,current.getClouds());
        assertEquals("Bon dia",current.getDescription());
        assertEquals("Sol",current.getCondition());
        assertEquals(1423,current.getSunrise());
        assertEquals(1424,current.getSunset());
        assertEquals(1023f,current.getPressure());
        assertEquals(14.5f,current.getWindSpeed());
        assertEquals(45.6f,current.getWindDirection());
    }
}
