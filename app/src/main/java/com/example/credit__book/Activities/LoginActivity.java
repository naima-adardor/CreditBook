package com.example.credit__book.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit__book.Model.CheckInternetConnection;
import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private Button signUp, signIn, forgotPass;
    ImageView logo;
    TextView welcomText, signinText;
    TextInputLayout phone, passwordInput;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUp = findViewById(R.id.signup_up);
        signIn = findViewById(R.id.signIn);
        logo = findViewById(R.id.logo);
        welcomText = findViewById(R.id.welcomText);
        signinText = findViewById(R.id.signinText);
        phone = findViewById(R.id.telephone_login);
        passwordInput = findViewById(R.id.newPassword);
        forgotPass = findViewById(R.id.forgotPass);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        SessionManager sessionManager = new SessionManager(LoginActivity.this);
        if (sessionManager.checkLogin()) {
            startActivity(new Intent(LoginActivity.this, MainDashboardActivity.class));
            finish();
        }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telephoneVal = phone.getEditText().getText() + "";
                String passwordVal = passwordInput.getEditText().getText() + "";
                if (!CheckInternetConnection.isConnected(LoginActivity.this)) {
                    CheckInternetConnection.showDialog(LoginActivity.this);
                    return;
                }
                if(!validatePassword(passwordVal) | !validateTelephone(telephoneVal)){
                    return;
                }else {
                    progressDialog.setMessage("Verifing your informations");
                    progressDialog.show();
                    checkUser(telephoneVal.trim(), passwordVal.trim());
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(logo, "logo_image");
                pairs[1] = new Pair<View,String>(signIn, "button_sign");
                pairs[2] = new Pair<View,String>(signUp, "new_user");
                pairs[3] = new Pair<View,String>(welcomText, "logo_text");
                pairs[4] = new Pair<View,String>(signinText, "sub_title");
                pairs[5] = new Pair<View,String>(phone, "phone");
                pairs[6] = new Pair<View,String>(passwordInput, "password");

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, activityOptions.toBundle());
                finish();
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View,String>(logo, "logo_image");
                pairs[1] = new Pair<View,String>(signIn, "button_sign");
                pairs[2] = new Pair<View,String>(welcomText, "logo_text");
                pairs[3] = new Pair<View,String>(signinText, "sub_title");
                pairs[4] = new Pair<View,String>(phone, "phone");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, activityOptions.toBundle());
            }
        });
    }

    private boolean validateTelephone(String telephone) {
        if (telephone.isEmpty()) {
            this.phone.setError("This Field is Required!");
            return false;
        }else {
            this.phone.setError(null);
            this.phone.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            this.passwordInput.setError("This Field is Required!");
            return false;
        } else {
            this.passwordInput.setError(null);
            this.passwordInput.setErrorEnabled(false);
            return true;
        }
    }

    private void checkUser(String entredTele, String entredPass) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query checkTele = databaseReference.orderByChild("phone_number").equalTo(entredTele);
        checkTele.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    progressDialog.dismiss();
                    phone.setErrorEnabled(false);
                    phone.setError(null);
                    String password = snapshot.child(entredTele).child("password").getValue(String.class);
                    String email = snapshot.child(entredTele).child("email").getValue(String.class);
                    String fullName = snapshot.child(entredTele).child("full_name").getValue(String.class);
                    String phoneNumber = snapshot.child(entredTele).child("phone_number").getValue(String.class);

                    System.out.println("password entrer " + entredPass);
                    System.out.println("password retrive " + password);
                    if (password.equals(entredPass)) {
                        SessionManager sessionManager = new SessionManager(LoginActivity.this);
                        sessionManager.createUserLoginSession(fullName, phoneNumber, email, password);
                        passwordInput.setErrorEnabled(false);
                        passwordInput.setError(null);
                        startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                        finish();
                    }else {
                        passwordInput.setError("Wrong Password!");
                        passwordInput.requestFocus();
                    }
                }else {
                    progressDialog.dismiss();
                    phone.setError("No such User exist with this Number Phone!");
                    phone.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}