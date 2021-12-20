package llovija.wisin.data;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.TestCase;

/**
 * Created by Victor on 12/05/15.
 */
public class WeatherDBTest extends ApplicationTestCase {
    public WeatherDBTest() {
        super(Application.class);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * @author Victor
     * Test P12
     * @throws Exception
     */
    public void testConstructor() throws Exception {
        String exep="";
        try {
            WeatherDB db = new WeatherDB(mContext);
        } catch(Exception ex) {
        exep=ex.getMessage();


        }
        assertEquals("", exep);
    }

}
