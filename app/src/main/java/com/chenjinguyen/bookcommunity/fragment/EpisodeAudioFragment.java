package com.chenjinguyen.bookcommunity.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.model.EpisodeModel;
import com.chenjinguyen.bookcommunity.service.ApiService;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpisodeAudioFragment extends Fragment {

    YouTubePlayer YPlayer;
    EpisodeModel episode;
    ApiService apiService;
    SharedPreferences dataLocal;

    public EpisodeAudioFragment(EpisodeModel episode) {
        this.episode = episode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_episode_audio, container, false);
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.youtube_fragment, youTubePlayerFragment);
        transaction.commit();

        apiService = new ApiService();

        dataLocal = rootView.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        String token = dataLocal.getString("token","");

        apiService.EpisodeFragmentAudio(token, episode.getEpisode_id(), rootView);


        youTubePlayerFragment.initialize("AIzaSyDKVtZyUc0auqDil_6I9cO7LlTYejiOz7o", new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    Pattern pattern = Pattern.compile("^http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch\\?v=|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?\u200C\u200B[\\w\\?\u200C\u200B=]*)?$");
                    Matcher matcher = pattern.matcher(episode.getContent().get(0));
                    while (matcher.find()) {
                        String id =  matcher.group(1);
                        YPlayer = youTubePlayer;
                        YPlayer.setFullscreen(false);
                        YPlayer.loadVideo(id);
                        YPlayer.play();
                        break;
                    }

                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                // TODO Auto-generated method stub

            }
        });
        return rootView;

    }

}


