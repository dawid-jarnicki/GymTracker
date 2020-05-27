package com.example.gymtracker;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.gymtracker.ui.home.HomeFragment;

public class Przekierowanie extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new HomeFragment()).commit();}
    }
}
