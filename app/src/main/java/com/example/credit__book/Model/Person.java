package com.example.credit__book.Model;

public class Person {
    int id;
    String last_name;
    String first_name;
    String phone_number;
    String email;
    String address;

    public Person(int id, String last_name, String first_name, String phone_number, String email, String address) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
