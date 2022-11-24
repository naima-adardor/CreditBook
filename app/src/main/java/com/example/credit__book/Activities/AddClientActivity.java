    package com.example.credit__book.Activities;

    import android.os.Bundle;
    import android.widget.Button;

    import androidx.appcompat.app.AppCompatActivity;

    import com.example.credit__book.Model.Client;
    import com.example.credit__book.R;
    import com.google.android.material.textfield.TextInputLayout;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;

    public class AddClientActivity extends AppCompatActivity {
        TextInputLayout first_name,last_name,email, phone,adresse;
        private Button add_client_btn;
        private DatabaseReference DBreference;
        private FirebaseDatabase DBfirebase;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_client);


            first_name = findViewById(R.id.editTextFirstName);
            last_name = findViewById(R.id.editTextLastName);
            phone = findViewById(R.id.telephone);
            email= findViewById(R.id.editTextEmail);
            adresse = findViewById(R.id.editTextAdress);
            add_client_btn=findViewById(R.id.AddClient);
            DBfirebase = FirebaseDatabase.getInstance();
            DBreference= DBfirebase.getReference();


            add_client_btn.setOnClickListener(view -> {
                String firstName = first_name.getEditText().getText().toString();
                String lastName = last_name.getEditText().getText().toString();
                String phoneNumber = phone.getEditText().getText().toString();
                String emailClient = email.getEditText().getText().toString();
                String adresseClient = adresse.getEditText().getText().toString();
                Client client = new Client(Client.IDclient, firstName + " " + lastName ,phoneNumber,emailClient,adresseClient);
                DBreference.child("clients").child(String.valueOf(Client.IDclient)).setValue(client);

            });


        }}
