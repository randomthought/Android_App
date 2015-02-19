package com.example.malcolmmd.sunshine;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by malcolmmd on 1/25/15.
 */
public class FestchWeatherTask extends AsyncTask<Void, Void, Void> {

    private final String LOG_TAG = FestchWeatherTask.class.getSimpleName();

    protected Void doInBackground() {
        HttpURLConnection connection = null;
        BufferedReader reader     = null;
        String forecastJsonStr    = null;

        try {
            URL url    = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            StringBuilder builder   = new StringBuilder();
            reader                  = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

            forecastJsonStr = builder.toString();
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
    }

}
