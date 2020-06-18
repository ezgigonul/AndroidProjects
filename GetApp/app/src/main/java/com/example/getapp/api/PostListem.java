package com.example.getapp.api;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class PostListem {
    @SerializedName("is_open")
    @Expose
    private Boolean isOpen;
    @SerializedName("closed_date")
    @Expose
    private String closedDate;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("closed")
    @Expose
    private Integer closed;

    public PostListem(Boolean isOpen, String closedDate, Integer created, Integer closed) {
        this.isOpen = isOpen;
        this.closedDate = closedDate;
        this.created = created;
        this.closed = closed;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }
}
