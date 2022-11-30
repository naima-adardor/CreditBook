package com.example.credit__book.Model;

import java.util.Date;

public class Client extends Person {

    private String address;
    private String user;
    private String dateCreation;

    public Client() {
    }

    public Client(int id, String full_name, String phone_number, String email, String address, String user, String dateCreation) {
        super(id, full_name, phone_number, email);
        this.address = address;
        this.user = user;
        this.dateCreation = dateCreation;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
