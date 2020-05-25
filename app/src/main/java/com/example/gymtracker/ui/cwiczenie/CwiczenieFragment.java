package com.example.gymtracker.ui.cwiczenie;

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

public class CwiczenieFragment extends Fragment {

    private CwiczenieViewModel cwiczenieViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cwiczenieViewModel =
                ViewModelProviders.of(this).get(CwiczenieViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cwiczenie, container, false);

        return root;
    }


    }

