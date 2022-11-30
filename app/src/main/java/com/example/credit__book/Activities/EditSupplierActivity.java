package com.example.credit__book.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.Model.Supplier;
import com.example.credit__book.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class EditSupplierActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView fullNameTxt;
    private TextInputEditText first_nameV,last_nameV,emailV, phoneV,adresseV;
    private TextInputLayout first_name,last_name, email, phone, adresse;
    private Button Delete ,Update;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private ImageView Back;
    private String phoneI, name, emailI, address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);
        Back=findViewById(R.id.back);
        Back.setOnClickListener(this);

        first_name = findViewById(R.id.editTextFirstName);
        last_name = findViewById(R.id.editTextLastName);
        email = findViewById(R.id.editTextEmail);
        phone = findViewById(R.id.telephone);
        adresse = findViewById(R.id.editTextAdress);

        first_nameV = findViewById(R.id.FirstName);
        last_nameV = findViewById(R.id.LastName);
        emailV = findViewById(R.id.email);
        phoneV = findViewById(R.id.tele);
        adresseV = findViewById(R.id.adress);

        name = getIntent().getStringExtra("Supplier Name");
        phoneI = getIntent().getStringExtra("Supplier Phone");
        emailI = getIntent().getStringExtra("Supplier Email");
        address = getIntent().getStringExtra("Supplier Address");

        String[] arrStr = name.split(" ");

        first_nameV.setText(arrStr[0]);
        last_nameV.setText(arrStr[1]);
        phoneV.setText(phoneI);
        emailV.setText(emailI);
        adresseV.setText(address);
        phoneV.setFocusable(false);

        Update=findViewById(R.id.Update);
        Delete=findViewById(R.id.Delete);

        progressDialog = new ProgressDialog(EditSupplierActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Do you want to update these informations").setCancelable(false)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String firstName = first_name.getEditText().getText()+ "";
                                String lastName = last_name.getEditText().getText().toString();
                                String phoneNumber = phone.getEditText().getText().toString();
                                String emailSupplier = email.getEditText().getText().toString();
                                String adresseSupplier = adresse.getEditText().getText().toString();
                                progressDialog.setMessage("Updating Your supplier informations");
                                progressDialog.show();
                                SessionManager sessionManager = new SessionManager(view.getContext());
                                HashMap<String, String> data = sessionManager.getUserDetails();
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


                                Date date = new Date();
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                Supplier supplier = new Supplier(Integer.parseInt(phoneNumber), firstName + " " + lastName, phoneNumber, emailSupplier, adresseSupplier );
                                databaseReference.child("suppliers " + data.get(SessionManager.TELEPHONE)).child(phoneNumber).setValue(supplier).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressDialog.dismiss();
                                        if (task.isSuccessful()) {
                                            Toast.makeText(EditSupplierActivity.this, "Your Informations has been updated successfuly!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(EditSupplierActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure You want to delete this supplier").setCancelable(false)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                progressDialog.setMessage("Deleting");
                                progressDialog.show();
                                SessionManager sessionManager = new SessionManager(view.getContext());
                                HashMap<String, String> data = sessionManager.getUserDetails();
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                                String firstName = first_name.getEditText().getText()+ "";
                                String lastName = last_name.getEditText().getText().toString();
                                String phoneNumber = phone.getEditText().getText().toString();
                                String emailSupplier = email.getEditText().getText().toString();
                                String adresseSupplier = adresse.getEditText().getText().toString();
                                databaseReference.child("suppliers " + data.get(SessionManager.TELEPHONE)).child(phoneNumber).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressDialog.dismiss();
                                        if (task.isSuccessful()) {
                                            Toast.makeText(EditSupplierActivity.this, "Your Supplier has been deleted successfuly!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(EditSupplierActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                finish();
                break;
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
}}