    package com.example.credit__book.Activities;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;

    import androidx.appcompat.app.AppCompatActivity;

    import com.example.credit__book.Model.Client;
    import com.example.credit__book.R;
    import com.google.android.material.textfield.TextInputLayout;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;

    public class AddClientActivity extends AppCompatActivity implements View.OnClickListener {
        TextInputLayout first_name,last_name,email, phone,adresse;
        private Button add_client_btn ,Back;
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
            Back=findViewById(R.id.button_back);
            add_client_btn.setOnClickListener(this);
            Back.setOnClickListener(this);






        }

        @Override
        public void onClick(View view) {
            DBfirebase = FirebaseDatabase.getInstance();
            DBreference= DBfirebase.getReference();
            switch(view.getId()){
                case R.id.button_back:
                    Intent intent1=new Intent(AddClientActivity.this, client_supplier.class);
                    startActivity(intent1);
                    break;
                case R.id.AddClient:
                    String firstName = first_name.getEditText().getText().toString();
                    String lastName = last_name.getEditText().getText().toString();
                    String phoneNumber = phone.getEditText().getText().toString();
                    String emailClient = email.getEditText().getText().toString();
                    String adresseClient = adresse.getEditText().getText().toString();
                    Client client = new Client(Client.IDclient, firstName + " " + lastName ,phoneNumber,emailClient,adresseClient);
                    DBreference.child("clients").child(String.valueOf(Client.IDclient)).setValue(client);

                    break;
        }
    }}
