package com.chenjinguyen.bookcommunity.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.BuildConfig;
import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.activity.HomeActivity;
import com.chenjinguyen.bookcommunity.activity.ListofWorksPostedActivity;
import com.chenjinguyen.bookcommunity.activity.LoginActivity;
import com.chenjinguyen.bookcommunity.activity.LogoutActivity;
import com.chenjinguyen.bookcommunity.activity.PointHistoryActivity;
import com.chenjinguyen.bookcommunity.activity.RegisterActivity;
import com.chenjinguyen.bookcommunity.adapter.BookAdapter;
import com.chenjinguyen.bookcommunity.adapter.InfoAccountAdapter;
import com.chenjinguyen.bookcommunity.adapter.MyPointAdapter;
import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.InfoAccount;
import com.chenjinguyen.bookcommunity.model.PointModel;
import com.chenjinguyen.bookcommunity.model.Response.AuthResponse;
import com.chenjinguyen.bookcommunity.model.Response.BookResponse;
import com.chenjinguyen.bookcommunity.model.Response.BooksResponse;
import com.chenjinguyen.bookcommunity.model.Response.PointResponse;
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

    public ApiService() {
        String url = String.join("",BuildConfig.SERVER_PROTOCOL, "://", BuildConfig.SERVER_HOST, (BuildConfig.SERVER_PORT == "80") ? "" : ":", (BuildConfig.SERVER_PORT == "80") ? "" : BuildConfig.SERVER_PORT, "/api/");
        api = new Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = api.create(BookCommunityService.class);
    }

    public void getAllBookRecycler(RecyclerView recyclerView, View v) {
        service.allBook().enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                ArrayList<BookModel> data = response.body().getBooks();
                BookAdapter adapter = new BookAdapter(v.getContext(), data, 1);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e("chenjinguyen", t.getMessage());
            }
        });
    }

    public void getSearchBook(RecyclerView recyclerView, View v) {
        service.allBook().enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                ArrayList<BookModel> data = response.body().getBooks();
                BookAdapter adapter = new BookAdapter(v.getContext(), data, 2);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e("chenjinguyen", t.getMessage());
            }
        });
    }

    public void getBookDetail(int id, View v) {
        service.detailBook(id).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
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
                view_book.setText(book.getView() + "");
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Log.e("chenjinguyen", t.getMessage());
            }
        });
    }

    public void postSignup(String username, String email, String password, String name, String birthday, String gender, View v) {
        service.signup(username, email, password, name, birthday, gender).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                boolean success = response.body().isSuccess();
                if (success) {
                    Toast.makeText(v.getContext(), "Chào mừng  đã đến với Book Community", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    v.getContext().startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("DANGNHAP_LOI", t.getMessage());
            }
        });
    }

    public void postSignin(String username, String password, boolean remember, View v) {
        service.signin(username, password, remember).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                boolean success = response.body().isSuccess();

                if (success) {
                    UserModel user = response.body().getUser();
                    dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = dataLocal.edit();
                    editor.putString("name", user.getName());
                    editor.putString("token", response.body().getToken());
                    editor.commit();
                    Toast.makeText(v.getContext(), "Chào mừng " + user.getName() + " đã đến với Book Community", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), HomeActivity.class);
                    v.getContext().startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("DANGNHAP_LOI", t.getMessage());
            }
        });
    }

    public void ListofWorksPosted(String bearer, View v) {
        service.me(bearer).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response){
               if(response.code() != 401){
                   boolean success = response.body().isSuccess();

                   if(success) {
                       UserModel userModel = response.body().getUser();
                       TextView tvName = v.findViewById(R.id.tvName);
                       ImageView imgAvt = v.findViewById(R.id.imageView);

                       tvName.setText(userModel.getName());
                       Picasso.get().load(userModel.getAvatar()).fit().centerCrop().into(imgAvt);
                   }
                   else {
                       Toast.makeText(v.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                   }
               }

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("Not valid", t.getMessage());
            }
        });

        service.meBook(bearer).enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                boolean success = response.body().isSuccess();

                if(success) {

                   ArrayList<BookModel> bookModels = response.body().getBooks();
                   BookAdapter bookAdapter = new BookAdapter(v.getContext(), bookModels, 1);
                   RecyclerView recyclerView = v.findViewById(R.id.rclListofWorks);
                   recyclerView.setAdapter(bookAdapter);

                    recyclerView.setLayoutManager(new GridLayoutManager(v.getContext(), 2));

                }
                else {
                    Toast.makeText(v.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {

            }
        });
    }



    public void UserFragment(String bearer, View v) {
        service.me(bearer).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response){

                ArrayList<InfoAccount> InfoAccounts = new ArrayList<>();
                RecyclerView recyclerView = v.findViewById(R.id.rclinfor);


                if(response.code() != 401){
                    boolean success = response.body().isSuccess();

                    if(success) {
                        InfoAccounts.add(new InfoAccount(R.drawable.ic_baseline_account_circle_24, "Đăng xuất",LogoutActivity .class));

                        UserModel userModel = response.body().getUser();
                        TextView tvName = v.findViewById(R.id.tvName);
                        ImageView imgAvt = v.findViewById(R.id.imageView);

                        tvName.setText(userModel.getName());
                        Picasso.get().load(userModel.getAvatar()).fit().centerCrop().into(imgAvt);

                        imgAvt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(v.getContext(), ListofWorksPostedActivity.class);
                                v.getContext().startActivity(intent);
                            }
                        });
                    }
                    else {
                        Toast.makeText(v.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    InfoAccounts.add(new InfoAccount(R.drawable.ic_baseline_stars_24, "Đăng Nhập", LoginActivity.class));
                }
                InfoAccountAdapter InfoAccountAdapter = new InfoAccountAdapter(v.getContext(), InfoAccounts);
                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(InfoAccountAdapter);

            }



            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("Not valid", t.getMessage());
            }
        });
        service.meBook(bearer).enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                if(response.code() != 401) {
                    boolean success = response.body().isSuccess();
                    if(success) {
                        int book_count = response.body().getBooks().size();
                        TextView tvSLTruyen = v.findViewById(R.id.tvSLDocTruyen);
                        tvSLTruyen.setText(book_count + "");
                    }
                }
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {

            }
        });

        service.me(bearer).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.code() != 401) {
                    boolean success = response.body().isSuccess();
                    if(success) {
                        int point = response.body().getUser().getPoint();
                        TextView tvSoDiem = v.findViewById(R.id.tvSoDiem);
                        tvSoDiem.setText(point + "");

                        tvSoDiem.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                Intent intent = new Intent(v.getContext(), PointHistoryActivity.class);
                                v.getContext().startActivity(intent);
                            }
                        });

                        TextView tvDiem = v.findViewById(R.id.tvDiem);
                        tvDiem.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                Intent intent = new Intent(v.getContext(), PointHistoryActivity.class);
                                v.getContext().startActivity(intent);
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });
    }

    public void PointHistory(String bearer, View v) {
        service.me(bearer).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                boolean success = response.body().isSuccess();
                if(success) {
                    int point = response.body().getUser().getPoint();
                    TextView tvSoDiem = v.findViewById(R.id.tvDiemSo);
                    tvSoDiem.setText(point + "");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });
        service.mePoint(bearer).enqueue(new Callback<PointResponse>() {
            @Override
            public void onResponse(Call<PointResponse> call, Response<PointResponse> response) {
                boolean success = response.body().isSuccess();
                if(success) {
                    ArrayList<PointModel> pointModels = response.body().getData();
                    MyPointAdapter myPointAdapter = new MyPointAdapter(v.getContext(), pointModels);
                    RecyclerView recyclerView = v.findViewById(R.id.recyclerLSDiem);
                    recyclerView.setAdapter(myPointAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), RecyclerView.VERTICAL,false));
                }
            }

            @Override
            public void onFailure(Call<PointResponse> call, Throwable t) {

            }
        });
    }


}