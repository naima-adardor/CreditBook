package com.example.credit__book.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.credit__book.Model.Supplier;
import com.example.credit__book.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSupplierActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout first_name,last_name,email, phone,adresse;
    private Button add_supplier_btn,Back;
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
        Back=findViewById(R.id.button_back);
        add_supplier_btn.setOnClickListener(this);
        Back.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        DBfirebase = FirebaseDatabase.getInstance();
        DBreference = DBfirebase.getReference();
        switch (view.getId()) {
            case R.id.button_back:
                Intent intent1 = new Intent(AddSupplierActivity.this, client_supplier.class);
                startActivity(intent1);
                break;
            case R.id.AddSupplier:

                String firstName = first_name.getEditText().getText().toString();
                String lastName = last_name.getEditText().getText().toString();
                String phoneNumber = phone.getEditText().getText().toString();
                String emailSupplier = email.getEditText().getText().toString();
                String adresseSupplier = adresse.getEditText().getText().toString();
                if(!validateFirstName(firstName) |  !validateLastName(lastName) | !validateTelephone(phoneNumber) ){
                    return;
                }
                Supplier supplier = new Supplier(Integer.parseInt(phoneNumber), firstName + " " + lastName, phoneNumber, emailSupplier, adresseSupplier);
                DBreference.child("suppliers").child(String.valueOf(Supplier.IDsupplier)).setValue(supplier);

                break;
        }
    }
    private boolean validateFirstName(String firstname) {
        if (firstname.isEmpty()) {
            this.first_name.setError("This Field is Required!");
            return false;
        }else {
            this.first_name.setError(null);
            this.first_name.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateLastName(String lastname) {
        if (lastname.isEmpty()) {
            this.last_name.setError("This Field is Required!");
            return false;
        }else {
            this.last_name.setError(null);
            this.last_name.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateTelephone(String telephone) {
        if (telephone.isEmpty()) {
            this.phone.setError("This Field is Required!");
            return false;
        }else {
            this.phone.setError(null);
            this.phone.setErrorEnabled(false);
            return true;
        }
    }
    }