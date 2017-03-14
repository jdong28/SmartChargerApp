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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.appyvet.rangebar.RangeBar;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConnectActivity extends Activity {

    private static final String TAG = ConnectActivity.class.getName();
    private ViewGroup mainLayout;
    private static final int MY_PERMISSION_REQUEST_CAMERA = 0;
    private TimePicker timePicker1;
    private TextView time;
    private Calendar calendar;
    Spinner sp1,sp2;
    ArrayAdapter<String> adp1,adp2;
    List<String> l1,l2;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        timePicker1.setIs24HourView(true);

        l1=new ArrayList<String>();
        l1.add("AUDI");
        l1.add("BMW");
        l1.add("Chevrolet");
        l1.add("Fiat");
        l1.add("Ford");
        l1.add("Hyundai");
        l1.add("Kia");
        l1.add("Mercedes");
        l1.add("Nissan");
        l1.add("Tesla");
        l1.add("Toyota");


        sp1= (Spinner) findViewById(R.id.spinner1);
        sp2= (Spinner) findViewById(R.id.spinner2);

        adp1=new ArrayAdapter<String> (this,android.R.layout.simple_dropdown_item_1line,l1);
        adp1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp1.setAdapter(adp1);


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                pos=arg2;
                add();

            }

            private void add() {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(), ""+pos, Toast.LENGTH_SHORT).show();

                switch(pos)
                {
                    case 0:
                        l2= new ArrayList<String>();
                        l2.add("A 1");
                        l2.add("A 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 1:
                        l2= new ArrayList<String>();
                        l2.add("B 1");
                        l2.add("B 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 2:
                        l2= new ArrayList<String>();
                        l2.add("W 1");
                        l2.add("W 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 3:
                        l2= new ArrayList<String>();
                        l2.add("C 1");
                        l2.add("C 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 4:
                        l2= new ArrayList<String>();
                        l2.add("D 1");
                        l2.add("D 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 5:
                        l2= new ArrayList<String>();
                        l2.add("E 1");
                        l2.add("E 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 6:
                        l2= new ArrayList<String>();
                        l2.add("F 1");
                        l2.add("F 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 7:
                        l2= new ArrayList<String>();
                        l2.add("G 1");
                        l2.add("G 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 8:
                        l2= new ArrayList<String>();
                        l2.add("H 1");
                        l2.add("H 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 9:
                        l2= new ArrayList<String>();
                        l2.add("I 1");
                        l2.add("I 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                    case 10:
                        l2= new ArrayList<String>();
                        l2.add("J 1");
                        l2.add("J 2");

                        adp2=new ArrayAdapter<String>(ConnectActivity.this,
                                android.R.layout.simple_dropdown_item_1line,l2);
                        adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        sp2.setAdapter(adp2);

                        select();

                        break;
                }

            }

            private void select() {
                // TODO Auto-generated method stub

                sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getBaseContext(), "Test "+arg2, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


    mainLayout = (ViewGroup) findViewById(R.id.activity_connect);
    }




    public void openQRScanner (View view) {
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
        String make = spinner1.getSelectedItem().toString();

        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        String model = spinner2.getSelectedItem().toString();

        TimePicker timePicker1;
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        int leaving_hour = timePicker1.getCurrentHour();
        int leaving_min = timePicker1.getCurrentMinute();

        RangeBar rangebar = (RangeBar) findViewById(R.id.rangebar);
        String barvalue = rangebar.getRightPinValue();

        Calendar c = Calendar.getInstance();
        int arriving_hour = c.get(Calendar.HOUR_OF_DAY);
        int arriving_min = c.get(Calendar.MINUTE);


        Intent intent = new Intent(this, DecoderActivity.class);
        intent.putExtra("MAKE", make);
        intent.putExtra("MODEL", model);
        intent.putExtra("LEAVINGHOUR", leaving_hour);
        intent.putExtra("LEAVINGMIN", leaving_min);
        intent.putExtra("ARRIVINGHOUR", arriving_hour);
        intent.putExtra("ARRIVINGMIN", arriving_min);
        intent.putExtra("BARVALUE", barvalue);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "already have permission");
            startActivity(intent);
            finish();
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
            Intent intent = new Intent(this, DecoderActivity.class);
            startActivity(intent);
        } else {
            Snackbar.make(mainLayout, "Camera permission request was denied.", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }


}
