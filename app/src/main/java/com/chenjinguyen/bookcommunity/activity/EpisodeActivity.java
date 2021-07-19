package com.chenjinguyen.bookcommunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.dialog.BottomEpisodeDialog;
import com.chenjinguyen.bookcommunity.fragment.EpisodeImageFragment;
import com.chenjinguyen.bookcommunity.fragment.EpisodeTextFragment;
import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.EpisodeModel;
import com.chenjinguyen.bookcommunity.service.ApiService;

import org.w3c.dom.Text;

public class EpisodeActivity extends AppCompatActivity {
    View v;
    BookModel book;
    EpisodeModel episode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);
        v = getWindow().getDecorView().getRootView();
        Intent itent = getIntent();
        book = (BookModel) itent.getSerializableExtra("book");
        episode = (EpisodeModel) itent.getSerializableExtra("episode");

        if(book.getCategory().equals("TEXT")){
            loadFragment(new EpisodeTextFragment(episode));
        }else if(book.getCategory().equals("IMAGE")){
            loadFragment(new EpisodeImageFragment(episode));
        }

        ApiService apiService = new ApiService();

        TextView name_episode_text = findViewById(R.id.name_episode_text);
        name_episode_text.setText(episode.getName().toUpperCase());

        apiService.EpisodeActivity(getSupportFragmentManager(),episode,book,v);

    }

    @Override
    public void onBackPressed() {
        Intent t = new Intent(v.getContext(), DetailActivity.class);
        t.putExtra("id",book.getId());
        t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        v.getContext().startActivity(t);
        return;
    }

    public void loadFragment(Fragment frm){
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.frame_episode,frm);
        trans.commit();
    }
}