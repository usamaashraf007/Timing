package com.example.timing.ParyerTimeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Month {
    @SerializedName("ar")
    @Expose

    /* renamed from: ar */
    private String f56ar;
    @SerializedName("en")
    @Expose

    /* renamed from: en */
    private String f57en;
    @SerializedName("number")
    @Expose
    private Integer number;

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer num) {
        this.number = num;
    }

    public String getEn() {
        return this.f57en;
    }

    public void setEn(String str) {
        this.f57en = str;
    }

    public String getAr() {
        return this.f56ar;
    }

    public void setAr(String str) {
        this.f56ar = str;
    }
}
