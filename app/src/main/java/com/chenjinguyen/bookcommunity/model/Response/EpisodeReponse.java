package com.chenjinguyen.bookcommunity.model.Response;

import com.chenjinguyen.bookcommunity.model.EpisodeModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EpisodeReponse extends Response {
    @SerializedName("data")
    @Expose
    EpisodeModel data;

    public EpisodeReponse(int status, String message, boolean success, EpisodeModel data) {
        super(status, message, success);
        this.data = data;
    }

    public EpisodeModel getData() {
        return data;
    }

    public void setData(EpisodeModel data) {
        this.data = data;
    }
}
