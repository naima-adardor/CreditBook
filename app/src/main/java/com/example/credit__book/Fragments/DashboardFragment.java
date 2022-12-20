package com.example.credit__book.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class DashboardFragment extends Fragment {

    DatabaseReference client, supplier, cashIn, cashOut;
    TextView clientCount, cashOutCount, cashinCount, supplierCount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);
        clientCount = view.findViewById(R.id.clientCount);
        cashOutCount = view.findViewById(R.id.cashOutCount);
        cashinCount = view.findViewById(R.id.cashinCount);
        supplierCount = view.findViewById(R.id.supplierCount);
        SessionManager sessionManager = new SessionManager(getContext());
        HashMap<String, String> data = sessionManager.getUserDetails();
        System.out.println("testtttttttttt" + data.get(SessionManager.TELEPHONE));
        client = FirebaseDatabase.getInstance().getReference("clients "+ data.get(SessionManager.TELEPHONE));
        supplier = FirebaseDatabase.getInstance().getReference("suppliers "+ data.get(SessionManager.TELEPHONE));
        cashIn = FirebaseDatabase.getInstance().getReference("OperationsClients");
        cashOut = FirebaseDatabase.getInstance().getReference("clients "+ data.get(SessionManager.TELEPHONE));
        client.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    clientCount.setText(snapshot.getChildrenCount() + "");
                }else {
                    clientCount.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        supplier.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    supplierCount.setText(snapshot.getChildrenCount() + "");
                }else {
                    supplierCount.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cashinCount.setText(sessionManager.getCashin() +"");
        cashOutCount.setText(sessionManager.getCashOut() + "");
        return view;
    }
}