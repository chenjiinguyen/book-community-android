package com.chenjinguyen.bookcommunity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.model.PointModel;
import com.chenjinguyen.bookcommunity.R;

import java.util.ArrayList;

public class MyPointAdapter extends RecyclerView.Adapter<MyPointAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<PointModel> data;

    public MyPointAdapter(Context context, ArrayList<PointModel> data) {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_diem_recycler, null);
        return new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
        PointModel myAcc = data.get(position);
        holder.tenTruyen.setText(myAcc.getBook() +" - "+myAcc.getEpisode());
        holder.soDiem.setText(((myAcc.isCharge())?"+":"-")+myAcc.getPoint());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class KHUNGNHIN extends RecyclerView.ViewHolder {
        TextView tenTruyen, soDiem;
        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);
            tenTruyen = itemView.findViewById(R.id.tvTen);
            soDiem = itemView.findViewById(R.id.tvDiem);
        }
    }
}
