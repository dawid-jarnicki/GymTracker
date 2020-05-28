package com.example.gymtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();

    EditText Nazwa, Email;
    TextView Login;
    RadioGroup Plec;
    Button createButton;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    // AlertDialog.Builder alert = new AlertDialog.Builder(this);

   // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nazwa = findViewById(R.id.nazwaId);
        Email = findViewById(R.id.emailText);
        Plec = findViewById(R.id.radioGroupPlec);
        createButton = findViewById(R.id.buttonCreate);
        Login = findViewById(R.id.loginText);

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this,"no co jest", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Logowanie.class);
                startActivity(intent);
            }
        });


        mAuth = FirebaseAuth.getInstance();
        //  progressBar = findViewById(R.id.bar);

        if (mAuth.getCurrentUser() != null)
        {
            FirebaseUser user = mAuth.getCurrentUser();
            String name = user.getEmail();
          //  user.delete();
            Intent intent = new Intent(MainActivity.this,nav.class);
            Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = Email.getText().toString();
                final String nazwa = Nazwa.getText().toString();
                final int plec = Plec.getCheckedRadioButtonId();
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email jest wymagany !");
                    return;
                }
                if (TextUtils.isEmpty(nazwa)) {
                    Nazwa.setError("Nazwa jest wymagana !");
                    return;
                }

                //rejestracja

                mAuth.createUserWithEmailAndPassword(email, "xxxxxx").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Stworzono.", Toast.LENGTH_SHORT).show();

                            //dodanie do bazy danych
                            Dodaj(nazwa, email, plec);
                           // startActivity(new Intent(getApplicationContext(), nav.class));
                            Intent intent = new Intent(MainActivity.this,nav.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Bład !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }
/*
    @Override
    public void onStart()
        {
            super.onStart();
            FirebaseUser currentUser = mAuth.getCurrentUser();
        }

    } */

    public void Dodaj(String nazwa, String email, int plec) {

        user.put("Nazwa", nazwa);
        user.put("E-mail", email);
        if (plec == R.id.femButton) user.put("Plec", "Kobieta");
        else user.put("Plec", "Mezczyzna");

        db.collection("users").document(email).set(user);
    }



}