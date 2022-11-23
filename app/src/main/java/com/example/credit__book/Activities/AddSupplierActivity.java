package com.example.credit__book.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.credit__book.Model.Client;
import com.example.credit__book.Model.Supplier;
import com.example.credit__book.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSupplierActivity extends AppCompatActivity {
    private EditText first_name;
    private EditText last_name;
    private EditText phone;
    private EditText email;
    private EditText adresse;
    private Button add_supplier_btn;
    private DatabaseReference DBreference;
    private FirebaseDatabase DBfirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        first_name = findViewById(R.id.editTextFirstName);
        last_name = findViewById(R.id.editTextLastName);
        phone = findViewById(R.id.editTextPhone);
        email = findViewById(R.id.editTextEmail);
        adresse = findViewById(R.id.editTextAdress);
        add_supplier_btn = findViewById(R.id.btnAddSupplier);
        DBfirebase = FirebaseDatabase.getInstance();
        DBreference = DBfirebase.getReference();
        add_supplier_btn.setOnClickListener(view -> {

            String firstName = first_name.getText().toString();
            String lastName = last_name.getText().toString();
            String phoneNumber = phone.getText().toString();
            String emailSupplier = email.getText().toString();
            String adresseSupplier = adresse.getText().toString();
            Supplier supplier = new Supplier(Integer.parseInt(phoneNumber), firstName + " " + lastName, phoneNumber, emailSupplier, adresseSupplier);
            DBreference.child("suppliers").child(String.valueOf(Supplier.IDsupplier)).setValue(supplier);


        });

    }
}