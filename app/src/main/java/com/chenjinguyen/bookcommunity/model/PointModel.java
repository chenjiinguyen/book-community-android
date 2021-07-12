package com.chenjinguyen.bookcommunity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class PointModel implements Serializable {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("charge")
    @Expose
    private  boolean charge;
    @SerializedName("point")
    @Expose
    private int point;
    @SerializedName("createdAt")
    @Expose
    private Date createdAt;
    @SerializedName("updatedAt")
    @Expose
    private Date updatedAt;
    @SerializedName("episode")
    @Expose
    private String episode;
    @SerializedName("book")
    @Expose
    private String book;


    public PointModel(String username, boolean charge, int point, Date createdAt, Date updatedAt, String episode, String book) {
        this.username = username;
        this.charge = charge;
        this.point = point;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.episode = episode;
        this.book = book;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
