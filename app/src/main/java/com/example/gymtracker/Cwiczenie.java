package com.example.gymtracker;

public class Cwiczenie {
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
