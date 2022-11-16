package com.example.creditbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        button = findViewById(R.id.Next_btn3);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(WelcomeActivity.this,home.class);
        startActivity(intent);
    }
}
