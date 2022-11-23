package com.example.credit__book.Model;

public class Client extends Person {

    private String address;

    public static int IDclient = 1;

    public Client() {
    }

    public Client(int id, String full_name, String phone_number, String email, String address) {
        super(id, full_name, phone_number, email);
        this.address = address;
        IDclient++;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
