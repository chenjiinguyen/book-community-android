package com.chenjinguyen.bookcommunity.fragment;

import android.os.Bundle;
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
import com.chenjinguyen.bookcommunity.model.InfoAccount;
import com.chenjinguyen.bookcommunity.adapter.InfoAccountAdapter;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class UserFragment extends Fragment {
    TextView tvName, tvSoDiem, tvTruyen;
    CircularImageView imgAvt;
    RecyclerView recyclerView;
    ArrayList<InfoAccount> InfoAccounts = new ArrayList<>();
    InfoAccountAdapter InfoAccountAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        tvName = v.findViewById(R.id.tvName);
        tvSoDiem = v.findViewById(R.id.tvSoDiem);
        tvTruyen = v.findViewById(R.id.tvSLDocTruyen);
        imgAvt = v.findViewById(R.id.imageView);


        tvName.setText("Diệp Hạ Nhi");
        tvSoDiem.setText("" + 0);
        tvTruyen.setText("" + 10);
        imgAvt.setImageResource(R.drawable.avatar);

        recyclerView = v.findViewById(R.id.rclinfor);
        InfoAccounts.add(new InfoAccount(R.drawable.ic_baseline_stars_24, "Nạp tiền"));
        InfoAccounts.add(new InfoAccount(R.drawable.ic_baseline_book_24, "Phiếu đọc truyện của tôi"));
        InfoAccounts.add(new InfoAccount(R.drawable.ic_baseline_account_circle_24, "Khung avatar của tôi"));
        InfoAccountAdapter = new InfoAccountAdapter(container.getContext(), InfoAccounts);
        recyclerView.setAdapter(InfoAccountAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
