package com.chenjinguyen.bookcommunity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentModel {
    @SerializedName("idcomment")
    @Expose
    private Integer idcomment;
    @SerializedName("iduser")
    @Expose
    private String iduser;
    @SerializedName("idbook")
    @Expose
    private String idbook;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avartar;

    public CommentModel(Integer idcomment, String iduser, String idbook, String content, String updatedAt, String createdAt, String name, String avartar) {
        this.idcomment = idcomment;
        this.iduser = iduser;
        this.idbook = idbook;
        this.content = content;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.name = name;
        this.avartar = avartar;
    }

    public Integer getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(Integer idcomment) {
        this.idcomment = idcomment;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdbook() {
        return idbook;
    }

    public void setIdbook(String idbook) {
        this.idbook = idbook;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }
}
