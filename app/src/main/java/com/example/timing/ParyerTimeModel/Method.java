package com.example.timing.ParyerTimeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Method {
    @SerializedName("id")
    @Expose

    /* renamed from: id */
    private Integer f55id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("params")
    @Expose
    private Params params;

    public Integer getId() {
        return this.f55id;
    }

    public void setId(Integer num) {
        this.f55id = num;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Params getParams() {
        return this.params;
    }

    public void setParams(Params params2) {
        this.params = params2;
    }
}
