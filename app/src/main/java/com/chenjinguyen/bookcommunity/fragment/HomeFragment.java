package com.chenjinguyen.bookcommunity.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.adapter.BookAdapter;
import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.service.ApiService;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class HomeFragment extends Fragment {
    RecyclerView trendingRecycler,textbookRecycler;
    BookAdapter trendingAdapter;
    ArrayList<BookModel> trendingBooks;
    ApiService apiService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        apiService = new ApiService();

        trendingRecycler = v.findViewById(R.id.category_trending_list);
        textbookRecycler = v.findViewById(R.id.category_textbook_list);
        apiService.getAllBookRecycler(textbookRecycler,v);
        apiService.getAllBookRecycler(trendingRecycler,v);

        textbookRecycler.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));
        trendingRecycler.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));

        return v;
    }

    public void getApi(View v){

    }
}
