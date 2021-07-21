package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.dialog.NameDialog;
import com.chenjinguyen.bookcommunity.service.ApiService;
import com.chenjinguyen.bookcommunity.util.FileUtil;
import com.mikhaellopez.circularimageview.CircularImageView;

public class PersionalInfoActivity extends AppCompatActivity {

    TextView tvName, tvChangePass;
    CircularImageView imgAvt;
    LinearLayout linearLayout;
    ApiService apiService;
    SharedPreferences dataLocal;
    String token;
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persional_info);
        v = getWindow().getDecorView().getRootView();
        apiService = new ApiService();

        dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        token = dataLocal.getString("token","");
        apiService.PersionalInfoActivity(token, v);



        tvName = (TextView)findViewById(R.id.tvUserName);
        imgAvt = findViewById(R.id.imageView);
        tvChangePass = findViewById(R.id.tvChangePass);
        linearLayout = findViewById(R.id.linearLayout);

        tvName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openDialog();

            }

        });

        imgAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChangePasswordActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
    public void openDialog() {
        NameDialog nameDialog = new NameDialog();
        nameDialog.show(getSupportFragmentManager(), "name dialog");

    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(PersionalInfoActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgAvt = findViewById(R.id.imageView);
        Uri selectedImage = null;
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    selectedImage = data.getData();
                    imgAvt.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    selectedImage = data.getData();
                    imgAvt.setImageURI(selectedImage);
                }
                break;
        }

            // Load image
           if(selectedImage != null){
                apiService.ChangeAvatar(token, selectedImage, v);
           }


    }
}