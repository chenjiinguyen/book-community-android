package com.chenjinguyen.bookcommunity.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageEpisodeAdapter extends RecyclerView.Adapter<ImageEpisodeAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mLinks;

    public ImageEpisodeAdapter(Context mContext, ArrayList<String> mLinks) {
        this.mContext = mContext;
        this.mLinks = mLinks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_image_episode, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String link = mLinks.get(position);
        Picasso.get().load(link).fit().placeholder(R.drawable.image_not_available).into(holder.imageContent);

    }

    @Override
    public int getItemCount() {
        return mLinks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageContent = itemView.findViewById(R.id.imageContent);
        }


    }
}
