package com.example.credit__book.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Activities.AddSupplierActivity;
import com.example.credit__book.Activities.MyApplication;
import com.example.credit__book.Activities.ViewClientDetailsActivity;
import com.example.credit__book.Activities.ViewSupplierDetailsActivity;
import com.example.credit__book.Adapter.SupplierAdapter;
import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.Model.Supplier;
import com.example.credit__book.R;
import com.example.credit__book.RecycleViewClientInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class supplier_fragment extends Fragment  implements View.OnClickListener, RecycleViewClientInterface {

    SupplierAdapter supplierAdapter;
    RecyclerView recyclerView;

    FloatingActionButton ajouter;
    DatabaseReference database;
    ArrayList<Supplier> suppliersList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supplier, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyApplication context = (MyApplication) this.getActivity().getApplicationContext();

        recyclerView = view.findViewById(R.id.recyclerViewSupplier);
        SessionManager sessionManager = new SessionManager(context);
        HashMap<String, String> data = sessionManager.getUserDetails();
        database = FirebaseDatabase.getInstance().getReference("suppliers "+ data.get(SessionManager.TELEPHONE));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        suppliersList = new ArrayList<>();
        supplierAdapter = new SupplierAdapter(this, context, suppliersList);
        recyclerView.setAdapter(supplierAdapter);

        ajouter= view.findViewById(R.id.btnAddsupplier);
        ajouter.setOnClickListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    ajouter.hide();
                }else{
                    ajouter.show();
                }
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                suppliersList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Supplier supplier = dataSnapshot.getValue(Supplier.class);
                    suppliersList.add(supplier);
                }
                supplierAdapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity(), AddSupplierActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), ViewSupplierDetailsActivity.class);
        intent.putExtra("Supplier Name", suppliersList.get(position).getFull_name());
        intent.putExtra("Supplier Phone", suppliersList.get(position).getPhone_number());
        intent.putExtra("Supplier Email", suppliersList.get(position).getEmail());
        intent.putExtra("Supplier Address", suppliersList.get(position).getAddress());
        startActivity(intent);
    }
}