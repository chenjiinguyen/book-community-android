package com.chenjinguyen.bookcommunity.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;


public class CommentFragment extends Fragment {
    View v;
    ApiService apiService;
    SharedPreferences dataLocal;
    Button btn_comment;
    TextView txt_comment;
    int id;

    public CommentFragment(int id) {
        this.id = id;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comment, container, false);
        dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        String token = dataLocal.getString("token","");
        btn_comment=v.findViewById(R.id.btn_comment);
        txt_comment=v.findViewById(R.id.txt_comment);
        apiService = new ApiService();
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content=txt_comment.getText().toString();
                apiService.PostCommentFragment(token,id,content,v);
                txt_comment.setText("");

            }
        });


        apiService.CommentActivity(token,id,v);

        return v;
    }
}