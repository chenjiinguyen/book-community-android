package com.chenjinguyen.bookcommunity.model.Response;

import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.CommentModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommentsResponse extends Response{
    @SerializedName("data")
    @Expose
    ArrayList<CommentModel> datacomment;

    public ArrayList<CommentModel> getDatacomment() {
        return datacomment;
    }

    public void setDatacomment(ArrayList<CommentModel> datacomment) {
        this.datacomment = datacomment;
    }

    public CommentsResponse(int status, String message, boolean success, ArrayList<CommentModel> datacomment) {
        super(status, message, success);
        this.datacomment= datacomment;
    }
}
