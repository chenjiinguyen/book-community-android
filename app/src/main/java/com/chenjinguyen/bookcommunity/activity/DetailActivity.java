package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
//    ImageView poster_book;
//    TextView description_book;
    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent itent = getIntent();
        int id = itent.getIntExtra("id",1);


        apiService = new ApiService();
        apiService.getBookDetail(id,getWindow().getDecorView());
        RelativeLayout back_button = findViewById(R.id.back_button);
        RelativeLayout read_first_button = findViewById(R.id.read_first_button_book);

        read_first_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(v.getContext(), EpisodeTextActivity.class);
                startActivity(t);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.this.finish();
            }
        });


    }
}