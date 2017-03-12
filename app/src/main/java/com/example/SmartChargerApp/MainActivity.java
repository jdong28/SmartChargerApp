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
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final String TAG = MainActivity.class.getName();
    //private static final int MY_PERMISSION_REQUEST_CAMERA = 0;
    private ViewGroup mainLayout;

    private WaveLoadingView mWaveLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (ViewGroup) findViewById(R.id.activity_main);

        mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        // Sets the length of the animation, default is 1000.
        mWaveLoadingView.setAnimDuration(3000);

        // TODO: properly position decoded message
        Intent intent = getIntent();
        String message = intent.getStringExtra(DecoderActivity.DECODE_MESSAGE);

        if (message != null) {
            displayChargerInfo(message);
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
    }
}

