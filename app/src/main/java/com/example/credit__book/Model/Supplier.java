package com.example.credit__book.Model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Supplier extends Person {
    private String address;
    public static int IDsupplier=1;
    private DatabaseReference databaseReference;
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
    public void updateSupplier(int id, String full_name, String phone_number, String email, String address, Context context)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Supplier supplier= new Supplier(Integer.parseInt(id),full_name, phone_number,email,  address);
        databaseReference.child("suppliers").child(id).setValue(supplier).addOnCompleteListener(new OnCompleteListener<Void>() {
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
