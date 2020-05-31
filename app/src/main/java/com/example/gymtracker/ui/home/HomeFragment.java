package com.example.gymtracker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.gymtracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Map;



public class HomeFragment extends Fragment {

    private FirebaseUser user;
    private TextView Email;
    private FirebaseAuth Auth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView Nazwa;
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
        Nazwa = view.findViewById(R.id.textHomeName);
        Auth = FirebaseAuth.getInstance();
        if (Auth.getCurrentUser() != null) user = Auth.getCurrentUser();
        String email = user.getEmail();
        Email.setText(email);

        db.collection("users").document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    final Map<String, Object> cw;
                    cw = document.getData();

                    String nazwa = (String) cw.get("Nazwa");
                    Nazwa.setText(nazwa);
                }
            }
        });

    }



    }

