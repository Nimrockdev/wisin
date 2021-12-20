package llovija.wisin.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Manages the database for the locations
 * Created by Llorens on 27/04/2015.
 */
public class PoblationDataSource {

    //Database metainformation
    public static final String POBLATION_TABLE_NAME = "Poblation";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";
    public static final String REAL_TYPE = "real";

    /**
     * Fields for the quotes table
     * @author Llorens
     */
    public static class ColumnPoblation{
        public static final String ID_POBLATION = "ID";
        public static final String NAME_POBLATION = "name";
        public static final String LATITUDE_POBLATION = "latitude";
        public static final String LONGITUDE_POBLATION = "longitude";
    }

    /**
     * Quotes table creation script
     * @author Llorens
     */
    public static final String CREATE_POBLATION_SCRIPT =
            "CREATE TABLE IF NOT EXISTS "+POBLATION_TABLE_NAME+"(" +
                    ColumnPoblation.ID_POBLATION+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnPoblation.NAME_POBLATION+" "+STRING_TYPE+" not null," +
                    ColumnPoblation.LATITUDE_POBLATION+" "+REAL_TYPE+" not null,"+
                    ColumnPoblation.LONGITUDE_POBLATION+" "+REAL_TYPE+" not null)";

    //Quotes table creation script
    public static final String INSERT_POBLATION_SCRIPT =
            "insert into "+POBLATION_TABLE_NAME+" values(" +
                    "null, 'Barcelona'," + 41.38792 +","+ 2.169919 +")," +
                    "(null,'Madrid'," + 40.416762 +","+ -3.703492+")," +
                    "(null,'Sidney'," + -33.867613 +","+ 151.206981+")," +
                    "(null,'London'," + 51.507365 +","+  -0.127759+")," +
                    "(null,'Toronto'," + 43.653211 +","+ -79.383322+")," +
                    "(null,'New York'," + 40.712666 +","+ -74.005979+")"+
                    "";

    private WeatherDB openHelper;
    private SQLiteDatabase database;

    /**
     * Creates an instance to the database
     * @author Llorens
     * @param context
     */
    public PoblationDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        openHelper = new WeatherDB(context);
        database = openHelper.getWritableDatabase();
    }

    /**
     * Saves the location
     * @param name name of the town
     * @param latitude latitude
     * @param longitude longitude
     */
    public void savePoblationRow(String name, float latitude, float longitude){
        ContentValues values = new ContentValues();

        values.put(ColumnPoblation.NAME_POBLATION,name);
        values.put(ColumnPoblation.LATITUDE_POBLATION,latitude);
        values.put(ColumnPoblation.LONGITUDE_POBLATION,longitude);

        database.insert(POBLATION_TABLE_NAME,null,values);

    }

    /**
     * Select all the rows in quotes table
     * @author Llorens
     * @return Returns all the rows in Quotes table
     */
    public Cursor getAllQuotes(){
        return database.rawQuery(
                "select * from " + POBLATION_TABLE_NAME, null);
    }

    /**
     * Select one row in quotes table
     * @author Llorens
     * @param id the id of the poblation
     * @return Returns one row in Quotes table
     */
    public Cursor getOneById(String[] id){
        return database.rawQuery("select * from "+ POBLATION_TABLE_NAME + " WHERE id= ?",id);


    }
}
