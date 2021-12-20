package llovija.wisin.utilities;

import junit.framework.TestCase;

import org.json.JSONException;

import llovija.wisin.data.CurrentWeather;
import llovija.wisin.data.HourWeather;
import llovija.wisin.data.WeekWeather;
import llovija.wisin.utilities.JSONParser;


public class JSONParserTest extends TestCase {
    String info ;
    public void setUp() throws Exception {
        super.setUp();
    }
    /**
	 * @author Llorenç
     * Test P04
     * @throws Exception
     */
    public void testParserCurrentWeather() throws JSONException {
        info = "{\"coord\":{\"lon\":2.25,\"lat\":41.45},\"sys\":{\"message\":0.0109,\"country\":\"SO\",\"sunrise\":1427858033,\"sunset\":1427901731},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"nubes rotas\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":32.954,\"temp_min\":31.954,\"temp_max\":33.954,\"pressure\":985.27,\"sea_level\":1020.15,\"grnd_level\":985.27,\"humidity\":45},\"wind\":{\"speed\":1.92,\"deg\":240.501},\"clouds\":{\"all\":68},\"dt\":1427900431,\"id\":65170,\"name\":\"Badalona\",\"cod\":200}";
        CurrentWeather current = new JSONParser().getWeather(info);
        assertEquals("04d",current.getIcon());
        assertEquals(33.0f, current.getTemperature());
        assertEquals(34.0f,current.getMax());
        assertEquals(32.0f,current.getMin());
        assertEquals(45,current.getHumidity());
        assertEquals(68,current.getClouds());
        assertEquals("nubes rotas",current.getDescription());
        assertEquals("Badalona",current.getCity());
        assertEquals("Clouds",current.getCondition());
        assertEquals(1427858033,current.getSunrise());
        assertEquals(1427901731,current.getSunset());
        assertEquals(985.0f,current.getPressure());
        assertEquals(2.0f,current.getWindSpeed());
        assertEquals(241.0f,current.getWindDirection());
    }

