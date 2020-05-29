package com.example.gymtracker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gymtracker.R;
import com.example.gymtracker.nav;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {

    private FirebaseUser user;
    private TextView Email;
    private FirebaseAuth Auth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
            }
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Email = view.findViewById(R.id.textHomeMail);
        Auth = FirebaseAuth.getInstance();
        if (Auth.getCurrentUser() != null) user = Auth.getCurrentUser();

        String email = user.getEmail();
       // String email = "xd";
        Email.setText(email);

    }


    }

