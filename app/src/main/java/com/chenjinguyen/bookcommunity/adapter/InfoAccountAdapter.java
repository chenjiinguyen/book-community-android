package com.chenjinguyen.bookcommunity.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.activity.PointHistoryActivity;
import com.chenjinguyen.bookcommunity.model.InfoAccount;

import java.util.ArrayList;

public class InfoAccountAdapter extends RecyclerView.Adapter<InfoAccountAdapter.KHUNGNHIN>{

    Context context;
    ArrayList<InfoAccount> data;

    public InfoAccountAdapter(Context context, ArrayList<InfoAccount> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_info_account,null);
        KHUNGNHIN viewHolder = new KHUNGNHIN(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
        InfoAccount inforAccount = data.get(position);
        holder.imgview.setImageResource(inforAccount.getIconinfo());
        holder.txtview.setText(inforAccount.getNameinfo());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inforAccount.get_class() != null)
                {
                    Intent intent = new Intent(v.getContext(),inforAccount.get_class());
                    v.getContext().startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class KHUNGNHIN extends RecyclerView.ViewHolder {
        View view;
        ImageView imgview;
        TextView txtview;
        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);
            imgview = itemView.findViewById(R.id.imginfor);
            txtview = itemView.findViewById(R.id.tvnameinfo);
            view = itemView;
        }
    }
}
