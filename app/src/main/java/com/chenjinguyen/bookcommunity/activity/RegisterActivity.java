package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.chenjinguyen.bookcommunity.R;

public class RegisterActivity extends AppCompatActivity {

    Toolbar toolbarregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Anhxa();
        ActionToolbar();
    }

    private void Anhxa() {
        toolbarregister= findViewById(R.id.toolbar);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarregister);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarregister.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}