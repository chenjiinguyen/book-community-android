package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;

public class DetailActivity extends AppCompatActivity {
//    ImageView poster_book;
//    TextView description_book;
    ApiService apiService;
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        v = getWindow().getDecorView().getRootView();
        Intent itent = getIntent();
        int id = itent.getIntExtra("id",1);

        apiService = new ApiService();
        apiService.DetailActivity(id,getWindow().getDecorView());
        RelativeLayout back_button = findViewById(R.id.back_button);


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