package com.example.timing;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.timing.ParyerTimeModel.Paryertimes;
import com.example.timing.Retrofit.NamazTime;
import com.example.timing.Retrofit.RetrofitClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    Halal_Places halal_places;

    //Tag used to cancel the request
    String tag_json_req = "json_obj_req";
    //progress dialog
    ProgressDialog pDialog;
    private String TAG = "tag";

    TextView mFajrtv, mDuhrtv, mAsrtv, mMagribtv, mIshatv;

    String prayertimeConertor;
    String monthNo, yearNo, method;

    Retrofit retrofit;
    NamazTime namazTimeInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inItRetrofit();

        mFajrtv = findViewById(R.id.fajrtv);
        mDuhrtv = findViewById(R.id.duhrtv);
        mAsrtv = findViewById(R.id.asrtv);
        mMagribtv = findViewById(R.id.magribtv);
        mIshatv = findViewById(R.id.ishatv);

        pDialog = new ProgressDialog(this);
       /* pDialog.setMessage("Loading");
        pDialog.show();*/
        Date date = new Date();
        this.monthNo = (String) DateFormat.format("MM", date);
        this.yearNo = (String) DateFormat.format("yyyy", date);
        this.method = "1";

        //        getApiData(halal_places.lat, halal_places.lng, this.method, this.monthNo, this.yearNo);
        checkForPermission();
    }

    public String AllTimeConvertor(String str) {
        try {
            this.prayertimeConertor = new SimpleDateFormat("hh:mm a").format(new SimpleDateFormat("HH:mm").parse(str));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.prayertimeConertor;
    }

    public void getApiData(String str, String str2, String str3, String str4, String str5) {
        this.namazTimeInterface.getNmazTime(str, str2, str3, str4, str5).enqueue(new Callback<Paryertimes>() {

            @Override
            public void onResponse(Call<Paryertimes> call, retrofit2.Response<Paryertimes> response) {
                Log.d("abt", "onResponse: " + response.body());
                if (response.body() != null) {

                    String fajartimeConvertor = AllTimeConvertor(response.body().getData().getTimings().getFajr());

                    String dhuartimeConvertor = AllTimeConvertor(response.body().getData().getTimings().getDhuhr());

                    String asartimeConvertor = AllTimeConvertor(response.body().getData().getTimings().getAsr());

                    String magribtimeConvertor = AllTimeConvertor(response.body().getData().getTimings().getMaghrib());
                    String ishatimeConvertor = AllTimeConvertor(response.body().getData().getTimings().getIsha());

                    String sunrisetimeConvertor = AllTimeConvertor(response.body().getData().getTimings().getSunrise());

                    //  Toast.makeText(MainActivity.this, "done"+halal_places.currentLong, Toast.LENGTH_SHORT).show();
                    mFajrtv.setText(fajartimeConvertor);
                    mDuhrtv.setText(dhuartimeConvertor);
                    mAsrtv.setText(asartimeConvertor);
                    mMagribtv.setText(magribtimeConvertor);
                    mIshatv.setText(ishatimeConvertor);

                    /*  pDialog.hide();*/
                }
            }

            public void onFailure(Call<Paryertimes> call, Throwable th) {
                Log.d("uzi", "fail" + th.getMessage());
            }
        });
    }

    private void inItRetrofit() {
        retrofit = RetrofitClient.getRetrofitInstance();
        namazTimeInterface = (NamazTime) this.retrofit.create(NamazTime.class);
    }

    private void getCurrentLocation() {
        //Initialize task location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //When Success
                if (location != null) {
                    //When location not equal to null

                    //Get current latitude
                    double currentlat = location.getLatitude();
                    //Get current longitude
                    double currentLong = location.getLongitude();

                    String latStr = String.valueOf(currentlat);
                    String lngStr = String.valueOf(currentLong);

                    System.out.println("This is lat" + currentlat);

                    Toast.makeText(MainActivity.this, "LatLng (" + latStr + "," + lngStr + ")", Toast.LENGTH_SHORT).show();

                    getApiData(latStr, lngStr, method, monthNo, yearNo);
                }
            }
        });
    }

    private void checkForPermission() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        } else {
            getCurrentLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == 44) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
            }
        }
    }

}