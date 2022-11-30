package com.example.credit__book.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit__book.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;

public class EditClientActivity extends AppCompatActivity implements View.OnClickListener {

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

        name = getIntent().getStringExtra("Client Name");
        phoneI = getIntent().getStringExtra("Client Phone");
        emailI = getIntent().getStringExtra("Client Email");
        address = getIntent().getStringExtra("Client Address");

        String[] arrStr = name.split(" ");

        first_nameV.setText(arrStr[0]);
        last_nameV.setText(arrStr[1]);
        phoneV.setText(phoneI);
        emailV.setText(emailI);
        adresseV.setText(address);

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
    }
}