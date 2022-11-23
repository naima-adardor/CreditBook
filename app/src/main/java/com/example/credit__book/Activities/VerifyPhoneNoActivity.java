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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.credit__book.Model.User;
import com.example.credit__book.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNoActivity extends AppCompatActivity {

    private TextInputLayout verification_code_entered_by_user;
    private Button verify_btn, resendCode;
    private ProgressBar progressBar;
    private ImageView logo;
    private TextView title, subTitle;
    private String verificationBysystem;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private String phone;
    private String passwordVal;
    private String emailVal;
    private String fullNameVal;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_no);

        verify_btn = findViewById(R.id.verify_btn);
        resendCode = findViewById(R.id.resendCode);
        verification_code_entered_by_user = findViewById(R.id.verification_code_entered_by_user);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subTitle);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(VerifyPhoneNoActivity.this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        phone = getIntent().getStringExtra("phone");
        passwordVal = getIntent().getStringExtra("password");
        emailVal = getIntent().getStringExtra("email");
        fullNameVal = getIntent().getStringExtra("fullName");
        mode = getIntent().getStringExtra("checkType");

        sendVerificationCode(phone);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entredCode = verification_code_entered_by_user.getEditText().getText() + "";
                if (entredCode.isEmpty()) {
                    verification_code_entered_by_user.setError("Please enter a verification code!");
                    verification_code_entered_by_user.requestFocus();
                    return;
                }else if (entredCode.length() < 6) {
                    verification_code_entered_by_user.setError("Please enter a valid verification code!");
                    verification_code_entered_by_user.requestFocus();
                    return;
                }
                progressDialog.setMessage("Verifing Code");
                progressDialog.show();
                verification_code_entered_by_user.setError(null);
                verification_code_entered_by_user.setErrorEnabled(false);
                //progressBar.setVisibility(View.VISIBLE);
                verifyCode(entredCode);
            }
        });

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendCode(phone, forceResendingToken);
            }
        });
    }

    private void resendCode(String phone, PhoneAuthProvider.ForceResendingToken token) {
        progressDialog.setMessage("Resending Code");
        progressDialog.show();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+212"+phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void sendVerificationCode(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+212"+phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                    super.onCodeSent(s, token);
                    verificationBysystem = s;
//                    progressDialog.dismiss();
                    forceResendingToken = token;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if(code != null) {
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    progressDialog.dismiss();
                    Toast.makeText(VerifyPhoneNoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential authCredential = PhoneAuthProvider.getCredential(verificationBysystem, code);
        SignInUser(authCredential);
    }

    private void SignInUser(PhoneAuthCredential authCredential) {
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(VerifyPhoneNoActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    if(mode.equals("forgotPassword") == true) {
                        progressDialog.dismiss();
                        Intent intent = new Intent(VerifyPhoneNoActivity.this, SetNewPasswordActivity.class);
                        intent.putExtra("phone", phone);
                        Pair[] pairs = new Pair[5];
                        pairs[0] = new Pair<View,String>(logo, "logo_image");
                        pairs[1] = new Pair<View,String>(verify_btn, "button_sign");
                        pairs[2] = new Pair<View,String>(title, "logo_text");
                        pairs[3] = new Pair<View,String>(subTitle, "sub_title");
                        pairs[4] = new Pair<View,String>(verification_code_entered_by_user, "phone");
                        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(VerifyPhoneNoActivity.this, pairs);
                        startActivity(intent, activityOptions.toBundle());
                        finish();
                    }else {
                        progressDialog.dismiss();
                        User user = new User();
                        user.addUser(phone, fullNameVal, phone, emailVal, passwordVal, VerifyPhoneNoActivity.this);
                        Intent intent = new Intent(VerifyPhoneNoActivity.this, MainDashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                }else {
                    progressDialog.dismiss();
                    //progressBar.setVisibility(View.GONE);
                    Toast.makeText(VerifyPhoneNoActivity.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}