package com.example.credit__book.Model;

public class Person {
    private int id;
    private String full_name;
    private String phone_number;
    private String email;

    public Person() {
    }

    public Person(int id, String full_name, String phone_number, String email) {
        this.id = id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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

}
