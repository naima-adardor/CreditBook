package com.example.credit__book.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Model.Client;
import com.example.credit__book.Model.Supplier;
import com.example.credit__book.R;
import com.example.credit__book.RecycleViewClientInterface;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.MyViewHolder> {

    private final RecycleViewClientInterface clientInterface;
    private Context context;
    private ArrayList<Supplier> suppliersList;

    public SupplierAdapter(RecycleViewClientInterface clientInterface, Context context, ArrayList<Supplier> suppliersList) {
        this.clientInterface = clientInterface;
        this.context = context;
        this.suppliersList = suppliersList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(view, clientInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Supplier supplier = suppliersList.get(position);
        holder.supplierName.setText(supplier.getFull_name());
        holder.supplierPhone.setText(supplier.getPhone_number());
        holder.creationDate.setText(supplier.getDateCreation());

    }

    @Override
    public int getItemCount() {
        return suppliersList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView supplierName, creationDate, supplierPhone;

        public MyViewHolder(@NonNull View itemView, RecycleViewClientInterface clientInterface) {
            super(itemView);

            supplierName = itemView.findViewById(R.id.clientName);
            creationDate = itemView.findViewById(R.id.creationDate);
            supplierPhone = itemView.findViewById(R.id.clientPhone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clientInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            clientInterface.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
