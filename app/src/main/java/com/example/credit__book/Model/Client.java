package com.example.credit__book.Model;

public class Client extends Person {

    private String address;
    private String user;

    public Client() {
    }

    public Client(int id, String full_name, String phone_number, String email, String address, String user) {
        super(id, full_name, phone_number, email);
        this.address = address;
        this.user = user;
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
