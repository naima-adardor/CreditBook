package com.example.creditbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class CashBookActivity extends AppCompatActivity {
    TextView CountOp ;
    OperationsAdapter opAD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_book);

        MyApplication context = (MyApplication) this.getApplicationContext();

        for(int i=0 ;i<10;i++){
            Operation listItem = new Operation("11-16-2022",100.00,"Cash in") ;
            context.getListOperations().add(listItem);
        }
        opAD = new OperationsAdapter(context.getListOperations());
        RecyclerView recyclerViewOperation = findViewById(R.id.recycleViewOperation);
        recyclerViewOperation.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOperation.setAdapter(opAD);
        recyclerViewOperation.setHasFixedSize(true);
        CountOp = findViewById(R.id.textViewoperation);
        CountOp.setText("Operations("+opAD.getItemCount()+")");

    }
}