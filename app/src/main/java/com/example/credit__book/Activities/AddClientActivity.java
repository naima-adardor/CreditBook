package com.example.credit__book.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

<<<<<<< HEAD:app/src/main/java/com/example/creditbook/MainActivity.java
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

=======
import com.example.credit__book.R;


public class AddClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
>>>>>>> 0a317c40187149add9397a8b236fa5e489f4f5f6:app/src/main/java/com/example/credit__book/Activities/AddClientActivity.java
    }
    public void onClick(View view) {

                  Intent intent=new Intent(MainActivity.this,InscriptionActivity.class);
                  startActivity(intent);
}}