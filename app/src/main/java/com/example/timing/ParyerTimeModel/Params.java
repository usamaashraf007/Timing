package com.example.timing.ParyerTimeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params {
    @SerializedName("Fajr")
    @Expose
    private Integer fajr;
    @SerializedName("Isha")
    @Expose
    private Integer isha;

    public Integer getFajr() {
        return this.fajr;
    }

    public void setFajr(Integer num) {
        this.fajr = num;
    }

    public Integer getIsha() {
        return this.isha;
    }

    public void setIsha(Integer num) {
        this.isha = num;
    }
}
