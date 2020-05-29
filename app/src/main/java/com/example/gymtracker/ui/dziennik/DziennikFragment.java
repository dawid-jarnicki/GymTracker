package com.example.gymtracker.ui.dziennik;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
    private TextView bmr;
    private DziennikViewModel dziennikViewModel;
    private ImageButton FatHelp;
    private ImageButton WaterHelp;
    private ImageButton BMIHelp;
    private ImageButton FFMIHelp;
    private ImageButton BMRHelp;


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
        bmr = view.findViewById(R.id.textDZBMIZakres);
        button  = view.findViewById(R.id.buttonDZCalculateBMI);

        FatHelp = view.findViewById(R.id.helpTluszcz);
        WaterHelp = view.findViewById(R.id.helpNawodnienie);
        BMIHelp = view.findViewById(R.id.helpBMI);
        FFMIHelp = view.findViewById(R.id.helpFFMI);
        BMRHelp = view.findViewById(R.id.helpBMR);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        FatHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Procent tkanki tłuszczowej");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        WaterHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Nawodnienie organizmu");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        BMIHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Wskaźnik masy ciała");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        FFMIHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Wskaźnik masy mięśni w ciele");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        BMRHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Zapotrzebowanie kaloryczne");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  float miesnie =

              //  float bmrWartosc = Float.parseFloat()
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






