package com.example.credit__book.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.credit__book.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;

public class EditClientSupplierActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView fullNameTxt;
    private TextInputEditText first_nameV,last_nameV,emailV, phoneV,adresseV;
    private TextInputLayout first_name,last_name, email, phone, adresse;
    private Button Delete ,Update;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client_supplier);
   /*     first_nameV = findViewById(R.id.editTextFirstName);
        last_nameV = findViewById(R.id.editTextLastName);
        phoneV = findViewById(R.id.telephone);
        emailV = findViewById(R.id.editTextEmail);
        adresseV = findViewById(R.id.editTextAdress);
        Update=findViewById(R.id.Update);
        Delete=findViewById(R.id.Delete);

        Update.setOnClickListener(this);*/

    }

    @Override
    public void onClick(View view) {
        /*String firstName = first_name.getEditText().getText()+ "";
        String lastName = last_name.getEditText().getText().toString();
        String phoneNumber = phone.getEditText().getText().toString();
        String emailSupplier = email.getEditText().getText().toString();
        String adresseSupplier = adresse.getEditText().getText().toString();
        progressDialog = new ProgressDialog(EditClientSupplierActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Updating Your Informations");
        progressDialog.show();
        Supplier supplier=new Supplier();
        supplier.updateSupplier(phoneNumber, firstName + " " + lastName, phoneNumber, emailSupplier, adresseSupplier,EditClientSupplierActivity.this);
*/
    }
}