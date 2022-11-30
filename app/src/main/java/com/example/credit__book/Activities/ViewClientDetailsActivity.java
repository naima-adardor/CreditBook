package com.example.credit__book.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Adapter.OperationClientDetailstAdapter;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.R;

import java.util.ArrayList;
import java.util.List;


public class ViewClientDetailsActivity extends AppCompatActivity {

    private TextView clientName;
    private RecyclerView recyclerView;
    private List<OperationClient> listitem;
    private RecyclerView.Adapter adapter;
    private ImageView clientupdate,back, callClient, messageClient;
    private String phone, name, email, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_details);

        name = getIntent().getStringExtra("Client Name");
        phone = getIntent().getStringExtra("Client Phone");
        email = getIntent().getStringExtra("Client Email");
        address = getIntent().getStringExtra("Client Address");

        clientName = findViewById(R.id.clientName);
        recyclerView = findViewById(R.id.recyclerView2);
        clientupdate=findViewById(R.id.clientupdate);
        back=findViewById(R.id.back);
        callClient = findViewById(R.id.callClient);
        messageClient = findViewById(R.id.messageClient);

        clientName.setText(name);

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
        adapter = new OperationClientDetailstAdapter(ViewClientDetailsActivity.this, listitem);
        recyclerView.setAdapter(adapter);

        // CountOp = findViewById(R.id.operation);
        //CountOp.setText("Supplier(" + opAD.getItemCount() + ")");
       // count2 = findViewById(R.id.balance);
        //count2.setText("Transactions(" + opAD.getItemCount() + ")");
        clientupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewClientDetailsActivity.this,EditClientActivity.class);
                intent.putExtra("Client Name", name);
                intent.putExtra("Client Phone", phone);
                intent.putExtra("Client Email", email);
                intent.putExtra("Client Address", address);
                startActivity(new Intent (intent));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        callClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);



            }
        });


        messageClient.setOnClickListener(new View.OnClickListener() {
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
