package com.example.credit__book.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit__book.Model.CheckInternetConnection;
import com.example.credit__book.Model.User;
import com.example.credit__book.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout fullName, telephone_signup, email_signup, password_signUp;
    private ImageView logo;
    private TextView welcomText, signinText;
    private Button signup_up, signIn_up;
    private boolean emailNotExist;
    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName = findViewById(R.id.fullName);
        telephone_signup = findViewById(R.id.telephone_signup);
        email_signup = findViewById(R.id.email_signup);
        password_signUp = findViewById(R.id.password_signUp);
        signup_up = findViewById(R.id.signup_up);
        signIn_up = findViewById(R.id.signIn_up);
        welcomText = findViewById(R.id.welcomText);
        signinText = findViewById(R.id.signinText);
        logo = findViewById(R.id.logo);
        mAuth = FirebaseAuth.getInstance();

        signup_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameVal = fullName.getEditText().getText() + "";
                String telephoneVal = telephone_signup.getEditText().getText() + "";
                String emailVal = email_signup.getEditText().getText() + "";
                String passwordVal = password_signUp.getEditText().getText() + "";
                if (!CheckInternetConnection.isConnected(SignUpActivity.this)) {
                    CheckInternetConnection.showDialog(SignUpActivity.this);
                    return;
                }
                if(!validateFullName(fullNameVal) | !validatePassword(passwordVal) | !validateTelephone(telephoneVal) | !validateEmail(emailVal)){
                    return;
                }
                Intent intent = new Intent(SignUpActivity.this, VerifyPhoneNoActivity.class);
                intent.putExtra("checkType", "newUser");
                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<View,String>(logo, "logo_image");
                pairs[1] = new Pair<View,String>(signup_up, "button_sign");
                pairs[2] = new Pair<View,String>(signIn_up, "new_user");
                pairs[3] = new Pair<View,String>(welcomText, "logo_text");
                pairs[4] = new Pair<View,String>(signinText, "sub_title");
                pairs[5] = new Pair<View,String>(telephone_signup, "phone");

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
                intent.putExtra("phone", telephoneVal);
                intent.putExtra("fullName", fullNameVal);
                intent.putExtra("email", emailVal);
                intent.putExtra("password", passwordVal);
                startActivity(intent, activityOptions.toBundle());

            }
        });

        signIn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<View,String>(logo, "logo_image");
                pairs[1] = new Pair<View,String>(signup_up, "button_sign");
                pairs[2] = new Pair<View,String>(signIn_up, "new_user");
                pairs[3] = new Pair<View,String>(welcomText, "logo_text");
                pairs[4] = new Pair<View,String>(signinText, "sub_title");
                pairs[5] = new Pair<View,String>(telephone_signup, "phone");

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this, pairs);
                startActivity(intent, activityOptions.toBundle());
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
            this.email_signup.setError("This Field is Required!");
            return false;
        } else if (!email.matches(emailPattern)) {
            this.email_signup.setError("Invalid email address!");
            return false;
        }else {
            this.email_signup.setError(null);
            this.email_signup.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateTelephone(String telephone) {
        if (telephone.isEmpty()) {
            this.telephone_signup.setError("This Field is Required!");
            return false;
        }else {
            this.telephone_signup.setError(null);
            this.telephone_signup.setErrorEnabled(false);
            return true;
        }
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
            password_signUp.setError("This Field is Required!");
            return false;
        } else if (!password.matches(passwordVal)) {
            password_signUp.setError("Password is too weak!");
            return false;
        } else {
            password_signUp.setError(null);
            password_signUp.setErrorEnabled(false);
            return true;
        }
    }

//    public boolean checkEmailExistsOrNot(){
//        FirebaseAuth firebaseauth = FirebaseAuth.getInstance();
//        firebaseauth.fetchSignInMethodsForEmail(email_signup.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
//            @Override
//            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
//                Log.d(TAG,""+task.getResult().getSignInMethods().size());
//                if (task.getResult().getSignInMethods().size() == 0){
//                    password_signUp.setError("This Field is Required!");
//                    emailNotExist = true;
//                }else {
//                    password_signUp.setError(null);
//                    password_signUp.setErrorEnabled(false);
//                    emailNotExist = false;
//                }
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                e.printStackTrace();
//            }
//        });
//        return emailNotExist;
//    }
}