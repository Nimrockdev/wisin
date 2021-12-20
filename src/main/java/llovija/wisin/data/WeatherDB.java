package llovija.wisin.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by llorens on 23/04/15.
 */
public class WeatherDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "WISIN";
    public static final int DATABASE_VERSION = 30;

    /**
     * Constructor
     * @author LLorens
     * @param context
     */
    public WeatherDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * On create database
     * @author Llorens
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creates the Poblation table
        db.execSQL(PoblationDataSource.CREATE_POBLATION_SCRIPT);
        //Creates the Weather table
        db.execSQL(WeatherDataSource.CREATE_WEATHER_SCRIPT);
        db.execSQL(CurrentWeatherDataSource.CREATE_CURRENT_SCRIPT);
        db.execSQL(HourWeatherDataSource.CREATE_HOUR_SCRIPT);
        db.execSQL(WeekWeatherDataSource.CREATE_WEEK_SCRIPT);
        //Insert the initial registers
        db.execSQL(PoblationDataSource.INSERT_POBLATION_SCRIPT);

    }

    /**
     * On upgrade of the database version delete the old tables
     * @author Llorens
     * @param db the database
     * @param oldVersion the old version
     * @param newVersion the new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Poblation");
        db.execSQL("DROP TABLE IF EXISTS Weather");
        db.execSQL("DROP TABLE IF EXISTS CurrentWeather");
        db.execSQL("DROP TABLE IF EXISTS HourWeather");
        db.execSQL("DROP TABLE IF EXISTS WeekWeather");
        onCreate(db);

    }
}