    /**
	 * @author Javier
     * Test P05
     * @throws Exception
     */
    public void testParserHourWeather() throws JSONException {
        info = "{\"cod\":\"200\",\"message\":0.0115,\"city\":{\"id\":3129028,\"name\":\"Badalona\",\"coord\":{\"lon\":2.24741,\"lat\":41.450039},\"country\":\"ES\",\"population\":0},\"cnt\":38,\"list\":[{\"dt\":1427954400,\"main\":{\"temp\":13.59,\"temp_min\":10.95,\"temp_max\":13.59,\"pressure\":1028.48,\"sea_level\":1039.21,\"grnd_level\":1028.48,\"humidity\":100,\"temp_kf\":2.64},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.06,\"deg\":304.501},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-02 06:00:00\"},{\"dt\":1427965200,\"main\":{\"temp\":15.7,\"temp_min\":13.59,\"temp_max\":15.7,\"pressure\":1028.87,\"sea_level\":1039.53,\"grnd_level\":1028.87,\"humidity\":95,\"temp_kf\":2.11},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.06,\"deg\":6.00006},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-02 09:00:00\"},{\"dt\":1427976000,\"main\":{\"temp\":15.75,\"temp_min\":14.17,\"temp_max\":15.75,\"pressure\":1028.03,\"sea_level\":1038.5,\"grnd_level\":1028.03,\"humidity\":94,\"temp_kf\":1.58},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"algo de nubes\",\"icon\":\"02d\"}],\"clouds\":{\"all\":12},\"wind\":{\"speed\":2.56,\"deg\":132.5},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-02 12:00:00\"},{\"dt\":1427986800,\"main\":{\"temp\":15.13,\"temp_min\":14.08,\"temp_max\":15.13,\"pressure\":1026.33,\"sea_level\":1036.53,\"grnd_level\":1026.33,\"humidity\":93,\"temp_kf\":1.06},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.22,\"deg\":154.001},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-02 15:00:00\"},{\"dt\":1427997600,\"main\":{\"temp\":13.94,\"temp_min\":13.41,\"temp_max\":13.94,\"pressure\":1025.51,\"sea_level\":1035.87,\"grnd_level\":1025.51,\"humidity\":95,\"temp_kf\":0.53},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.62,\"deg\":205.001},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-02 18:00:00\"},{\"dt\":1428008400,\"main\":{\"temp\":12.04,\"temp_min\":12.04,\"temp_max\":12.04,\"pressure\":1025.91,\"sea_level\":1036.51,\"grnd_level\":1025.91,\"humidity\":100},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":3.21,\"deg\":233.505},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-02 21:00:00\"},{\"dt\":1428019200,\"main\":{\"temp\":11.22,\"temp_min\":11.22,\"temp_max\":11.22,\"pressure\":1025.46,\"sea_level\":1036.11,\"grnd_level\":1025.46,\"humidity\":100},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.2,\"deg\":255.502},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-03 00:00:00\"},{\"dt\":1428030000,\"main\":{\"temp\":10.64,\"temp_min\":10.64,\"temp_max\":10.64,\"pressure\":1023.96,\"sea_level\":1034.6,\"grnd_level\":1023.96,\"humidity\":100},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"02n\"}],\"clouds\":{\"all\":8},\"wind\":{\"speed\":0.73,\"deg\":252.5},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-03 03:00:00\"},{\"dt\":1428040800,\"main\":{\"temp\":10.47,\"temp_min\":10.47,\"temp_max\":10.47,\"pressure\":1023.4,\"sea_level\":1034.18,\"grnd_level\":1023.4,\"humidity\":100},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"lluvia ligera\",\"icon\":\"10d\"}],\"clouds\":{\"all\":44},\"wind\":{\"speed\":0.27,\"deg\":82.5025},\"rain\":{\"3h\":0.015},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-03 06:00:00\"},{\"dt\":1428051600,\"main\":{\"temp\":13.08,\"temp_min\":13.08,\"temp_max\":13.08,\"pressure\":1023.51,\"sea_level\":1033.94,\"grnd_level\":1023.51,\"humidity\":99},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"lluvia ligera\",\"icon\":\"10d\"}],\"clouds\":{\"all\":24},\"wind\":{\"speed\":1.31,\"deg\":206.007},\"rain\":{\"3h\":0.05},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-03 09:00:00\"},{\"dt\":1428062400,\"main\":{\"temp\":14.58,\"temp_min\":14.58,\"temp_max\":14.58,\"pressure\":1021.93,\"sea_level\":1032.25,\"grnd_level\":1021.93,\"humidity\":93},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"lluvia ligera\",\"icon\":\"10d\"}],\"clouds\":{\"all\":20},\"wind\":{\"speed\":3.61,\"deg\":208.001},\"rain\":{\"3h\":0.02},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-03 12:00:00\"},{\"dt\":1428073200,\"main\":{\"temp\":15.03,\"temp_min\":15.03,\"temp_max\":15.03,\"pressure\":1019.36,\"sea_level\":1029.68,\"grnd_level\":1019.36,\"humidity\":88},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"algo de nubes\",\"icon\":\"02d\"}],\"clouds\":{\"all\":24},\"wind\":{\"speed\":5.56,\"deg\":218.001},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-03 15:00:00\"},{\"dt\":1428084000,\"main\":{\"temp\":14.24,\"temp_min\":14.24,\"temp_max\":14.24,\"pressure\":1017.75,\"sea_level\":1028.1,\"grnd_level\":1017.75,\"humidity\":92},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"nubes dispersas\",\"icon\":\"03d\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":5.71,\"deg\":228.002},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-03 18:00:00\"},{\"dt\":1428094800,\"main\":{\"temp\":12.82,\"temp_min\":12.82,\"temp_max\":12.82,\"pressure\":1017.06,\"sea_level\":1027.51,\"grnd_level\":1017.06,\"humidity\":100},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"nubes rotas\",\"icon\":\"04n\"}],\"clouds\":{\"all\":80},\"wind\":{\"speed\":5.38,\"deg\":233.502},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-03 21:00:00\"},{\"dt\":1428105600,\"main\":{\"temp\":12.4,\"temp_min\":12.4,\"temp_max\":12.4,\"pressure\":1016.29,\"sea_level\":1026.95,\"grnd_level\":1016.29,\"humidity\":100},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"nubes rotas\",\"icon\":\"04n\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed\":3.56,\"deg\":250.504},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-04 00:00:00\"},{\"dt\":1428116400,\"main\":{\"temp\":11.67,\"temp_min\":11.67,\"temp_max\":11.67,\"pressure\":1015.52,\"sea_level\":1026.1,\"grnd_level\":1015.52,\"humidity\":100},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"02n\"}],\"clouds\":{\"all\":8},\"wind\":{\"speed\":0.51,\"deg\":152.001},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-04 03:00:00\"},{\"dt\":1428127200,\"main\":{\"temp\":11.55,\"temp_min\":11.55,\"temp_max\":11.55,\"pressure\":1015.97,\"sea_level\":1026.4,\"grnd_level\":1015.97,\"humidity\":100},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"nubes rotas\",\"icon\":\"04d\"}],\"clouds\":{\"all\":56},\"wind\":{\"speed\":3.46,\"deg\":108.5},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-04 06:00:00\"},{\"dt\":1428138000,\"main\":{\"temp\":13.25,\"temp_min\":13.25,\"temp_max\":13.25,\"pressure\":1016.86,\"sea_level\":1027.28,\"grnd_level\":1016.86,\"humidity\":99},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"lluvia ligera\",\"icon\":\"10d\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":4.67,\"deg\":140.002},\"rain\":{\"3h\":0.13},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-04 09:00:00\"},{\"dt\":1428148800,\"main\":{\"temp\":13.67,\"temp_min\":13.67,\"temp_max\":13.67,\"pressure\":1016.74,\"sea_level\":1027.03,\"grnd_level\":1016.74,\"humidity\":97},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"lluvia ligera\",\"icon\":\"10d\"}],\"clouds\":{\"all\":24},\"wind\":{\"speed\":3.27,\"deg\":202.003},\"rain\":{\"3h\":0.1},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-04 12:00:00\"},{\"dt\":1428159600,\"main\":{\"temp\":14.11,\"temp_min\":14.11,\"temp_max\":14.11,\"pressure\":1015.81,\"sea_level\":1026,\"grnd_level\":1015.81,\"humidity\":92},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":4.42,\"deg\":214.501},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-04 15:00:00\"},{\"dt\":1428170400,\"main\":{\"temp\":13.54,\"temp_min\":13.54,\"temp_max\":13.54,\"pressure\":1015.45,\"sea_level\":1025.72,\"grnd_level\":1015.45,\"humidity\":95},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":3.83,\"deg\":220.514},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-04 18:00:00\"},{\"dt\":1428181200,\"main\":{\"temp\":12.25,\"temp_min\":12.25,\"temp_max\":12.25,\"pressure\":1016.34,\"sea_level\":1026.8,\"grnd_level\":1016.34,\"humidity\":100},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"algo de nubes\",\"icon\":\"02n\"}],\"clouds\":{\"all\":20},\"wind\":{\"speed\":3.85,\"deg\":235.002},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-04 21:00:00\"},{\"dt\":1428192000,\"main\":{\"temp\":12.02,\"temp_min\":12.02,\"temp_max\":12.02,\"pressure\":1016.16,\"sea_level\":1026.76,\"grnd_level\":1016.16,\"humidity\":100},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"nubes rotas\",\"icon\":\"04n\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed\":3.49,\"deg\":269.001},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-05 00:00:00\"},{\"dt\":1428202800,\"main\":{\"temp\":11.33,\"temp_min\":11.33,\"temp_max\":11.33,\"pressure\":1015.33,\"sea_level\":1025.93,\"grnd_level\":1015.33,\"humidity\":100},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.19,\"deg\":306.002},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-05 03:00:00\"},{\"dt\":1428213600,\"main\":{\"temp\":10.67,\"temp_min\":10.67,\"temp_max\":10.67,\"pressure\":1015.88,\"sea_level\":1026.61,\"grnd_level\":1015.88,\"humidity\":100},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":1.39,\"deg\":15.0018},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-05 06:00:00\"},{\"dt\":1428224400,\"main\":{\"temp\":13.94,\"temp_min\":13.94,\"temp_max\":13.94,\"pressure\":1018,\"sea_level\":1028.55,\"grnd_level\":1018,\"humidity\":94},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":1.5,\"deg\":168.501},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-05 09:00:00\"},{\"dt\":1428235200,\"main\":{\"temp\":14.47,\"temp_min\":14.47,\"temp_max\":14.47,\"pressure\":1019.51,\"sea_level\":1029.74,\"grnd_level\":1019.51,\"humidity\":91},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":4.66,\"deg\":179.502},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-05 12:00:00\"},{\"dt\":1428246000,\"main\":{\"temp\":14.35,\"temp_min\":14.35,\"temp_max\":14.35,\"pressure\":1019.95,\"sea_level\":1030.35,\"grnd_level\":1019.95,\"humidity\":89},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"02d\"}],\"clouds\":{\"all\":8},\"wind\":{\"speed\":2.31,\"deg\":141.004},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-05 15:00:00\"},{\"dt\":1428256800,\"main\":{\"temp\":13.6,\"temp_min\":13.6,\"temp_max\":13.6,\"pressure\":1021.26,\"sea_level\":1031.54,\"grnd_level\":1021.26,\"humidity\":90},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"02d\"}],\"clouds\":{\"all\":8},\"wind\":{\"speed\":2.45,\"deg\":117.002},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-05 18:00:00\"},{\"dt\":1428267600,\"main\":{\"temp\":12.19,\"temp_min\":12.19,\"temp_max\":12.19,\"pressure\":1023.14,\"sea_level\":1033.82,\"grnd_level\":1023.14,\"humidity\":99},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"algo de nubes\",\"icon\":\"02n\"}],\"clouds\":{\"all\":12},\"wind\":{\"speed\":1.75,\"deg\":124.501},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-05 21:00:00\"},{\"dt\":1428278400,\"main\":{\"temp\":11.44,\"temp_min\":11.44,\"temp_max\":11.44,\"pressure\":1024.3,\"sea_level\":1034.93,\"grnd_level\":1024.3,\"humidity\":100},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"02n\"}],\"clouds\":{\"all\":8},\"wind\":{\"speed\":1.17,\"deg\":252.008},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-06 00:00:00\"},{\"dt\":1428289200,\"main\":{\"temp\":10.76,\"temp_min\":10.76,\"temp_max\":10.76,\"pressure\":1024.2,\"sea_level\":1034.95,\"grnd_level\":1024.2,\"humidity\":100},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":1.83,\"deg\":338.006},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-06 03:00:00\"},{\"dt\":1428300000,\"main\":{\"temp\":10.14,\"temp_min\":10.14,\"temp_max\":10.14,\"pressure\":1025.28,\"sea_level\":1036.03,\"grnd_level\":1025.28,\"humidity\":100},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.16,\"deg\":13.0131},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-06 06:00:00\"},{\"dt\":1428310800,\"main\":{\"temp\":13.74,\"temp_min\":13.74,\"temp_max\":13.74,\"pressure\":1027.07,\"sea_level\":1037.6,\"grnd_level\":1027.07,\"humidity\":93},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.76,\"deg\":94.502},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-06 09:00:00\"},{\"dt\":1428321600,\"main\":{\"temp\":14.08,\"temp_min\":14.08,\"temp_max\":14.08,\"pressure\":1028.24,\"sea_level\":1038.49,\"grnd_level\":1028.24,\"humidity\":90},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":3.66,\"deg\":120.501},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-06 12:00:00\"},{\"dt\":1428332400,\"main\":{\"temp\":13.89,\"temp_min\":13.89,\"temp_max\":13.89,\"pressure\":1027.95,\"sea_level\":1038.33,\"grnd_level\":1027.95,\"humidity\":90},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":3.61,\"deg\":124.005},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-06 15:00:00\"},{\"dt\":1428343200,\"main\":{\"temp\":12.96,\"temp_min\":12.96,\"temp_max\":12.96,\"pressure\":1028.62,\"sea_level\":1039.07,\"grnd_level\":1028.62,\"humidity\":96},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":3.26,\"deg\":111.002},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2015-04-06 18:00:00\"},{\"dt\":1428354000,\"main\":{\"temp\":11.46,\"temp_min\":11.46,\"temp_max\":11.46,\"pressure\":1030.21,\"sea_level\":1040.9,\"grnd_level\":1030.21,\"humidity\":100},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo claro\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.26,\"deg\":108.504},\"rain\":{\"3h\":0},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2015-04-06 21:00:00\"}]}\n";

        HourWeather current = new JSONParser().getHourWeather(info).get(0);
        assertEquals("01d",current.getIcon());
        assertEquals(14.0f, current.getTemperature());
        assertEquals(14.0f,current.getMax());
        assertEquals(11.0f,current.getMin());
        assertEquals(100,current.getHumidity());
        assertEquals(0,current.getClouds());
        assertEquals("Badalona",current.getCity());
        assertEquals("2015-04-02 06:00:00".substring("2015-04-02 06:00:00".indexOf(' '),"2015-04-02 06:00:00".length()-3),current.getHour());
    }

