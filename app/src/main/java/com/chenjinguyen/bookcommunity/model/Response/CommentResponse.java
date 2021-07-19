package com.chenjinguyen.bookcommunity.model.Response;

import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.CommentModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentResponse extends Response{

    @SerializedName("datacomment")
    @Expose
    CommentModel datacomment;

    public CommentModel getDatacomment() {
        return datacomment;
    }

    public void setDatacomment(CommentModel datacomment) {
        this.datacomment = datacomment;
    }

    public CommentResponse(int status, String message, boolean success, CommentModel datacomment) {
        super(status, message, success);
        this.datacomment = datacomment;
    }
}
