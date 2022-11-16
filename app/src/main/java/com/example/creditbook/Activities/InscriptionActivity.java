package com.example.creditbook.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.creditbook.R;

public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        button = findViewById(R.id.Next_btn2);
        button.setOnClickListener(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    public void onClick(View view) {

        Intent intent=new Intent(InscriptionActivity.this,WelcomeActivity.class);
        startActivity(intent);
    }
    }
