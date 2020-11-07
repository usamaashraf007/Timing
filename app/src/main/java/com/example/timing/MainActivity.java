package com.example.timing;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timing.ParyerTimeModel.Paryertimes;
import com.example.timing.Retrofit.NamazTime;
import com.example.timing.Retrofit.RetrofitClient;

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

    TextView mFajrtv,mDuhrtv,mAsrtv,mMagribtv,mIshatv;

    String prayertimeConertor;
    String monthNo,yearNo,method;

    Retrofit retrofit;
    NamazTime namazTimeInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inItRetrofit();

        mFajrtv   = findViewById(R.id.fajrtv);
        mDuhrtv   = findViewById(R.id.duhrtv);
        mAsrtv    = findViewById(R.id.asrtv);
        mMagribtv = findViewById(R.id.magribtv);
        mIshatv   = findViewById(R.id.ishatv);

        halal_places = new Halal_Places();
        pDialog = new ProgressDialog(this);
       /* pDialog.setMessage("Loading");
        pDialog.show();*/
        Date date = new Date();
        this.monthNo = (String) DateFormat.format("MM", date);
        this.yearNo = (String) DateFormat.format("yyyy", date);
        this.method = "1";
        getApiData(halal_places.lat, halal_places.lng, this.method, this.monthNo, this.yearNo);

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
        this.namazTimeInterface.getNmazTime( str, str2, str3, str4, str5).enqueue(new Callback<Paryertimes>() {

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

}