package llovija.wisin.fragments;


import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import llovija.wisin.R;
import llovija.wisin.data.CurrentWeatherDataSource;

/**
 * Class to obtain the Now fragment
 * Created by Victor on 18/04/2015.
 */
public class NowDBFragment extends Fragment {
    //Widgets
    private ImageView icon;
    private TextView city, temperature, max, min, condition, description, sunrise, sunset, humidity, pressure,
            wSpeed, wDirection, clouds;
    private RelativeLayout progressBar;
    private CurrentWeatherDataSource wds;
    private static int poblation_selected=0;

    private Spinner sp_ubi;

    /**
     * Creates the fragment
     *
     * @return the fragment
     * @author Victor
     */
    public static NowDBFragment create(int poblation){
        NowDBFragment nowDBFragment = new NowDBFragment();
        poblation_selected=poblation;
        return nowDBFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates the view of the fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the fragment view
     * @author Victor
     */
    @Nullable
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
        String[] ciudades = new String[] {"Actual", "Barcelona","Madrid", "Sidney", "London", "Toronto", "New York"};
        String[] param = new String[] { Integer.toString(poblation_selected)};
        Cursor c = wds.getOneWeatherById(param);
        Cursor c2 = wds.getOneCurrentWeatherById(param);
        carregarDades(ciudades[poblation_selected], c, c2);
        progressBar.setVisibility(View.GONE);
        return view;
    }

    /**
     * Loads the data from the database
     * @author Victor
     * @param name
     * @param c
     * @param c2
     */
    public void carregarDades(String name, Cursor c, Cursor c2){
        String dateFormatted;
        c.moveToFirst();
        c2.moveToFirst();

        String iconCode = "i"+c.getString(2);
        int resID = getActivity().getApplicationContext().getResources().getIdentifier("drawable/" + iconCode, null, getActivity().getApplicationContext().getPackageName());

        Drawable d = Drawable.createFromPath("R.drawable."+ iconCode + ".png" );
        icon.setImageDrawable(d);
        icon.setImageResource(resID);
        city.setText(name);
        temperature.setText(Float.toString(c.getFloat(3)) + " ºC");
        max.setText(Float.toString(c.getFloat(5)) + " ºC");
        min.setText(Float.toString(c.getFloat(4)) + " ºC");
        condition.setText(c2.getString(7));
        description.setText(c2.getString(8));
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis((long) c2.getInt(5) * 1000);
        dateFormatted = formatter.format(cal.getTime());
        sunrise.setText(dateFormatted);
        cal.setTimeInMillis((long) c2.getInt(6) * 1000);
        dateFormatted = formatter.format(cal.getTime());
        sunset.setText(dateFormatted);
        humidity.setText(Float.toString(c.getInt(6)) + "%");
        pressure.setText(Float.toString(c2.getFloat(2)));
        wSpeed.setText(Float.toString(c2.getFloat(3)));
        wDirection.setText(Float.toString(c2.getFloat(4)));
        clouds.setText(Integer.toString(c.getInt(7)));
    }
}
