package com.example.timing.ParyerTimeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gregorian {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("designation")
    @Expose
    private Designation_ designation;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("month")
    @Expose
    private Month_ month;
    @SerializedName("weekday")
    @Expose
    private Weekday_ weekday;
    @SerializedName("year")
    @Expose
    private String year;

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String str) {
        this.day = str;
    }

    public Weekday_ getWeekday() {
        return this.weekday;
    }

    public void setWeekday(Weekday_ weekday_) {
        this.weekday = weekday_;
    }

    public Month_ getMonth() {
        return this.month;
    }

    public void setMonth(Month_ month_) {
        this.month = month_;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String str) {
        this.year = str;
    }

    public Designation_ getDesignation() {
        return this.designation;
    }

    public void setDesignation(Designation_ designation_) {
        this.designation = designation_;
    }
}
