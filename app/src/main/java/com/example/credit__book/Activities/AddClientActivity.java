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

    import com.example.credit__book.Model.Client;
    import com.example.credit__book.Model.SessionManager;
    import com.example.credit__book.R;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.android.material.textfield.TextInputLayout;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;

    import java.util.HashMap;

    public class AddClientActivity extends AppCompatActivity implements View.OnClickListener {
        TextInputLayout first_name,last_name,email, phone,adresse;
        private Button add_client_btn;
        private ImageView Back;
        private DatabaseReference DBreference;
        private FirebaseDatabase DBfirebase;
        private ProgressDialog progressDialog;

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
            Back=findViewById(R.id.back);
            add_client_btn.setOnClickListener(this);
            Back.setOnClickListener(this);
            progressDialog = new ProgressDialog(AddClientActivity.this);
            progressDialog.setTitle("Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        public void onClick(View view) {
            DBfirebase = FirebaseDatabase.getInstance();
            DBreference= DBfirebase.getReference();
            switch(view.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.AddClient:
                    String firstName = first_name.getEditText().getText().toString();
                    String lastName = last_name.getEditText().getText().toString();
                    String phoneNumber = phone.getEditText().getText().toString();
                    String emailClient = email.getEditText().getText().toString();
                    String adresseClient = adresse.getEditText().getText().toString();
                    if(!validateFirstName(firstName) |  !validateFirstName(lastName) | !validateTelephone(phoneNumber) ){
                        return;
                    }
                    progressDialog.setMessage("Verifing Informations");
                    progressDialog.show();
                    SessionManager sessionManager = new SessionManager(view.getContext());
                    HashMap<String, String> data = sessionManager.getUserDetails();
                    Client client = new Client(Integer.parseInt(phoneNumber), firstName + " " + lastName ,phoneNumber,emailClient,adresseClient, data.get(SessionManager.TELEPHONE));
                    DBreference.child("clients " + data.get(SessionManager.TELEPHONE)).child(phoneNumber).setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                Toast.makeText(AddClientActivity.this, "The client has been created successfuly!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddClientActivity.this, AddClientActivity.class));
                                finish();
                            }else {
                                Toast.makeText(AddClientActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
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
