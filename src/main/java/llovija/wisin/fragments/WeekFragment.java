package llovija.wisin.fragments;

import android.database.Cursor;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import org.json.JSONException;

import java.util.ArrayList;

import llovija.wisin.R;
import llovija.wisin.data.HourWeatherDataSource;
import llovija.wisin.data.PoblationDataSource;
import llovija.wisin.data.WeekWeather;
import llovija.wisin.data.WeekWeatherDataSource;
import llovija.wisin.utilities.GPSTracker;
import llovija.wisin.utilities.JSONParser;
import llovija.wisin.utilities.WeatherHTTPClient;
import llovija.wisin.utilities.WeekAdapter;

/**
 * Class to obtain the Now fragment
 * Created by Javier on 24/03/2015.
 */
public class WeekFragment extends Fragment {

    private ListView list;
    private WeekAdapter adapter;
    private Spinner sp_ubi;
    private static int poblation_selected=0;
    ArrayList<WeekWeather> weekWeather = new ArrayList<WeekWeather>();
    private PoblationDataSource dataSource;
    private WeekWeatherDataSource wds;
    private RelativeLayout progressBar;
    JSONParser parser;


    /**
     * Creates the fragment
     * @author Javier
     * @return the fragment
     */
    public static WeekFragment create(int poblation) {
        WeekFragment fragment = new WeekFragment();
        poblation_selected = poblation;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates the view of the fragment
     * @author Javier
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_fragment, container, false);
        // Code executed only on create
        progressBar = (RelativeLayout) view.findViewById(R.id.loadingPanel);
        sp_ubi= (Spinner)view.findViewById(R.id.sp_ciudad);
        list = (ListView) view.findViewById(R.id.listView);
        adapter = new WeekAdapter(getActivity(), R.layout.week, weekWeather);
        list.setAdapter(adapter);
        wds = new WeekWeatherDataSource(view.getContext());
        Location loc = new Location("");
        double latitude;
        double longitude;
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
     * Assigns the data to the listView
     * @author Javier
     * @param weekWeather ArrayList of WeekWeather
     */
    private void createWeather(ArrayList<WeekWeather> weekWeather) {

        adapter = new WeekAdapter(getActivity(), R.layout.week, weekWeather);
        list.setAdapter(adapter);
    }


    /**
     * AsyncTask to obtain the JSON object with the current weather
     */
    private class RequestWeatherAsyncTask extends AsyncTask<Location, Void, String> {
        private static final String TAG = "RequestWeatherAsyncTask";

        /**
         * Obtains the information from the server
         * @author Javier
         * @param params Location to check
         * @return String with the JSON object info
         */
        @Override
        protected String doInBackground(Location... params) {
            Location here = params[0];
            return new WeatherHTTPClient().getWeekWeatherData(here);
        }

        /**
         * Call the parse  for JSON objects
         * @author Javier
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
    private class ParsingAsyncTask extends AsyncTask<String, Void, ArrayList<WeekWeather>> {

        /**
         * Creates a weather object with the info in params
         * @author Javier
         * @param params String with the info
         * @return WeekWeather arraylist
         */
        @Override
        protected ArrayList<WeekWeather> doInBackground(String... params) {
            String toParse = params[0];
            try {
                weekWeather = parser.getWeekWeatherData(toParse);
                wds.deleteWeatherById(new String[] { Integer.toString(poblation_selected)});
                wds.saveWeatherRow(weekWeather,poblation_selected,0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weekWeather;
        }

        /**
         * Assign the info to the listview
         * @author Javier
         * @param weekWeather ArrayList of WeekWeather
         */
        @Override
        protected void onPostExecute(ArrayList<WeekWeather> weekWeather) {
            createWeather(weekWeather);
            progressBar.setVisibility(View.GONE);
        }
    }


}
