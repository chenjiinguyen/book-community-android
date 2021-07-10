package com.chenjinguyen.bookcommunity.service;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.BuildConfig;
import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.adapter.BookAdapter;
import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.Response.BookResponse;
import com.chenjinguyen.bookcommunity.model.Response.BooksResponse;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiService {
    Retrofit api;
    BookCommunityService service;

    public ApiService(){
        String url = String.join("",BuildConfig.SERVER_PROTOCOL,"://",BuildConfig.SERVER_HOST,":",BuildConfig.SERVER_PORT,"/api/");
        api = new Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = api.create(BookCommunityService.class);
    }

    public void getAllBookRecycler(RecyclerView recyclerView, View v){
        service.allBook().enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response){
                ArrayList<BookModel> data = response.body().getBooks();
                BookAdapter adapter = new BookAdapter(v.getContext(),data);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e("chenjinguyen",t.getMessage());
            }
        });
    }

    public void getBookDetail(int id,View v){
        service.detailBook(id).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response){
                BookModel book = response.body().getData();

                TextView title_book = v.findViewById(R.id.title_book);
                TextView author_book = v.findViewById(R.id.author_book);
                TextView description_book = v.findViewById(R.id.description_book);
                TextView view_book = v.findViewById(R.id.view_book);
                ImageView poster_book = v.findViewById(R.id.poster_book);


                Picasso.get().load(book.getPoster()).fit().centerCrop().into(poster_book);
                title_book.setText(book.getTitle());
                author_book.setText(book.getAuthor());
                description_book.setText(book.getDescription());
                view_book.setText(book.getView()+"");
            }
            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Log.e("chenjinguyen",t.getMessage());
            }
        });
    }
}