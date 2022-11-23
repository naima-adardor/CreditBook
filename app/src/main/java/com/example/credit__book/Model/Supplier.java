package com.example.credit__book.Model;

public class Supplier extends Person {
    private String address;
    public static int IDsupplier=1;
    public Supplier(){
        super();

    }


    public Supplier(int id, String full_name, String phone_number, String email, String address) {
        super(id, full_name, phone_number, email);
        this.address = address;
        IDsupplier++;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
