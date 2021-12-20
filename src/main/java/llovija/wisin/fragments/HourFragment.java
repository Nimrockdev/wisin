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
import llovija.wisin.data.CurrentWeatherDataSource;
import llovija.wisin.data.HourWeather;
import llovija.wisin.data.HourWeatherDataSource;
import llovija.wisin.data.PoblationDataSource;
import llovija.wisin.utilities.GPSTracker;
import llovija.wisin.utilities.HourAdapter;
import llovija.wisin.utilities.JSONParser;
import llovija.wisin.utilities.WeatherHTTPClient;

/**
 * Creates the view Hourly fragment
 * Created by Llorens on 23/03/2015.
 */
public class HourFragment extends Fragment {
    //Widgets
    private ListView lista;
    private HourAdapter adapter;
    ArrayList<HourWeather> hourWeather = new ArrayList<HourWeather>();
    private RelativeLayout progressBar;
    private PoblationDataSource dataSource;
    private HourWeatherDataSource wds;
    private static int poblation_selected=0;

    private Spinner sp_ubi;
    JSONParser parser;

    /**
     * Creates the fragment
     *
     * @return returns the fragment
     * @author Llorens
     */
    public static HourFragment create(int poblation) {
        HourFragment fragment = new HourFragment();
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
     * @param inflater inflates the view
     * @param container
     * @param savedInstanceState
     * @return The view
     * @author Llorens
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hour_fragment, container, false);
        // Code executed only on create
        sp_ubi= (Spinner)view.findViewById(R.id.sp_ciudad);
        lista = (ListView) view.findViewById(R.id.listView);
        adapter = new HourAdapter(getActivity(), R.layout.hour, hourWeather);
        lista.setAdapter(adapter);
        wds = new HourWeatherDataSource(view.getContext());
        progressBar = (RelativeLayout) view.findViewById(R.id.loadingPanel);

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
     * Assigns the ArrayLIst of HourWeather to the ListView
     *
     * @param hourWeather the list of all the hourly weather
     * @author Llorens
     */
    private void createWeather(ArrayList<HourWeather> hourWeather) {

        adapter = new HourAdapter(getActivity(), R.layout.hour, hourWeather);
        lista.setAdapter(adapter);





    }

    /**
     * Class to obtain the weather from the server
     */
    private class RequestWeatherAsyncTask extends AsyncTask<Location, Void, String> {
        private static final String TAG = "RequestWeatherAsyncTask";

        /**
         * Obtains a JSON object without parsing
         *
         * @param params the location to check
         * @return String with the information
         * @author Llorens
         */
        @Override
        protected String doInBackground(Location... params) {
            Location here = params[0];
            return new WeatherHTTPClient().getHourWeatherData(here);
        }

        /**
         * Execute ParsingAsyncTask() for parsing the string
         *
         * @param s the string to parse
         * @author Llorens
         */
        @Override
        protected void onPostExecute(String s) {
            new ParsingAsyncTask().execute(s);
        }
    }

    private class ParsingAsyncTask extends AsyncTask<String, Void, ArrayList<HourWeather>> {

        /**
         * Parse the JSON object
         *
         * @param params String to parse
         * @return HourWeather array
         * @author Llorens
         */
        @Override
        protected ArrayList<HourWeather> doInBackground(String... params) {
            String toParse = params[0];
            try {
                hourWeather = parser.getHourWeather(toParse);
                wds.deleteWeatherById(new String[] { Integer.toString(poblation_selected)});
                wds.saveWeatherRow(hourWeather,poblation_selected,0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return hourWeather;
        }

        /**
         * Creates the view
         *
         * @param hourWeather Array of hourly weather
         * @author Llorens
         */
        @Override
        protected void onPostExecute(ArrayList<HourWeather> hourWeather) {
            createWeather(hourWeather);
            progressBar.setVisibility(View.GONE);
        }
    }
}
