package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;
import com.mikhaellopez.circularimageview.CircularImageView;

import pl.droidsonroids.gif.GifImageView;

public class ListofWorksPostedActivity extends AppCompatActivity {

    ApiService apiService;
    SharedPreferences dataLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_works_posted);

        apiService = new ApiService();
        View v = getWindow().getDecorView().getRootView();
        dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        String token = dataLocal.getString("token","");
        apiService.ListofWorksPosted(token,v);

        CircularImageView image = findViewById(R.id.imageView);
        image.setZ(10);
    }

}