package com.example.credit__book.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Adapter.OperationsAdapter;
import com.example.credit__book.Model.Operation;
import com.example.credit__book.R;
import com.example.credit__book.RecycleViewClientInterface;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CashBookActivity extends AppCompatActivity implements View.OnClickListener, RecycleViewClientInterface {
    TextView CountOp ;
    OperationsAdapter opAD;

    Button ViewRepport;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_book);

        MyApplication context = (MyApplication) this.getApplicationContext();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        for(int i=0 ;i<10;i++){
            Operation listItem = new Operation("11-16-2022",100.00,"Cash in") ;
            context.getListOperations().add(listItem);
        }
//        opAD = new OperationsAdapter(context.getListOperations(), this);
        RecyclerView recyclerViewOperation = findViewById(R.id.recycleViewOperation);
        recyclerViewOperation.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOperation.setAdapter(opAD);
        recyclerViewOperation.setHasFixedSize(true);
        CountOp = findViewById(R.id.textViewoperation);
        CountOp.setText("Operations("+opAD.getItemCount()+")");
        ViewRepport = findViewById(R.id.buttonViewReport);
        ViewRepport.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent1=new Intent(CashBookActivity.this, ViewReportCashbook.class);
        startActivity(intent1);
    }

    public void writeNewOperation(String OpId, String type_op, Double balance,String date) {
        Operation client = new Operation(date,balance,type_op);
        mDatabase.child("clients").child(OpId).setValue(client);
    }

    @Override
    public void onItemClick(int post) {
        Intent intent1=new Intent(CashBookActivity.this, ViewClientDetailsActivity.class);
        startActivity(intent1);
    }
}