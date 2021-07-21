package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText editNew, editConfirm;
    ImageView eyeViewNew, eyeViewConfirm;
    ApiService apiService;
    SharedPreferences dataLocal;
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        eyeViewNew = findViewById(R.id.eyeNew);
        eyeViewConfirm = findViewById(R.id.eyeConfirm);
        editNew = findViewById(R.id.tvNewPass);
        editConfirm = findViewById(R.id.tvConfirmPass);

        View v = getWindow().getDecorView().getRootView();
        dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        String token = dataLocal.getString("token","");
        apiService = new ApiService();
        apiService.ChangePasswordActivity(token, v);

        editNew.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editConfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        eyeViewNew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        editNew.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        editNew.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }
                return true;
            }
        });
        eyeViewConfirm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        editConfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        editConfirm.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }
                return true;
            }
        });
    }
}