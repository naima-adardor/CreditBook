package com.example.creditbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OperationsAdapter  extends RecyclerView.Adapter<OperationsAdapter.ViewHolder> {


    @NonNull
    @Override
    public OperationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.operation_item_list,parent,false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OperationsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
             public TextView date;
             public TextView balance;
             public TextView typeOperation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textViewDateOperation);
            balance = itemView.findViewById(R.id.textViewBalanceOpeartion);
            typeOperation = itemView.findViewById(R.id.textViewTypeOperation);

        }
    }
}
