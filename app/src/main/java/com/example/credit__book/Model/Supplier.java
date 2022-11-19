package com.example.credit__book.Model;

public class Supplier extends Person {
    public static int IDsupplier=1;
    public Supplier(){
        super();

    }


    public Supplier(String last_name, String first_name, String phone_number, String email, String address) {
        super(last_name, first_name, phone_number, email, address);
        IDsupplier++;
    }


}
