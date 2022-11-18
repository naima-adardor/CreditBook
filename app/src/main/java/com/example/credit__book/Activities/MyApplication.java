package com.example.credit__book.Activities;

import android.app.Application;

import com.example.credit__book.Model.Operation;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    List<Operation> listOperations = new ArrayList<>();

    public List<Operation> getListOperations() {
        return listOperations;
    }
}
