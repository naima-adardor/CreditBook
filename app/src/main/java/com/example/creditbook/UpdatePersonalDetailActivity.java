package com.example.creditbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpdatePersonalDetailActivity extends AppCompatActivity implements View.OnClickListener {
  private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personal_detail);
        back = findViewById(R.id.button_back);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent1=new Intent(UpdatePersonalDetailActivity.this,home.class);
        startActivity(intent1);
    }
}