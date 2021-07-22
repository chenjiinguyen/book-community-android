package com.chenjinguyen.bookcommunity.model.Response;

import com.chenjinguyen.bookcommunity.model.BookModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeResponse extends Response{
    @SerializedName("like")
    @Expose
    boolean data;

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public LikeResponse(int status, String message, boolean success, boolean data) {
        super(status, message, success);
        this.data = data;
    }


}
