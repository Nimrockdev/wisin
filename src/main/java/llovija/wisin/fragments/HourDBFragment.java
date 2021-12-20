package llovija.wisin.fragments;

import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import llovija.wisin.R;
import llovija.wisin.data.CurrentWeatherDataSource;
import llovija.wisin.data.HourWeather;
import llovija.wisin.data.HourWeatherDataSource;
import llovija.wisin.data.PoblationDataSource;
import llovija.wisin.utilities.HourAdapter;

/**
 * Creates the view Hourly fragment
 * Created by Llorenç on 27/04/2015.
 */
public class HourDBFragment extends Fragment {
    //Widgets
    private ListView lista;
    private HourAdapter adapter;
    private HourWeatherDataSource wds;
    private static int poblation_selected=0;
    private RelativeLayout progressBar;


    /**
     * Creates the fragment
     *
     * @return returns the fragment
     * @author Llorens
     */
    public static HourDBFragment create(int poblation){
        HourDBFragment hourDBFragment = new HourDBFragment();
        poblation_selected = poblation;
        return hourDBFragment;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hour_fragment, container, false);
        // Code executed only on create


        lista = (ListView) view.findViewById(R.id.listView);
        progressBar = (RelativeLayout) view.findViewById(R.id.loadingPanel);
        wds = new HourWeatherDataSource(view.getContext());
        String[] param = new String[] { Integer.toString(poblation_selected)};
        Cursor c = wds.getWeatherById(param);
        Cursor c2 = wds.getHourWeatherById(param);
        carregarDades("", c, c2);

        return view;
    }

    /**
     * Loads the data from the database
     * @param name
     * @param c
     * @param c2
     */
    public void carregarDades(String name, Cursor c, Cursor c2){
       ArrayList<HourWeather> llista = new ArrayList<HourWeather>();
       while(c.moveToNext() && c2.moveToNext()){
           HourWeather tmp = new HourWeather(c.getString(2),c.getFloat(3),c.getFloat(5),c.getFloat(4),c.getInt(6),c.getInt(7),c2.getString(2));
           llista.add(tmp);
       }
        adapter = new HourAdapter(getActivity(), R.layout.hour, llista);
        lista.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }
}
