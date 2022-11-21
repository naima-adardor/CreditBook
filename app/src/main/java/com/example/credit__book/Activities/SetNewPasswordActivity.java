package com.example.credit__book.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.credit__book.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNewPasswordActivity extends AppCompatActivity {
    private Button updateBtn;
    private TextInputLayout password, confirmPassword;
    private ImageView logo;
    private TextView title, subTitle;
    private ProgressDialog progressDialog;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        updateBtn = findViewById(R.id.update_btn);
        password = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subTitle);
        progressDialog = new ProgressDialog(SetNewPasswordActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        phone = getIntent().getStringExtra("phone");

        updateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String passwordVal = password.getEditText().getText() + "";
                String confirmPasswordVal = confirmPassword.getEditText().getText() + "";
                System.out.println("password1 " + passwordVal);
                System.out.println("password1 " + confirmPasswordVal);
                if(!validatePassword(passwordVal) | !validateConfirmPassword(passwordVal, confirmPasswordVal)){
                    return;
                }
                progressDialog.setMessage("Updating Your Password");
                progressDialog.show();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                databaseReference.child(phone).child("password").setValue(passwordVal).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(SetNewPasswordActivity.this, "Your password has been updated successfuly!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SetNewPasswordActivity.this, LoginActivity.class));
                            finish();
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(SetNewPasswordActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    private boolean validatePassword(String password) {
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (password.isEmpty()) {
            this.password.setError("This Field is Required!");
            return false;
        } else if (!password.matches(passwordVal)) {
            this.password.setError("Password is too weak!");
            return false;
        } else {
            this.password.setError(null);
            this.password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateConfirmPassword(String password, String confirmPassword) {
        if (confirmPassword.isEmpty()) {
            this.confirmPassword.setError("This Field is Required!");
            return false;
        } else if (password.equals(confirmPassword) == false) {
            this.confirmPassword.setError("Those Password are not matched!");
            return false;
        }
        else {
            this.confirmPassword.setError(null);
            this.confirmPassword.setErrorEnabled(false);
            return true;
        }
    }
}