package com.example.credit__book.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.credit__book.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private EditText name;
private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nickname);
        button = findViewById(R.id.Next_btn);
        button.setOnClickListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    public void onClick(View view) {

                  Intent intent=new Intent(MainActivity.this,InscriptionActivity.class);
                  startActivity(intent);
}}