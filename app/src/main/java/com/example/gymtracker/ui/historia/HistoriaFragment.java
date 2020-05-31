package com.example.gymtracker.ui.historia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.util.Map;
import java.util.Vector;


public class HistoriaFragment extends  Fragment {
    private HistoriaViewModel historiaViewModel;

    private TextView Historia;
    private Spinner Spinner;
    private FirebaseAuth Auth = FirebaseAuth.getInstance();
    private FirebaseUser user ;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historiaViewModel =
                ViewModelProviders.of(this).get(HistoriaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_historia, container, false);

        return root;
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Historia = view.findViewById(R.id.textHistoria);
        Spinner = view.findViewById(R.id.spinnerDzien);
        user = Auth.getCurrentUser();
        final String email = user.getEmail();

        db.collection("users").document(email).collection("listaDat").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        Vector<String> daty = new Vector<String>();
                        int i = 0;
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            final Map<String, Object> cw;
                            cw = documentSnapshot.getData();
                            String nazwa = (String) cw.get("Data");

                            data = nazwa;
                            daty.add(data);
                            i++;
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item);

                        for (int j=0; j<i; j++)
                        {
                            adapter.add(daty.get(j));

                        }
                        Spinner.setAdapter(adapter);
                    }
                });
        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String dataTreningu = (String) Spinner.getSelectedItem();
                db.collection("users").document(email).collection(dataTreningu).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                String data = "";
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    final Map<String, Object> cw;
                                    cw = documentSnapshot.getData();
                                    String cwNazwa = (String) cw.get("Nazwa");
                                    String cwIlosc = (String) cw.get("Ilosc serii");
                                    String cwCiezar = (String) cw.get("Ciezar");
                                    String cwPowtorzenia = (String) cw.get("Powtorzenia");
                                    String cwCzas = (String) cw.get("Czas");
                                    String cwTime = (String) cw.get("Czas wykonania");

                                    data += cwNazwa+"\t\t\t\t\t\t\t\tIlosc\t\t\tPowtorzenia\t\t\tCiezar\t\t\tCzas\n"+cwTime+"\t\t\t\t"+cwIlosc+"\t\t\t\t\t"+cwPowtorzenia+"\t\t\t\t\t\t\t\t\t\t\t\t"+cwCiezar+"\t\t\t\t\t\t\t"+cwCzas+"\n\n";

                                }
                                Historia.setText(data);
                            }
                        });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
