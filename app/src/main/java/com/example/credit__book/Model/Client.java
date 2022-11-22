package com.example.credit__book.Model;

public class Client extends Person {
 public static int IDclient=1;
    public Client(){
        super();

    }
    public Client(String last_name, String first_name, String phone_number, String email, String address) {
        super(last_name, first_name, phone_number, email, address);
        IDclient++;
    }





}
