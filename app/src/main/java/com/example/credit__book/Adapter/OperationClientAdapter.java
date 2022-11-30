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
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.R;

import java.util.List;


public class OperationClientAdapter extends RecyclerView.Adapter<OperationClientAdapter.ViewHolder> {
    private final List<OperationClient> operations;
    private Context context;

    public OperationClientAdapter(List<OperationClient> operations, Context context) {
        this.operations = operations;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView balance;
        public TextView typeOperation;
        public TextView nameClient;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
//            date = itemView.findViewById(R.id.dateOperation);
//            balance = itemView.findViewById(R.id.balanceClient);
//            typeOperation = itemView.findViewById(R.id.description);
//            nameClient = itemView.findViewById(R.id.nameClient);
        }
    }

    @Override
    public OperationClientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_row, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(OperationClientAdapter.ViewHolder holder, int position) {
        OperationClient operation = operations.get(position);

        holder.nameClient.setText(operation.getName_client());
        holder.typeOperation.setText(operation.getDescription());
        holder.date.setText(operation.getOperation_client_date() + " ");
        holder.balance.setText(operation.getBalance_client() + "dh");

        holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(context, ViewClientDetailsActivity.class);
                    intent.putExtra("position", position);
                    startActivity(context, intent, null);
                }
        );
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }


}
