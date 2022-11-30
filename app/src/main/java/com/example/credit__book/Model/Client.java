package com.example.credit__book.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

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
    public Client(int id, String full_name, String phone_number, String email, String address) {
        super(id, full_name, phone_number, email);
        this.address = address;

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
    public static void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Do you want to update these informations").setCancelable(false)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();

    }

}
