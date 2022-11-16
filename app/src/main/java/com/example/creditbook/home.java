package com.example.creditbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class home extends AppCompatActivity implements View.OnClickListener {
private LinearLayout c1;
private LinearLayout c2;
private LinearLayout c3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        c1 = findViewById(R.id.profile);
        c2 = findViewById(R.id.cashbook);
        c3 = findViewById(R.id.creditbook);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.profile:
                Intent intent1=new Intent(home.this,profile.class);
                startActivity(intent1);
                break;
            case R.id.cashbook:
                Intent intent2=new Intent(home.this,CashBookActivity.class);
                startActivity(intent2);
                break;
            case R.id.creditbook:
                Intent intent3=new Intent(home.this,client_supplier.class);
                startActivity(intent3);
                break;

    }}
}