package com.example.gymtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonCreateClick(View view) {
       //boolean checked = ((RadioButton) view).isChecked();
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupPlec);
        int plec = rg.getCheckedRadioButtonId();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        EditText getWaga = (EditText) findViewById(R.id.wagaText);
        String waga = getWaga.getText().toString();
        EditText getWzrost = (EditText) findViewById(R.id.wzrostText);
        String wzrost = getWzrost.getText().toString();

        EditText getNazwa = (EditText) findViewById(R.id.nazwaId);
        String nazwa = getNazwa.getText().toString();

        if (plec == R.id.femButton)
        {

            alert.setMessage(nazwa+"\nKobieta\n"+waga+" "+wzrost);


       }
        else if (plec == R.id.maleButton)
        {
            alert.setMessage(nazwa+"\nMężczyzna\n"+waga+" "+wzrost);
        }
        else
        {
            alert.setMessage("Nie uzupełniłeś wszystkich pól !");
        }
        alert.show();
    }
}