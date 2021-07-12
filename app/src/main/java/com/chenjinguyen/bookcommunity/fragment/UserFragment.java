package com.chenjinguyen.bookcommunity.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.activity.HomeActivity;
import com.chenjinguyen.bookcommunity.activity.ListofWorksPostedActivity;
import com.chenjinguyen.bookcommunity.activity.LoginActivity;
import com.chenjinguyen.bookcommunity.activity.LogoutActivity;
import com.chenjinguyen.bookcommunity.activity.PointHistoryActivity;
import com.chenjinguyen.bookcommunity.activity.RegisterActivity;
import com.chenjinguyen.bookcommunity.model.InfoAccount;
import com.chenjinguyen.bookcommunity.adapter.InfoAccountAdapter;
import com.chenjinguyen.bookcommunity.model.UserModel;
import com.chenjinguyen.bookcommunity.service.ApiService;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class UserFragment extends Fragment {
    TextView tvName, tvSoDiem, tvTruyen, tvDiem;
    CircularImageView imgAvt;
    RecyclerView recyclerView;
    ArrayList<InfoAccount>  InfoAccounts;
    InfoAccountAdapter InfoAccountAdapter;
    ApiService apiService;
    SharedPreferences dataLocal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        apiService = new ApiService();

        dataLocal = v.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        String token = dataLocal.getString("token","");
        apiService.UserFragment(token, v);

        return v;



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
