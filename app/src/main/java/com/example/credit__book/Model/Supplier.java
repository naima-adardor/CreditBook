package com.example.credit__book.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Supplier extends Person {

    private String address;
    private String user;
    private String dateCreation;

    private DatabaseReference databaseReference;

    public Supplier() {
        super();

    }


    public Supplier(int id, String full_name, String phone_number, String email, String address, String user, String dateCreation) {
        super(id, full_name, phone_number, email);
        this.address = address;
        this.user = user;
        this.dateCreation = dateCreation;
    }
    public Supplier(int id, String full_name, String phone_number, String email, String address) {
        super(id, full_name, phone_number, email);
        this.address = address;

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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
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
    public void updateSupplier(String id, String full_name, String phone_number, String email, String address, Context context) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        SessionManager sessionManager = new SessionManager(context);
        HashMap<String, String> data = sessionManager.getUserDetails();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
       Supplier supplier = new Supplier(Integer.parseInt(phone_number), full_name, phone_number, email, address , data.get(SessionManager.TELEPHONE), format.format(date));
       databaseReference.child("suppliers").child(id).setValue(supplier).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
            public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful()) {
                   Toast.makeText(context, "Your Informations has been updated successfuly!", Toast.LENGTH_SHORT).show();
               } else {
                 Toast.makeText(context, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
              }
         }
     });
    }
}
