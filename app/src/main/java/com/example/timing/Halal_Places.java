package com.example.timing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Halal_Places extends AppCompatActivity {
    //initialize variables
    Spinner sptype;
    Button btfind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    String place_name;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentlat = 0, currentLong = 0;
    GpsTracker gpsTracker;
    String lat ="28.3111",lng="70.1261";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halal__places);

        gpsTracker = new GpsTracker(this);
        //Assigning variables
        sptype = findViewById(R.id.sp_type);
        btfind = findViewById(R.id.btn_find);
        supportMapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        //initialize array of place type

        final String[] placeTypeList = {"restaurant"};

        //initialize array of place name

        String[] placeNameList = {"Restaurant"};

        //set adapter on spinner

        sptype.setAdapter(new ArrayAdapter<>(Halal_Places.this
                , android.R.layout.simple_spinner_dropdown_item, placeNameList));

        //Initialize fused location provider client

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Check permission

        if (ActivityCompat.checkSelfPermission(Halal_Places.this
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            //When permission granted
            //Call method

            getCurrentLocation();
        } else {
            //when permission denied
            //Request Permission
            ActivityCompat.requestPermissions(Halal_Places.this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        lat = String.valueOf(gpsTracker.getLatitude());
        lng = String.valueOf(gpsTracker.getLatitude());
        btfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                place_name = "restaurants";
                moveintent(place_name);
            }
        });

    }

    void moveintent(String place) {
        Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lng + "?q=" + place);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void getCurrentLocation() {
        //Initialize task location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //When Success
                if (location != null) {
                    //When location not equal to null
                    //Get current latitude
                    currentlat = location.getLatitude();
                    //Get current longitude
                    currentLong = location.getLongitude();

                    System.out.println("This is lat"+currentlat);
                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //When map is ready
                            map = googleMap;
                            //Zoom Current location on map
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(currentlat, currentLong), 10
                            ));
                        }
                    });

                }
            }
        });

    }









}
