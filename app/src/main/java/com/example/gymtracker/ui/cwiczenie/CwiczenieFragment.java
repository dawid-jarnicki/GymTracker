package com.example.gymtracker.ui.cwiczenie;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gymtracker.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CwiczenieFragment extends Fragment {

    private CwiczenieViewModel cwiczenieViewModel;

    private EditText Nazwa;
    private RadioButton Silowe;
    private RadioButton Cardio;
    private RadioButton Kalistenika;
    private EditText Opis;
    private Button Dodaj;
    private Button Lista;
    private TextView ListaCw;
    private FirebaseAuth Auth;
    private FirebaseUser user ;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> cwiczenie = new HashMap<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cwiczenieViewModel =
                ViewModelProviders.of(this).get(CwiczenieViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cwiczenie, container, false);

        return root;
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Nazwa = view.findViewById(R.id.nazwaCwiczeniaId);
        Silowe = view.findViewById(R.id.siloweButton);
        Cardio =  view.findViewById(R.id.kardioButton);
        Kalistenika = view.findViewById(R.id.kalisteniczneButton);
        Opis = view.findViewById(R.id.opisCwiczeniaId);
        Dodaj = view.findViewById(R.id.dodajCwiczenieId);
        Lista = view.findViewById(R.id.listaCwButton);
        ListaCw = view.findViewById(R.id.listaTextView);
        Auth = FirebaseAuth.getInstance();
        if (Auth.getCurrentUser() != null) user = Auth.getCurrentUser();

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        Dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nazwa = Nazwa.getText().toString();
                String typ;
                if (Silowe.isChecked()) typ = Silowe.getText().toString();
                else if (Cardio.isChecked()) typ = Cardio.getText().toString();
                else typ = Kalistenika.getText().toString();
                String opis = Opis.getText().toString();
                String email = user.getEmail();
                cwiczenie.put("Nazwa",nazwa);
                cwiczenie.put("Typ",typ);
                cwiczenie.put("Opis",opis);

                    // Dodanie cwiczenia do bazy

                db.collection("users").document(email).collection("Cwiczenia").document(nazwa).set(cwiczenie);
                 Toast.makeText(getContext(),"Pomyślnie dodano ćwiczenie.", Toast.LENGTH_SHORT).show();
                 //   db.collection("users/"+email+"/"+nazwa)
                 //       .add(cwiczenie)
                 //       .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                 //           @Override
                 //           public void onSuccess(DocumentReference documentReference) {
                 //               Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                 //           }
                 //       })
                 //       .addOnFailureListener(new OnFailureListener() {
                 //           @Override
                 //           public void onFailure(@NonNull Exception e) {
                //                Log.w(TAG, "Error adding document", e);
                 //           }
                 //       });


            }
        });

        Lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user.getEmail();
                db.collection("users").document(email).collection("Cwiczenia").get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        String data = "";
                                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                            final Map<String, Object> cw;
                                            cw = documentSnapshot.getData();
                                            String nazwa = (String) cw.get("Nazwa");
                                            String typ = (String) cw.get("Typ");
                                            String opis = (String) cw.get("Opis");

                                           data += "Nazwa: " + nazwa + " Typ: " + typ + "\nOpis: " + opis +"\n\n";

                                        }
                                        ListaCw.setText(data);
                                    }
                                });
            }
        });

    }

    }

