package com.example.malcolmmd.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ForecastFragment extends Fragment {
        // Initialize adaptor for holding list of days
        private ArrayAdapter<String> mForecastAdapter;

        public ForecastFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // Create list array for days
            List<String> weekForecast = Arrays.asList(
                    "Today - Sunny - 88 / 63",
                    "Tomorrow- Foggy - 70 / 46",
                    "Wed - Cloudy - 72 / 63",
                    "Thurs - Rainy - 64 / 51",
                    "Friday - Froggy - 70 / 46",
                    "Sat - Sunny 76 / 68"
            );

            // Pass the list of days into the Array adaptor
            mForecastAdapter =
                 new ArrayAdapter<String>(
                        // The current contex (this fragments activtivity)
                        getActivity(),
                        R.layout.list_item_forcast,
                        R.id.list_item_forcast_textview,
                        weekForecast);


            ListView listView = (ListView) rootView.findViewById(
                    R.id.list_item_forcast_textview);
            listView.setAdapter(mForecastAdapter);

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;



            return rootView;
        }
    }
}
