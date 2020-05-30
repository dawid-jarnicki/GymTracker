package com.example.gymtracker.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gymtracker.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

public class SlideshowFragment extends Fragment {

    private Spinner Spiner;
    private Button Dodaj;
    private Button Zapisz;
    private EditText Serie;
    private EditText Ciezar;
    private EditText Powtorzenia;
    private EditText Czas;
    private TextView TreningLista;
    private FirebaseAuth Auth = FirebaseAuth.getInstance();
    private FirebaseUser user ;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int ilosc = 0;
    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        return root;
            }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spiner = view.findViewById(R.id.spinnerCwWybor);
        Dodaj = view.findViewById(R.id.buttonSSDodaj);
        Zapisz = view.findViewById(R.id.buttonZapiszTrening);
        Serie = view.findViewById(R.id.editTextSerie);
        Ciezar = view.findViewById(R.id.editTextCiezar);
        Powtorzenia = view.findViewById(R.id.editTextPowtorzenia);
        Czas = view.findViewById(R.id.editTextCzas);
        TreningLista = view.findViewById(R.id.textViewTrening);

        user = Auth.getCurrentUser();

        final String email = user.getEmail();

      //  final Map<String, Object>[] show = new Map[]{new HashMap<>()};
        final List<Map<String,Object>> treningCwiczenia = new ArrayList<Map<String,Object>>();

        db.collection("users").document(email).collection("Cwiczenia").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        Vector<String> ListaCw = new Vector<String>();
                        int i = 0;
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            final Map<String, Object> cw;
                            cw = documentSnapshot.getData();
                            String nazwa = (String) cw.get("Nazwa");

                            data = nazwa;
                            ListaCw.add(data);
                            i++;
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item);

                        for (int j=0; j<i; j++)
                        {
                            adapter.add(ListaCw.get(j));

                        }
                        Spiner.setAdapter(adapter);
                    }
                });


        Dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = "";

                final Map<String,Object> cwiczenie = new HashMap<>();

                String nazwa = Spiner.getSelectedItem().toString();
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String serie = Serie.getText().toString();
                String powtorzenia = Powtorzenia.getText().toString();
                String czas = Czas.getText().toString();
                String ciezar = Ciezar.getText().toString();
                String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                if (serie == null) serie = "";
                if (powtorzenia == null) powtorzenia = "";
                if (czas == null) czas = "";
                if (ciezar == null) ciezar = "";


                cwiczenie.put("Nazwa",nazwa);
                cwiczenie.put("Ilosc serii",serie);
                cwiczenie.put("Powtorzenia",powtorzenia);
                cwiczenie.put("Czas",czas);
                cwiczenie.put("Ciezar",ciezar);
                cwiczenie.put("Czas wykonania",time);
                treningCwiczenia.add(cwiczenie);

                ilosc++;
                for (int poz=0; poz<ilosc; poz++)
                {
                    String cwNazwa = (String) treningCwiczenia.get(poz).get("Nazwa");
                    String cwIlosc = (String) treningCwiczenia.get(poz).get("Ilosc serii");
                    String cwCiezar = (String) treningCwiczenia.get(poz).get("Ciezar");
                    String cwPowtorzenia = (String) treningCwiczenia.get(poz).get("Powtorzenia");
                    String cwCzas = (String) treningCwiczenia.get(poz).get("Czas");
                    String cwTime = (String) treningCwiczenia.get(poz).get("Czas wykonania");
                    Toast.makeText(getContext(),cwNazwa,Toast.LENGTH_SHORT).show();
                    data += cwNazwa+"\t\t\t\t\t\t\t\tIlosc\t\t\tPowtorzenia\t\t\tCiezar\t\t\tCzas\n"+cwTime+"\t\t\t\t"+cwIlosc+"\t\t\t\t\t"+cwPowtorzenia+"\t\t\t\t\t\t\t\t\t\t\t\t"+cwCiezar+"\t\t\t\t\t\t\t"+cwCzas+"\n\n";
                }


                TreningLista.setText(data);

            }

        });

        final String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cwNazwa = "";
                for (int i=0; i<ilosc; i++)
                {
                     cwNazwa = (String) treningCwiczenia.get(i).get("Nazwa");
                    db.collection("users").document(email).collection(date).document(cwNazwa).set(treningCwiczenia.get(i));
                }
            }
        });


    }
};



