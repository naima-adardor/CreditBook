package com.example.credit__book.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.credit__book.Model.CheckInternetConnection;
import com.example.credit__book.Model.SessionManager;
import com.example.credit__book.Model.User;
import com.example.credit__book.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;


public class ProfileFragment extends Fragment {

    private TextView fullNameTxt;
    private TextInputLayout fullName,telephone, email, oldPassword, newPassword;
    private TextInputEditText fullNameVal, telephoneVal, emailVal, passwordVal, confirmPasswordVal;
    private Button update_btn;
    private String oldPasswordSession;
    private ProgressDialog progressDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fullNameTxt = view.findViewById(R.id.fullNameTxt);
        fullName = view.findViewById(R.id.fullName);
        telephone = view.findViewById(R.id.telephone);
        email = view.findViewById(R.id.email);
        oldPassword = view.findViewById(R.id.oldPassword);
        newPassword = view.findViewById(R.id.newPassword);
        fullNameVal = view.findViewById(R.id.fullNameVal);
        telephoneVal = view.findViewById(R.id.telephoneVal);
        emailVal = view.findViewById(R.id.emailVal);
        passwordVal = view.findViewById(R.id.passwordVal);
        confirmPasswordVal = view.findViewById(R.id.confirmPasswordVal);
        update_btn = view.findViewById(R.id.update_btn);
        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        SessionManager sessionManager = new SessionManager(view.getContext());
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

                if (!CheckInternetConnection.isConnected(view.getContext())) {
                    CheckInternetConnection.showDialog(view.getContext());
                    return;
                }

                if(!validateFullName(fullNameVal) | !validatePassword(newPasswordVal) | !validateTelephone(telephoneVal) | !validateEmail(emailVal) | !validateOldPassword(oldPasswordVal)){
                    return;
                }
                progressDialog.setMessage("Updating Your Informations");
                progressDialog.show();
                User user = new User();
                user.updateUser(telephoneVal, fullNameVal, telephoneVal, emailVal, newPasswordVal, view.getContext());
                SessionManager sessionManager = new SessionManager(view.getContext());
                sessionManager.logout();
                sessionManager.createUserLoginSession(fullNameVal, telephoneVal, emailVal, newPasswordVal);
                progressDialog.dismiss();
                startActivity(new Intent(view.getContext(), ProfileActivity.class));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
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