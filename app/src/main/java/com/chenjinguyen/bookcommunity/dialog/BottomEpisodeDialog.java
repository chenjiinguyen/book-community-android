package com.chenjinguyen.bookcommunity.dialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chenjinguyen.bookcommunity.R;

import com.chenjinguyen.bookcommunity.activity.EpisodeActivity;
import com.chenjinguyen.bookcommunity.model.BookModel;
import com.chenjinguyen.bookcommunity.model.EpisodeModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BottomEpisodeDialog extends BottomSheetDialogFragment implements AdapterView.OnItemSelectedListener {
    int style = BottomSheetDialogFragment.STYLE_NO_TITLE;
    int theme = R.style.BottomEpisodeDialog;
    public ArrayList<EpisodeModel> EpisodesArray;
    public Spinner spinner;
    public List<String> nameEpisodeArray;
    public ArrayAdapter<String> spinnerArrayAdapter;
    public BookModel book;
    public EpisodeModel episode;
    int check = 0;
    View v;
    public BottomEpisodeDialog() {
    }
    public BottomEpisodeDialog(ArrayList<EpisodeModel> episodesArray,EpisodeModel pEpisode, BookModel pBook) {
        EpisodesArray = episodesArray;
        episode = pEpisode;
        book = pBook;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_bottom_episode,container,false);
        this.v = v;
        spinner = v.findViewById(R.id.episode_spinner_book);
        nameEpisodeArray = EpisodesArray.stream().map(t -> t.getName()).collect(Collectors.toList());
        spinnerArrayAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item,nameEpisodeArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        int index = nameEpisodeArray.lastIndexOf(episode.getName());
        Log.e("SÀAS","XÀM"+index);
        spinner.setSelection(index,false);

        spinner.setOnItemSelectedListener(this);

        return v;
    }
    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//        if(++check > 1) {
            Intent t = new Intent(v.getContext(), EpisodeActivity.class);
            t.putExtra("episode", EpisodesArray.get(position));
            t.putExtra("book", book);
            v.getContext().startActivity(t);
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
