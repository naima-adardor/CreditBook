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

import com.example.credit__book.Activities.ViewDetailsActivity;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.R;

import java.util.List;


public class OperationClientAdapter extends RecyclerView.Adapter<OperationClientAdapter.ViewHolder> {
    private final List<OperationClient> opListe;
    private Context context;

    public OperationClientAdapter(List<OperationClient> list, Context context) {
        this.opListe = list;
        this.context = context;
    }

    @NonNull
    @Override
    public OperationClientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull OperationClientAdapter.ViewHolder holder, int position) {
        OperationClient listItem = opListe.get(position);
        holder.nameClient.setText(listItem.getName_client());
        holder.typeOperation.setText(listItem.getDescription());
        holder.date.setText((listItem.getOperation_client_date() + " "));
        holder.balance.setText((listItem.getBalance_client() + "dh"));
        holder.itemView.setOnClickListener({
                        Intent intent = new Intent(context, ViewDetailsActivity.class);
                        startActivity(context, intent, null);
                    }
                )
    }

    @Override
    public int getItemCount() {
        return opListe.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView balance;
        public TextView typeOperation;
        public TextView nameClient;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            date = itemView.findViewById(R.id.dateOperation);
            balance = itemView.findViewById(R.id.balanceClient);
            typeOperation = itemView.findViewById(R.id.description);
            nameClient = itemView.findViewById(R.id.nameClient);
        }
    }
}
