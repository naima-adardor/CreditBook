package com.example.credit__book.Model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "IsLoggedIn";



    public static final String FULL_NAME = "full_name";
    public static final String TELEPHONE = "phone_number";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CASHIN = "cash_in";
    public static final String CASHOUT = "cash_out";

    public SessionManager (Context context)  {

        this.context = context;
        sharedPreferences = this.context.getSharedPreferences("userLogin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createUserLoginSession(String fullName, String telephone, String email, String password) {

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(FULL_NAME, fullName);
        editor.putString(TELEPHONE, telephone);
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, password);
        editor.putString(CASHIN, "0" );
        editor.putString(CASHOUT, "0" );
        editor.commit();
    }

    public void addCashIn() {
        editor.putString(CASHIN, String.valueOf(Integer.parseInt(sharedPreferences.getString(CASHIN, String.valueOf(0))) + 1));
        editor.commit();
    }
    public void addCashOut() {
        editor.putString(CASHOUT, String.valueOf(Integer.parseInt(sharedPreferences.getString(CASHOUT, String.valueOf(0))) + 1));
        editor.commit();
    }
    public void subCashIn() {
        editor.putString(CASHIN, String.valueOf(Integer.parseInt(sharedPreferences.getString(CASHIN, String.valueOf(0))) - 1));
        editor.commit();
    }
    public void subCashOut() {
        editor.putString(CASHOUT, String.valueOf(Integer.parseInt(sharedPreferences.getString(CASHOUT, String.valueOf(0))) - 1));
        editor.commit();
    }

    public String getCashin() {
        return sharedPreferences.getString(CASHIN, String.valueOf(0));
    }
    public String getCashOut() {
        return sharedPreferences.getString(CASHOUT, String.valueOf(0));
    }

    public HashMap<String, String> getUserDetails() {

        HashMap<String, String> data = new HashMap<>();
        data.put(FULL_NAME, sharedPreferences.getString(FULL_NAME, null));
        data.put(TELEPHONE, sharedPreferences.getString(TELEPHONE, null));
        data.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        data.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));


        return  data;
    }

    public boolean checkLogin() {

        if(sharedPreferences.getBoolean(IS_LOGIN, false)) {
            return true;
        }
        return false;
    }

    public void logout() {

        editor.clear();
        editor.commit();
    }

}
