package com.example.credit__book.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit__book.Adapter.OperationClientDetailstAdapter;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.R;

import java.util.ArrayList;
import java.util.List;

public class ViewSupplierDetailsActivity extends AppCompatActivity {

    private TextView supplierName;
    private RecyclerView recyclerView;
    private List<OperationClient> listitem;
    private RecyclerView.Adapter adapter;
    private ImageView supplierupdate,back, callSupplier, messageSupplier;
    private String phone, name, email, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_supplier_details);

        name = getIntent().getStringExtra("Supplier Name");
        phone = getIntent().getStringExtra("Supplier Phone");
        email = getIntent().getStringExtra("Supplier Email");
        address = getIntent().getStringExtra("Supplier Address");

        supplierName = findViewById(R.id.supplierName);
        recyclerView = findViewById(R.id.recyclerView2);
        supplierupdate=findViewById(R.id.supplierupdate);
        back=findViewById(R.id.back);
        callSupplier = findViewById(R.id.callSupplier);
        messageSupplier = findViewById(R.id.messageSupplier);

        supplierName.setText(name);

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
        adapter = new OperationClientDetailstAdapter(ViewSupplierDetailsActivity.this, listitem);
        recyclerView.setAdapter(adapter);

        // CountOp = findViewById(R.id.operation);
        //CountOp.setText("Supplier(" + opAD.getItemCount() + ")");
        // count2 = findViewById(R.id.balance);
        //count2.setText("Transactions(" + opAD.getItemCount() + ")");
        supplierupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewSupplierDetailsActivity.this,EditSupplierActivity.class);
                intent.putExtra("Supplier Name", name);
                intent.putExtra("Supplier Phone", phone);
                intent.putExtra("Supplier Email", email);
                intent.putExtra("Supplier Address", address);
                startActivity(new Intent (intent));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        callSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });

        messageSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", phone);
                startActivity(smsIntent);
            }
        });
    }
}