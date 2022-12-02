package com.example.credit__book.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Adapter.OperationClientDetailstAdapter;
import com.example.credit__book.Adapter.OperationSupplierDetailsAdapter;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.Model.OperationSupplier;
import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewSupplierDetailsActivity extends AppCompatActivity {

    private TextView supplierName;
    private RecyclerView recyclerView;
    private List<OperationSupplier> listitem;
    private RecyclerView.Adapter adapter;
    private ImageView supplierupdate,back, callSupplier, messageSupplier;
    private String phone, name, email, address;
    private Button gavebtn;
    private Button gobtn;
    private DatabaseReference databaseReference;
    private TextView nbrOperations;


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
        gobtn = findViewById(R.id.gotBtn);
        gavebtn = findViewById(R.id.gaveBtn);
        nbrOperations=findViewById(R.id.nbrOperations);

        supplierName.setText(name);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitem = new ArrayList<>();
        adapter = new OperationSupplierDetailsAdapter(ViewSupplierDetailsActivity.this, listitem);
        recyclerView.setAdapter(adapter);
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> data = sessionManager.getUserDetails();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("OperationsSuppliers").child(data.get(SessionManager.TELEPHONE)).child(phone);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listitem.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    OperationSupplier opSup = dataSnapshot.getValue(OperationSupplier.class);
                    listitem.add(opSup);
                }
                adapter.notifyDataSetChanged();
                nbrOperations.setText("Operations ("+listitem.size()+")");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewSupplierDetailsActivity.this,"Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
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

        gavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewSupplierDetailsActivity.this,CashOutSupplierActivity.class);
                intent.putExtra("Supplier Phone",phone);

                startActivity(new Intent(intent));
            }
        });

        gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewSupplierDetailsActivity.this,CashInSupplierActivity.class);
                intent.putExtra("Supplier Phone",phone);
                startActivity(new Intent(intent));
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