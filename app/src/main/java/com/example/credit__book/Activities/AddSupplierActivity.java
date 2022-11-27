package com.example.credit__book.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.Model.Supplier;
import com.example.credit__book.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddSupplierActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout first_name,last_name,email, phone,adresse;
    private Button add_supplier_btn;
    private ImageView Back;
    private DatabaseReference DBreference;
    private FirebaseDatabase DBfirebase;
    private ProgressDialog progressDialog;

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
        Back=findViewById(R.id.back);
        add_supplier_btn.setOnClickListener(this);
        Back.setOnClickListener(this);
        progressDialog = new ProgressDialog(AddSupplierActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

    }

    @Override
    public void onClick(View view) {

        DBfirebase = FirebaseDatabase.getInstance();
        DBreference = DBfirebase.getReference();
        switch (view.getId()) {
            case R.id.back:
                Intent intent1 = new Intent(AddSupplierActivity.this, client_supplier.class);
                startActivity(intent1);
                break;
            case R.id.AddSupplier:

                String firstName = first_name.getEditText().getText().toString();
                String lastName = last_name.getEditText().getText().toString();
                String phoneNumber = phone.getEditText().getText().toString();
                String emailSupplier = email.getEditText().getText().toString();
                String adresseSupplier = adresse.getEditText().getText().toString();
                if(!validateFirstName(firstName) |  !validateFirstName(lastName) | !validateTelephone(phoneNumber) ){
                    return;
                }
                SessionManager sessionManager = new SessionManager(view.getContext());
                HashMap<String, String> data = sessionManager.getUserDetails();
                Supplier supplier = new Supplier(Integer.parseInt(phoneNumber), firstName + " " + lastName, phoneNumber, emailSupplier, adresseSupplier, data.get(SessionManager.TELEPHONE));
                DBreference.child("suppliers " + data.get(SessionManager.TELEPHONE)).child(phoneNumber).setValue(supplier).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(AddSupplierActivity.this, "The supplier has been created successfuly!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddSupplierActivity.this, AddClientActivity.class));
                            finish();
                        }else {
                            Toast.makeText(AddSupplierActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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