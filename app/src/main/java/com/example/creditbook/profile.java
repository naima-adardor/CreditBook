package com.example.creditbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class profile extends AppCompatActivity implements View.OnClickListener  {
    private Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        home = findViewById(R.id.buttonhome);
        home.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent1=new Intent(profile.this,home.class);
        startActivity(intent1);

    }
}