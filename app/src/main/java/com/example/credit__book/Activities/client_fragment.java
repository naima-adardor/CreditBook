package com.example.credit__book.Activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.credit__book.Adapter.OperationClientAdapter;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.R;

public class client_fragment extends Fragment {

    TextView CountOp;
    TextView count2;
    OperationClientAdapter opAD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client, container, false);

        MyApplication context = (MyApplication) this.getActivity().getApplicationContext();
        for (int i = 0; i < 10; i++) {
            OperationClient listItem = new OperationClient("Naima ELJID", "20-11-2022", 500, "You have to get");
            context.getListClientOperation().add(listItem);
        }
        opAD = new OperationClientAdapter(context.getListClientOperation());
        RecyclerView recyclerViewOperation = getActivity().findViewById(R.id.recyclerViewClient);
        recyclerViewOperation.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewOperation.setAdapter(opAD);
        recyclerViewOperation.setHasFixedSize(true);
        CountOp = getActivity().findViewById(R.id.textViewoperation);
        CountOp.setText("Clients(" + opAD.getItemCount() + ")");
        count2 = getActivity().findViewById(R.id.textViewBalance);
        count2.setText("Transactions(" + opAD.getItemCount() + ")");


        return view;
    }
}
