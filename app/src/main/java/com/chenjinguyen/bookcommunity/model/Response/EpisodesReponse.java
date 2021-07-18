package com.chenjinguyen.bookcommunity.model.Response;

import com.chenjinguyen.bookcommunity.model.EpisodeModel;
import com.chenjinguyen.bookcommunity.model.PointModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EpisodesReponse extends Response {
    @SerializedName("data")
    @Expose
    ArrayList<EpisodeModel> data;

    public EpisodesReponse(int status, String message, boolean success, ArrayList<EpisodeModel> data) {
        super(status, message, success);
        this.data = data;
    }

    public ArrayList<EpisodeModel> getData() {
        return data;
    }

    public void setData(ArrayList<EpisodeModel> data) {
        this.data = data;
    }
}
