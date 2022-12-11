package com.example.credit__book.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ViewClientDetailsActivity extends AppCompatActivity  {

    private TextView clientName;
    private RecyclerView recyclerView;
    private List<OperationClient> listOperation;
    /* private RecyclerView.Adapter adapter;*/
    private ImageView clientupdate,back, callClient, messageClient;
    private String  name, email, address;
    public String phone;
    private Button gavebtn;
    private Button gotbtn;
    private DatabaseReference databaseReference;

    private TextView nbrOperations;
    private String key ="";

    private String balance;
    private String description;
    private String typeop;
    private ProgressDialog loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_details);

        name = getIntent().getStringExtra("Client Name");
        phone = getIntent().getStringExtra("Client Phone");
        email = getIntent().getStringExtra("Client Email");
        address = getIntent().getStringExtra("Client Address");



        clientName = findViewById(R.id.clientName);
        recyclerView = findViewById(R.id.recyclerView2);
        clientupdate=findViewById(R.id.clientupdate);
        back=findViewById(R.id.back);
        callClient = findViewById(R.id.callClient);
        messageClient = findViewById(R.id.messageClient);
        gavebtn=findViewById(R.id.gaveBtn);
        gotbtn = findViewById(R.id.gotBtn);

        loader = new ProgressDialog(this);
        clientName.setText(name);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("OperationsClients").child( new SessionManager(this).getUserDetails().get(SessionManager.TELEPHONE)).child(phone);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        listOperation = new ArrayList<>();
         /* adapter = new OperationClientDetailstAdapter(ViewClientDetailsActivity.this, listOperation, new OperationClientDetailstAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(OperationClient op) {

                Intent intent = new Intent(ViewClientDetailsActivity.this, EditClientOperationActivity.class);
                intent.putExtra("Solde",    op.getBalance_client());
                intent.putExtra("note", op.getDescription());
                intent.putExtra("Client Phone", phone);
                intent.putExtra("ID", id);

                startActivity(intent);

            }
        });
        recyclerView.setAdapter(adapter);*/



       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOperation.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    OperationClient operationClient = dataSnapshot.getValue(OperationClient.class);
                    listOperation.add(operationClient);
                }
                  adapter.notifyDataSetChanged();
                  nbrOperations.setText("Operations ("+listOperation.size()+")");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewClientDetailsActivity.this,"Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });*/

        clientupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewClientDetailsActivity.this,EditClientActivity.class);
                intent.putExtra("Client Name", name);
                intent.putExtra("Client Phone", phone);
                intent.putExtra("Client Email", email);
                intent.putExtra("Client Address", address);
                startActivity(new Intent (intent));
            }
        });

        gavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGave();
            }
        });

        gotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGot();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        callClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);



            }
        });


        messageClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", phone);
                startActivity(smsIntent);
            }
        });

    }

    private void addGot() {
        AlertDialog.Builder myDialog=new AlertDialog.Builder(this);
        LayoutInflater inflater= LayoutInflater.from(this);

        View myview=inflater.inflate(R.layout.activity_cashin,null);
        myDialog.setView(myview);

        final AlertDialog dialog=myDialog.create();
        dialog.setCancelable(false);

        final EditText balance_EDIT_TEXT=myview.findViewById(R.id.editTextBalance);
        final EditText note_EDIT_TEXT=myview.findViewById(R.id.editTextNote);
        Button savebtn = myview.findViewById(R.id.buttonSave);
        Button cancel = myview.findViewById(R.id. buttoncancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String balance  = balance_EDIT_TEXT.getText().toString();
                String note = note_EDIT_TEXT.getText().toString();
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                SessionManager sessionManager = new SessionManager(v.getContext());
                HashMap<String, String> data = sessionManager.getUserDetails();


                String id = databaseReference.push().getKey();
                if(!TextUtils.isEmpty(balance)){
                    loader.setMessage("Adding your data");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    OperationClient operationClient = new OperationClient("cash in", format.format(date),balance,note);
                    databaseReference.child(id).setValue(operationClient).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(ViewClientDetailsActivity.this, "The operation has been created successfuly!", Toast.LENGTH_SHORT).show();
                                loader.dismiss();


                            }else {
                                Toast.makeText(ViewClientDetailsActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(ViewClientDetailsActivity.this,"You should enter a balance",Toast.LENGTH_LONG).show();
                }

                dialog.dismiss();
            }
        });



        dialog.show();
    }

    private void addGave() {
        AlertDialog.Builder myDialog=new AlertDialog.Builder(this);
        LayoutInflater inflater= LayoutInflater.from(this);

        View myview=inflater.inflate(R.layout.activity_cashout,null);
        myDialog.setView(myview);

        final AlertDialog dialog=myDialog.create();
        dialog.setCancelable(false);

        final EditText balance_EDIT_TEXT= myview.findViewById(R.id.editTextBalance);
        final EditText note_EDIT_TEXT=myview.findViewById(R.id.editTextNote);
        Button savebtn =myview.findViewById(R.id.buttonSave);
        Button cancel = myview.findViewById(R.id. buttoncancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String balance  = balance_EDIT_TEXT.getText().toString().trim();
                String note = note_EDIT_TEXT.getText().toString().trim();
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                SessionManager sessionManager = new SessionManager(v.getContext());
                HashMap<String, String> data = sessionManager.getUserDetails();
                String id = databaseReference.push().getKey();
                if(TextUtils.isEmpty(balance)){
                    balance_EDIT_TEXT.setError("Balance is Required");
                    return;
                }
                else{
                    loader.setMessage("Adding your data");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    OperationClient operationClient = new OperationClient("cash out", format.format(date),balance,note);
                    databaseReference.child(id).setValue(operationClient).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(ViewClientDetailsActivity.this, "The operation has been created successfuly!", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }else {
                                Toast.makeText(ViewClientDetailsActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }

                dialog.dismiss();

            }
        });



        dialog.show();
    }

    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<OperationClient> options=new FirebaseRecyclerOptions.Builder<OperationClient>()
                .setQuery(databaseReference, OperationClient.class)
                .build();
        FirebaseRecyclerAdapter<OperationClient, MyViewHolder> adapter = new FirebaseRecyclerAdapter<OperationClient, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final OperationClient op) {
                holder.typeOperation.setText(op.getOperationType());
                holder.datetv.setText(op.getOperation_client_date() + "");
                holder.opeartioBalance.setText(op.getBalance_client() +"dh");
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

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        key=getRef(holder.getAdapterPosition()).getKey();
                        balance = op.getBalance_client();
                        description=op.getDescription();
                        typeop=op.getOperationType();

                        updateOperation();
                    }
                });

            }
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row_client_details, parent, false);
                return new MyViewHolder(view);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void updateOperation() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_edit_client_operation, null);
        myDialog.setView(view);

        final AlertDialog dialog = myDialog.create();
        final EditText Sold=view.findViewById(R.id.editTextBalance);
        final EditText  Note=view.findViewById(R.id.editTextNote);
        final Button Update=view.findViewById(R.id.Update);
        final Button Delete=view.findViewById(R.id.Delete);
        Sold.setText(balance);
        Sold.setSelection(balance.length());
        Note.setText(description);
        Note.setSelection(description.length());

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balance = Sold.getText().toString().trim();
                description = Note.getText().toString().trim();
                loader.setMessage("Updating Your Operation");
                loader.show();
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                OperationClient op = new OperationClient(typeop, format.format(date),balance,description);

                databaseReference.child(key).setValue(op).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        loader.dismiss();
                        if (task.isSuccessful()){
                            Toast.makeText(ViewClientDetailsActivity.this, "Data has been updated successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            String err = task.getException().toString();
                            Toast.makeText(ViewClientDetailsActivity.this, "update failed "+err, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                dialog.dismiss();

            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                                loader.setMessage("Deleting");
                                loader.show();
                databaseReference.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        loader.dismiss();
                        if (task.isSuccessful()){
                            Toast.makeText(ViewClientDetailsActivity.this, "Operation deleted successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            String err = task.getException().toString();
                            Toast.makeText(ViewClientDetailsActivity.this, "Failed to delete the operation "+ err, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.dismiss();
            }
        });


        dialog.show();

    }


    /*@Override
    public void onItemClick(int position) {
        Intent intent = new Intent(ViewClientDetailsActivity.this, EditClientOperationActivity.class);
        intent.putExtra("Solde", listOperation.get(position).getBalance_client());
        intent.putExtra("note", listOperation.get(position).getDescription());
        startActivity(intent);
    }*/
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView datetv,opeartioBalance,typeOperation;
        ImageView imgOpType;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            datetv = mView.findViewById(R.id.oparationDate);
            opeartioBalance = mView.findViewById(R.id.oprationBalance);
            imgOpType= mView.findViewById(R.id.iconTypeOperation);
            typeOperation = mView.findViewById(R.id.opearationType);


        }



    }


}
