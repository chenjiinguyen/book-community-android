package com.chenjinguyen.bookcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.chenjinguyen.bookcommunity.activity.CommentActivity;
import com.chenjinguyen.bookcommunity.activity.HomeActivity;
import com.chenjinguyen.bookcommunity.fragment.HomeFragment;
import com.chenjinguyen.bookcommunity.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.chenjinguyen.bookcommunity.service.ApiService;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent t = new Intent(this, CommentActivity.class);
        t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(t);



    }

}