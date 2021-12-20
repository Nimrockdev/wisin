package llovija.wisin.data;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;

import java.util.ArrayList;

/**
 * Created by Javier on 12/05/15.
 */
public class HourWeatherDataSourceTest extends ApplicationTestCase {


    public HourWeatherDataSourceTest() {
        super(Application.class);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    HourWeatherDataSource wds;

    /**
     * @author Javier
     * Test P20
     * @throws Exception
     */
    public void testDeleteConstructor() throws Exception {
        wds = new HourWeatherDataSource(mContext);
        boolean fet = wds.deleteWeatherById(new String[] { Integer.toString(99)});
        assertEquals(true, fet);
    }


    /**
     * @author Javier
     * Test P14
     * @throws Exception
     */
    public void testInsertConstructor() throws Exception {
        wds = new HourWeatherDataSource(mContext);
        ArrayList<HourWeather> hours = new ArrayList<HourWeather>();
        HourWeather hourweatther = new HourWeather(
                "icon",
                18.0f,
                20.0f,
                10.0f,
                88,
                92,
                "Badalona",
                "2015-03-23 09:00:00");
        hours.add(hourweatther);
        boolean fet = wds.saveWeatherRow(hours,99,99);
        assertEquals(true, fet);
    }





    /**
     * @author Javier
     * Test P17
     * @throws Exception
     */
    public void testObtainConstructor() throws Exception {
        wds = new HourWeatherDataSource(mContext);
        Cursor c =wds.getWeatherById(new String[]{Integer.toString(99)});
        Cursor c2 = wds.getHourWeatherById(new String[]{Integer.toString(99)});
        c.moveToNext();
        c2.moveToNext();

        HourWeather current = new HourWeather(c.getString(2), c.getFloat(3), c.getFloat(5), c.getFloat(4), c.getInt(6), c.getInt(7), c2.getString(2));

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
