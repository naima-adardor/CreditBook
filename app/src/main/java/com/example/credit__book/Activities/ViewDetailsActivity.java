package com.example.credit__book.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Adapter.OperationClientAdapter;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.R;
import com.example.credit__book.recycleview_client_interface;

public class ViewDetailsActivity extends AppCompatActivity implements recycleview_client_interface {
    TextView CountOp;
    TextView count2;
    OperationClientAdapter opAD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_details);
        MyApplication context = (MyApplication) this.getApplicationContext();
        for (int i = 0; i < 10; i++) {
            OperationClient listItem = new OperationClient("Naima ADARDOR", "20-11-2022", 500, "You have to get");
            context.getListClientOperation().add(listItem);
        }
        OperationClientAdapter opAD = new OperationClientAdapter(context.getListClientOperation(),  this);

        RecyclerView recyclerViewOperation = findViewById(R.id.recycleview2);
        recyclerViewOperation.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOperation.setAdapter(opAD);
        recyclerViewOperation.setHasFixedSize(true);
       // CountOp = findViewById(R.id.operation);
        //CountOp.setText("Supplier(" + opAD.getItemCount() + ")");
       // count2 = findViewById(R.id.balance);
        //count2.setText("Transactions(" + opAD.getItemCount() + ")");
}

    @Override
    public void onItemClick(int post) {

    }
}
