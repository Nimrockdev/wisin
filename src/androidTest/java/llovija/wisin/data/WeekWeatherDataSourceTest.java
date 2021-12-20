package llovija.wisin.data;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;

import java.util.ArrayList;

/**
 * Created by Victor on 12/05/15.
 */
public class WeekWeatherDataSourceTest extends ApplicationTestCase {


    public WeekWeatherDataSourceTest() {
        super(Application.class);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    WeekWeatherDataSource wds;

    /**
     * @author Victor
     * Test P21
     * @throws Exception
     */
    public void testDeleteConstructor() throws Exception {
        wds = new WeekWeatherDataSource(mContext);
        boolean fet = wds.deleteWeatherById(new String[] { Integer.toString(99)});
        assertEquals(true, fet);
    }


    /**
     * @author Victor
     * Test P18
     * @throws Exception
     */
    public void testInsertConstructor() throws Exception {
        wds = new WeekWeatherDataSource(mContext);
        ArrayList<WeekWeather> hours = new ArrayList<WeekWeather>();
        WeekWeather week = new WeekWeather(
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
        hours.add(week);
        boolean fet = wds.saveWeatherRow(hours,99,99);
        assertEquals(true, fet);
    }





    /**
     * @author Victor
     * Test P15
     * @throws Exception
     */
    public void testObtainConstructor() throws Exception {
        wds = new WeekWeatherDataSource(mContext);
        Cursor c =wds.getWeatherById(new String[]{Integer.toString(99)});
        Cursor c2 = wds.getWeekWeatherById(new String[]{Integer.toString(99)});
        c.moveToNext();
        c2.moveToNext();

        WeekWeather current = new WeekWeather(c.getString(2), c.getFloat(3), c.getFloat(5), c.getFloat(4), c.getInt(6), c.getInt(7), c2.getString(2), c2.getFloat(3), c2.getFloat(4), c2.getFloat(5),c2.getString(6));

        assertEquals("icon",current.getIcon());
        assertEquals(18.0f, current.getTemperature());
        assertEquals(20.0f,current.getMax());
        assertEquals(10.0f,current.getMin());
        assertEquals(88,current.getHumidity());
        assertEquals(92,current.getClouds());
        assertEquals("08/04/2015",current.getDay());
        assertEquals(1021.77f,current.getPressure());
        assertEquals("Pluja lleugera",current.getDescription());
        assertEquals(8.51f,current.getWindSpeed());
        assertEquals(39f,current.getWindDirection());



    }
}

