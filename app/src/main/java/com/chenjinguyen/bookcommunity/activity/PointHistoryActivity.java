package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.adapter.MyPointAdapter;
import com.chenjinguyen.bookcommunity.model.MyPoint;

import java.util.ArrayList;

public class PointHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    TextView txtTitle, txtDiem;
    ArrayList<MyPoint> data, filter_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_history);
        getSupportActionBar().hide();

        data = new ArrayList<>();
        data.add(new MyPoint("Điểm +4", "Thất Vương Phi báo thù Thất Vương Phi báo thù"));
        data.add(new MyPoint("Điểm +20", "Đạo tình"));
        data.add(new MyPoint("Điểm +10", "Bộ bộ Kinh Tâm"));

        recyclerview = findViewById(R.id.recyclerLSDiem);
        MyPointAdapter myPointAdapter = new MyPointAdapter(this, data);

        recyclerview.setAdapter(myPointAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
    }
}