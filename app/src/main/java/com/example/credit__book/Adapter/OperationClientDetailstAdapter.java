package com.example.credit__book.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        holder.typeOperation.setText(operation.getOperationType());
        holder.date.setText(operation.getOperation_client_date() + "");
        holder.opeartioBalance.setText(operation.getBalance_client() +"dh");
        holder.opeartioBalance.setTextColor(Color.RED);
//        if(operation.getOperationType()=="cash out"){
//            holder.opeartioBalance.setTextColor(Color.RED);
        if("cash out".equals(holder.typeOperation.getText().toString())) {
           holder.opeartioBalance.setTextColor(Color.RED);
           holder.imgOpType.setImageResource(R.drawable.arrow_up);

        }else{
            holder.opeartioBalance.setTextColor(Color.GREEN);
            holder.imgOpType.setImageResource(R.drawable.arrow_in);
        }

    }

    @Override
    public int getItemCount() {
        return operations.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView opeartioBalance;
        private TextView typeOperation;
        private ImageView imgOpType;

        //        public TextView nameClient;
        private OperationClientDetailstAdapter adapter;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            date = itemView.findViewById(R.id.oparationDate);
            opeartioBalance = itemView.findViewById(R.id.oprationBalance);
            typeOperation = itemView.findViewById(R.id.opearationType);
            imgOpType= itemView.findViewById(R.id.iconTypeOperation);
        }

        public ViewHolder linkAdapter(OperationClientDetailstAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }



}
