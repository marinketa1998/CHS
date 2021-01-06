package com.example.currencyconverter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity  {
    CountryCurrencyButton button, button1;
    Button button3,buttonReset;
    ImageButton swapButton;
    String toBeConverted, converted;
    EditText edit1, edit2;
    String first = "US";
    String second = "DE";
    Double rate = 0.0;
    FusedLocationProviderClient fusedLocationProviderClient;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        firstButton();
        secondButton();
        locateButton();
        resetButton();
        swapButtonimp();
        getToBeFirstf();
        textChanged();
        textChanged2();

    }

    private void initialization() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        s = locationHandler();
        buttonReset = (Button) findViewById(R.id.buttonReset);
        swapButton = (ImageButton) findViewById(R.id.imageButton);
        button3 = (Button) findViewById(R.id.button3);
        button = (CountryCurrencyButton) findViewById(R.id.button);
        button1 = (CountryCurrencyButton) findViewById(R.id.button1);
        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
    }

    private void firstButton(){
        button.setCountry(first);
        toBeConverted = Objects.requireNonNull(button.getCountry().getCurrency()).getCode().toString();
        button.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(MainActivity.this,
                            String.format("name: %s\ncode: %s", country.getName(), country.getCode())
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            String.format("name: %s\ncurrencySymbol: %s", country.getName(), country.getCurrency())
                            , Toast.LENGTH_SHORT).show();
                    Log.e("ms", "cea: " + country.getCurrency().getCode());
                }
                toBeConverted = country.getCurrency().getCode().toString();
                Log.e("maki", "1231"+toBeConverted);
                getToBeFirstf();
            }

            @Override
            public void onSelectCurrency(Currency currency) {

            }
        });
    }

    private void secondButton() {
        button1 = (CountryCurrencyButton) findViewById(R.id.button1);
        button1.setCountry(second);
        converted = Objects.requireNonNull(button1.getCountry().getCurrency()).getCode();
        button1.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(MainActivity.this,
                            String.format("11 name: %s\ncode: %s\ncode: %s", country.getName(), country.getCode(),country.getCurrency())
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            String.format("11name: %s\ncurrencySymbol: %s", country.getName(), country.getCurrency().getSymbol())
                            , Toast.LENGTH_SHORT).show();
                }
                Log.e("at", "1231");

                converted = country.getCurrency().getCode().toString();
                Log.e("makii", "}}}"+converted);
                getToBeFirstf();
            }

            @Override
            public void onSelectCurrency(Currency currency) {

            }
        });
    }

    private void locateButton() {
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getLocation();
                button.setCountry(s);
                toBeConverted = Objects.requireNonNull(button.getCountry().getCurrency()).getCode();
                getToBeFirstf();
                ///getResult3();
                Toast.makeText(MainActivity.this,
                        // String.format("name: %s\ncode: %s", country.getName(), country.getCode())
                        String.format("%s", s)
                        , Toast.LENGTH_LONG).show();
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                //   inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                //         InputMethodManager.HIDE_NOT_ALWAYS);
                edit1.clearFocus();
            }
        });
    }
    private void resetButton() {

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit1.setText("1");
                getToBeFirstf();
            }
        });

    }

    private void swapButtonimp() {

        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Country c = button.getCountry();
                button.setCountry(button1.getCountry());
                button1.setCountry(c);

                String conv = converted;
                converted = toBeConverted;
                toBeConverted = conv;
                getToBeFirstf();
            }
        });
    }

    private String locationHandler() {
        String ss = "US";
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            ss=getLocation();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        return ss;
    }

    private String getLocation() {
         String ss = "";
        final String[] c = new String[1];
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            return ss;
        }else{
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            //String ss = "";
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                        List<Address> adresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        Log.e("s", "ceva lat/log" + location.getLatitude() + "/" + location.getLongitude() + adresses.get(0).getCountryCode());
                       s = adresses.get(0).getCountryCode();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        ss = s;
        return ss;
        }
    }

    private void getToBeFirst() {
        rate = APIHandler.getRate(MainActivity.this,toBeConverted,converted);
        String str = String.valueOf(Double.parseDouble(edit1.getText().toString())*rate);
        Log.e("at", "afsssster1" + str);
        if(edit1.isFocused()) {
            if(str.length()>12){
            String s = str.substring(0,12);
            edit2.setText(s);
            } else {
                edit2.setText(str);
            }
        }
    }

    private void getConvFirst() {
        rate = APIHandler.getRate(MainActivity.this,converted,toBeConverted);
        String str = String.valueOf(Double.parseDouble(edit2.getText().toString())*rate);
        Log.e("at", "after1" + str);
        if( edit2.isFocused()) {
            if(str.length()>12) {
                String s = str.substring(0, 12);
                edit1.setText(s);
                Log.e("at", "focused");
            } else {
                edit1.setText(str);
            }
        }
    }

    private void getToBeFirstf() {
        rate = APIHandler.getRate(MainActivity.this,toBeConverted,converted);
       // Log.e("aaati3", "convert" + converted + "toBe"+toBeConverted+"rate"+rate);
        String str = String.valueOf(Double.parseDouble(edit1.getText().toString())*rate);
        if(str.length()>12) {
            String s = str.substring(0, 12);
            edit2.setText(s);
        } else {
            edit2.setText(str);
        }
    }


    private void textChanged(){
        edit1.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               // Log.e("wat","bef");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              // Log.e("wat","on");
              /// edit2.setText(String.valueOf(getResult()));
            }

            @Override
            public void afterTextChanged(Editable s) {
              // Log.e("wat","after"+edit2.getText());
                if (edit1.getText().length()>0) {
                    getToBeFirst();
                }
            }
        });
    }
    private void textChanged2() {
        edit2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             //   Log.e("wat", "bef");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              //  Log.e("wat", "on");
                /// edit2.setText(String.valueOf(getResult()));

            }

            @Override
            public void afterTextChanged(Editable s) {
                   // Log.e("wat", "after" + edit2.getText());
                    if (edit2.getText().length()>0){
                        getConvFirst();
                    }

            }
        });
    }

    public void deselect(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        edit1.clearFocus();
        edit2.clearFocus();
    }
}