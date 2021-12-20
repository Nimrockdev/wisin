package llovija.wisin.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import llovija.wisin.R;

/**
 * Creates a personalized toast message
 * Created by Victor on 21/02/2015.
 */
public class SimpleToast {
    private static final String TAG = "SimpleToast";

    private Context context;

    /**
     * Defines the activity to toast
     * @author Victor
     * @param context activity that will apply the toast
     */
    public SimpleToast(Context context) {
        this.context = context;
    }

    /**
     * Constructor of the class, receive an int and obtain the resources
     * @author Victor
     * @param text int to obtain resources
     */
    public void toast(int text){
        toast(context.getResources().getString(text));
    }

    /**
     * Constructor of the class, with the text creates a toast with a personalized view
     * @author Victor
     * @param text text to show
     */
    public void toast(String text){
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) ((Activity) context)
                        .findViewById(R.id.toast_layout_root));
        TextView textView = (TextView) layout.findViewById(R.id.text);
        textView.setText(text);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
