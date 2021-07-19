package com.chenjinguyen.bookcommunity.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.chenjinguyen.bookcommunity.R;
import com.chenjinguyen.bookcommunity.service.ApiService;

public class NameDialog  extends AppCompatDialogFragment {
    private EditText editName;
    private  View view;
    ApiService apiService;
    SharedPreferences dataLocal;
    String token;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.layout_dialog_name, null);
        editName = view.findViewById(R.id.edit_name);
        apiService = new ApiService();

        dataLocal = view.getContext().getSharedPreferences("dataLocal", Context.MODE_PRIVATE);
        token = dataLocal.getString("token","");

        builder.setView(view).setTitle("Change your name below").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                apiService.ChangeNameDialog(token, editName.getText().toString(), view);
            }
        });

        AlertDialog dialog = builder.create();
        return  dialog;
    }

}
