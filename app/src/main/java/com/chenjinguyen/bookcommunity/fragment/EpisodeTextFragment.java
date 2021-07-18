package com.chenjinguyen.bookcommunity.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.adapter.BookAdapter;
import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.EpisodeModel;
import com.chenjinguyen.bookcommunity.service.ApiService;

import java.util.ArrayList;

public class EpisodeTextFragment extends Fragment {

    private EpisodeModel episode;

    public EpisodeTextFragment(EpisodeModel episode) {
        this.episode = episode;
    }

    ApiService apiService;
    SharedPreferences dataLocal;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_episode_text,container,false);
        apiService = new ApiService();

        dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        String token = dataLocal.getString("token","");
//        apiService.EpisodeTextFragment(token,id,v);
        String content = episode.getContent().get(0);
        TextView episode_text_content = v.findViewById(R.id.episode_text_content);
        episode_text_content.setText(content);
        return v;
    }

}
