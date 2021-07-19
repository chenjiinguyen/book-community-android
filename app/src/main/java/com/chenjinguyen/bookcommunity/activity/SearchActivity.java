package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;

public class SearchActivity extends AppCompatActivity {
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        apiService = new ApiService();

        apiService.SearchActivity(getWindow().getDecorView().getRootView());



}
}