package com.example.SmartChargerApp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class DecoderActivity extends Activity implements QRCodeReaderView.OnQRCodeReadListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private TextView resultTextView;
    private QRCodeReaderView qrCodeReaderView;

    private static final int MY_PERMISSION_REQUEST_CAMERA = 0;
    private ViewGroup mainLayout;

    public final static String DECODE_MESSAGE = "com.example.myfirstapp.DECODE_MESSAGE";
    private static final String TAG = DecoderActivity.class.getName();
    public String make;
    public String model;
    public int leaving_hour;
    public int leaving_min;
    public int  arriving_hour;
    public int arriving_min;
    public String barvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoder);
        Intent intent = getIntent();
        make =intent.getStringExtra("MAKE");
        model =intent.getStringExtra("MODEL");
        leaving_hour =intent.getIntExtra("LEAVINGHOUR",0);
        leaving_min =intent.getIntExtra("LEAVINGMIN",0);
        arriving_hour =intent.getIntExtra("ARRIVINGHOUR",0);
        arriving_min =intent.getIntExtra("ARRIVINGMIN",0);
        barvalue =intent.getStringExtra("BARVALUE");

        mainLayout = (ViewGroup) findViewById(R.id.activity_decoder);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "already have permission");
            initQRCodeReaderView();
        }
        else {
            Log.d(TAG, "requesting permission");
            requestCameraPermission();
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Snackbar.make(mainLayout, "Camera access is required to display the camera preview.",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override public void onClick(View view) {
                    ActivityCompat.requestPermissions(DecoderActivity.this,
                            new String[] { Manifest.permission.CAMERA }, MY_PERMISSION_REQUEST_CAMERA);
                }
            }).show();
        } else {
            Snackbar.make(mainLayout, "Permission is not available. Requesting camera permission.",
                    Snackbar.LENGTH_SHORT).show();
            Log.d(TAG, "requesting permission2");
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA },
                    MY_PERMISSION_REQUEST_CAMERA);
        }
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        if (requestCode != MY_PERMISSION_REQUEST_CAMERA) {
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Snackbar.make(mainLayout, "Camera permission was granted.", Snackbar.LENGTH_SHORT).show();
            Log.d(TAG, "permission granted, about to initialize");
            initQRCodeReaderView();
        } else {
            Snackbar.make(mainLayout, "Camera permission request was denied.", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    public void initQRCodeReaderView () {
        Log.d(TAG, "permission granted, initializing scanner");
        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();

        qrCodeReaderView.startCamera();
    }

    // Called when a QR is decoded
    // "text" : the text encoded in QR
    // "points" : points where QR control points are placed in View
    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("MAKE", make);
        intent.putExtra("MODEL", model);
        intent.putExtra("LEAVINGHOUR", leaving_hour);
        intent.putExtra("LEAVINGMIN", leaving_min);
        intent.putExtra("ARRIVINGHOUR", arriving_hour);
        intent.putExtra("ARRIVINGMIN", arriving_min);
        intent.putExtra("BARVALUE", barvalue);
        intent.putExtra(DECODE_MESSAGE, text);
        // send information to server
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }


}
