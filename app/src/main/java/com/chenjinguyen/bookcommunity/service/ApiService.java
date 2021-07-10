package com.chenjinguyen.bookcommunity.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.BuildConfig;
import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.activity.HomeActivity;
import com.chenjinguyen.bookcommunity.activity.LoginActivity;
import com.chenjinguyen.bookcommunity.activity.RegisterActivity;
import com.chenjinguyen.bookcommunity.adapter.BookAdapter;
import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.Response.AuthResponse;
import com.chenjinguyen.bookcommunity.model.Response.BookResponse;
import com.chenjinguyen.bookcommunity.model.Response.BooksResponse;
import com.chenjinguyen.bookcommunity.model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiService {
    Retrofit api;
    BookCommunityService service;
    SharedPreferences dataLocal;

    public ApiService(){
        String url = String.join("",BuildConfig.SERVER_PROTOCOL,"://",BuildConfig.SERVER_HOST,(BuildConfig.SERVER_PORT=="80")?"":":",(BuildConfig.SERVER_PORT=="80")?"":BuildConfig.SERVER_PORT,"/api/");
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
                BookAdapter adapter = new BookAdapter(v.getContext(),data,1);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e("chenjinguyen",t.getMessage());
            }
        });
    }

    public void getSearchBook(RecyclerView recyclerView, View v){
        service.allBook().enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response){
                ArrayList<BookModel> data = response.body().getBooks();
                BookAdapter adapter = new BookAdapter(v.getContext(),data,2);
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
public  void postSignup(String username,String email,  String password, String name, String birthday, String gender, View v){
service.signup(username,email,password,name,birthday,gender).enqueue(new Callback<AuthResponse>() {
    @Override
    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
        boolean success=response.body().isSuccess();
        if(success){
            Toast.makeText(v.getContext(),"Chào mừng  đã đến với Book Community", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            v.getContext().startActivity(intent);
        }else {
            Toast.makeText(v.getContext(),response.body().getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<AuthResponse> call, Throwable t) {
        Log.e("DANGNHAP_LOI",t.getMessage());
    }
});
}
    public void postSignin(String username,String password,boolean remember, View v){
        service.signin(username,password,remember).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                boolean success = response.body().isSuccess();

                if(success){
                    UserModel user = response.body().getUser();
                    dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = dataLocal.edit();
                    editor.putString("name", user.getName());
                    editor.putString("token", response.body().getToken());
                    editor.commit();
                    Toast.makeText(v.getContext(),"Chào mừng "+user.getName()+" đã đến với Book Community", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), HomeActivity.class);
                    v.getContext().startActivity(intent);
                }else{
                    Toast.makeText(v.getContext(),response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("DANGNHAP_LOI",t.getMessage());
            }
        });
    }
}