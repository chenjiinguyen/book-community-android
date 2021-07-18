package com.chenjinguyen.bookcommunity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.model.CategoryTitleModel;
import com.chenjinguyen.bookcommunity.service.ApiService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CategoryTitleAdater extends RecyclerView.Adapter<CategoryTitleAdater.ViewHolder> {

    private Context mContext;
    private ArrayList<CategoryTitleModel> mTitle;
    private RecyclerView rv_category;
    private ApiService apiService;

    public CategoryTitleAdater(Context mContext, ArrayList<CategoryTitleModel> mTitle, RecyclerView rv_category) {
        this.mContext = mContext;
        this.mTitle = mTitle;
        this.rv_category = rv_category;
        this.apiService = new ApiService();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_category_title, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        CategoryTitleModel title = mTitle.get(position);

        holder.title_category.setText(title.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiService.setCategoryData(rv_category,title.getId(),view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_category;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_category = itemView.findViewById(R.id.category_title);
            this.itemView = itemView;
        }
    }
}
