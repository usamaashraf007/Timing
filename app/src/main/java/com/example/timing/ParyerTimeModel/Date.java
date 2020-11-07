package com.example.timing.ParyerTimeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date {
    @SerializedName("gregorian")
    @Expose
    private Gregorian gregorian;
    @SerializedName("hijri")
    @Expose
    private Hijri hijri;
    @SerializedName("readable")
    @Expose
    private String readable;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public String getReadable() {
        return this.readable;
    }

    public void setReadable(String str) {
        this.readable = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public Hijri getHijri() {
        return this.hijri;
    }

    public void setHijri(Hijri hijri2) {
        this.hijri = hijri2;
    }

    public Gregorian getGregorian() {
        return this.gregorian;
    }

    public void setGregorian(Gregorian gregorian2) {
        this.gregorian = gregorian2;
    }
}
