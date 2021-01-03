package com.example.currencyconverter;
/*
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "marin";//HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }
    public String makeServiceCall(String reqUrl) {
        String res = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            res = convertStreamToString(in);
            Log.e("ms", "marin"+ res);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
             e.printStackTrace();
        }
        return res;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null){
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e("mar", "marin");
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("mar", "mari" );
            }
        }
        Log.e("ms", "ceamaaaa: ");
        return sb.toString();
    }
}*/