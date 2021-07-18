package com.chenjinguyen.bookcommunity.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.activity.SearchActivity;
import com.chenjinguyen.bookcommunity.adapter.CategoryTitleAdater;
import com.chenjinguyen.bookcommunity.model.CategoryTitleModel;
import com.chenjinguyen.bookcommunity.service.ApiService;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;

public class CategoryFragment extends Fragment {
    CircleButton search_button;
    ApiService apiService;
    SharedPreferences dataLocal;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category,container,false);
        apiService = new ApiService();
        apiService.CategoryFragment(v);
        RecyclerView rv_category = v.findViewById(R.id.rv_category);
        ArrayList<CategoryTitleModel> category_titles = new ArrayList<>();

        category_titles.add(new CategoryTitleModel("TEXT","Truyện Chữ"));
        category_titles.add(new CategoryTitleModel("IMAGE","Truyện Tranh"));
        category_titles.add(new CategoryTitleModel("AUDIO","Truyện Audio"));
        RecyclerView rv_category_title = v.findViewById(R.id.rv_category_title);
        CategoryTitleAdater categoryTitleAdater = new CategoryTitleAdater(v.getContext(),category_titles,rv_category);
        rv_category_title.setAdapter(categoryTitleAdater);
        rv_category_title.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));


        return v;
    }


}
