package com.chenjinguyen.bookcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chenjinguyen.bookcommunity.activity.HomeActivity;
import com.chenjinguyen.bookcommunity.activity.PersionalInfoActivity;
import com.chenjinguyen.bookcommunity.fragment.HomeFragment;
import com.chenjinguyen.bookcommunity.fragment.UserFragment;
import com.chenjinguyen.bookcommunity.service.PushNotificationService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.chenjinguyen.bookcommunity.service.ApiService;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    public static final String CHANNEL_ID = "Channel1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = getWindow().getDecorView().getRootView();
        SharedPreferences dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);


//        String device = dataLocal.getString("device", "");
//        if(device != ""){
////            ApiService apiService = new ApiService();
////            apiService.importToken(device);
//            Toast.makeText(this, device, Toast.LENGTH_SHORT).show();
//        }
        Intent t = new Intent(this, HomeActivity.class);
        t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(t);


    }



}