package com.example.timing.ParyerTimeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Month_ {
    @SerializedName("en")
    @Expose

    /* renamed from: en */
    private String f58en;
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
        return this.f58en;
    }

    public void setEn(String str) {
        this.f58en = str;
    }
}
