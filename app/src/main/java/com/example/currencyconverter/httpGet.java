package com.example.currencyconverter;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;

public class httpGet extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params){
        String urlString = params[0];
        String result = null;
        String inputLine;

        try {
            //Create a URL object holding our url
            URL myUrl = new URL(urlString);
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);

            //Connect to our url
            connection.connect();

            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}