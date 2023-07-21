
        package com.ebookfrenzy.httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the EditText widget with the city input
        editCity = findViewById(R.id.editText);
    }

    // Method to be called when the button is clicked
    public void getInfo(View view) {
        String urlString = ""; // TODO: The link will go here, e.g., "https://example.com/data"
        new asyncTask().execute(urlString);
    }

    // Inner class to perform the HTTP request in the background
    protected class asyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // TODO: Add any setup or UI changes before starting the background task.
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // TODO: Add any actions that need to be performed after the background task is completed.
        }

        @Override
        protected void onProgressUpdate(String... values) {
            // TODO: Implement UI updates using the JSON data received in values[0].
            // For example, you can display the JSON data in a TextView.
            // Example:
            // TextView textView = findViewById(R.id.textView);
            // textView.setText(values[0]);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String NewsData;
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(5000);
                try {
                    // Get the input stream from the connection and read it into a String
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    NewsData = convertInputToString(in);

                    // Publish the JSON data to onProgressUpdate for UI update
                    publishProgress(NewsData);
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                // TODO: Handle any exceptions that might occur during the HTTP request.
                // For example, you can log the error or display a message to the user.
            }

            return null;
        }
    }

    // Helper method to convert an InputStream to a String
    private String convertInputToString(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line;
        String allLines = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                allLines += line;
            }
        } catch (Exception e) {
            // TODO: Handle any exceptions that might occur during the conversion.
        }
        return allLines;
    }
}
