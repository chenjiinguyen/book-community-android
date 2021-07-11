package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;

public class SearchActivity extends AppCompatActivity {
    ApiService apiService;
    RecyclerView rcls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        apiService = new ApiService();

        rcls = findViewById(R.id.rcls);

        apiService.getSearchBook(rcls,getWindow().getDecorView().getRootView());
    }
}