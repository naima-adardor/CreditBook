package com.example.credit__book.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.credit__book.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener  {
    private Button home;
    private Button detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        home = findViewById(R.id.buttonhome);
        detail = findViewById(R.id.updatedetail);
        home.setOnClickListener(this);
        detail.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonhome:
        Intent intent1=new Intent(ProfileActivity.this, HomeAtivity.class);
        startActivity(intent1);
            break;
            case R.id.updatedetail:
                Intent intent2=new Intent(ProfileActivity.this,UpdatePersonalDetailActivity.class);
                startActivity(intent2);
                break;

    }}
}