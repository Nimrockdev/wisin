package llovija.wisin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Manages the database for weather
 * Created by Llorens on 27/04/2015.
 */
public class WeatherDataSource {

    //Database metainformation
    public static final String WEATHER_TABLE_NAME = "Weather";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";
    public static final String REAL_TYPE = "real";

    /**
     * Fields for the quotes table
     * @author Llorens
     */
    public static class ColumnWeather{
        public static final String ID_WEATHER = "id";
        public static final String ID_POBLATION = "poblation_id";
        public static final String ICON_WEATHER = "icon";
        public static final String TEMPERATURE_WEATHER = "temperature";
        public static final String MIN_WEATHER = "min";
        public static final String MAX_WEATHER = "max";
        public static final String HUMIDITY_WEATHER = "humidity";
        public static final String CLOUDS_WEATHER = "clouds";
    }

    //Quotes table creation script
    public static final String CREATE_WEATHER_SCRIPT =
            "CREATE TABLE IF NOT EXISTS "+WEATHER_TABLE_NAME+"(" +
                    ColumnWeather.ID_WEATHER+" "+INT_TYPE+" , " +
                    ColumnWeather.ID_POBLATION+" "+INT_TYPE+" , "+
                    ColumnWeather.ICON_WEATHER+" "+STRING_TYPE+" not null, " +
                    ColumnWeather.TEMPERATURE_WEATHER+" "+REAL_TYPE+" not null, "+
                    ColumnWeather.MIN_WEATHER+" "+REAL_TYPE+" not null, "+
                    ColumnWeather.MAX_WEATHER+" "+REAL_TYPE+" not null, "+
                    ColumnWeather.HUMIDITY_WEATHER+" "+INT_TYPE+" not null, "+
                    ColumnWeather.CLOUDS_WEATHER+" "+INT_TYPE+" not null," +
                    " PRIMARY KEY ("+ColumnWeather.ID_WEATHER+","+ColumnWeather.ID_POBLATION+"))";


    private WeatherDB openHelper;
    private SQLiteDatabase database;

    /**
     * Creates an instance to the database
     * @author Llorens
     * @param context
     */
    public WeatherDataSource(Context context) {
        openHelper = new WeatherDB(context);
        database = openHelper.getWritableDatabase();
    }

    /**
     * Saves a weather row
     * @author Llorens
     * @param weather the weather values
     * @param poblation_id the poblation id
     * @param id the id
     */
    public void saveWeatherRow(Weather weather,int poblation_id,int id){
        ContentValues values = new ContentValues();

        values.put(ColumnWeather.ID_WEATHER,id);
        values.put(ColumnWeather.ID_POBLATION,poblation_id);
        values.put(ColumnWeather.ICON_WEATHER,weather.getIcon());
        values.put(ColumnWeather.TEMPERATURE_WEATHER,weather.getTemperature());
        values.put(ColumnWeather.MIN_WEATHER,weather.getMin());
        values.put(ColumnWeather.MAX_WEATHER,weather.getMax());
        values.put(ColumnWeather.HUMIDITY_WEATHER,weather.getHumidity());
        values.put(ColumnWeather.CLOUDS_WEATHER,weather.getClouds());

        database.insert(WEATHER_TABLE_NAME,null,values);

    }

    /**
     * Select all the rows in quotes table
     * @author Llorens
     * @return Returns all the rows in Quotes table
     */
    public Cursor getAllQuotes(){
        return database.rawQuery(
                "select * from " + WEATHER_TABLE_NAME, null);
    }

    /**
     * Select one row in quotes table
     * @author Llorens
     * @param id the id of the poblation
     * @return Returns one row in Quotes table
     */
    public Cursor getOneWeatherById(String[] id){
        return database.rawQuery("select * from "+ WEATHER_TABLE_NAME + " WHERE poblation_id= ?",id);
    }

    /**
     * Delete one row in weather and quote table
     * @author Llorens
     * @param id the id of the poblation
     * @return true if the row is correctly deleted
     */
    public void deleteWeatherById(String[] id){
        database.rawQuery("Delete FROM "+ WEATHER_TABLE_NAME +" WHERE poblation_id = ?", id);
    }
}
