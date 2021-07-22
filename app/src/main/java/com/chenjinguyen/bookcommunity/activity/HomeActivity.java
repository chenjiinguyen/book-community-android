package com.chenjinguyen.bookcommunity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.fragment.CategoryFragment;
import com.chenjinguyen.bookcommunity.fragment.FavoriteFragment;
import com.chenjinguyen.bookcommunity.fragment.HomeFragment;
import com.chenjinguyen.bookcommunity.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    Fragment fragment;
    BottomNavigationView bottomNavigationView;
    TextView tvDiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        loadFragment(new HomeFragment());

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_home);
        tvDiem = findViewById(R.id.tvDiem);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragment = new HomeFragment();
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_item_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_item_category:
                        fragment = new CategoryFragment();
                        break;
                    case R.id.nav_item_account:
                        fragment = new UserFragment();
                        break;
                    case R.id.nav_item_favorite:
                        fragment = new FavoriteFragment();
                        break;
                }
                loadFragment(fragment);
                return true;
            }
        });

    }

    public void loadFragment(Fragment frm){
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.frame_home,frm);
        trans.commit();
    }
}