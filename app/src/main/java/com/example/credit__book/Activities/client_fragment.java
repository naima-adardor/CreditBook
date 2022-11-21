package com.example.credit__book.Activities;

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




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyApplication context = (MyApplication) this.getActivity().getApplicationContext();
        for (int i = 0; i < 10; i++) {
            OperationClient listItem = new OperationClient("Naima ELJID", "20-11-2022", 500, "You have to get");
            context.getListClientOperation().add(listItem);
        }
        opAD = new OperationClientAdapter(context.getListClientOperation());

        RecyclerView recyclerViewOperation = view.findViewById(R.id.recyclerViewClient);
        recyclerViewOperation.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewOperation.setAdapter(opAD);
        recyclerViewOperation.setHasFixedSize(true);
        CountOp = view.findViewById(R.id.textViewoperation);
        CountOp.setText("Clients(" + opAD.getItemCount() + ")");
        count2 = view.findViewById(R.id.textViewBalance);
        count2.setText("Transactions(" + opAD.getItemCount() + ")");
    }
}
