package com.example.timing.Retrofit;


import com.example.timing.ParyerTimeModel.Paryertimes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NamazTime {
    @GET("timings")
    Call<Paryertimes> getNmazTime(@Query("latitude") String str, @Query("longitude") String str2, @Query("method") String str3, @Query("month") String str4, @Query("year") String str5);
}
