package com.example.credit__book.Fragments;

import static java.lang.Float.parseFloat;

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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Activities.AddClientActivity;
import com.example.credit__book.Activities.MyApplication;
import com.example.credit__book.Activities.ViewClientDetailsActivity;
import com.example.credit__book.Adapter.ClientAdapter;
import com.example.credit__book.Model.Client;
import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.R;
import com.example.credit__book.RecycleViewClientInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class client_fragment extends Fragment implements View.OnClickListener, RecycleViewClientInterface {
    ClientAdapter clientAdapter;
    RecyclerView recyclerView;
    SearchView searchView;
    FloatingActionButton add_client_btn;
    DatabaseReference database;
    ArrayList<Client> clientList;
    TextView nbr_clients;
    TextView Cashin, Cashout;
    float cashIn = 0;
    float cashOut = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*int strtext = getArguments().getInt("key",0);*/
        View view = inflater.inflate(R.layout.fragment_client, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayCashinAndCashOut();

        MyApplication context = (MyApplication) this.getActivity().getApplicationContext();
        nbr_clients = view.findViewById(R.id.textViewClientNbr);

        searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerView = view.findViewById(R.id.recyclerViewClient);
        SessionManager sessionManager = new SessionManager(context);
        HashMap<String, String> data = sessionManager.getUserDetails();
        database = FirebaseDatabase.getInstance().getReference("clients " + data.get(SessionManager.TELEPHONE));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        clientList = new ArrayList<>();
        clientAdapter = new ClientAdapter(context, clientList, this);
        Cashin = view.findViewById(R.id.cashin);
        Cashout = view.findViewById(R.id.cashout);
        recyclerView.setAdapter(clientAdapter);

        add_client_btn = view.findViewById(R.id.btnclient);
        add_client_btn.setOnClickListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    add_client_btn.hide();
                } else {
                    add_client_btn.show();
                }
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clientList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Client client = dataSnapshot.getValue(Client.class);
                    clientList.add(client);

                }
                clientAdapter.notifyDataSetChanged();
                nbr_clients.setText("Clients (" + clientList.size() + ")");
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void filterList(String text) {
        ArrayList<Client> filteredList = new ArrayList<>();
        for (Client item : clientList) {
            if (item.getFull_name().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this.getActivity(), "No Data Found !", Toast.LENGTH_SHORT).show();
        } else {
            clientAdapter.setFilteredList(filteredList);
        }
    }

    //    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//        MyApplication context = (MyApplication) this.getActivity().getApplicationContext();
//     /*   for (int i = 0; i < 10; i++) {
//            OperationClient listItem = new OperationClient("Naima ELJID", "20-11-2022", 500, "You have to get");
//            context.getListClientOperation().add(listItem);
//        }*/
//
//
//        RecyclerView recyclerViewOperation = view.findViewById(R.id.recyclerViewClient);
//        db= FirebaseDatabase.getInstance().getReference("OperationClients");
//        recyclerViewOperation.setLayoutManager(new LinearLayoutManager(getContext()));
//        client =new ArrayList<>();
//        opAD = new OperationClientAdapter(context.getListClientOperation(),this.getContext());
//
//        recyclerViewOperation.setHasFixedSize(true);
//        CountOp = view.findViewById(R.id.textViewoperation);
//        CountOp.setText("Clients(" + opAD.getItemCount() + ")");
//        countTransaction = view.findViewById(R.id.textViewBalance);
//        countTransaction.setText("Transactions(" + opAD.getItemCount() + ")");
//        ajouter= view.findViewById(R.id.btnclient);
//        recyclerViewOperation.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if(dy>0){
//                    ajouter.hide();
//                }else{
//                    ajouter.show();
//                }
//            }
//        });
//
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                client.clear();
//                for(DataSnapshot dataSnapshot :snapshot.child("OperationClients").getChildren()){
//                    if(dataSnapshot.hasChild("operation_client_date")&&dataSnapshot.hasChild("balance_client")&&dataSnapshot.hasChild("description")&&dataSnapshot.hasChild("name_client")){
//                    final String date=dataSnapshot.child("operation_client_date").getValue(String.class);
//                    final String balance_client=dataSnapshot.child("balance_client").getValue(String.class);
//                    final String description=dataSnapshot.child("description").getValue(String.class);
//                    final String name_client=dataSnapshot.child("name_client").getValue(String.class);
//                  /*  OperationClient operationClient= dataSnapshot.getValue(OperationClient.class);
//                    client.add(operationClient);*/
//                    OperationClient operationClient=new OperationClient(name_client,date,balance_client,description);
//                    client.add(operationClient);
//                }}
//                recyclerViewOperation.setAdapter(opAD);
//                opAD.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        ajouter.setOnClickListener(this);
//
//    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), AddClientActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), ViewClientDetailsActivity.class);
        intent.putExtra("Client Name", clientList.get(position).getFull_name());
        intent.putExtra("Client Phone", clientList.get(position).getPhone_number());
        intent.putExtra("Client Email", clientList.get(position).getEmail());
        intent.putExtra("Client Address", clientList.get(position).getAddress());
        startActivity(intent);
    }

    public void displayCashinAndCashOut() {
        FirebaseDatabase dbRef = FirebaseDatabase.getInstance();

        dbRef.getReference("OperationsClients").child(Objects.requireNonNull(new SessionManager(getContext()).getUserDetails().get(SessionManager.TELEPHONE))).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            if (snapshot1.child("operationType").getValue().toString().equalsIgnoreCase("Cash In")) {
                                cashIn += parseFloat(snapshot1.child("balance_client").getValue().toString());
                            } else {
                                cashOut += parseFloat(snapshot1.child("balance_client").getValue().toString());
                            }
                        }
                    }
                }
                Log.i("debug", "cashIn: " + cashIn + " cashOut: " + cashOut);
                // format to morrocan dirham
                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("fr", "MA"));
                Cashin.setText(formatter.format(cashIn));
                Cashout.setText(formatter.format(cashOut));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("debug", "onFailure: " + e.getMessage());
            }
        });
    }
}
