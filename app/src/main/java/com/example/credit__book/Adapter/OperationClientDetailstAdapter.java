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


public class OperationClientDetailstAdapter extends RecyclerView.Adapter<OperationClientDetailstAdapter.ViewHolder> {
    private static List<OperationClient> operations;
    private Context context;

    public OperationClientDetailstAdapter(Context context, List operations) {
        this.context = context;
        this.operations = operations;
    }

    public OperationClientDetailstAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_row_client_details, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(OperationClientDetailstAdapter.ViewHolder holder, int position) {
        OperationClient operation = operations.get(position);

//        holder.nameClient.setText(operation.getName_client());
        holder.typeOperation.setText(operation.getDescription());
        holder.date.setText(operation.getOperation_client_date() + " ");
        holder.opeartioBalance.setText(operation.getBalance_client() + "");

//        holder.itemView.setOnClickListener(v -> {
//                    Intent intent = new Intent(context, ViewDetailsActivity.class);
//                    intent.putExtra("position", position);
//                    startActivity(context, intent, null);
//                }
//        );
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView opeartioBalance;
        private TextView typeOperation;
        //        public TextView nameClient;
        private OperationClientDetailstAdapter adapter;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            date = itemView.findViewById(R.id.oparationDate);
            opeartioBalance = itemView.findViewById(R.id.oprationMontant);
            typeOperation = itemView.findViewById(R.id.opearationType);
//            nameClient = itemView.findViewById(R.id.nameClient);
        }

        public ViewHolder linkAdapter(OperationClientDetailstAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }



}
