package com.example.gymtracker.ui.dziennik;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gymtracker.R;


public class DziennikFragment extends  Fragment {

    private EditText height;
    private EditText weight;
    private EditText fat;
    private EditText water;
    private TextView result;
    private TextView opis;
    private Button button;

    private DziennikViewModel dziennikViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dziennikViewModel =
                ViewModelProviders.of(this).get(DziennikViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dziennik, container, false);

        return root;
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize your view here for use view.findViewById("your view id")
        height = view.findViewById(R.id.editTextDZWzrost);
        weight = view.findViewById(R.id.editTextDZWaga);
        fat = view.findViewById(R.id.editTextDZTluszcz);
        water = view.findViewById(R.id.editTextDZWoda);
        result = view.findViewById(R.id.textDZBMIZakres);
        opis = view.findViewById(R.id.textDZBMIZakresOpis);

        button  = view.findViewById(R.id.buttonDZCalculateBMI);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI(view);
            }
        });
    }

    private void displayBMI(float bmi) {
        String bmiLabel ;
        String desc;
        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
            desc = "Bardzo duża niedowaga";
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
            desc = "Duża niedowaga";
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
            desc = "Niedowaga";
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
            desc = "Norma";
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
            desc = "Nadwaga";
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
            desc = "Otyłość klasy 1";
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
            desc = "Otyłość klasy 2";
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
            desc = "Otyłość klasy 3";
        }

        bmiLabel = bmi + "\n\n" + bmiLabel;

        result.setText(bmiLabel);
        opis.setText(desc);
    }

    public void calculateBMI(View view) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null  &&  !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }
}






