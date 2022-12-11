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
import com.example.credit__book.Model.OperationClient;
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


public class ViewClientDetailsActivity extends AppCompatActivity  {

    private TextView clientName;
    private RecyclerView recyclerView;
    private List<OperationClient> listOperation;
    private RecyclerView.Adapter adapter;
    private ImageView clientupdate,back, callClient, messageClient;
    private String  name, email, address,id;
    public String phone;
    private Button gavebtn;
    private Button gotbtn;
    private DatabaseReference databaseReference;
    private TextView nbrOperations;
    private String key ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_details);

        name = getIntent().getStringExtra("Client Name");
        phone = getIntent().getStringExtra("Client Phone");
        email = getIntent().getStringExtra("Client Email");
        address = getIntent().getStringExtra("Client Address");

        id = getIntent().getStringExtra("ID");

        clientName = findViewById(R.id.clientName);
        recyclerView = findViewById(R.id.recyclerView2);
        clientupdate=findViewById(R.id.clientupdate);
        back=findViewById(R.id.back);
        callClient = findViewById(R.id.callClient);
        messageClient = findViewById(R.id.messageClient);
        gavebtn=findViewById(R.id.gaveBtn);
        gotbtn = findViewById(R.id.gotBtn);
        nbrOperations = findViewById(R.id.nbrOperations);

        clientName.setText(name);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listOperation = new ArrayList<>();
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> data = sessionManager.getUserDetails();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("OperationsClients").child(data.get(SessionManager.TELEPHONE)).child(phone);
        adapter = new OperationClientDetailstAdapter(ViewClientDetailsActivity.this, listOperation, new OperationClientDetailstAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(OperationClient op) {

                Intent intent = new Intent(ViewClientDetailsActivity.this, EditClientOperationActivity.class);
                intent.putExtra("Solde",    op.getBalance_client());
                intent.putExtra("note", op.getDescription());
                intent.putExtra("Client Phone", phone);
                intent.putExtra("ID", id);

                startActivity(intent);

            }
        });
        recyclerView.setAdapter(adapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOperation.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    OperationClient operationClient = dataSnapshot.getValue(OperationClient.class);
                    listOperation.add(operationClient);
                }
                  adapter.notifyDataSetChanged();
                  nbrOperations.setText("Operations ("+listOperation.size()+")");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewClientDetailsActivity.this,"Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

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

        gavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewClientDetailsActivity.this,CashOutActivity.class);
                intent.putExtra("Client Phone",phone);
                startActivity(new Intent(intent));
            }
        });

        gotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewClientDetailsActivity.this,CashinActivity.class);
                intent.putExtra("Client Phone",phone);
                startActivity(new Intent(intent));
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


    /*@Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ViewClientDetailsActivity.this, EditClientOperationActivity.class);
        intent.putExtra("Solde", listOperation.get(position).getBalance_client());
        intent.putExtra("note", listOperation.get(position).getDescription());
        startActivity(intent);
    }*/


}
