package com.chenjinguyen.bookcommunity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.activity.DetailActivity;
import com.chenjinguyen.bookcommunity.activity.HomeActivity;
import com.chenjinguyen.bookcommunity.model.BookModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<BookModel> mBooks;

    public BookAdapter(Context mContext, ArrayList<BookModel> mBooks) {
        this.mContext = mContext;
        this.mBooks = mBooks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_book, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        BookModel book = mBooks.get(position);

        Picasso.get().load(book.getPoster()).fit().centerCrop().into(holder.imgPoster);
        holder.txtTitle.setText(book.getTitle());
        holder.txtAuthor.setText(book.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(v.getContext(), DetailActivity.class);
                t.putExtra("id",book.getId());
                v.getContext().startActivity(t);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPoster;
        private TextView txtTitle,txtAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title_book);
            txtAuthor = itemView.findViewById(R.id.author_book);
            imgPoster = itemView.findViewById(R.id.poster_book);
        }


    }
}