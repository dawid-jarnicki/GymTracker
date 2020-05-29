package com.example.gymtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Logowanie extends AppCompatActivity {

    Button Loguj;
    EditText Email;
    FirebaseAuth lAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);

        Loguj = findViewById(R.id.logowanieButton);
        Email = findViewById(R.id.emailTextLogin);
        Loguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lAuth = FirebaseAuth.getInstance();
                String email = Email.getText().toString();

                lAuth.signInWithEmailAndPassword(email, "xxxxxx").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Zalogowano :)", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Logowanie.this, nav.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Nie udało się zalogować :(", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


    }
}
