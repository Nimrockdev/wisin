package llovija.wisin.fragments;


import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import llovija.wisin.R;
import llovija.wisin.activities.MainActivity;
import llovija.wisin.data.CurrentWeather;
import llovija.wisin.data.CurrentWeatherDataSource;
import llovija.wisin.data.PoblationDataSource;
import llovija.wisin.data.WeatherDataSource;
import llovija.wisin.utilities.GPSTracker;
import llovija.wisin.utilities.HourAdapter;
import llovija.wisin.utilities.JSONParser;
import llovija.wisin.utilities.WeatherHTTPClient;


import llovija.wisin.fragments.HourFragment;


/**
 * Class to obtain the Now fragment
 * Created by Victor on 02/03/2015.
 */
public class NowFragment extends Fragment{
    //Widgets
    private ImageView icon;
    private TextView city, temperature, max, min, condition, description, sunrise, sunset, humidity, pressure,
            wSpeed, wDirection, clouds;
    private RelativeLayout progressBar;
    private PoblationDataSource dataSource;
    private CurrentWeatherDataSource wds;
    private static int poblation_selected=0;

    private Spinner sp_ubi;
    private String packageName;

    /**
     * Creates the fragment
     *
     * @return the fragment
     * @author Victor
     */
    public static NowFragment create(int poblation) {
        NowFragment fragment = new NowFragment();
        poblation_selected=poblation;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates the view of the fragment
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the fragment view
     * @author Victor
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.now_fragment, container, false);
        // Code executed only on create
        icon = (ImageView) view.findViewById(R.id.nowIconId);
        city = (TextView) view.findViewById(R.id.nowCityId);
        temperature = (TextView) view.findViewById(R.id.nowTempId);
        max = (TextView) view.findViewById(R.id.nowMaxId);
        min = (TextView) view.findViewById(R.id.nowMinId);
        condition = (TextView) view.findViewById(R.id.nowConditionId);
        description = (TextView) view.findViewById(R.id.nowDescriptionId);
        sunrise = (TextView) view.findViewById(R.id.nowSunriseId);
        sunset = (TextView) view.findViewById(R.id.nowSunsetId);
        humidity = (TextView) view.findViewById(R.id.nowHumidityId);
        pressure = (TextView) view.findViewById(R.id.nowPressureId);
        wSpeed = (TextView) view.findViewById(R.id.nowWindspeedId);
        wDirection = (TextView) view.findViewById(R.id.nowWindDirectionId);
        clouds = (TextView) view.findViewById(R.id.nowCloudinessId);
        progressBar = (RelativeLayout) view.findViewById(R.id.loadingPanel);



        wds = new CurrentWeatherDataSource(view.getContext());

        Location loc;
        switch(poblation_selected){

            case 0:
                GPSTracker gps = new GPSTracker(view.getContext());
                new RequestWeatherAsyncTask().execute(gps.getLocation());
                break;
            default:
                dataSource = new PoblationDataSource(view.getContext());
                String[] id = new String[]{Integer.toString(poblation_selected)};
                Cursor c = dataSource.getOneById(id);
                c.moveToNext();
                loc = new Location("");
                loc.setLatitude(c.getFloat(2));
                loc.setLongitude(c.getFloat(3));
                new RequestWeatherAsyncTask().execute(loc);
                break;
        }


        return view;
    }

    /**
     * Assigns the data to the widgets
     *
     * @param currentWeather weather object
     * @author Victor
     */
    private void createWeather(final CurrentWeather currentWeather) {

        String dateFormatted;
        int resID;
        String iconCode = "i"+currentWeather.getIcon();

        resID = getResources().getIdentifier(iconCode, "drawable", getView().getContext().getPackageName());
        Drawable d = Drawable.createFromPath("R.drawable."+ iconCode );

        icon.setImageDrawable(d);
        icon.setImageResource(resID);
        city.setText(currentWeather.getCity());
        temperature.setText(Float.toString(currentWeather.getTemperature()) + " ºC");
        max.setText(Float.toString(currentWeather.getMax()) + " ºC");
        min.setText(Float.toString(currentWeather.getMin()) + " ºC");
        condition.setText(currentWeather.getCondition());
        description.setText(currentWeather.getDescription());
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis((long) currentWeather.getSunrise() * 1000);
        dateFormatted = formatter.format(cal.getTime());
        sunrise.setText(dateFormatted);
        cal.setTimeInMillis((long) currentWeather.getSunset() * 1000);
        dateFormatted = formatter.format(cal.getTime());
        sunset.setText(dateFormatted);
        humidity.setText(Float.toString(currentWeather.getHumidity()) + "%");
        pressure.setText(Float.toString(currentWeather.getPressure()));
        wSpeed.setText(Float.toString(currentWeather.getWindSpeed()));
        wDirection.setText(Float.toString(currentWeather.getWindDirection()));
        clouds.setText(Integer.toString(currentWeather.getClouds()));













    }



    /**
     * AsyncTask to obtain the JSON object with the current weather
     */
    private class RequestWeatherAsyncTask extends AsyncTask<Location, Void, String> {

        private static final String TAG = "RequestWeatherAsyncTask";



        /**
         * Obtains the information from the server
         *
         * @param params Location to check
         * @return String with the JSON object info
         * @author Victor
         */
        @Override
        protected String doInBackground(Location... params) {
            Location here = params[0];
            return new WeatherHTTPClient().getCurrentWeatherData(here);
        }

        /**
         * Call the parse  for JSON objects
         *
         * @param s String with the information to parse
         */
        @Override
        protected void onPostExecute(String s) {
            new ParsingAsyncTask().execute(s);
        }
    }

    /**
     * Class to  parse the information
     */
    private class ParsingAsyncTask extends AsyncTask<String, Void, CurrentWeather> {

        /**
         * Creates a weather object with the info in params
         *
         * @param params String with the info
         * @return CurrentWeather object
         * @author Victor
         */
        @Override
        protected CurrentWeather doInBackground(String... params) {
            CurrentWeather currentWeather = null;
            String toParse = params[0];
            try {
                JSONParser parser = new JSONParser();
                currentWeather = parser.getWeather(toParse);
                wds.deleteWeatherById(new String[] { Integer.toString(poblation_selected)});
                wds.saveWeatherRow(currentWeather,poblation_selected,0);
            } catch (JSONException e) {
                e.printStackTrace();
                currentWeather=null;
            }
            return currentWeather;
        }

        /**
         * Assign the info to the widgets
         *
         * @param currentWeather CurrentWeather object
         * @author Victor
         */
        @Override
        protected void onPostExecute(CurrentWeather currentWeather) {
            createWeather(currentWeather);
            progressBar.setVisibility(View.GONE);
        }
    }
}
