package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Toolbar;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;

public class LoginActivity extends AppCompatActivity {

    Button btn_register;
    Button btn_login;
    EditText edtext_username;
    EditText edtext_pass;
    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();
        EventButtons();

    }


    private void Anhxa() {
        btn_register = findViewById(R.id.editext_register);
        edtext_username=findViewById(R.id.editext_username);
        edtext_pass=findViewById(R.id.edittext_pass);
        btn_login=findViewById(R.id.btn_login);
        apiService = new ApiService();
    }

    private void EventButtons() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
       btn_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                String username = edtext_username.getText().toString();
                String password = edtext_pass.getText().toString();
                boolean remember = true;
                apiService.postSignin(username,password,remember,v);

           }
       });
    }
}