package com.example.gymtracker.ui.historia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gymtracker.R;


public class HistoriaFragment extends  Fragment {
    private HistoriaViewModel historiaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historiaViewModel =
                ViewModelProviders.of(this).get(HistoriaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_historia, container, false);

        return root;
    }
}
