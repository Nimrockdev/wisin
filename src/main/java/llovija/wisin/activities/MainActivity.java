package llovija.wisin.activities;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import llovija.wisin.R;
import llovija.wisin.data.PoblationDataSource;
import llovija.wisin.fragments.NowDBFragment;
import llovija.wisin.fragments.HourDBFragment;
import llovija.wisin.fragments.HourFragment;
import llovija.wisin.fragments.NowFragment;
import llovija.wisin.fragments.WeekDBFragment;
import llovija.wisin.fragments.WeekFragment;
import llovija.wisin.ui.SimpleToast;
import llovija.wisin.utilities.NetworkReceiver;
import llovija.wisin.utilities.UpdatableWeather;

/**
 * Main Activity
 * Created by Victor on 02/03/2015.
 */
public class MainActivity extends FragmentActivity implements ActionBar.OnNavigationListener{

    private final int PAGE_COUNT = 3;
    private ViewPager viewPager;
    private SpinnerAdapter spAdapter;
    private SharedPreferences prefs;
    private NowFragment nowFragment;
    private int poblation_selected=0;
    // Broadcast Receiver
    private NetworkReceiver receiver;

    private PoblationDataSource dataSource;
    /**
     * Creates a viewPager to contain the fragments
     * @author Victor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        dataSource = new PoblationDataSource(this);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(poblation_selected));


        // Define the SharedPreferences file
        prefs = getSharedPreferences("WisinPreferences", MODE_PRIVATE);

        // Define the fragments
        nowFragment = new NowFragment();

        // Define the receiver
        receiver = new NetworkReceiver(this);

        //Define the spinner
        spAdapter = ArrayAdapter.createFromResource(this, R.array.spvalues, R.layout.simple_spinner_dropdown_item );
        actionBar.setListNavigationCallbacks(spAdapter, this);
    }

    /**
     * Creates the fragment with the selected poblation
     *
     * @author Victor, Llorens
     * @param itemPosition The position
     * @param itemId the id of the item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        poblation_selected=itemPosition;

        if(receiver.checkSilentConnection()) {
            nowFragment.create(poblation_selected);
            HourFragment.create(poblation_selected);
            WeekFragment.create(poblation_selected);
        }else {
            NowDBFragment.create(poblation_selected);
            HourDBFragment.create(poblation_selected);
            WeekDBFragment.create(poblation_selected);
        }

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(itemPosition));
        int position = 0;
        return true;
    }






    /**
     * Creates a fragment page adapter and call the fragments
     * @author Victor
     */
    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{
        private int poblation;
        private float lat;
        private float lon;
        /**
         * Creates a fragment page adapter
         * @author Victor
         */

        public SimpleFragmentPagerAdapter(int poblation) {
            super(getSupportFragmentManager());
            this.poblation=poblation;
        }



        /**
         * The count of pages
         * @author Victor
         * @return the number of fragments
         */
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        /**
         * Assigns the fragment to the position
         * @author Victor
         * @param position the page of the fragment
         * @return the view of the fragment
         */
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show the current weather
                    if(receiver.checkSilentConnection()){
                        return nowFragment.create(poblation);
                    }else{
                        return NowDBFragment.create(poblation);
                    }
                case 1: // Fragment # 1 - This will show the hourly fragment
                    if(receiver.checkSilentConnection()){
                        return HourFragment.create(poblation);
                    }else {
                        return HourDBFragment.create(poblation);
                    }
                case 2: // Fragment # 2 - This will show the week fragment
                    if(receiver.checkSilentConnection()){
                        return WeekFragment.create(poblation);
                    }else {
                        return WeekDBFragment.create(poblation);
                    }
                default:
                    return null;
            }
        }

        /**
         * Assigns the page title
         * @author Victor
         * @param position of the page
         * @return the page title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.current);
                case 1:
                    return getString(R.string.hours);
                default:
                    return getString(R.string.week);
            }


        }

    }

    /**
     * Inflates the menu with the layout
     * @author Victor
     * @param menu
     * @return the action bar menu inflated
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }


    /**
     * Event on touch items at the menu
     * @param item item of the menu
     * @return the action of the item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
