package com.chenjinguyen.bookcommunity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;


public class DetailFragment extends Fragment {

    int id;
    ApiService apiService;
    View v;
    public DetailFragment(int id) {
      this.id = id;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        apiService = new ApiService();
        apiService.DetailFragment(id,v);
        return v;
    }
}