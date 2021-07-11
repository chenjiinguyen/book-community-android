package com.chenjinguyen.bookcommunity.model.Response;

import com.chenjinguyen.bookcommunity.model.BookModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BooksResponse extends Response {
    @SerializedName("data")
    @Expose
    ArrayList<BookModel> data;

    public BooksResponse(int status, String message, boolean success, ArrayList<BookModel> data) {
        super(status, message, success);
        this.data = data;
    }

    public ArrayList<BookModel> getBooks() {
        return data;
    }

    public void setBooks(ArrayList<BookModel> books) {
        this.data = books;
    }
}
