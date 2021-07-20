package com.chenjinguyen.bookcommunity.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.BuildConfig;
import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.activity.EpisodeActivity;
import com.chenjinguyen.bookcommunity.activity.HomeActivity;
import com.chenjinguyen.bookcommunity.activity.ListofWorksPostedActivity;
import com.chenjinguyen.bookcommunity.activity.LoginActivity;
import com.chenjinguyen.bookcommunity.activity.LogoutActivity;
import com.chenjinguyen.bookcommunity.activity.PersionalInfoActivity;
import com.chenjinguyen.bookcommunity.activity.PointHistoryActivity;
import com.chenjinguyen.bookcommunity.activity.RegisterActivity;
import com.chenjinguyen.bookcommunity.adapter.BookAdapter;
import com.chenjinguyen.bookcommunity.adapter.CommentAdapter;
import com.chenjinguyen.bookcommunity.adapter.InfoAccountAdapter;
import com.chenjinguyen.bookcommunity.adapter.MyPointAdapter;
import com.chenjinguyen.bookcommunity.dialog.BottomEpisodeDialog;
import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.CommentModel;
import com.chenjinguyen.bookcommunity.model.EpisodeModel;
import com.chenjinguyen.bookcommunity.model.InfoAccount;
import com.chenjinguyen.bookcommunity.model.PointModel;
import com.chenjinguyen.bookcommunity.model.Response.AuthResponse;
import com.chenjinguyen.bookcommunity.model.Response.BookResponse;
import com.chenjinguyen.bookcommunity.model.Response.BooksResponse;
import com.chenjinguyen.bookcommunity.model.Response.CommentsResponse;
import com.chenjinguyen.bookcommunity.model.Response.DeviceResponse;
import com.chenjinguyen.bookcommunity.model.Response.EpisodeReponse;
import com.chenjinguyen.bookcommunity.model.Response.EpisodesReponse;
import com.chenjinguyen.bookcommunity.model.Response.PointResponse;
import com.chenjinguyen.bookcommunity.model.UserModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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

    public void HomeFragment(View v) {
        service.allBook().enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                ArrayList<BookModel> data = response.body().getBooks();
                RecyclerView trendingRecycler = v.findViewById(R.id.category_trending_list);
                BookAdapter adapter = new BookAdapter(v.getContext(), data, 1);
                trendingRecycler.setAdapter(adapter);
                trendingRecycler.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e("chenjinguyen", t.getMessage());
            }
        });

        service.getBookCategory("TEXT").enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                ArrayList<BookModel> data = response.body().getBooks();
                RecyclerView textbookRecycler = v.findViewById(R.id.category_textbook_list);
                BookAdapter adapter = new BookAdapter(v.getContext(), data, 1);
                textbookRecycler.setAdapter(adapter);
                textbookRecycler.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e("chenjinguyen", t.getMessage());
            }
        });

        service.getBookCategory("IMAGE").enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                RecyclerView imageRecycler = v.findViewById(R.id.category_imagebook_list);
                ArrayList<BookModel> data = response.body().getBooks();
                BookAdapter adapter = new BookAdapter(v.getContext(), data, 1);
                imageRecycler.setAdapter(adapter);
                imageRecycler.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));

            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e("chenjinguyen", t.getMessage());
            }
        });

        service.getBookCategory("AUDIO").enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                RecyclerView audioRecycler = v.findViewById(R.id.category_audiobook_list);
                ArrayList<BookModel> data = response.body().getBooks();
                BookAdapter adapter = new BookAdapter(v.getContext(), data, 1);
                audioRecycler.setAdapter(adapter);
                audioRecycler.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));

            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e("chenjinguyen", t.getMessage());
            }
        });
    }

    public void SearchActivity(View v) {
        SearchView searchView= v.findViewById(R.id.searchtruyen);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                service.searchBook(newText).enqueue(new Callback<BooksResponse>() {
                    @Override
                    public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {

                        RecyclerView recyclerView= v.findViewById(R.id.rcls);
                        ArrayList<BookModel> data = response.body().getBooks();
                        BookAdapter adapter = new BookAdapter(v.getContext(), data, 2);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));
                    }

                    @Override
                    public void onFailure(Call<BooksResponse> call, Throwable t) {
                        Log.e("chenjinguyen", t.getMessage());
                    }
                });
                return false;
            }
        });

    }

    public void DetailActivity(int id, View v) {
        service.detailBook(id).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                BookModel book = response.body().getData();

                TextView title_book = v.findViewById(R.id.title_book);
                TextView author_book = v.findViewById(R.id.author_book);
                TextView description_book = v.findViewById(R.id.description_book);
                TextView episode_count_book = v.findViewById(R.id.episode_count_book);
                TextView category_book = v.findViewById(R.id.category_book);
                TextView view_book = v.findViewById(R.id.view_book);
                ImageView poster_book = v.findViewById(R.id.poster_book);

                Picasso.get().load(book.getPoster()).fit().centerCrop().into(poster_book);
                title_book.setText(book.getTitle());
                author_book.setText(book.getAuthor());
                description_book.setText(book.getDescription());
                view_book.setText(book.getView() + "");

                String category;
                switch (book.getCategory()){
                    case "TEXT":
                        category = "Truyện Chữ";
                        break;
                    case "IMAGE":
                        category = "Truyện Tranh";
                        break;
                    case "AUDIO":
                        category = "Truyện Audio";
                        break;
                    default:
                        category = "Truyện";
                        break;
                }
                category_book.setText(category);


                service.getEpisodeOfBook(id).enqueue(new Callback<EpisodesReponse>() {
                    @Override
                    public void onResponse(Call<EpisodesReponse> call, Response<EpisodesReponse> response) {
                        EpisodeModel episode = response.body().getData().get(0);
                        RelativeLayout read_first_button = v.findViewById(R.id.read_first_button_book);
                        TextView read_first_text = v.findViewById(R.id.read_first_text_book);
                        Log.e("LOIIIII",Integer.toString(response.body().getData().size()));
                        episode_count_book.setText(Integer.toString(response.body().getData().size()));
                        read_first_text.setText(("Đọc " + episode.getName()).toUpperCase());

                        read_first_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent t = new Intent(v.getContext(), EpisodeActivity.class);
                                t.putExtra("episode",episode);
                                t.putExtra("book",book);
                                v.getContext().startActivity(t);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<EpisodesReponse> call, Throwable t) {

                    }
                });

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
                       //ImageView imgEdit = v.findViewById(R.id.imgEdit);

                       tvName.setText(userModel.getName());
                       Picasso.get().load(userModel.getAvatar()).fit().centerCrop().into(imgAvt);

//                       imgEdit.setOnClickListener(new View.OnClickListener() {
//                           @Override
//                           public void onClick(View v) {
//                               Intent t = new Intent(v.getContext(), PersionalInfoActivity.class);
////                               t.putExtra("episode",episode);
////                               t.putExtra("book",book);
//                               v.getContext().startActivity(t);
//                           }
//                       });
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
                        InfoAccounts.add(new InfoAccount(R.drawable.ic_baseline_drive_file_rename_outline_24, "Chỉnh sửa thông tin cá nhân",PersionalInfoActivity .class));

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

    public void CategoryFragment(View v){
        service.getBookCategory("TEXT").enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                ArrayList<BookModel> books = response.body().getBooks();
                RecyclerView recyclerView = v.findViewById(R.id.rv_category);
                BookAdapter bookAdapter = new BookAdapter(v.getContext(),books,2);
                recyclerView.setAdapter(bookAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {

            }
        });
    }

    public void setCategoryData(RecyclerView recyclerView, String category, View v){
        service.getBookCategory(category).enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                ArrayList<BookModel> books = response.body().getBooks();
                BookAdapter bookAdapter = new BookAdapter(v.getContext(),books,2);

                recyclerView.setAdapter(bookAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {

            }
        });
    }

    public void EpisodeTextFragment(String bearer, int id, View v){
        service.getEpisode(id).enqueue(new Callback<EpisodeReponse>() {
            @Override
            public void onResponse(Call<EpisodeReponse> call, Response<EpisodeReponse> response) {
                EpisodeModel episode = response.body().getData();


            }

            @Override
            public void onFailure(Call<EpisodeReponse> call, Throwable t) {

            }
        });


    }
    public void EpisodeActivity(FragmentManager fm,EpisodeModel episode,BookModel book,View v){
        service.getEpisodeOfBook(book.getId()).enqueue(new Callback<EpisodesReponse>() {
            @Override
            public void onResponse(Call<EpisodesReponse> call, Response<EpisodesReponse> response) {
                ArrayList<EpisodeModel> result = response.body().getData();
                RelativeLayout back_button = v.findViewById(R.id.back_button);
                RelativeLayout next_button = v.findViewById(R.id.next_button);
                ImageView back_button_icon = v.findViewById(R.id.back_button_icon);
                ImageView next_button_icon = v.findViewById(R.id.next_button_icon);
                RelativeLayout layout_episode_text = v.findViewById(R.id.layout_episode_text);
                layout_episode_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        BottomEpisodeDialog bottomEpisodeDialog = new BottomEpisodeDialog(result,episode,book);
                        bottomEpisodeDialog.show(fm,"TAG");


                    }
                });
                int index = IntStream.range(0, result.size()) .filter(i -> result.get(i).getEpisode_id() == episode.getEpisode_id()).findFirst().orElse(-1);
                if(index > 0){

                    back_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent t = new Intent(v.getContext(), EpisodeActivity.class);
                            t.putExtra("episode",result.get(index-1));
                            t.putExtra("book",book);
                            t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            v.getContext().startActivity(t);
                        }
                    });
                }else{
                    back_button_icon.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24);
                }
                if( index < result.size()-1){

                    next_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent t = new Intent(v.getContext(), EpisodeActivity.class);
                            t.putExtra("episode",result.get(index+1));
                            t.putExtra("book",book);
                            t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            v.getContext().startActivity(t);
                        }
                    });
                }else{
                    next_button_icon.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24);
                }

            }

            @Override
            public void onFailure(Call<EpisodesReponse> call, Throwable t) {

            }
        });

    }

    public void EpisodeFragmentText(String bearer, int idEpdi, View v) {
        ScrollView scrollEpiText = v.findViewById(R.id.scrollEpiText);
        scrollEpiText.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                View view = (View) scrollEpiText.getChildAt(scrollEpiText.getChildCount() - 1);
                int diff = (view.getBottom() - (scrollEpiText.getHeight() + scrollEpiText.getScrollY()));

                // if diff is zero, then the bottom has been reached
                if (diff == 0) {
                    // do stuff
                    service.mePointCreate(bearer, idEpdi, true, 5).enqueue(new Callback<PointResponse>() {
                        @Override
                        public void onResponse(Call<PointResponse> call, Response<PointResponse> response) {

                        }

                        @Override
                        public void onFailure(Call<PointResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    public void EpisodeFragmentImage(String bearer, int idEpi, View v) {
        RecyclerView recyclerView = v.findViewById(R.id.episode_image_content);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    service.mePointCreate(bearer, idEpi, true, 5).enqueue(new Callback<PointResponse>() {
                        @Override
                        public void onResponse(Call<PointResponse> call, Response<PointResponse> response) {

                        }

                        @Override
                        public void onFailure(Call<PointResponse> call, Throwable t) {

                        }
                    });

                }
            }
        });
    }

    public void ChangeNameDialog(String bearer, String name, View v) {
        service.upDateName(bearer, name).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                boolean success = response.body().isSuccess();

                if(success) {
                    UserModel user = response.body().getUser();
                    dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = dataLocal.edit();
                    editor.putString("name", user.getName());
                    editor.commit();
                }

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });
    }

    public void PersionalInfoActivity(String bearer, View v) {
        service.me(bearer).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                TextView tvName = v.findViewById(R.id.tvUserName);
                TextView tvBirth = v.findViewById(R.id.tvBirth);
                TextView tvGender = v.findViewById(R.id.tvGender);
                TextView tvMail = v.findViewById(R.id.tvMail);
                ImageView imgAvatar = v.findViewById(R.id.imageView);


                UserModel userModel = response.body().getUser();
                tvName.setText(userModel.getName());

                tvGender.setText(userModel.getGender());
                tvMail.setText(userModel.getEmail());
                Picasso.get().load(userModel.getAvatar()).fit().centerCrop().into(imgAvatar);

                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(userModel.getBirthday());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
                tvBirth.setText(formattedDate);
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });
    }
    public void setCommentData(String bearer,int id,View v){
        service.getCommentBook(id).enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {


                    boolean success = response.body().isSuccess();
                    if(success) {

                        ArrayList<CommentModel> comments = response.body().getDatacomment();
                        CommentAdapter commentAdapter = new CommentAdapter(v.getContext(),comments);
                        RecyclerView recyclerView= v.findViewById(R.id.rv_comment);
                        recyclerView.setAdapter(commentAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));
                        TextView txt_username=  v.findViewById(R.id.txt_username);
                        TextView txt_content=  v.findViewById(R.id.txt_content);
                        TextView txt_datecomment=  v.findViewById(R.id.txt_datecomment);
                        ImageView img_user=v.findViewById(R.id.imguser);



                    }
            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {

            }
        });

        }

    public  void importToken(String token){
        service.createDevice(token).enqueue(new Callback<DeviceResponse>() {
            @Override
            public void onResponse(Call<DeviceResponse> call, Response<DeviceResponse> response) {

            }

            @Override
            public void onFailure(Call<DeviceResponse> call, Throwable t) {

            }
        });
    }


}