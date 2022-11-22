package com.example.credit__book.Model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.credit__book.Activities.VerifyPhoneNoActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User extends Person{

    private String password;

    private DatabaseReference databaseReference;

    public User() {
    }

    public User(int id, String full_name, String phone_number, String email, String password) {
        super(id, full_name, phone_number, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addUser(String id, String full_name, String phone_number, String email, String password, Context context)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        User user = new User(Integer.parseInt(id), full_name, phone_number, email, password);
        databaseReference.child("users").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Your account has been created successfuly!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateUser(String id, String full_name, String phone_number, String email, String password, Context context)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        User user = new User(Integer.parseInt(id), full_name, phone_number, email, password);
        databaseReference.child("users").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Your Informations has been updated successfuly!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
