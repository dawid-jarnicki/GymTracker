package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Cwiczenie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cwiczenie);

    }

    private String nazwa;
    private String opis;
    private String typ;

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public String getTyp() {
        return typ;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Cwiczenie() {
    }

    public Cwiczenie(String nazwa, String opis, String typ) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.typ = typ;
    }


}
