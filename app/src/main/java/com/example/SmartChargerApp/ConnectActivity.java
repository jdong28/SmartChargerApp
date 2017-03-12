package com.example.SmartChargerApp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class ConnectActivity extends Activity {

    private static final String TAG = ConnectActivity.class.getName();
    private ViewGroup mainLayout;
    private static final int MY_PERMISSION_REQUEST_CAMERA = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        mainLayout = (ViewGroup) findViewById(R.id.activity_connect);
    }

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
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Snackbar.make(mainLayout, "Camera access is required to display the camera preview.",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override public void onClick(View view) {
                    ActivityCompat.requestPermissions(ConnectActivity.this,
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
            Log.d(TAG, "permission granted, opening scanner");
            Intent intent = new Intent(this, DecoderActivity.class);
            startActivity(intent);
        } else {
            Snackbar.make(mainLayout, "Camera permission request was denied.", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

}
