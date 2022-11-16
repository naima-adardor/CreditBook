package com.example.creditbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity implements View.OnClickListener {
private CardView c1;
private CardView c2;
private CardView c3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        c1 = findViewById(R.id.profile);
        c1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(home.this,profile.class);
        startActivity(intent);
    }
}