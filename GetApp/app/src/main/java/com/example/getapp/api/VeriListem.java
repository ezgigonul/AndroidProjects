package com.example.getapp.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VeriListem {
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("next")
    @Expose
    public String next;
    @SerializedName("previous")
    @Expose
    public Object previous;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;

    public List<Result> getInfo(){
        return results;
    }

}