    /**
	 * @author Victor
     * Test P06
     * @throws JSONException
     */
    public void testParserWeekWeather() throws JSONException {
        info = "{\"cod\":\"200\",\"message\":0.0048,\"city\":{\"id\":1851632,\"name\":\"Badalona\",\"coord\":{\"lon\":138.933334,\"lat\":34.966671},\"country\":\"JP\",\"population\":0},\"cnt\":10,\"list\":[{\"dt\":1428372000,\"temp\":{\"day\":11.6,\"min\":9.91,\"max\":11.6,\"night\":9.91,\"eve\":11.29,\"morn\":11.6},\"pressure\":1021.77,\"humidity\":100,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"cielo claro\",\"icon\":\"10d\"}],\"speed\":8.51,\"deg\":39,\"clouds\":80,\"rain\":2.41},{\"dt\":1428458400,\"temp\":{\"day\":7.69,\"min\":7.01,\"max\":8.84,\"night\":7.2,\"eve\":7.21,\"morn\":8.84},\"pressure\":1029.5,\"humidity\":100,\"weather\":[{\"id\":502,\"main\":\"Rain\",\"description\":\"heavy intensity rain\",\"icon\":\"10d\"}],\"speed\":11.85,\"deg\":31,\"clouds\":92,\"rain\":21.8},{\"dt\":1428544800,\"temp\":{\"day\":9.72,\"min\":7.81,\"max\":10.84,\"night\":9.39,\"eve\":10.82,\"morn\":7.81},\"pressure\":1032.66,\"humidity\":100,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"02d\"}],\"speed\":11.31,\"deg\":29,\"clouds\":8},{\"dt\":1428631200,\"temp\":{\"day\":9.86,\"min\":8.21,\"max\":13,\"night\":10.46,\"eve\":13,\"morn\":8.21},\"pressure\":998.02,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":6.24,\"deg\":27,\"clouds\":98,\"rain\":18.03,\"snow\":0.01},{\"dt\":1428717600,\"temp\":{\"day\":12.26,\"min\":8.98,\"max\":13.81,\"night\":11.21,\"eve\":13.81,\"morn\":8.98},\"pressure\":1005.45,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":3.57,\"deg\":44,\"clouds\":77,\"rain\":1.47},{\"dt\":1428804000,\"temp\":{\"day\":11.37,\"min\":9.71,\"max\":13.22,\"night\":10.95,\"eve\":13.22,\"morn\":9.71},\"pressure\":1011.44,\"humidity\":0,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":6,\"deg\":44,\"clouds\":97,\"rain\":8.62},{\"dt\":1428890400,\"temp\":{\"day\":13.29,\"min\":10.92,\"max\":16.27,\"night\":15.31,\"eve\":16.27,\"morn\":10.92},\"pressure\":998.23,\"humidity\":0,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":0.87,\"deg\":5,\"clouds\":98,\"rain\":11.84},{\"dt\":1428976800,\"temp\":{\"day\":14.17,\"min\":12.83,\"max\":14.53,\"night\":12.83,\"eve\":14.52,\"morn\":14.53},\"pressure\":990.92,\"humidity\":0,\"weather\":[{\"id\":502,\"main\":\"Rain\",\"description\":\"heavy intensity rain\",\"icon\":\"10d\"}],\"speed\":3.85,\"deg\":38,\"clouds\":99,\"rain\":21.42},{\"dt\":1429063200,\"temp\":{\"day\":13.94,\"min\":12.24,\"max\":15.58,\"night\":13.09,\"eve\":15.58,\"morn\":12.24},\"pressure\":997.69,\"humidity\":0,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":1.31,\"deg\":29,\"clouds\":41,\"rain\":3.32},{\"dt\":1429149600,\"temp\":{\"day\":16.12,\"min\":11.07,\"max\":17.75,\"night\":13.66,\"eve\":17.75,\"morn\":11.07},\"pressure\":1008.06,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":0.63,\"deg\":190,\"clouds\":15,\"rain\":0.35,\"snow\":0}]}";
        WeekWeather current = new JSONParser().getWeekWeatherData(info).get(0);
        assertEquals("10d",current.getIcon());
        assertEquals(10.0f, current.getTemperature());
        assertEquals(12.0f,current.getMax());
        assertEquals(10.0f,current.getMin());
        assertEquals(100,current.getHumidity());
        assertEquals(80,current.getClouds());
        assertEquals("cielo claro",current.getDescription());
        assertEquals("Badalona",current.getCity());
        assertEquals("07/04/2015",current.getDay());
        assertEquals(1022.0f,current.getPressure());
        assertEquals(9.0f,current.getWindSpeed());
        assertEquals(39.0f,current.getWindDirection());
    }
}
