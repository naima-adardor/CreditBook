package com.example.creditbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private Spinner spinner;
private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         spinner = findViewById(R.id.spinnerCountry);
        button = findViewById(R.id.Next_btn);
        button.setOnClickListener(this);

    }
    public void onClick(View view) {

                  Intent intent=new Intent(MainActivity.this,InscriptionActivity.class);
                  startActivity(intent);
}}