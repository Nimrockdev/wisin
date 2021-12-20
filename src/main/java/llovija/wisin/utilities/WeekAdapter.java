package llovija.wisin.utilities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import llovija.wisin.R;
import llovija.wisin.data.HourWeather;
import llovija.wisin.data.WeekWeather;

/**
 * Adaptor to set the hourly weather formatted for a listview
 * Created by Javier on 24/03/2015.
 */
public class WeekAdapter extends ArrayAdapter{


    private ArrayList<?> inputs;
    private int R_layout_IdView;
    private Context context;

    /**
     * Creates de view
     * @author Javier
     * @param context contains the context
     * @param R_layout_IdView Layout with the items for the adapter
     * @param inputs Information to set in the layout
     */
    public WeekAdapter(Context context, int R_layout_IdView, ArrayList<?> inputs) {
        super(context, R_layout_IdView, inputs);
        this.context = context;
        this.inputs = inputs;
        this.R_layout_IdView = R_layout_IdView;
    }

    /**
     * Sets the data into the widgets
     * @author Javier
     * @param position Position into the array
     * @param convertView not in use set by Override
     * @param parent not in use set by Override
     * @return the View with all the widgets set
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R_layout_IdView, null);

        ImageView icon = (ImageView) view.findViewById(R.id.ico);
        String  iconCode = "i"+((WeekWeather)inputs.get(position)).getIcon();
        int resID = context.getResources().getIdentifier("drawable/" + iconCode, null, context.getPackageName());
        Drawable d = Drawable.createFromPath("R.drawable."+ iconCode + ".png" );
        icon.setImageDrawable(d);
        icon.setImageResource(resID);

        TextView day = (TextView) view.findViewById(R.id.day);
        day.setText(((WeekWeather)inputs.get(position)).getDay());

        TextView temp_max = (TextView) view.findViewById(R.id.temp_max);
        temp_max.setText(((WeekWeather)inputs.get(position)).getMax()+" ºC");

        TextView temp_min = (TextView) view.findViewById(R.id.temp_min);
        temp_min.setText(((WeekWeather)inputs.get(position)).getMin()+" ºC");

        TextView nuv = (TextView) view.findViewById(R.id.cloudiness);
        nuv.setText(String.valueOf(((WeekWeather)inputs.get(position)).getClouds())+"%");

        TextView pressure = (TextView) view.findViewById(R.id.pressure);
        pressure.setText(Float.toString(((WeekWeather)inputs.get(position)).getPressure())+" hpa");

        TextView description = (TextView) view.findViewById(R.id.rain);
        description.setText(((WeekWeather)inputs.get(position)).getDescription());

        TextView speed = (TextView) view.findViewById(R.id.speed);
        speed.setText(((WeekWeather)inputs.get(position)).getWindSpeed()+" Km/h ");

        TextView direction = (TextView) view.findViewById(R.id.direc);
        direction.setText(((WeekWeather)inputs.get(position)).getWindDirection()+"º");
        return view;
    }

    /**
     * @author Javier
     * @return The size of the inputs array
     */
    @Override
    public int getCount() {
        return inputs.size();
    }

    /**
     * Returns an object in a specific position
     * @author Javier
     * @param position Position to check
     * @return The object in the position
     */
    @Override
    public Object getItem(int position) {
        return inputs.get(position);
    }

    /**
     * Gets the id of an item
     * @author Javier
     * @param position of the item
     * @return The id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }



}
