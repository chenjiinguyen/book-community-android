package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chenjinguyen.bookcommunity.R;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText editCurrent, editNew, editConfirm;
    ImageView eyeViewCurr, eyeViewNew, eyeViewConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        eyeViewCurr = findViewById(R.id.eyeCurrent);
        eyeViewNew = findViewById(R.id.eyeNew);
        eyeViewConfirm = findViewById(R.id.eyeConfirm);
        editCurrent = findViewById(R.id.tvCurrentPass);
        editNew = findViewById(R.id.tvNewPass);
        editConfirm = findViewById(R.id.tvConfirmPass);

        editCurrent.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editNew.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editConfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        eyeViewCurr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        editCurrent.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        editCurrent.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }
                return true;
            }
        });
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