package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    Button btn_sigin;
    Toolbar toolbarregister;
    EditText editext_username;
    EditText edittext_password;
    EditText editext_email;
    EditText editText_yourname;
    EditText editText_birthday;
    RadioButton rdbtn_male;
    RadioButton rdbtn_female;
    ApiService apiService;
     Spinner spinner;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Anhxa();
        ActionToolbar();
        Evenbutton();
    }
    private void Anhxa() {
        apiService=new ApiService();
        btn_sigin=findViewById(R.id.btn_singin);
        toolbarregister= findViewById(R.id.toolbar);
        editext_email=findViewById(R.id.editext_email);
        editext_username=findViewById(R.id.editext_username);
        editText_birthday=findViewById(R.id.edittext_birthday);
        edittext_password=findViewById(R.id.edittext_pass);
        editText_yourname=findViewById(R.id.editext_name);
        spinner=findViewById(R.id.spinner_gender);
        ArrayList<String>arraygender=new ArrayList<String>();
        arraygender.add("Nam");
        arraygender.add("Nữ");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,arraygender);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
    private void Evenbutton() {
        btn_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editext_username.getText().toString();
                String password = edittext_password.getText().toString();
                String name= editText_yourname.getText().toString();
                String birthday = editText_birthday.getText().toString();
                String email=editext_email.getText().toString();
                String gender=spinner.getSelectedItem().toString();
                apiService.postSignup(username,email,password,name,birthday,gender,v);
            }
        });
        editText_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                editText_birthday.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
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