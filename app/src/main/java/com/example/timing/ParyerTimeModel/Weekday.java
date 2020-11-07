package com.example.timing.ParyerTimeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weekday {
    @SerializedName("ar")
    @Expose

    /* renamed from: ar */
    private String f59ar;
    @SerializedName("en")
    @Expose

    /* renamed from: en */
    private String f60en;

    public String getEn() {
        return this.f60en;
    }

    public void setEn(String str) {
        this.f60en = str;
    }

    public String getAr() {
        return this.f59ar;
    }

    public void setAr(String str) {
        this.f59ar = str;
    }
}
