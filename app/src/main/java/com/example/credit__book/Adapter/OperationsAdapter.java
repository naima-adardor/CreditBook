package com.example.credit__book.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Model.Operation;
import com.example.credit__book.R;
import com.example.credit__book.RecycleViewClientInterface;

import java.util.List;

public class OperationsAdapter  extends RecyclerView.Adapter<OperationsAdapter.ViewHolder> {

    private final List<Operation> opListe;
    private final RecycleViewClientInterface inter;
    public OperationsAdapter(List<Operation> listOperations, RecycleViewClientInterface inter) {
        opListe = listOperations;
        this.inter = inter;
    }

    @NonNull
    @Override
    public OperationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.operation_item_list,parent,false);
         return new ViewHolder(view, inter);
    }

    @Override
    public void onBindViewHolder(@NonNull OperationsAdapter.ViewHolder holder, int position) {
          Operation listItem= opListe.get(position);
          holder.typeOperation.setText(listItem.getType_operation());
          holder.date.setText((listItem.getOperation_date()+" "));
          holder.balance.setText((listItem.getBalance() + "dh"));
    }

    @Override
    public int getItemCount() {
        return opListe.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{
             public TextView date;
             public TextView balance;
             public TextView typeOperation;
        public ViewHolder(@NonNull View itemView ,RecycleViewClientInterface inter) {
            super(itemView);
            date = itemView.findViewById(R.id.textViewDateOperation);
            balance = itemView.findViewById(R.id.textViewBalanceOpeartion);
            typeOperation = itemView.findViewById(R.id.textViewTypeOperation);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(inter !=null){
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            inter.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
