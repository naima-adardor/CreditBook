package com.example.credit__book.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.Model.OperationSupplier;
import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CashInSupplierActivity extends AppCompatActivity {
    private Button savebtn;
    private EditText balance_EDIT_TEXT;
    private  EditText note_EDIT_TEXT;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in_supplier);
        String phoneSup = getIntent().getStringExtra("Supplier Phone");
        savebtn = findViewById(R.id.buttonSave);
        balance_EDIT_TEXT = findViewById(R.id.editTextBalance);
        note_EDIT_TEXT=findViewById(R.id.editTextNote);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference();
                String balance  = balance_EDIT_TEXT.getText().toString();
                String note = note_EDIT_TEXT.getText().toString();
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                SessionManager sessionManager = new SessionManager(view.getContext());
                HashMap<String, String> data = sessionManager.getUserDetails();
                String id = databaseReference.push().getKey();
                if(!TextUtils.isEmpty(balance)){
                    OperationSupplier operationSupplier = new OperationSupplier(format.format(date),balance,note,"cash in" );
                    databaseReference.child("OperationsSuppliers").child(data.get(SessionManager.TELEPHONE)).child(phoneSup).child(id).setValue(operationSupplier).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CashInSupplierActivity.this, "The operation has been created successfuly!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CashInSupplierActivity.this, CashInSupplierActivity.class));
                                finish();
                            }else {
                                Toast.makeText(CashInSupplierActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(CashInSupplierActivity.this,"You should enter a balance",Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}