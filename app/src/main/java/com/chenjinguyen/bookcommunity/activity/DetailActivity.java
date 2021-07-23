package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.adapter.TabLayoutDetailBookAdapter;
import com.chenjinguyen.bookcommunity.service.ApiService;
import com.google.android.material.tabs.TabLayout;

import at.markushi.ui.CircleButton;

public class DetailActivity extends AppCompatActivity {
//    ImageView poster_book;
//    TextView description_book;
    ViewPager viewPager;
    ApiService apiService;
    View v;
    TabLayout tabLayout;
    CircleButton btn_heart;
    SharedPreferences dataLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        v = getWindow().getDecorView().getRootView();
        Intent itent = getIntent();
        int id = itent.getIntExtra("id",1);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Tóm tắt"));
        tabLayout.addTab(tabLayout.newTab().setText("Bình luận"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        btn_heart=findViewById(R.id.heart_button);
        dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        String token = dataLocal.getString("token","");
        final TabLayoutDetailBookAdapter adapter = new TabLayoutDetailBookAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,DetailActivity.this,tabLayout.getTabCount(),id);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        apiService = new ApiService();
        apiService.DetailActivity(token,id,v);


        RelativeLayout back_button = findViewById(R.id.back_button);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
            btn_heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    apiService.PostFavorite(token, id,v);
                }
            });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(v.getContext(), HomeActivity.class);
                t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                v.getContext().startActivity(t);
                return;
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent t = new Intent(v.getContext(), HomeActivity.class);
        t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        v.getContext().startActivity(t);
        return;
    }
}