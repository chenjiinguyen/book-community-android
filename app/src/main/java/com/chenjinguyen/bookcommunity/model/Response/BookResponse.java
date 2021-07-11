package com.chenjinguyen.bookcommunity.model.Response;

import com.chenjinguyen.bookcommunity.model.BookModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookResponse extends Response {

    @SerializedName("data")
    @Expose
    BookModel data;

    public BookResponse(int status, String message, boolean success, BookModel data) {
        super(status, message, success);
        this.data = data;
    }

    public BookModel getData() {
        return data;
    }

    public void setData(BookModel data) {
        this.data = data;
    }
}
