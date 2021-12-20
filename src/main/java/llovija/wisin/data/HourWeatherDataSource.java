package llovija.wisin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Manages the database for hourly weather
 * Created by Llorens on 27/04/2015.
 */
public class HourWeatherDataSource  {

    //Database metainformation
    public static final String WEATHER_TABLE_NAME = "Weather";
    public static final String HOUR_TABLE_NAME = "HourWeather";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";
    public static final String REAL_TYPE = "real";

    /**
     * Fields for the quotes table
     * @author Llorens
     */
    public static class ColumnWeather{
        public static final String ID_WEATHER = "id";
        public static final String HOUR = "hour";

        public static final String ID_POBLATION = "poblation_id";
        public static final String ICON_WEATHER = "icon";
        public static final String TEMPERATURE_WEATHER = "temperature";
        public static final String MIN_WEATHER = "min";
        public static final String MAX_WEATHER = "max";
        public static final String HUMIDITY_WEATHER = "humidity";
        public static final String CLOUDS_WEATHER = "clouds";
    }

    //Quotes table creation script
    public static final String CREATE_HOUR_SCRIPT =
            "CREATE TABLE IF NOT EXISTS "+HOUR_TABLE_NAME+"(" +
                    ColumnWeather.ID_WEATHER+" "+INT_TYPE+" , " +
                    ColumnWeather.ID_POBLATION+" "+INT_TYPE+" , "+
                    ColumnWeather.HOUR+" "+STRING_TYPE+" , "+
                    " PRIMARY KEY ("+ColumnWeather.ID_WEATHER+","+ColumnWeather.ID_POBLATION+"))";



    private WeatherDB openHelper;
    private SQLiteDatabase database;

    /**
     * Creates an instance to the database
     * @author Llorens
     * @param context
     */
    public HourWeatherDataSource(Context context) {
        openHelper = new WeatherDB(context);
        database = openHelper.getWritableDatabase();
    }

    /**
     * Inserts a weather row in the database
     * @author Llorens
     * @param Aweather List with the weather
     * @param poblation_id poblation to check
     * @param id id
     * @return the result of the insertion
     */
    public boolean saveWeatherRow(ArrayList<HourWeather> Aweather,int poblation_id,int id){
        for (int i=0;i<Aweather.size();i++) {
            ContentValues values = new ContentValues();
            HourWeather weather = Aweather.get(i);
            values.put(ColumnWeather.ID_WEATHER, i+1);
            values.put(ColumnWeather.ID_POBLATION, poblation_id);
            values.put(ColumnWeather.HOUR, weather.getHour());
            database.insert(HOUR_TABLE_NAME, null, values);

            values = new ContentValues();
            values.put(ColumnWeather.ID_WEATHER, i+1);//0
            values.put(ColumnWeather.ID_POBLATION, poblation_id);//1
            values.put(ColumnWeather.ICON_WEATHER, weather.getIcon());//2
            values.put(ColumnWeather.TEMPERATURE_WEATHER, weather.getTemperature());//3
            values.put(ColumnWeather.MIN_WEATHER, weather.getMin());//4
            values.put(ColumnWeather.MAX_WEATHER, weather.getMax());//5
            values.put(ColumnWeather.HUMIDITY_WEATHER, weather.getHumidity());
            values.put(ColumnWeather.CLOUDS_WEATHER, weather.getClouds());
            database.insert(WEATHER_TABLE_NAME, null, values);
        }
        return true;
    }

    /**
     * Select all the rows in quotes table
     * @author Llorens
     * @return Returns all the rows in Quotes table
     */
    public Cursor getAllQuotes(){
        return database.rawQuery(
                "select * from " + HOUR_TABLE_NAME, null);
    }

    /**
     * Select one row in quotes table
     * @author Llorens
     * @param id the id of the poblation
     * @return Returns one row in Quotes table
     */
    public Cursor getHourWeatherById(String[] id){
        return database.rawQuery("select * from "+ HOUR_TABLE_NAME + " WHERE poblation_id= ? AND id>0 AND id<10",id);


    }

    /**
     * Select one row in weather table
     * @author Llorens
     * @param id the id of the poblation
     * @return Returns one row in weather table
     */
    public Cursor getWeatherById(String[] id){
        return database.rawQuery("select * from "+ WEATHER_TABLE_NAME + " WHERE poblation_id= ? AND id>0 AND id<10",id);
    }

    /**
     * Delete one row in weather and quote table
     * @author Llorens
     * @param id the id of the poblation
     * @return true if the row is correctly deleted
     */
    public boolean deleteWeatherById(String[] id){
        database.delete(WEATHER_TABLE_NAME, " poblation_id = "+id[0]+" and id >0 AND id<10", null);
        database.delete(HOUR_TABLE_NAME, " poblation_id = "+id[0]+" and id >0 AND id<10", null);
        return true;
    }
}
