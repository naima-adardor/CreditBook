package com.example.creditbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InscriptionActivity extends AppCompatActivity {
    private EditText edittext;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        edittext = findViewById(R.id.editTextPhone);
        button = findViewById(R.id.Next_btn);
    }
}