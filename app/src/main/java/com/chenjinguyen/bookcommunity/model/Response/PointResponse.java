package com.chenjinguyen.bookcommunity.model.Response;


import com.chenjinguyen.bookcommunity.model.PointModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PointResponse extends Response {
    @SerializedName("data")
    @Expose
    ArrayList<PointModel> data;


    public ArrayList<PointModel> getData() {
        return data;
    }

    public void setData(ArrayList<PointModel> data) {
        this.data = data;
    }

    public PointResponse(int status, String message, boolean success, ArrayList<PointModel> data) {
        super(status, message, success);
        this.data = data;
    }
}
