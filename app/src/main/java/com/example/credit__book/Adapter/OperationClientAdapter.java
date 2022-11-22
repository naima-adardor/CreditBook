package com.example.credit__book.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Activities.DetailClientFragment;
import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.R;
import com.example.credit__book.recycleview_client_interface;

import java.util.List;

public class OperationClientAdapter extends RecyclerView.Adapter<OperationClientAdapter.ViewHolder> {
    private final List<OperationClient> opListe;
    private final recycleview_client_interface inter;

    public OperationClientAdapter(List<OperationClient> list,recycleview_client_interface inter) {
        this.opListe = list;
        this.inter=inter;
    }

    @NonNull
    @Override
    public OperationClientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent,false);
        return new ViewHolder(view,inter);
    }

    @Override
    public void onBindViewHolder(@NonNull OperationClientAdapter.ViewHolder holder, int position) {
        OperationClient listItem= opListe.get(position);
        holder.nameClient.setText(listItem.getName_client());
        holder.typeOperation.setText(listItem.getDescription());
        holder.date.setText((listItem.getOperation_client_date()+" "));
        holder.balance.setText((listItem.getBalance_client() + "dh"));
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
        @Override

            public void onClick(View v) {
            AppCompatActivity  activity =(AppCompatActivity) v.getContext();
            DetailClientFragment detail=new DetailClientFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.recyclerClient,detail).addToBackStack(null).commit();
        }
        });
    }

    @Override
    public int getItemCount() {
        return opListe.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView balance;
        public TextView typeOperation;
        public TextView nameClient;

        public ViewHolder(@NonNull View itemView,recycleview_client_interface inter) {
            super(itemView);
            date = itemView.findViewById(R.id.dateOperation);
            balance = itemView.findViewById(R.id.balanceClient);
            typeOperation = itemView.findViewById(R.id.description);
            nameClient = itemView.findViewById(R.id.nameClient);
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
