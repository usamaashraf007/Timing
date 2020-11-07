package com.example.timing.ParyerTimeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Designation_ {
    @SerializedName("abbreviated")
    @Expose
    private String abbreviated;
    @SerializedName("expanded")
    @Expose
    private String expanded;

    public String getAbbreviated() {
        return this.abbreviated;
    }

    public void setAbbreviated(String str) {
        this.abbreviated = str;
    }

    public String getExpanded() {
        return this.expanded;
    }

    public void setExpanded(String str) {
        this.expanded = str;
    }
}
