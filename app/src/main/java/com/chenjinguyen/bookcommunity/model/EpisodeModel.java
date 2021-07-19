package com.chenjinguyen.bookcommunity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class EpisodeModel implements Serializable {
    @SerializedName("idepisode")
    @Expose
    int episode_id;
    @SerializedName("idbook")
    @Expose
    int book_id;
    @SerializedName("index")
    @Expose
    int index;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("content")
    @Expose
    ArrayList<String> content;
    @SerializedName("view")
    @Expose
    int view;
    @SerializedName("createdAt")
    @Expose
    Date created_at;
    @SerializedName("updatedAt")
    @Expose
    Date updated_at;

    public EpisodeModel(int episode_id, int book_id, int index, String name, ArrayList<String> content, int view, Date created_at, Date updated_at) {
        this.episode_id = episode_id;
        this.book_id = book_id;
        this.index = index;
        this.name = name;
        this.content = content;
        this.view = view;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(int episode_id) {
        this.episode_id = episode_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
