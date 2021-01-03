package com.example.currencyconverter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class APIHandler {

    static double getRate(Context context, String toBeConverted, String converted){
        Double d = 0.0;
        String api = "https://api.ratesapi.io/api/latest?base="+toBeConverted+"&symbols="+converted;
        if (converted.equals(toBeConverted)) {
            Toast.makeText(context,
                    String.format("It's the same currency")
                    , Toast.LENGTH_SHORT).show();
        } else {
            try {
                //  HttpHandler sh = new HttpHandler();
                // Making a request to url and getting response
                String url = "http://api.androidhive.info/contacts/";
                //String apii = "https://api.exchangerate.host/latest?base=USD&symbols=EUR";
                String apii = "https://api.exchangerate.host/latest?base="+toBeConverted+"&symbols="+converted;
                //String jsonStr = sh.makeServiceCall(api);
                String result;
                //Instantiate new instance of our class
                httpGet getRequest = new httpGet();
                //Perform the doInBackground method, passing in our url
                result = getRequest.execute(apii).get();
                Log.e("msi", "Response from url: " + api+" " + result);
                try {
                    JSONObject obj = new JSONObject(result);
                  //  JSONObject obj1 = obj.getJSONObject("rates");
                   // d = obj1.getDouble(converted);
                   // Log.e("mis", "base: " + d);
                    //JSONObject obj1 = obj.getJSONObject("motd");
                    JSONObject obj2 = obj.getJSONObject("rates");
                    d = obj2.getDouble(converted);
                    Log.e("mssi", "Response from url: " +d);
                    //d = obj2.getDouble(converted);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }  catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }
        return d;
    }

}
