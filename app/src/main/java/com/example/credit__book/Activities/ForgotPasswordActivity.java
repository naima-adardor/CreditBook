package com.example.credit__book.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit__book.Model.CheckInternetConnection;
import com.example.credit__book.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForgotPasswordActivity extends AppCompatActivity {
    private Button nextbtn;
    private TextInputLayout phoneNumber;
    private ProgressDialog progressDialog;
    private ImageView logo;
    private TextView title, subTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        nextbtn = findViewById(R.id.next_btn);
        phoneNumber = findViewById(R.id.phone_number);
        progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subTitle);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneNumber.getEditText().getText() + "";
                if (!CheckInternetConnection.isConnected(ForgotPasswordActivity.this)) {
                    CheckInternetConnection.showDialog(ForgotPasswordActivity.this);
                    return;
                }
                if(!validateTelephone(phone)) {
                    return;
                }
                progressDialog.setMessage("Verifing your number");
                progressDialog.show();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                Query checkTele = databaseReference.orderByChild("phone_number").equalTo(phone);
                checkTele.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            progressDialog.dismiss();
                            phoneNumber.setErrorEnabled(false);
                            phoneNumber.setError(null);
                            Intent intent = new Intent(ForgotPasswordActivity.this, VerifyPhoneNoActivity.class);
                            intent.putExtra("phone", phone);
                            intent.putExtra("checkType", "forgotPassword");
                            Pair[] pairs = new Pair[5];
                            pairs[0] = new Pair<View,String>(logo, "logo_image");
                            pairs[1] = new Pair<View,String>(nextbtn, "button_sign");
                            pairs[2] = new Pair<View,String>(title, "logo_text");
                            pairs[3] = new Pair<View,String>(subTitle, "sub_title");
                            pairs[4] = new Pair<View,String>(phoneNumber, "phone");

                            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(ForgotPasswordActivity.this, pairs);
                            startActivity(intent, activityOptions.toBundle());
                            finish();
                        }else {
                            progressDialog.dismiss();
                            phoneNumber.setError("No such User exist with this Number Phone!");
                            phoneNumber.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private boolean validateTelephone(String telephone) {
        if (telephone.isEmpty()) {
            this.phoneNumber.setError("This Field is Required!");
            return false;
        }else {
            this.phoneNumber.setError(null);
            this.phoneNumber.setErrorEnabled(false);
            return true;
        }
    }
}