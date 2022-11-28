package com.example.credit__book.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Activities.AddSupplierActivity;
import com.example.credit__book.Activities.MyApplication;
import com.example.credit__book.Adapter.OperationClientAdapter;
import com.example.credit__book.Adapter.OperationSupplierAdapter;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.Model.OperationSupplier;
import com.example.credit__book.R;
import com.example.credit__book.recycleview_client_interface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class supplier_fragment extends Fragment  implements View.OnClickListener, recycleview_client_interface {

    TextView CountOp;
    TextView count2;
    OperationSupplierAdapter opAD;
    RecyclerView recyclerViewSupplier;
    FloatingActionButton btnAddsupplier;

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
//        for (int i = 0; i < 10; i++) {
//            OperationSupplier listItem = new OperationSupplier("Naima ELJID", "20-11-2022", "500", "You have to get");
//            context.getListClientOperation().add(listItem);
//        }
        opAD = new OperationSupplierAdapter(context.getListSupplierOperation(),this.getContext());

        recyclerViewSupplier = view.findViewById(R.id.recyclerViewSupplier);
        recyclerViewSupplier.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSupplier.setAdapter(opAD);
        recyclerViewSupplier.setHasFixedSize(true);
        CountOp = view.findViewById(R.id.textViewoperation);
        CountOp.setText("Supplier(" + opAD.getItemCount() + ")");
        count2 = view.findViewById(R.id.textViewBalance);
        count2.setText("Transactions(" + opAD.getItemCount() + ")");
        btnAddsupplier=view.findViewById(R.id.btnAddsupplier);
        btnAddsupplier.setOnClickListener(this);
        recyclerViewSupplier.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    btnAddsupplier.hide();
                }else{
                    btnAddsupplier.show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity(), AddSupplierActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int post) {

    }
}