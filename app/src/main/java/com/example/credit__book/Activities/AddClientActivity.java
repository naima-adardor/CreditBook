package com.example.credit__book.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.credit__book.Model.Client;
import com.example.credit__book.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddClientActivity extends AppCompatActivity {

    private EditText first_name;
    private EditText last_name;
    private EditText phone;
    private EditText  email;
    private EditText adresse;
    private Button add_client_btn;
    private DatabaseReference DBreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);


        first_name = findViewById(R.id.editTextFirstName);
        last_name = findViewById(R.id.editTextLastName);
        phone = findViewById(R.id.editTextPhone);
        email= findViewById(R.id.editTextEmail);
        adresse = findViewById(R.id.editTextAdress);
        add_client_btn=findViewById(R.id.buttonAddClient);

        DBreference= FirebaseDatabase.getInstance().getReference();


        add_client_btn.setOnClickListener(view -> {
            String firstName = first_name.getText().toString();
            String lastName = last_name.getText().toString();
            String phoneNumber = phone.getText().toString();
            String emailClient = email.getText().toString();
            String adresseClient = adresse.getText().toString();

            Client client = new Client(lastName,firstName,phoneNumber,emailClient,adresseClient);
            DBreference.child("clients").setValue("client");

        });


    }
}