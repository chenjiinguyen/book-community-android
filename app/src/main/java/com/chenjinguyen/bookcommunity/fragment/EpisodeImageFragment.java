package com.chenjinguyen.bookcommunity.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.adapter.ImageEpisodeAdapter;
import com.chenjinguyen.bookcommunity.model.EpisodeModel;
import com.chenjinguyen.bookcommunity.service.ApiService;

import java.util.ArrayList;

public class EpisodeImageFragment extends Fragment {

    private EpisodeModel episode;

    public EpisodeImageFragment(EpisodeModel episode) {
        this.episode = episode;
    }

    ApiService apiService;
    SharedPreferences dataLocal;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_episode_image,container,false);
        apiService = new ApiService();

        dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        String token = dataLocal.getString("token","");
        RecyclerView recyclerView = v.findViewById(R.id.episode_image_content);
        ArrayList<String> content = episode.getContent();
        Log.e("LOIIIII",""+content.size());
        ImageEpisodeAdapter imageEpisodeAdapter = new ImageEpisodeAdapter(v.getContext(),content);
        recyclerView.setAdapter(imageEpisodeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));

        return v;
    }

}
