package com.example.credit__book.Activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.credit__book.Model.Supplier;
import com.example.credit__book.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSupplierActivity extends AppCompatActivity {

    TextInputLayout first_name,last_name,email, phone,adresse;
    private Button add_supplier_btn;
    private DatabaseReference DBreference;
    private FirebaseDatabase DBfirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        first_name = findViewById(R.id.editTextFirstName);
        last_name = findViewById(R.id.editTextLastName);
        phone = findViewById(R.id.telephone);
        email = findViewById(R.id.editTextEmail);
        adresse = findViewById(R.id.editTextAdress);
        add_supplier_btn = findViewById(R.id.AddSupplier);
        DBfirebase = FirebaseDatabase.getInstance();
        DBreference = DBfirebase.getReference();
        add_supplier_btn.setOnClickListener(view -> {

            String firstName = first_name.getEditText().getText().toString();
            String lastName = last_name.getEditText().getText().toString();
            String phoneNumber = phone.getEditText().getText().toString();
            String emailSupplier = email.getEditText().getText().toString();
            String adresseSupplier = adresse.getEditText().getText().toString();
            Supplier supplier = new Supplier(Integer.parseInt(phoneNumber), firstName + " " + lastName, phoneNumber, emailSupplier, adresseSupplier);
            DBreference.child("suppliers").child(String.valueOf(Supplier.IDsupplier)).setValue(supplier);


        });

    }
}