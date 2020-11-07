package com.example.timing.ParyerTimeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hijri {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("designation")
    @Expose
    private Designation designation;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("holidays")
    @Expose
    private List<Object> holidays = null;
    @SerializedName("month")
    @Expose
    private Month month;
    @SerializedName("weekday")
    @Expose
    private Weekday weekday;
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

    public Weekday getWeekday() {
        return this.weekday;
    }

    public void setWeekday(Weekday weekday2) {
        this.weekday = weekday2;
    }

    public Month getMonth() {
        return this.month;
    }

    public void setMonth(Month month2) {
        this.month = month2;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String str) {
        this.year = str;
    }

    public Designation getDesignation() {
        return this.designation;
    }

    public void setDesignation(Designation designation2) {
        this.designation = designation2;
    }

    public List<Object> getHolidays() {
        return this.holidays;
    }

    public void setHolidays(List<Object> list) {
        this.holidays = list;
    }
}
