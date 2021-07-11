package com.chenjinguyen.bookcommunity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookModel {
    @SerializedName("idbook")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("uploader")
    @Expose
    private String uploader;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("view")
    @Expose
    private Integer view;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("like")
    @Expose
    private Integer like;

    public BookModel(Integer id, String title, String author, String uploader, String category, String poster, String description, Integer view, String createdAt, String updatedAt, Integer like) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.uploader = uploader;
        this.category = category;
        this.poster = poster;
        this.description = description;
        this.view = view;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.like = like;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }
}

