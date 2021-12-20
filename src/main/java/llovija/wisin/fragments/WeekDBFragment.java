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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;

import llovija.wisin.R;
import llovija.wisin.data.HourWeather;
import llovija.wisin.data.HourWeatherDataSource;
import llovija.wisin.data.PoblationDataSource;
import llovija.wisin.data.WeekWeather;
import llovija.wisin.data.WeekWeatherDataSource;
import llovija.wisin.utilities.HourAdapter;
import llovija.wisin.utilities.WeekAdapter;

/**
 * Class to obtain the Now fragment
 * Created by Javier on 28/04/15.
 */
public class WeekDBFragment  extends Fragment {
    private ListView lista;
    private WeekAdapter adapter;
    private PoblationDataSource dataSource;
    private WeekWeatherDataSource wds;
    private static int poblation_selected=0;
    private RelativeLayout progressBar;

    private Spinner sp_ubi;

    /**
     * Creates the fragment
     * @author Javier
     * @return the fragment
     */
    public static WeekDBFragment create(int poblation){
        WeekDBFragment weekDBFragment = new WeekDBFragment();
        poblation_selected = poblation;
        return weekDBFragment;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hour_fragment, container, false);
        // Code executed only on create

        progressBar = (RelativeLayout) view.findViewById(R.id.loadingPanel);
        lista = (ListView) view.findViewById(R.id.listView);

        wds = new WeekWeatherDataSource(view.getContext());
        String[] param = new String[] { Integer.toString(poblation_selected)};
        Cursor c = wds.getWeatherById(param);
        Cursor c2 = wds.getWeekWeatherById(param);
        carregarDades("", c, c2);
        return view;
    }

    /**
     * Loads the data from the database
     * @author Javier
     * @param name
     * @param c
     * @param c2
     */
    public void carregarDades(String name, Cursor c, Cursor c2){
        ArrayList<WeekWeather> llista = new ArrayList<WeekWeather>();
        while(c.moveToNext() && c2.moveToNext()){
            WeekWeather tmp = new WeekWeather(c.getString(2),c.getFloat(3),c.getFloat(5),c.getFloat(4),c.getInt(6),c.getInt(7),c2.getString(2),c2.getFloat(3),c2.getFloat(4),c2.getFloat(5),c2.getString(6));
            llista.add(tmp);
        }
        adapter = new WeekAdapter(getActivity(), R.layout.week, llista);
        lista.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }
}
