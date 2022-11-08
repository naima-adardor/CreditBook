package com.example.creditbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edittext;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        edittext = findViewById(R.id.editTextPhone);
        button = findViewById(R.id.Next_btn);
        button.setOnClickListener(this);

    }
    public void onClick(View view) {

        Intent intent=new Intent(InscriptionActivity.this,WelcomeActivity.class);
        startActivity(intent);
    }
    }
