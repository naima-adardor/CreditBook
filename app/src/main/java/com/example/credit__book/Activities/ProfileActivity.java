package com.example.credit__book.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.credit__book.Model.CheckInternetConnection;
import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.Model.User;
import com.example.credit__book.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity{
    private TextView fullNameTxt;
    private TextInputLayout fullName,telephone, email, oldPassword, newPassword;
    private TextInputEditText fullNameVal, telephoneVal, emailVal, passwordVal, confirmPasswordVal;
    private Button update_btn;
    private String oldPasswordSession;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        fullNameTxt = findViewById(R.id.fullNameTxt);
        fullName = findViewById(R.id.fullName);
        telephone = findViewById(R.id.telephone);
        email = findViewById(R.id.email);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        fullNameVal = findViewById(R.id.fullNameVal);
        telephoneVal = findViewById(R.id.telephoneVal);
        emailVal = findViewById(R.id.emailVal);
        passwordVal = findViewById(R.id.passwordVal);
        confirmPasswordVal = findViewById(R.id.confirmPasswordVal);
        update_btn = findViewById(R.id.update_btn);
        progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        SessionManager sessionManager = new SessionManager(ProfileActivity.this);
        HashMap<String, String> data = sessionManager.getUserDetails();

        fullNameTxt.setText(data.get(SessionManager.FULL_NAME));
        fullNameVal.setText(data.get(SessionManager.FULL_NAME));
        telephoneVal.setText(data.get(SessionManager.TELEPHONE));
        emailVal.setText(data.get(SessionManager.EMAIL));
        oldPasswordSession = data.get(SessionManager.PASSWORD);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameVal = fullName.getEditText().getText() + "";
                String telephoneVal = telephone.getEditText().getText() + "";
                String emailVal = email.getEditText().getText() + "";
                String oldPasswordVal = oldPassword.getEditText().getText() + "";
                String newPasswordVal = newPassword.getEditText().getText() + "";

                if (!CheckInternetConnection.isConnected(ProfileActivity.this)) {
                    CheckInternetConnection.showDialog(ProfileActivity.this);
                    return;
                }

                if(!validateFullName(fullNameVal) | !validatePassword(newPasswordVal) | !validateTelephone(telephoneVal) | !validateEmail(emailVal) | !validateOldPassword(oldPasswordVal)){
                    return;
                }
                progressDialog.setMessage("Updating Your Informations");
                progressDialog.show();
                User user = new User();
                user.updateUser(telephoneVal, fullNameVal, telephoneVal, emailVal, newPasswordVal, ProfileActivity.this);
                SessionManager sessionManager = new SessionManager(ProfileActivity.this);
                sessionManager.logout();
                sessionManager.createUserLoginSession(fullNameVal, telephoneVal, emailVal, newPasswordVal);
                progressDialog.dismiss();
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
            }
        });
    }

    private boolean validateFullName(String fullName) {
        if (fullName.isEmpty()) {
            this.fullName.setError("This Field is Required!");
            return false;
        }else {
            this.fullName.setError(null);
            this.fullName.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.isEmpty()) {
            this.email.setError("This Field is Required!");
            return false;
        } else if (!email.matches(emailPattern)) {
            this.email.setError("Invalid email address!");
            return false;
        }else {
            this.email.setError(null);
            this.email.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateTelephone(String telephone) {
        if (telephone.isEmpty()) {
            this.telephone.setError("This Field is Required!");
            return false;
        }else {
            this.telephone.setError(null);
            this.telephone.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword(String newPassword) {
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (newPassword.isEmpty()) {
            this.newPassword.setError("This Field is Required!");
            return false;
        } else if (!newPassword.matches(passwordVal)) {
            this.oldPassword.setError("Password is too weak!");
            return false;
        } else {
            this.newPassword.setError(null);
            this.newPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateOldPassword(String oldPassword) {
        System.out.println("entred old password " + oldPassword);
        System.out.println("old password " + this.oldPasswordSession);
        if (oldPassword.isEmpty()) {
            this.oldPassword.setError("This Field is Required!");
            return false;
        } else if (this.oldPasswordSession.equals(oldPassword) == false) {
            this.oldPassword.setError("The old password is incorrect!");
            return false;
        }
        else {
            this.oldPassword.setError(null);
            this.oldPassword.setErrorEnabled(false);
            return true;
        }
    }
}