package com.example.credit__book.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.credit__book.Model.OperationClient;
import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class EditClientOperationActivity extends AppCompatActivity {
    private EditText Sold,Note;

    private ProgressDialog progressDialog;
    private Button Delete ,Update;
    private String SoldI, NoteI;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client_operation);
        SoldI = getIntent().getStringExtra("Solde");
        NoteI = getIntent().getStringExtra("note");

        String phoneSup = getIntent().getStringExtra("Client Phone");

        Sold=findViewById(R.id.editTextBalance);
        Note=findViewById(R.id.editTextNote);
        Update=findViewById(R.id.Update);
        Delete=findViewById(R.id.Delete);

        Sold.setText(SoldI);
        Note.setText(NoteI);

        progressDialog = new ProgressDialog(EditClientOperationActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

   Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Do you want to update this operation").setCancelable(false)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String Solde = Sold.getText().toString();
                                String Notee = Note.getText().toString();
                                progressDialog.setMessage("Updating Your Operation");
                                progressDialog.show();
                                SessionManager sessionManager = new SessionManager(view.getContext());
                                HashMap<String, String> data = sessionManager.getUserDetails();
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


                                Date date = new Date();
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                               String id = databaseReference.push().getKey();

                                OperationClient operationClient = new OperationClient("cash in", format.format(date),Solde,Notee);
                                databaseReference.child("OperationsClients").child(data.get(SessionManager.TELEPHONE)).child(phoneSup).child(id).setValue(operationClient).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressDialog.dismiss();
                                        if (task.isSuccessful()) {
                                            Toast.makeText(EditClientOperationActivity.this, "Your Informations has been updated successfuly!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(EditClientOperationActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure You want to delete this Operation").setCancelable(false)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                progressDialog.setMessage("Deleting");
                                progressDialog.show();
                                SessionManager sessionManager = new SessionManager(view.getContext());
                                HashMap<String, String> data = sessionManager.getUserDetails();
                                firebaseDatabase = FirebaseDatabase.getInstance();
                                databaseReference = firebaseDatabase.getReference();
                                String Solde = Sold.getText().toString();
                                String Notee = Note.getText().toString();
                                String id = databaseReference.push().getKey();
                                databaseReference.child("OperationsClients").child(data.get(SessionManager.TELEPHONE)).child(phoneSup).child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressDialog.dismiss();
                                        if (task.isSuccessful()) {
                                            Toast.makeText(EditClientOperationActivity.this, "Your Operation has been deleted successfuly!", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(EditClientOperationActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
  }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();

            }
        });

    }
}