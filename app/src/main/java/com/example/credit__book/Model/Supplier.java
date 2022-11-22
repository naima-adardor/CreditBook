package com.example.credit__book.Model;

public class Supplier extends Person {

    private String adresse;

    public Supplier(int id, String full_name, String phone_number, String email, String adresse) {
        super(id, full_name, phone_number, email);
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
