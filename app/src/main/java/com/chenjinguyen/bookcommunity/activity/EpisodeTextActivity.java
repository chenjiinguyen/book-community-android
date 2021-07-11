package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.dialog.BottomEpisodeDialog;

public class EpisodeTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_text);

        RelativeLayout layout_episode_text = findViewById(R.id.layout_episode_text);

        layout_episode_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomEpisodeDialog bottomEpisodeDialog = new BottomEpisodeDialog();
                bottomEpisodeDialog.show(getSupportFragmentManager(),"TAG");
            }
        });
    }
}