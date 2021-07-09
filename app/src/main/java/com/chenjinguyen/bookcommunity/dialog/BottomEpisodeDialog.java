package com.chenjinguyen.bookcommunity.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chenjinguyen.bookcommunity.R;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomEpisodeDialog extends BottomSheetDialogFragment {
    int style = BottomSheetDialogFragment.STYLE_NO_TITLE;
    int theme = R.style.BottomEpisodeDialog;

    public BottomEpisodeDialog() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(style,theme);
    }



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_bottom_episode,container,false);

        return v;
    }
}
