package com.example.getapp.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("opened_date")
    @Expose
    public String openedDate;
    @SerializedName("is_open")
    @Expose
    public Boolean isOpen;
    @SerializedName("closed_date")
    @Expose
    public String closedDate;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("created")
    @Expose
    public Integer created;
    @SerializedName("closed")
    @Expose
    public Integer closed;

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", openedDate='" + openedDate + '\'' +
                ", isOpen=" + isOpen +
                ", closedDate='" + closedDate + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", closed=" + closed +
                '}';
    }
}
