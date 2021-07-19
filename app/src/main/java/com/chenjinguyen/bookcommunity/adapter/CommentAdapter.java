package com.chenjinguyen.bookcommunity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.model.CommentModel;
import com.chenjinguyen.bookcommunity.model.PointModel;
import com.chenjinguyen.bookcommunity.service.ApiService;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CommentModel> mComment;



    public CommentAdapter(Context mContext, ArrayList<CommentModel> mComment) {
        this.mContext = mContext;
        this.mComment = mComment;

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        CommentModel myComment = mComment.get(position);
        holder.txt_username.setText(myComment.getIduser());
        holder.txt_datecomment.setText(myComment.getCreatedAt());
        holder.txt_content.setText(myComment.getContent());
        Picasso.get().load(myComment.getAvartar()).fit().placeholder(R.drawable.icon_navbar_user).into(holder.avatar);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_username,txt_content,txt_datecomment;
        ImageView avatar;

        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_username = itemView.findViewById(R.id.txt_username);
            txt_content=itemView.findViewById(R.id.txt_content);
            txt_datecomment=itemView.findViewById(R.id.txt_datecomment);
            avatar = itemView.findViewById(R.id.imguser);
            this.itemView = itemView;
        }
    }
    @Override
    public int getItemCount() {
        return mComment.size();
    }
}
