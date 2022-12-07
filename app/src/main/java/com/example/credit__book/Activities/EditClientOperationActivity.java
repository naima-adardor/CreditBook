package com.example.credit__book.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.credit__book.R;
import com.google.firebase.database.DatabaseReference;

public class EditClientOperationActivity extends AppCompatActivity {
    private EditText Sold,Note;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;
    private Button Delete ,Update;
    private String SoldI, NoteI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client_operation);
        SoldI = getIntent().getStringExtra("Solde");
        NoteI = getIntent().getStringExtra("note");
        Sold=findViewById(R.id.editTextBalance);
        Note=findViewById(R.id.editTextNote);
        Sold.setText(SoldI);
        Note.setText(NoteI);
    }
}