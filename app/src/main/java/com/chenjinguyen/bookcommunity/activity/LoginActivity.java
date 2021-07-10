package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.Toolbar;

import com.chenjinguyen.bookcommunity.R;

public class LoginActivity extends AppCompatActivity {

    Button btnregister;

   
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();

        EvenButton();

    }



    private void Anhxa() {
        btnregister=findViewById(R.id.editextregister);


    }

    private void EvenButton() {
    btnregister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
   });

    }
}