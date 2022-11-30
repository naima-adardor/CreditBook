package com.example.credit__book.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Activities.ViewClientDetailsActivity;
import com.example.credit__book.Model.OperationSupplier;
import com.example.credit__book.R;

import java.util.List;

public class OperationSupplierAdapter extends RecyclerView.Adapter<OperationSupplierAdapter.ViewHolder> {

    private final List<OperationSupplier> opListe;
    private Context context;

    public OperationSupplierAdapter(List<OperationSupplier> list,Context context) {
        this.opListe = list;
        this.context=context;
    }



    @NonNull
    @Override
    public OperationSupplierAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OperationSupplierAdapter.ViewHolder holder, int position) {
        OperationSupplier listItem= opListe.get(position);
        holder.nameSupplier.setText(listItem.getName_supplier());
        holder.typeOperation.setText(listItem.getDescription());
        holder.date.setText((listItem.getOperation_supplier_date()+" "));
        holder.balance.setText((listItem.getBalance_supplier() + "dh"));
        holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(context, ViewClientDetailsActivity.class);
                    intent.putExtra("position", position);
                    startActivity(context, intent, null);
                }
        );
    }

    @Override
    public int getItemCount() {
        return opListe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView balance;
        public TextView typeOperation;
        public TextView nameSupplier;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            date = itemView.findViewById(R.id.dateOperation);
//            balance = itemView.findViewById(R.id.balanceClient);
//            typeOperation = itemView.findViewById(R.id.description);
//            nameSupplier = itemView.findViewById(R.id.nameClient);
        }
    }
}
