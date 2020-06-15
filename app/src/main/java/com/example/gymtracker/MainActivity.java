package com.example.gymtracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    EditText Haslo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nazwa = findViewById(R.id.nazwaId);
        Haslo = findViewById(R.id.hasloId);
        Email = findViewById(R.id.emailText);
        Plec = findViewById(R.id.radioGroupPlec);
        createButton = findViewById(R.id.buttonCreate);
        Login = findViewById(R.id.loginText);

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Logowanie.class);
                startActivity(intent);
            }
        });


        mAuth = FirebaseAuth.getInstance();


        if (mAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(MainActivity.this,nav.class);
            startActivity(intent);
        }


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = Email.getText().toString();
                final String nazwa = Nazwa.getText().toString();
                final String haslo = Haslo.getText().toString();
                final int plec = Plec.getCheckedRadioButtonId();

                if (TextUtils.isEmpty(nazwa)) {
                    Nazwa.setError("Nazwa jest wymagana !");
                    return;
                }
                if (TextUtils.isEmpty(haslo)) {
                    Haslo.setError("Hasło jest wymagane !");
                    return;
                }
                if (haslo.length() < 6) {
                    Haslo.setError("Haslo musi się składać z co najmniej 6 znaków !");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email jest wymagany !");
                    return;
                }
                //rejestracja

                mAuth.createUserWithEmailAndPassword(email, haslo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Stworzono.", Toast.LENGTH_SHORT).show();

                            //dodanie do bazy danych
                            Dodaj(nazwa, email, plec);
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


    public void Dodaj(String nazwa, String email, int plec) {

        user.put("Nazwa", nazwa);
        user.put("E-mail", email);
        if (plec == R.id.femButton) user.put("Plec", "Kobieta");
        else user.put("Plec", "Mezczyzna");

        db.collection("users").document(email).set(user);
    }



}