package com.chenjinguyen.bookcommunity.adapter;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chenjinguyen.bookcommunity.fragment.CommentFragment;
import com.chenjinguyen.bookcommunity.fragment.DetailFragment;

import org.jetbrains.annotations.NotNull;

public class TabLayoutDetailBookAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    int id;

    public TabLayoutDetailBookAdapter(FragmentManager fm,int behavior,Context context,  int totalTabs, int id) {
        super(fm, behavior);
        this.myContext = context;
        this.totalTabs = totalTabs;
        this.id = id;
    }


    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DetailFragment detailFragment = new DetailFragment(id);
                return detailFragment;
            case 1:
                CommentFragment commentFragment = new CommentFragment(id);
                return commentFragment;
            default:
                return null;
    }}



    @Override
    public int getCount() {
        return totalTabs;
    }
}
