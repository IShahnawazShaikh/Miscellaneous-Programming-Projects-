package com.example.oasisNav.ui.delete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.oasisNav.R;

public class DeleteFragment extends Fragment {

    private DeleteViewModel deleteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        deleteViewModel =
                ViewModelProviders.of(this).get(DeleteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_delete, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        deleteViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}