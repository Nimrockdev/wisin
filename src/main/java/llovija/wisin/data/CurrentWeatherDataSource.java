package llovija.wisin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Manages the database for current weather
 * Created by Llorens on 27/04/2015.
 */
public class CurrentWeatherDataSource {

    //Database metainformation
    public static final String WEATHER_TABLE_NAME = "Weather";
    public static final String CURRENT_TABLE_NAME = "CurrentWeather";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";
    public static final String REAL_TYPE = "real";

    /**
     * Fields for the quotes table
     * @author Llorens
     */
    public static class ColumnWeather{
        public static final String ID_WEATHER = "id";
        public static final String PRESSURE = "pressure";
        public static final String WIND_SPEED = "wind_speed";
        public static final String WIND_DIRECTION = "wind_direction";
        public static final String SUNRISE = "sunrise";
        public static final String SUNSET = "sunset";
        public static final String CONDITION = "condition";
        public static final String DESCRIPTION = "description";

        public static final String ID_POBLATION = "poblation_id";
        public static final String ICON_WEATHER = "icon";
        public static final String TEMPERATURE_WEATHER = "temperature";
        public static final String MIN_WEATHER = "min";
        public static final String MAX_WEATHER = "max";
        public static final String HUMIDITY_WEATHER = "humidity";
        public static final String CLOUDS_WEATHER = "clouds";
    }


    //Quotes table creation script
    public static final String CREATE_CURRENT_SCRIPT =
            "CREATE TABLE IF NOT EXISTS "+CURRENT_TABLE_NAME+"(" +
                    ColumnWeather.ID_WEATHER+" "+INT_TYPE+" , " +
                    ColumnWeather.ID_POBLATION+" "+INT_TYPE+" , "+
                    ColumnWeather.PRESSURE+" "+INT_TYPE+" , "+
                    ColumnWeather.WIND_SPEED+" "+STRING_TYPE+" not null, " +
                    ColumnWeather.WIND_DIRECTION+" "+REAL_TYPE+" not null," +
                    ColumnWeather.SUNRISE+" "+INT_TYPE+" not null," +
                    ColumnWeather.SUNSET+" "+INT_TYPE+" not null," +
                    ColumnWeather.CONDITION+" "+STRING_TYPE+" not null," +
                    ColumnWeather.DESCRIPTION+" "+STRING_TYPE+" not null," +
                    " PRIMARY KEY ("+ColumnWeather.ID_WEATHER+","+ColumnWeather.ID_POBLATION+"))";



    private WeatherDB openHelper;
    private SQLiteDatabase database;

    /**
     * Creates an instance to the database
     * @author Llorens
     * @param context
     */
    public CurrentWeatherDataSource(Context context) {
        openHelper = new WeatherDB(context);
        database = openHelper.getWritableDatabase();
    }

    /**
     * Inserts a weather row in the database
     * @author Llorens
     * @param weather CurrentWeather object
     * @param poblation_id The poblation selected
     * @param id The id
     * @return boolean with the result of the insertion
     */
    public boolean saveWeatherRow(CurrentWeather weather,int poblation_id,int id){
        ContentValues values = new ContentValues();

        values.put(ColumnWeather.ID_WEATHER,id);
        values.put(ColumnWeather.ID_POBLATION,poblation_id);
        values.put(ColumnWeather.PRESSURE,weather.getPressure());
        values.put(ColumnWeather.WIND_SPEED,weather.getWindSpeed());
        values.put(ColumnWeather.WIND_DIRECTION,weather.getWindDirection());
        values.put(ColumnWeather.SUNRISE,weather.getSunrise());
        values.put(ColumnWeather.SUNSET,weather.getSunset());
        values.put(ColumnWeather.CONDITION,weather.getCondition());
        values.put(ColumnWeather.DESCRIPTION,weather.getDescription());
        database.insert(CURRENT_TABLE_NAME,null,values);

        values = new ContentValues();
        values.put(ColumnWeather.ID_WEATHER,id);
        values.put(ColumnWeather.ID_POBLATION,poblation_id);
        values.put(ColumnWeather.ICON_WEATHER,weather.getIcon());
        values.put(ColumnWeather.TEMPERATURE_WEATHER,weather.getTemperature());
        values.put(ColumnWeather.MIN_WEATHER,weather.getMin());
        values.put(ColumnWeather.MAX_WEATHER,weather.getMax());
        values.put(ColumnWeather.HUMIDITY_WEATHER,weather.getHumidity());
        values.put(ColumnWeather.CLOUDS_WEATHER,weather.getClouds());
        database.insert(WEATHER_TABLE_NAME,null,values);

        return true;
    }

    /**
     * Select all the rows in quotes table
     * @author Llorens
     * @return Returns all the rows in Quotes table
     */
    public Cursor getAllQuotes(){
        //Seleccionamos todas las filas de la tabla Quotes
        return database.rawQuery(
                "select * from " + CURRENT_TABLE_NAME, null);
    }

    /**
     * Select one row in quotes table
     * @author Llorens
     * @param id the id of the poblation
     * @return Returns one row in Quotes table
     */
    public Cursor getOneCurrentWeatherById(String[] id){
        return database.rawQuery("select * from "+ CURRENT_TABLE_NAME + " WHERE poblation_id= ?",id);


    }

    /**
     * Select one row in weather table
     * @author Llorens
     * @param id the id of the poblation
     * @return Returns one row in weather table
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
    public boolean deleteWeatherById(String[] id){

        database.delete(WEATHER_TABLE_NAME, " poblation_id = "+id[0]+" and id=0", null);
        database.delete(CURRENT_TABLE_NAME, " poblation_id = "+id[0]+" and id=0", null);

        return true;
    }
}
