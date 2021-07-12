package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.chenjinguyen.bookcommunity.R;

public class LogoutActivity extends AppCompatActivity {

    SharedPreferences dataLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        dataLocal = getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        dataLocal.edit().remove("token").commit();

        dataLocal.edit().remove("name").commit();

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}