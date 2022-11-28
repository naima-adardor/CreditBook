package com.example.credit__book.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Activities.AddClientActivity;
import com.example.credit__book.Activities.MyApplication;
import com.example.credit__book.Adapter.OperationClientAdapter;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.R;
import com.example.credit__book.recycleview_client_interface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class client_fragment extends Fragment implements View.OnClickListener, recycleview_client_interface {

    TextView CountOp;
    TextView countTransaction;
    RecyclerView recyclerViewOperation;
    FloatingActionButton add_client_btn;
    OperationClientAdapter operationClientAdapter;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
//    ArrayList<OperationClient> list_client;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        MyApplication context = (MyApplication) this.getActivity().getApplicationContext();

        recyclerViewOperation = view.findViewById(R.id.recyclerViewClient);
        CountOp = view.findViewById(R.id.textViewoperation);
        countTransaction = view.findViewById(R.id.textViewBalance);
        add_client_btn= view.findViewById(R.id.btnclient);
        add_client_btn.setOnClickListener(this);
        operationClientAdapter = new OperationClientAdapter(context.getListClientOperation(),this.getContext());
        recyclerViewOperation.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewOperation.setHasFixedSize(true);

//        operationClientAdapter = new OperationClientAdapter(context.getListClientOperation(),this.getContext());
        CountOp.setText("Clients(" + operationClientAdapter.getItemCount() + ")");
        countTransaction.setText("Transactions(" + operationClientAdapter.getItemCount() + ")");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference().child("OperationClients").child("1");
        recyclerViewOperation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    add_client_btn.hide();
                }else{
                    add_client_btn.show();
                }
            }
        });

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                context.getListClientOperation().clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    OperationClient operationClient = dataSnapshot.getValue(OperationClient.class);
                    context.getListClientOperation().add(operationClient);

                }

                recyclerViewOperation.setAdapter(operationClientAdapter);
                operationClientAdapter.notifyDataSetChanged();
                Log.d("omg",context.getListClientOperation().size()+"");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        };

          databaseReference.addValueEventListener(valueEventListener);


    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity(), AddClientActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemClick(int post) {

    }
}
