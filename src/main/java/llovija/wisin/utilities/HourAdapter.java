package llovija.wisin.utilities;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import llovija.wisin.R;
import llovija.wisin.data.HourWeather;

/**
 * Adaptor to set the hourly weather formatted for a listview
 * Created by Llorens on 23/03/2015.
 */
public class HourAdapter extends ArrayAdapter {

    private ArrayList<?> inputs;
    private int R_layout_IdView;
    private Context context;

    /**
     * Creates de view
     * @author Llorens
     * @param context contains the context
     * @param R_layout_IdView Layout with the items for the adapter
     * @param inputs Information to set in the layout
     */
    public HourAdapter(Context context, int R_layout_IdView, ArrayList<?> inputs) {
        super(context, R_layout_IdView, inputs);
        this.context = context;
        this.inputs = inputs;
        this.R_layout_IdView = R_layout_IdView;
    }

    /**
     * Sets the data into the widgets
     * @author Llorens
     * @param position Position into the array
     * @param convertView not in use set by Override
     * @param parent not in use set by Override
     * @return the View with all the widgets set
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R_layout_IdView, null);

        ImageView icon = (ImageView) view.findViewById(R.id.icon_hour);
        String  iconCode = "i"+((HourWeather)inputs.get(position)).getIcon();
        int resID = context.getResources().getIdentifier("drawable/" + iconCode, null, context.getPackageName());
        Drawable d = Drawable.createFromPath("R.drawable."+ iconCode + ".png" );
        icon.setImageDrawable(d);
        icon.setImageResource(resID);

        TextView hour = (TextView) view.findViewById(R.id.hour);
        hour.setText(((HourWeather) inputs.get(position)).getHour());
        TextView temp = (TextView) view.findViewById(R.id.temp_hour);
        temp.setText(String.valueOf(((HourWeather) inputs.get(position)).getTemperature())+" ºC");
        TextView temp_min = (TextView) view.findViewById(R.id.tmin_hour);
        temp_min.setText(String.valueOf(((HourWeather) inputs.get(position)).getMin())+" ºC");
        TextView temp_max = (TextView) view.findViewById(R.id.tmax_hour);
        temp_max.setText(String.valueOf(((HourWeather) inputs.get(position)).getMax())+" ºC");
        TextView nuv = (TextView) view.findViewById(R.id.nuv_hour);
        nuv.setText(String.valueOf(((HourWeather) inputs.get(position)).getClouds())+"%");
        TextView hum = (TextView) view.findViewById(R.id.hum_hour);
        hum.setText(String.valueOf(((HourWeather) inputs.get(position)).getHumidity())+"%");




        return view;
    }

    /**
     * @author Llorens
     * @return The size of the inputs array
     */
    @Override
    public int getCount() {
        return inputs.size();
    }

    /**
     * Returns an object in a specific position
     * @author Llorens
     * @param position Position to check
     * @return The object in the position
     */
    @Override
    public Object getItem(int position) {
        return inputs.get(position);
    }

    /**
     * Gets the id of an item
     * @author Llorens
     * @param position of the item
     * @return The id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }



}