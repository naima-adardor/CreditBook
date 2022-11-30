package com.example.credit__book.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Model.Client;
import com.example.credit__book.R;
import com.example.credit__book.RecycleViewClientInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Client> clientList;
    private final RecycleViewClientInterface clientInterface;

    public ClientAdapter(Context context, ArrayList<Client> clientList, RecycleViewClientInterface clientInterface) {
        this.context = context;
        this.clientList = clientList;
        this.clientInterface = clientInterface;
    }

    public void setFilteredList(ArrayList<Client> filtredList) {
        clientList = filtredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(view, clientInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Client client = clientList.get(position);
        holder.clientName.setText(client.getFull_name());
        holder.clientPhone.setText(client.getPhone_number());
        holder.creationDate.setText(client.getDateCreation());


    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView clientName, creationDate, clientPhone;

        public MyViewHolder(@NonNull View itemView, RecycleViewClientInterface clientInterface) {
            super(itemView);

            clientName = itemView.findViewById(R.id.clientName);
            creationDate = itemView.findViewById(R.id.creationDate);
            clientPhone = itemView.findViewById(R.id.clientPhone);

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
