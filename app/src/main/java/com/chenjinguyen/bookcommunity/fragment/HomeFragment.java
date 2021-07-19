package com.chenjinguyen.bookcommunity.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.chenjinguyen.bookcommunity.activity.DetailActivity;
import com.chenjinguyen.bookcommunity.activity.SearchActivity;
import com.chenjinguyen.bookcommunity.adapter.BookAdapter;
import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.service.ApiService;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {
    CircleButton search_button;
    ApiService apiService;
    SharedPreferences dataLocal;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        apiService = new ApiService();

        dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        TextView wellcome_name = v.findViewById(R.id.wellcome_name);
        wellcome_name.setText("Chào "+ dataLocal.getString("name", "Bạn"));

        apiService.HomeFragment(v);

        search_button = v.findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(v.getContext(), SearchActivity.class);
                v.getContext().startActivity(t);
            }
        });

        return v;
    }


}
