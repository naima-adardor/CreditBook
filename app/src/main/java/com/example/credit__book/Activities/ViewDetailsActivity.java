package com.example.credit__book.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Adapter.OperationClientAdapter;
import com.example.credit__book.Adapter.OperationClientDetailstAdapter;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.R;

import java.util.ArrayList;
import java.util.List;


public class ViewDetailsActivity extends AppCompatActivity {
    TextView CountOp;
    TextView count2;
    OperationClientAdapter opAD;
    private RecyclerView recyclerView;
    private List<OperationClient> listitem;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_details);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitem = new ArrayList<>();

        listitem.add(new OperationClient("Mohammed Elachyry", "11/11/2022", "80.0", "You Got"));
        listitem.add(new OperationClient("Mohammed Elachyry", "11/11/2022", "80.0", "You Got"));
        listitem.add(new OperationClient("Mohammed Elachyry", "12/11/2022", "80.0", "You Got"));
        listitem.add(new OperationClient("Mohammed Elachyry", "12/11/2022", "80.0", "You Got"));
        listitem.add(new OperationClient("Mohammed Elachyry", "13/11/2022", "80.0", "You Gave"));
        listitem.add(new OperationClient("Mohammed Elachyry", "14/11/2022", "80.0", "You Got"));
        listitem.add(new OperationClient("Mohammed Elachyry", "14/11/2022", "80.0", "You Got"));
        listitem.add(new OperationClient("Mohammed Elachyry", "14/11/2022", "80.0", "You Got"));
        listitem.add(new OperationClient("Mohammed Elachyry", "14/11/2022", "80.0", "You Gave"));
        listitem.add(new OperationClient("Mohammed Elachyry", "14/11/2022", "80.0", "You Gave"));
        adapter = new OperationClientDetailstAdapter(ViewDetailsActivity.this, listitem);
        recyclerView.setAdapter(adapter);
        // CountOp = findViewById(R.id.operation);
        //CountOp.setText("Supplier(" + opAD.getItemCount() + ")");
       // count2 = findViewById(R.id.balance);
        //count2.setText("Transactions(" + opAD.getItemCount() + ")");
}
//
//    @Override
//    public void onItemClick(int post) {
//
//    }

}
