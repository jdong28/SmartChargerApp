package com.example.SmartChargerApp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.appyvet.rangebar.RangeBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import me.itangqi.waveloadingview.WaveLoadingView;

import static com.example.SmartChargerApp.R.id.rangebar;


public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final String TAG = MainActivity.class.getName();
    //private static final int MY_PERMISSION_REQUEST_CAMERA = 0;
    private ViewGroup mainLayout;

    private WaveLoadingView mWaveLoadingView;

    public String make;
    public String model;
    public int leaving_hour;
    public int leaving_min;
    public int  arriving_hour;
    public int arriving_min;
    public String barvalue;
    public String car_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainLayout = (ViewGroup) findViewById(R.id.activity_main);

        mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        // Sets the length of the animation, default is 1000.
        mWaveLoadingView.setAnimDuration(3000);


        Intent intent = getIntent();
        make = intent.getStringExtra("MAKE");
        model = intent.getStringExtra("MODEL");
        leaving_hour = intent.getIntExtra("LEAVINGHOUR", 0);
        leaving_min = intent.getIntExtra("LEAVINGMIN", 0);
        arriving_hour = intent.getIntExtra("ARRIVINGHOUR", 0);
        arriving_min = intent.getIntExtra("ARRIVINGMIN", 0);
        barvalue = intent.getStringExtra("BARVALUE");
        car_id = intent.getStringExtra(DecoderActivity.DECODE_MESSAGE);

        if (model != null) {
            displayChargerInfo(model);
        }
        // send all the information to server
        String urlString= "http://159.203.60.242/demo.php?id="+car_id+"&battery="+barvalue+"&start=3&end=44&chargevalue=3";

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection.setRequestProperty("User-Agent", "");
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setDoInput(true);
        try {
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
/*
    public void openQRScanner (View view) {
        Intent intent = new Intent(this, DecoderActivity.class);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "already have permission");
            startActivity(intent);
        }
        else {
            Log.d(TAG, "requesting permission");
            requestCameraPermission();
        }
    }*/

    // TODO: Implement setVariableTile in WaveLoadingView that adjusts position of label as progress changes
    private void displayChargerInfo(String msg) {
        TextView chargeStatusText = (TextView) findViewById(R.id.chargerID);
        chargeStatusText.setText(msg);

        mWaveLoadingView.setProgressValue(28);
        mWaveLoadingView.setTopTitle("");
        mWaveLoadingView.setBottomTitle("126km");

    }

    // get all the information from server

    private void onServerResponse(){
        TextView chargelabel = (TextView) findViewById(R.id.chargelabel);
        chargelabel.setText("");

        TextView hoursRemaining = (TextView) findViewById(R.id.timeRemainingLabel);
        hoursRemaining.setText("");

        TextView chargingRate = (TextView) findViewById(R.id.chargingRate);
        chargingRate.setText("");

        TextView distanceChargingHour = (TextView) findViewById(R.id.distanceChargingHour);
        distanceChargingHour.setText("");

        TextView voltageCurrent = (TextView) findViewById(R.id.voltageCurrent);
        voltageCurrent.setText("");


        mWaveLoadingView.setProgressValue(28);
        mWaveLoadingView.setTopTitle("");
        mWaveLoadingView.setBottomTitle("126km");
    }

    /*
    public void getJSON () {
        final String URL = "/server/app/algo.html";
        JsonObjectRequest req = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        ApplicationController.getInstance().addToRequestQueue(req);
    }*/
}

