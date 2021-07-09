package com.chenjinguyen.bookcommunity.service;

import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.Response.BookResponse;
import com.chenjinguyen.bookcommunity.model.Response.BooksResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookCommunityService {
    @GET("book/")
    Call<BooksResponse> allBook();

    @GET("book/")
    Call<BookResponse> detailBook(@Query("id") int id);
}