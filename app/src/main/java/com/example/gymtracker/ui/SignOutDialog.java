package com.example.gymtracker.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.gymtracker.MainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.nav;
import com.google.firebase.auth.FirebaseAuth;



public class SignOutDialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
        builder.setTitle("Wylogowanie się.")
                .setMessage("Czy na pewno chcesz się wylogować?")
                .setPositiveButton("Wyloguj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth logout = FirebaseAuth.getInstance();
                        logout.signOut();
                        Intent intent = new Intent(getContext(),MainActivity.class);
                        Toast.makeText(getContext(),"Pomyślnie wylogowano.", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }
}
